package GUI.controller;
import BLL.BLLQLGG;
import BLL.BLLQLS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import DTO.CTHD;
import BLL.CTHDBLL;
import BLL.HDBLL;
import DTO.Book;
import DTO.GG;
import DTO.HD;
import GUI.dialog.QLHD.QLHDKH;
import GUI.dialog.QLHD.addQLHD;
import GUI.dialog.QLHD.editQLHD;
import GUI.view.QLHD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import javax.swing.JFileChooser;

public class QLHDController {
    private QLHD view;
    private addQLHD addDialog;
    private editQLHD editDialog;
    private QLHDKH qlhdkh;
    public MouseAdapter cthdAdapter;
    public ActionListener showCTHD;
    public ActionListener confirmDelete;
    public ActionListener deleteHD;
    public ActionListener deleteCTHD;
    public ActionListener addCTHD;
    public ActionListener findHD;
    public ActionListener showHD;
    public ActionListener editHD;
    public ActionListener confirmNV;
    public ActionListener ctNV;
    public ActionListener confirmSach;
    public ActionListener confirmSachEdit;
    public ActionListener ctSach;
    public ActionListener ctSachEdit;
    public FocusAdapter ctSolg; //ko biết là gì nhưng sẽ ôn sau
    public FocusAdapter ctSolgEdit; //ko biết là gì nhưng sẽ ôn sau
    public ActionListener confirmAdd;
    public ActionListener confirmEdit;
    public ActionListener editCTHD;
    public ActionListener xuatHD;
    JTable tableCTHD ;
    DefaultTableModel modelCTHD ;
    
    public QLHDController(QLHD view) {
        this.view = view;
        tableCTHD = this.view.getTableCTHD();
        modelCTHD = (DefaultTableModel) tableCTHD.getModel();

        cthdAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel modelHD= (DefaultTableModel) table.getModel();
                
                int rowSl = table.getSelectedRow();
                if (rowSl != -1) {
                    String maHD = table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã HD")).toString();
                    String maNV= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã NV")).toString();
                    String maKH= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã KH")).toString();
                    String ngayLap= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Ngày lập")).toString();
                    String tongGG= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Tổng giảm giá")).toString();
                    String tongSL= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Tổng SL")).toString();
                    String phuongThuc= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Phương thức")).toString();
                    String thanhTien= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Thành tiền")).toString();
                    
                    view.maHD.setText(maHD);
                    view.maKH.setText(maKH);
                    view.maNV.setText(maNV);
                    view.ngayLap.setText(ngayLap);
                    view.tongTien.setText(thanhTien);
                    view.tongGG.setText(tongGG);
                    view.tongSL.setText(tongSL);
                    JComboBox cbb= view.phuongThuc;
                    DefaultComboBoxModel modelCbb= (DefaultComboBoxModel) cbb.getModel();
                    modelCbb.setSelectedItem(phuongThuc);
                    
                    CTHDBLL cthd = new CTHDBLL();
                    ArrayList<CTHD> rs = cthd.selectById(maHD);
                    updateCTHD(rs);
                }
            }
        };
        
        showCTHD= new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                CTHDBLL cthd = new CTHDBLL();
                ArrayList<CTHD> rs = cthd.selectAll();
                updateCTHD(rs);
                JTable tableHD= view.getTableHD();
                tableHD.clearSelection();
            }
        };
        
        confirmDelete= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFrame parent= view.frame;
//                new deleteQLHD(parent, view);
                    int row= tableCTHD.getSelectedRow();
                    int columnMaHD= tableCTHD.getColumnModel().getColumnIndex("Mã HĐ");
                    int columnMaSach= tableCTHD.getColumnModel().getColumnIndex("Mã sách");

                    if (row != -1){
                        int choice= JOptionPane.showConfirmDialog(view.frame, "Bạn chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (choice==0){
                            String maHD=  modelCTHD.getValueAt(row, columnMaHD).toString();
                            String maSach=  modelCTHD.getValueAt(row, columnMaSach).toString();

                            CTHDBLL cthdbll= new CTHDBLL();
                            CTHD cthd= cthdbll.selectById(maHD, maSach);

                            int rowCTHD= cthdbll.delete(maHD, maSach);

                            if (rowCTHD > 0){
                                updateCTHD(cthdbll, maHD);
                                HDBLL hdbll= new HDBLL();

                                
                                int rowHD= hdbll.updateTongTien(cthd);

                                if (rowHD > 0) updateHD(hdbll.selectAll());
                                JOptionPane.showMessageDialog(view.frame, "Xóa thành công");
                            }
                        }
                    }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn Chi tiết hóa đơn để xóa");
            }
        };
        addCTHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= view.getTableHD();
                int row= table.getSelectedRow();
                if (row != -1){
                    JFrame parent= view.frame;
                    addDialog= new addQLHD(view.frame, view);
                    int column= table.getColumnModel().getColumnIndex("Mã HD");
                    String maHD= table.getValueAt(row, column).toString();
                    addDialog.ctMaHD.setText(maHD);
                    addDialog.setVisible(true);
                    addDialog.maSach.addActionListener(ctSach);
                    addDialog.ctSolg.addFocusListener(ctSolg);
                    addDialog.yes.addActionListener(confirmAdd);
                    BLLQLS bllqls= new BLLQLS();
//                    Book book= 
                }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn hóa đơn muốn thêm");
            }
        };
        
        findHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HDBLL hdbll= new HDBLL();
                
                String maHD = view.timMaHD.getText();
                String maNV = view.timMaNV.getText();
                String maKH = view.timMaKH.getText();
                String phuongThuc = view.timPhuongThuc.getSelectedItem().toString();
                switch (phuongThuc){
                    case "Tất cả":
                        phuongThuc= "Tat ca";
                        break;
                    case "Tiền mặt":
                        phuongThuc= "Tien mat";
                        break;
                    case "Chuyển khoản":
                        phuongThuc= "Chuyen khoan";
                        break;
                    case "Quẹt thẻ":
                        phuongThuc= "Quet the";
                        break;
                }
                String ngayBD = view.tgianBD.getText();
                String ngayKT = view.tgianKT.getText();
                String tienBD = view.tienBD.getText();
                String tienKT = view.tienKT.getText();
                
                int check= hdbll.check(ngayBD, ngayKT, tienBD, tienKT);
                if (check==1) {
                    JOptionPane.showMessageDialog(view.frame, "Nhập ngày tháng đúng định dạng yyyy-MM-dd");
                    return;
                }
                if (check==2) {
                    JOptionPane.showMessageDialog(view.frame, "Nhập giá tiền hợp lệ");
                    return;
                }
                ArrayList<HD> rs= hdbll.findHD(maHD, maNV, maKH, phuongThuc, ngayBD, ngayKT, tienBD, tienKT);
                updateHD(rs);
                
            }
        };
        showHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HDBLL hdbll= new HDBLL();
                ArrayList<HD> rs= hdbll.selectAll();
                updateHD(rs);
            }
        };
        deleteHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable tableHD= view.getTableHD();
                DefaultTableModel model= (DefaultTableModel) tableHD.getModel();
                int row= tableHD.getSelectedRow();
                int column= tableHD.getColumnModel().getColumnIndex("Mã HD");
                if (row != -1){
                    String maHD= (String) tableHD.getValueAt(row, column);
                    int choice= JOptionPane.showConfirmDialog(view.frame, "Xác nhận xóa?", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(choice==0){
                        HDBLL hd= new HDBLL();
                        int r =hd.delete(maHD);
                        if (r >0) {
                            CTHDBLL cthd= new CTHDBLL();
                            cthd.delete(maHD);
                            ArrayList<CTHD> rs= cthd.selectAll();
                            updateCTHD(rs);
                        }
                        JOptionPane.showMessageDialog(view.frame, "Đã xóa thành công");
                        ArrayList<HD> rs= hd.selectAll();
                        updateHD(rs);
                    }
                }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn hóa đơn để xóa");
            }
        };
        editHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD= view.maHD.getText();
                String maKH= view.maHD.getText();
                String maNV= view.maNV.getText();
                String ngayLap= view.ngayLap.getText();
                JComboBox cbb= view.phuongThuc;
                DefaultComboBoxModel modelCbb= (DefaultComboBoxModel) cbb.getModel();
                String phuongThuc= modelCbb.getSelectedItem().toString();
                HDBLL hd= new HDBLL();
                int rs= hd.editHD(maHD, maNV, maKH, phuongThuc, ngayLap);
                JTable table= view.getTableHD();
                if (table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn hóa đơn để sửa");
                    return;
                }
                switch (rs){
                    case 0:
                        JOptionPane.showMessageDialog(view.frame, "Vui lòng không để trống");
                        return;
                    case -1:
                        JOptionPane.showMessageDialog(view.frame, "Nhân viên chưa tồn tại, vui lòng thêm vào trước");
                        return;
                    case -2:
                        JOptionPane.showMessageDialog(view.frame, "Khách hàng chưa tồn tại, vui lòng thêm vào trước");
                        return;
                    case -3:
                        JOptionPane.showMessageDialog(view.frame, "Nhập ngày tháng đúng định dạng yyyy-MM-dd");
                        return;
                    case 1:
                        JOptionPane.showMessageDialog(view.frame, "Đã sửa thành công");
                        return;
                }
            }
        };
        
        ctSach= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (qlhdkh == null) {
                    qlhdkh = new QLHDKH(view.frame, view);
                    qlhdkh.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            qlhdkh = null;
                        }
                    });
                }
                qlhdkh.xacNhan.addActionListener(confirmSach);
                JTable table= qlhdkh.table;
                DefaultTableModel model= (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                table.setModel(model);
                BLLQLS bllqls= new BLLQLS();
                ArrayList<Book> rs= bllqls.getAllBooks();
                for (int i = 0; i < rs.size(); i++) {
                    Book s = rs.get(i);
                    Object[] row1 = new Object[]{
                        s.getMaSach(),
                        s.getTenSach(),
                        s.getMaNCC(),        
                        s.getMaLoai(),
                        s.getMaTacGia(),
                        s.getNamXB(),                
                        s.getSoLuong(),
                        s.getDonGia(),
                        s.getHA(),
                    };
                    model.addRow(row1);
                }
            }
        };
        
        confirmSach= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= qlhdkh.table;
                int row= table.getSelectedRow();
                int maSach= table.getColumnModel().getColumnIndex("MÃ SÁCH");
                int donGia= table.getColumnModel().getColumnIndex("ĐƠN GIÁ");
                if (row !=-1){
                    String maSach1= table.getValueAt(row, maSach).toString();
                    String donGia1= table.getValueAt(row, donGia).toString();
                    addDialog.ctMaSach.setText(maSach1);
                    addDialog.ctDonGia.setText(donGia1);
                    BLLQLGG bllqlgg= new BLLQLGG();
                    ArrayList<GG> gg= bllqlgg.getAllGGByBook(maSach1);
                    float s= 0;
                    for (int i=0; i< gg.size(); i++){
                        s+= gg.get(i).getLuongGiam();
                        System.out.println(s +"gg");
                    }
                    
                    try{
                        int solg= Integer.parseInt(addDialog.ctSolg.getText());
                        float giamGia= (s/100) *(Float.parseFloat(donGia1)) * solg;
                        addDialog.ctGiamGia.setText(String.valueOf(giamGia));
                        float donGia2= Float.parseFloat(addDialog.ctDonGia.getText());
                        float giamGia2= Float.parseFloat(addDialog.ctGiamGia.getText());
                        float tongTien= solg* donGia2;
                        float thanhTien= tongTien- giamGia2*solg;
                        addDialog.ctTongtien.setText(String.valueOf(tongTien));
                        addDialog.ctThanhTien.setText(String.valueOf(thanhTien));
                    }
                    catch(Exception er){
                        System.out.println("");
                    }
                }
                qlhdkh.dispose();
            }
        };
        ctSolg= new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!addDialog.ctMaSach.getText().equals(""))
                try{
                    BLLQLGG bllqlgg= new BLLQLGG();
                    ArrayList<GG> gg= bllqlgg.getAllGGByBook(addDialog.ctMaSach.getText());
                    float s= 0;
                    System.out.println(gg.size());
                    for (int i=0; i< gg.size(); i++){
                        s+= gg.get(i).getLuongGiam();
                    }
                    System.out.println(s+1000);
                    int solg= Integer.parseInt(addDialog.ctSolg.getText());
                    float donGia= Float.parseFloat(addDialog.ctDonGia.getText());
                    float giamGia= (s/100)*donGia*solg;
                    float tongTien= solg* donGia;
                    float thanhTien= tongTien- giamGia;
                    addDialog.ctGiamGia.setText(String.valueOf(giamGia));
                    addDialog.ctTongtien.setText(String.valueOf(tongTien));
                    addDialog.ctThanhTien.setText(String.valueOf(thanhTien));
                }
                catch(Exception er){
                    System.out.println("");
                }
            }
        }; 
        
        
        
        
        ctSachEdit= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (qlhdkh == null) {
                    qlhdkh = new QLHDKH(view.frame, view);
                    qlhdkh.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            qlhdkh = null;
                        }
                    });
                }
                qlhdkh.xacNhan.addActionListener(confirmSachEdit);
                JTable table= qlhdkh.table;
                DefaultTableModel model= (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                table.setModel(model);
                BLLQLS bllqls= new BLLQLS();
                ArrayList<Book> rs= bllqls.getAllBooks();
                for (int i = 0; i < rs.size(); i++) {
                    Book s = rs.get(i);
                    Object[] row1 = new Object[]{
                        s.getMaSach(),
                        s.getTenSach(),
                        s.getMaNCC(),        
                        s.getMaLoai(),
                        s.getMaTacGia(),
                        s.getNamXB(),                
                        s.getSoLuong(),
                        s.getDonGia(),
                        s.getHA(),
                    };
                    model.addRow(row1);
                }
            }
        };
        confirmSachEdit= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= qlhdkh.table;
                int row= table.getSelectedRow();
                int maSach= table.getColumnModel().getColumnIndex("MÃ SÁCH");
                int donGia= table.getColumnModel().getColumnIndex("ĐƠN GIÁ");
                if (row !=-1){
                    String maSach1= table.getValueAt(row, maSach).toString();
                    String donGia1= table.getValueAt(row, donGia).toString();
                    editDialog.ctMaSach.setText(maSach1);
                    editDialog.ctDonGia.setText(donGia1);
                    BLLQLGG bllqlgg= new BLLQLGG();
                    ArrayList<GG> gg= bllqlgg.getAllGGByBook(maSach1);
                    float s= 0;
                    for (int i=0; i< gg.size(); i++){
                        s+= gg.get(i).getLuongGiam();
                        System.out.println(s +"gg");
                    }
                    
                    try{
                        int solg= Integer.parseInt(editDialog.ctSolg.getText());
                        float giamGia= (s/100) *Float.parseFloat(donGia1) * solg;
                        editDialog.ctGiamGia.setText(String.valueOf(giamGia));
                        float donGia2= Float.parseFloat(editDialog.ctDonGia.getText());
                        float giamGia2= Float.parseFloat(editDialog.ctGiamGia.getText());
                        float tongTien= solg* donGia2;
                        float thanhTien= tongTien- giamGia2*solg;
                        editDialog.ctTongtien.setText(String.valueOf(tongTien));
                        editDialog.ctThanhTien.setText(String.valueOf(thanhTien));
                    }
                    catch(Exception er){
                        System.out.println("");
                    }
                }
                qlhdkh.dispose();
            }
        };
        ctSolgEdit= new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                
                    try{
                        BLLQLGG bllqlgg= new BLLQLGG();
                        ArrayList<GG> gg= bllqlgg.getAllGGByBook(editDialog.ctMaSach.getText());
                        float s= 0;
                        for (int i=0; i< gg.size(); i++){
                            s+= gg.get(i).getLuongGiam();
                        }
                        System.out.println(s+1000);
                        int solg= Integer.parseInt(editDialog.ctSolg.getText());
                        float donGia= Float.parseFloat(editDialog.ctDonGia.getText());
                        float giamGia= (s/100) * donGia * solg;
                        float tongTien= solg* donGia;
                        float thanhTien= tongTien- giamGia;
                        editDialog.ctGiamGia.setText(String.valueOf(giamGia));
                        editDialog.ctTongtien.setText(String.valueOf(tongTien));
                        editDialog.ctThanhTien.setText(String.valueOf(thanhTien));
                    }
                    catch(Exception er){
                        System.out.println("");
                    }
            }
        }; 
        
        
        
        
        confirmAdd= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(addDialog, "Bạn muốn thêm hóa đơn?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
                if (choice ==0){
                    String maHD= addDialog.ctMaHD.getText();
                    String maSach= addDialog.ctMaSach.getText();
                    String soLuong= addDialog.ctSolg.getText();
                    String donGia= addDialog.ctDonGia.getText();
                    String tongTien= addDialog.ctTongtien.getText();
                    String giamGia= addDialog.ctGiamGia.getText();
                    String thanhTien= addDialog.ctThanhTien.getText();
                    CTHDBLL cthdbll= new CTHDBLL();
                    int rs= cthdbll.add(maHD, maSach, soLuong, donGia, tongTien, giamGia, thanhTien);
                    if (rs== -2) JOptionPane.showMessageDialog(view.frame, "Chi tiết hóa đơn đã tồn tại");
                    
                    else if (rs== -1) JOptionPane.showMessageDialog(view.frame, "Nhập số lượng hợp lệ");
                    else if (rs== 0) JOptionPane.showMessageDialog(view.frame, "Đã tồn tại");
                    else {
                        CTHD cthd= cthdbll.selectById(maHD, maSach);
                        HDBLL hd= new HDBLL();
                        hd.updateAdd(cthd);
                        ArrayList<HD> tableHD= hd.selectAll();
                        updateHD(tableHD);

                        ArrayList<CTHD> tableCTHD= cthdbll.selectAll();
                        updateCTHD(tableCTHD);
                        addDialog.dispose();
                        JOptionPane.showMessageDialog(addDialog, "Đã thêm thành công");
                    }
                }
            }
        };
        confirmEdit= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(editDialog, "Bạn muốn sủa hóa đơn này?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
                if (choice ==0){
                    JTable tableCTHD= view.getTableCTHD();
                    int row= tableCTHD.getSelectedRow();
                        String maHD= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Mã HĐ")).toString();
                        String maSach= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Mã sách")).toString();
                        String soLuong= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Số lượng")).toString();
                        String donGia= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Đơn giá")).toString();
                        String tongTien= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Tổng tiền")).toString();
                        String giamGia= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Giảm giá")).toString();
                        String thanhTien= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Thành tiền")).toString();

                        String maHD1= editDialog.ctMaHD.getText();
                        String maSach1= editDialog.ctMaSach.getText();
                        String soLuong1= editDialog.ctSolg.getText();
                        String donGia1= editDialog.ctDonGia.getText();
                        String tongTien1= editDialog.ctTongtien.getText();
                        String giamGia1= editDialog.ctGiamGia.getText();
                        String thanhTien1= editDialog.ctThanhTien.getText();
                        CTHDBLL cthd= new CTHDBLL();
                        int rs= cthd.update(maHD1, maSach1, soLuong1, donGia1, tongTien1, giamGia1, thanhTien1, maHD, maSach);
                        if (rs== -2) JOptionPane.showMessageDialog(view.frame, "Chi tiết hóa đơn đã tồn tại");
                    
                        else if (rs== -1) JOptionPane.showMessageDialog(view.frame, "Nhập số lượng hợp lệ");
                        else if (rs== 0) JOptionPane.showMessageDialog(view.frame, "Đã tồn tại");
                        else {
                            CTHD cthdOld= new CTHD(maHD, maSach, Integer.parseInt(soLuong), Float.parseFloat(donGia), Float.parseFloat(tongTien), Float.parseFloat(giamGia), Float.parseFloat(thanhTien));
                            CTHD cthdNew= cthd.selectById(maHD1, maSach1);

                            HDBLL hd= new HDBLL();
                            hd.updateTongTien(cthdOld);
                            hd.updateAdd(cthdNew);

                            ArrayList<HD> tableHD= hd.selectAll();
                            updateHD(tableHD);

                            ArrayList<CTHD> tableCTHD1= cthd.selectAll();
                            updateCTHD(tableCTHD1);
                            editDialog.dispose();
                            JOptionPane.showMessageDialog(editDialog, "Đã sửa thành công");
                        }
                }
            }
        };
        editCTHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable tableCTHD= view.getTableCTHD();
                int row= tableCTHD.getSelectedRow();
                if(row != -1){
                    editDialog= new editQLHD(view.frame, view);
                    int columnMaHD= tableCTHD.getColumnModel().getColumnIndex("Mã HĐ");
                    String maHD= tableCTHD.getValueAt(row, columnMaHD).toString();
                    String maSach= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Mã sách")).toString();
                    String soLuong= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Số lượng")).toString();
                    String donGia= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Đơn giá")).toString();
                    String tongTien= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Tổng tiền")).toString();
                    String giamGia= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Giảm giá")).toString();
                    String thanhTien= tableCTHD.getValueAt(row, tableCTHD.getColumnModel().getColumnIndex("Thành tiền")).toString();
                    editDialog.ctMaHD.setText(maHD);
                    editDialog.ctMaSach.setText(maSach);
                    editDialog.ctSolg.setText(soLuong);
                    editDialog.ctDonGia.setText(donGia);
                    editDialog.ctTongtien.setText(tongTien);
                    editDialog.ctGiamGia.setText(giamGia);
                    editDialog.ctThanhTien.setText(thanhTien);
                    editDialog.setVisible(true);
                    
                    editDialog.maSach.addActionListener(ctSachEdit);
                    editDialog.ctSolg.addFocusListener(ctSolgEdit);
                    editDialog.yes.addActionListener(confirmEdit);
                    
                }
                else JOptionPane.showMessageDialog(view.frame, "Chọn chi tiết hóa đơn muốn sửa");
            }
        };
        xuatHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= view.getTableHD();
                int row= table.getSelectedRow();
                int column= table.getColumnModel().getColumnIndex("Mã HD");
                if (row != -1){
                    String maHD = table.getValueAt(row, column).toString(); // hoặc lấy từ bảng, tùy cách bạn hiển thị

                    // Cho người dùng chọn nơi lưu file
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setSelectedFile(new File("hoadon_" + maHD + ".pdf"));
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();

                        // Gọi hàm xuất PDF
                        exportHoaDonPDF(maHD, file.getAbsolutePath());
                    }
                }
                else JOptionPane.showMessageDialog(view.frame, "Chọn hóa đơn để xuất");
            }
        };
    }
    
    
    public void updateHD(ArrayList<HD> temp){
        ArrayList<HD> listHD=temp;
        JTable tableHD= view.getTableHD();
        System.out.println("heh");
        DefaultTableModel modelHD= (DefaultTableModel) tableHD.getModel();
        modelHD.setRowCount(0);
        for (int i=0; i< listHD.size(); i++){
            HD item= listHD.get(i);
            Object[] row1= new Object[] {item.getMaHD(), 
                item.getMaKH(), 
                item.getMaNV(), 
                item.getNgayLap(), 
                item.getTongTien(), 
                item.getTongGG(), 
                item.getTongSL(), 
                item.getPhuongThuc(), 
                item.getThanhTien() };
            modelHD.addRow(row1);
        }
    }
    
    public void updateCTHD(CTHDBLL cthdbll, String maHD){
        ArrayList<CTHD> rs;
        JTable tableHD= view.getTableHD();
        if(tableHD.getSelectedRow() != -1)
            rs = cthdbll.selectById(maHD);
        else 
            rs = cthdbll.selectAll();

        modelCTHD.setRowCount(0);
        for (int i = 0; i < rs.size(); i++) {
            CTHD item = rs.get(i);
            Object[] row1 = new Object[]{
                item.getMaHD(),
                item.getMaSach(),
                item.getSoLuong(),
                item.getGiaTien(),
                item.getTongTien(),
                item.getGiamGia(),
                item.getThanhTien()
            };
            modelCTHD.addRow(row1);
        }
        
    }
    
    public void updateCTHD(ArrayList<CTHD> rs){
        modelCTHD.setRowCount(0);
        for (int i = 0; i < rs.size(); i++) {
            CTHD item = rs.get(i);
            Object[] row1 = new Object[]{
                item.getMaHD(),
                item.getMaSach(),
                item.getSoLuong(),
                item.getGiaTien(),
                item.getTongTien(),
                item.getGiamGia(),
                item.getThanhTien()
            };
            modelCTHD.addRow(row1);
        }
        
    }
    public void exportHoaDonPDF(String maHD, String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            String fontPath = "C:\\D\\New folder (7) Java\\bruh\\src\\img\\font-times-new-roman.ttf";
             BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            // Tiêu đề
            document.add(new Paragraph("HÓA ĐƠN BÁN SÁCH", font));
            document.add(new Paragraph("Mã hóa đơn: " + maHD, font));
            document.add(new Paragraph("Ngày in: " + new java.util.Date().toString(), font));
            document.add(new Paragraph(" ")); // dòng trắng

            // Bảng chi tiết hóa đơn
            PdfPTable pdfTable = new PdfPTable(7); // 5 cột: Mã sách, Tên sách, SL, Đơn giá, Thành tiền
            pdfTable.addCell("Mã HD");
            pdfTable.addCell("Mã sách");
            pdfTable.addCell("Số lượng");
            pdfTable.addCell("Đơn giá");
            pdfTable.addCell("Tổng tiền");
            pdfTable.addCell("Giảm giá");
            pdfTable.addCell("Thành tiền");

            // Lấy chi tiết hóa đơn từ CTHDBUS hoặc CTHDDAL
            CTHDBLL cthd= new CTHDBLL();
            ArrayList<CTHD> ds = cthd.selectById(maHD);

            
            for (int i=0; i< ds.size(); i++) {
                CTHD ct= ds.get(i);
                pdfTable.addCell(ct.getMaHD());
                pdfTable.addCell(ct.getMaSach());
                pdfTable.addCell(String.valueOf(ct.getSoLuong()));
                pdfTable.addCell(String.valueOf(ct.getGiaTien()));
                pdfTable.addCell(String.valueOf(ct.getTongTien()));
                pdfTable.addCell(String.valueOf(ct.getGiamGia()));
                pdfTable.addCell(String.valueOf(ct.getThanhTien()));
            }

            document.add(pdfTable);

            document.add(new Paragraph(" "));
//            document.add(new Paragraph("Tổng cộng: " + tongTien + " VND"));

            document.close();
            JOptionPane.showMessageDialog(null, "Xuất PDF thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xuất PDF thất bại!");
        }
    }

}
