package GUI.controller;
import BLL.BLLNCC;
import BLL.BLLQLS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import DTO.CTPN;
import BLL.CTPNBLL;
import BLL.EmpBLL;
import BLL.PNBLL;
import DTO.Book;
import DTO.PN;
import GUI.dialog.QLPN.QLPNKH;
import GUI.dialog.QLPN.addPN;
import GUI.dialog.QLPN.addQLPN;
import GUI.dialog.QLPN.editQLPN;
import GUI.view.QLPN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import javax.swing.JFileChooser;

public class QLPNController {
    private QLPN view;
    private addQLPN addDialog;
    private addPN addPN;
    private editQLPN editDialog;
    private QLPNKH qlhdkh;
    public MouseAdapter cthdAdapter;
    public ActionListener showCTPN;
    public ActionListener confirmDelete;
    public ActionListener deletePN;
    public ActionListener deleteCTPN;
    public ActionListener addCTPN;
    public ActionListener findPN;
    public ActionListener showPN;
    public ActionListener editPN;
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
    public ActionListener editCTPN;
    public ActionListener themPN;
    public ActionListener ctSachPN;
    public ActionListener confirmCtSachPN;
    public ActionListener confirmAddPN;
    JTable tableCTPN ;
    DefaultTableModel modelCTPN ;
    
    public QLPNController(QLPN view) {
        this.view = view;
        tableCTPN = this.view.getTableCTPN();
        modelCTPN = (DefaultTableModel) tableCTPN.getModel();

        cthdAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel modelPN= (DefaultTableModel) table.getModel();
                
                int rowSl = table.getSelectedRow();
                if (rowSl != -1) {
                    String maPN = table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã PN")).toString();
                    String maNV= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã NV")).toString();
                    String maNCC= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã NCC")).toString();
                    String ngayLap= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Ngày lập")).toString();
                    String tongSL= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Tổng SL")).toString();
                    String thanhTien= table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Tổng tiền")).toString();
                    
                    view.maPN.setText(maPN);
                    view.maNCC.setText(maNCC);
                    view.maNV.setText(maNV);
                    view.ngayLap.setText(ngayLap);
                    view.tongTien.setText(thanhTien);
                    view.tongSL.setText(tongSL);
                    
                    CTPNBLL cthd = new CTPNBLL();
                    ArrayList<CTPN> rs = cthd.selectById(maPN);
                    updateCTPN(rs);
                }
            }
        };
        
        showCTPN= new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                CTPNBLL ctpn = new CTPNBLL();
                ArrayList<CTPN> rs = ctpn.selectAll();
                updateCTPN(rs);
                JTable tablePN= view.getTablePN();
                tablePN.clearSelection();
            }
        };
        
        confirmDelete= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFrame parent= view.frame;
//                new deleteQLPN(parent, view);
                    int row= tableCTPN.getSelectedRow();
                    int columnMaPN= tableCTPN.getColumnModel().getColumnIndex("Mã PN");
                    int columnMaSach= tableCTPN.getColumnModel().getColumnIndex("Mã sách");

                    if (row != -1){
                        int choice= JOptionPane.showConfirmDialog(view.frame, "Bạn chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (choice==0){
                            String maPN=  modelCTPN.getValueAt(row, columnMaPN).toString();
                            String maSach=  modelCTPN.getValueAt(row, columnMaSach).toString();

                            CTPNBLL ctpnbll= new CTPNBLL();
                            CTPN ctpn= ctpnbll.selectById(maPN, maSach);

                            int rowCTPN= ctpnbll.delete(maPN, maSach);

                            if (rowCTPN > 0){
                                updateCTPN(ctpnbll, maPN);
                                PNBLL pnbll= new PNBLL();

                                
                                int rowPN= pnbll.updateTongTien(ctpn);

                                if (rowPN > 0) updatePN(pnbll.selectAll());
                                JOptionPane.showMessageDialog(view.frame, "Xóa thành công");
                            }
                        }
                    }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn Chi tiết phiếu nhập để xóa");
            }
        };
        addCTPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= view.getTablePN();
                int row= table.getSelectedRow();
                if (row != -1){
                    JFrame parent= view.frame;
                    addDialog= new addQLPN(view.frame, view);
                    int column= table.getColumnModel().getColumnIndex("Mã PN");
                    String maPN= table.getValueAt(row, column).toString();
                    addDialog.ctMaPN.setText(maPN);
                    addDialog.setVisible(true);
                    addDialog.maSach.addActionListener(ctSach);
                    addDialog.ctSolg.addFocusListener(ctSolg);
                    addDialog.yes.addActionListener(confirmAdd);
                    BLLQLS bllqls= new BLLQLS();
//                    Book book= 
                }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn phiếu nhập muốn thêm");
            }
        };
        
        findPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PNBLL hdbll= new PNBLL();
                
                String maPN = view.timMaPN.getText();
                String maNV = view.timMaNV.getText();
                String maNCC = view.timMaNCC.getText();
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
                ArrayList<PN> rs= hdbll.findPN(maPN, maNV, maNCC, ngayBD, ngayKT, tienBD, tienKT);
                updatePN(rs);
                
            }
        };
        showPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PNBLL hdbll= new PNBLL();
                ArrayList<PN> rs= hdbll.selectAll();
                updatePN(rs);
            }
        };
        deletePN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable tablePN= view.getTablePN();
                DefaultTableModel model= (DefaultTableModel) tablePN.getModel();
                int row= tablePN.getSelectedRow();
                int column= tablePN.getColumnModel().getColumnIndex("Mã PN");
                if (row != -1){
                    String maPN= (String) tablePN.getValueAt(row, column);
                    int choice= JOptionPane.showConfirmDialog(view.frame, "Xác nhận xóa?", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(choice==0){
                        PNBLL pn= new PNBLL();
                        int r =pn.delete(maPN);
                        if (r >0) {
                            CTPNBLL ctpn= new CTPNBLL();
                            ctpn.delete(maPN);
                            ArrayList<CTPN> rs= ctpn.selectAll();
                            updateCTPN(rs);
                        }
                        JOptionPane.showMessageDialog(view.frame, "Đã xóa thành công");
                        ArrayList<PN> rs= pn.selectAll();
                        updatePN(rs);
                    }
                }
                else JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn phiếu nhập để xóa");
            }
        };
        editPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPN= view.maPN.getText();
                String maNCC= view.maPN.getText();
                String maNV= view.maNV.getText();
                String ngayLap= view.ngayLap.getText();
                PNBLL hd= new PNBLL();
                int rs= hd.editPN(maPN, maNV, maNCC, ngayLap);
                JTable table= view.getTablePN();
                if (table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(view.frame, "Vui lòng chọn phiếu nhập để sửa");
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
                    qlhdkh = new QLPNKH(view.frame, view);
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
                    
                    try{
                        int solg= Integer.parseInt(addDialog.ctSolg.getText());
                        float donGia2= Float.parseFloat(addDialog.ctDonGia.getText());
                        float tongTien= solg* donGia2;
                        addDialog.ctThanhTien.setText(String.valueOf(tongTien));
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
                    int solg= Integer.parseInt(addDialog.ctSolg.getText());
                    float donGia= Float.parseFloat(addDialog.ctDonGia.getText());
                    float tongTien= solg* donGia;
                    addDialog.ctThanhTien.setText(String.valueOf(tongTien));
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
                    qlhdkh = new QLPNKH(view.frame, view);
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
                    
                    try{
                        int solg= Integer.parseInt(editDialog.ctSolg.getText());
                        float donGia2= Float.parseFloat(editDialog.ctDonGia.getText());
                        float tongTien= solg* donGia2;
                        editDialog.ctThanhTien.setText(String.valueOf(tongTien));
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
                        
                        int solg= Integer.parseInt(editDialog.ctSolg.getText());
                        float donGia= Float.parseFloat(editDialog.ctDonGia.getText());
                        float tongTien= solg* donGia;
                        editDialog.ctThanhTien.setText(String.valueOf(tongTien));
                    }
                    catch(Exception er){
                        System.out.println("");
                    }
            }
        }; 
        
        
        
        
        confirmAdd= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(addDialog, "Bạn muốn thêm phiếu nhập?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
                if (choice ==0){
                    String maPN= addDialog.ctMaPN.getText();
                    String maSach= addDialog.ctMaSach.getText();
                    String soLuong= addDialog.ctSolg.getText();
                    String donGia= addDialog.ctDonGia.getText();
                    String thanhTien= addDialog.ctThanhTien.getText();
                    CTPNBLL ctpnbll= new CTPNBLL();
                    int rs= ctpnbll.add(maPN, maSach, soLuong, donGia, thanhTien);
                    if (rs== -2) JOptionPane.showMessageDialog(view.frame, "Chi tiết phiếu nhập đã tồn tại");
                    
                    else if (rs== -1) JOptionPane.showMessageDialog(view.frame, "Nhập số lượng hợp lệ");
                    else if (rs== 0) JOptionPane.showMessageDialog(view.frame, "Đã tồn tại");
                    else {
                        CTPN ctpn= ctpnbll.selectById(maPN, maSach);
                        PNBLL pn= new PNBLL();
                        pn.updateAdd(ctpn);
                        ArrayList<PN> tablePN= pn.selectAll();
                        updatePN(tablePN);

                        ArrayList<CTPN> tableCTPN= ctpnbll.selectAll();
                        updateCTPN(tableCTPN);
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
                    JTable tableCTPN= view.getTableCTPN();
                    int row= tableCTPN.getSelectedRow();
                        String maPN= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Mã PN")).toString();
                        String maSach= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Mã sách")).toString();
                        String soLuong= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Số lượng")).toString();
                        String donGia= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Đơn giá")).toString();
                        String thanhTien= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Thành tiền")).toString();

                        String maPN1= editDialog.ctMaPN.getText();
                        String maSach1= editDialog.ctMaSach.getText();
                        String soLuong1= editDialog.ctSolg.getText();
                        String donGia1= editDialog.ctDonGia.getText();
                        String thanhTien1= editDialog.ctThanhTien.getText();
                        CTPNBLL ctpn= new CTPNBLL();
                        int rs= ctpn.update(maPN1, maSach1, soLuong1, donGia1, thanhTien1, maPN, maSach);
                        if (rs== -2) JOptionPane.showMessageDialog(view.frame, "Chi tiết phiếu nhập đã tồn tại");
                    
                        else if (rs== -1) JOptionPane.showMessageDialog(view.frame, "Nhập số lượng hợp lệ");
                        else if (rs== 0) JOptionPane.showMessageDialog(view.frame, "Đã tồn tại");
                        else {
                            CTPN ctpnOld= new CTPN(maPN, maSach, Integer.parseInt(soLuong), Float.parseFloat(donGia), Float.parseFloat(thanhTien));
                            CTPN ctpnNew= ctpn.selectById(maPN1, maSach1);

                            PNBLL hd= new PNBLL();
                            hd.updateTongTien(ctpnOld);
                            hd.updateAdd(ctpnNew);

                            ArrayList<PN> tablePN= hd.selectAll();
                            updatePN(tablePN);

                            ArrayList<CTPN> tableCTPN1= ctpn.selectAll();
                            updateCTPN(tableCTPN1);
                            editDialog.dispose();
                            JOptionPane.showMessageDialog(editDialog, "Đã sửa thành công");
                        }
                }
            }
        };
        editCTPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable tableCTPN= view.getTableCTPN();
                int row= tableCTPN.getSelectedRow();
                if(row != -1){
                    editDialog= new editQLPN(view.frame, view);
                    int columnMaPN= tableCTPN.getColumnModel().getColumnIndex("Mã PN");
                    String maPN= tableCTPN.getValueAt(row, columnMaPN).toString();
                    String maSach= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Mã sách")).toString();
                    String soLuong= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Số lượng")).toString();
                    String donGia= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Đơn giá")).toString();
                    String thanhTien= tableCTPN.getValueAt(row, tableCTPN.getColumnModel().getColumnIndex("Thành tiền")).toString();
                    editDialog.ctMaPN.setText(maPN);
                    editDialog.ctMaSach.setText(maSach);
                    editDialog.ctSolg.setText(soLuong);
                    editDialog.ctDonGia.setText(donGia);
                    editDialog.ctThanhTien.setText(thanhTien);
                    editDialog.setVisible(true);
                    
                    editDialog.maSach.addActionListener(ctSachEdit);
                    editDialog.ctSolg.addFocusListener(ctSolgEdit);
                    editDialog.yes.addActionListener(confirmEdit);
                    
                }
                else JOptionPane.showMessageDialog(view.frame, "Chọn chi tiết hóa đơn muốn sửa");
            }
        };
        themPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPN= new addPN(view.frame, view);
                addPN.yes.addActionListener(confirmAddPN);
                addPN.setVisible(true);
            }
        };
        
        confirmAddPN= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PNBLL pnbll= new PNBLL();
                String maPN= addPN.maPN.getText();
                String maNV= addPN.maNV.getText();
                String maNCC= addPN.maNCC.getText();
                String ngayNhap= addPN.ngayNhap.getText();
                int rs= pnbll.addPN(maPN, maNV, maNCC, ngayNhap, 0, 0);
                if (rs==0)
                    JOptionPane.showMessageDialog(view.frame, "Vui lòng không để trống");
                else if (rs== -1)
                    JOptionPane.showMessageDialog(view.frame, "Không thấy nhân viên, vui lòng thêm nhân viên");
                else if (rs== -2)
                    JOptionPane.showMessageDialog(view.frame, "Không thấy NCC, vui lòng thêm NCC");
                else if (rs== -3)
                    JOptionPane.showMessageDialog(view.frame, "Nhập ngày tháng đúng yyyy-MM-dd");
                else{ 
                    JOptionPane.showMessageDialog(view.frame, "Đã thêm thành công");
                    addPN.dispose();
                }
            }
        };
    }
    
    
    public void updatePN(ArrayList<PN> temp){
        ArrayList<PN> listPN=temp;
        JTable tablePN= view.getTablePN();
        System.out.println("heh");
        DefaultTableModel modelPN= (DefaultTableModel) tablePN.getModel();
        modelPN.setRowCount(0);
        for (int i=0; i< listPN.size(); i++){
            PN item= listPN.get(i);
            Object[] row1= new Object[] {
                item.getMaPN(), 
                item.getMaNCC(), 
                item.getMaNV(), 
                item.getNgayNhap(), 
                item.getTongTien(), 
                item.getTongSL()
             };
            modelPN.addRow(row1);
        }
    }
    
    public void updateCTPN(CTPNBLL cthdbll, String maPN){
        ArrayList<CTPN> rs;
        JTable tablePN= view.getTablePN();
        if(tablePN.getSelectedRow() != -1)
            rs = cthdbll.selectById(maPN);
        else 
            rs = cthdbll.selectAll();

        modelCTPN.setRowCount(0);
        for (int i = 0; i < rs.size(); i++) {
            CTPN item = rs.get(i);
            Object[] row1 = new Object[]{
                item.getMaPN(),
                item.getMaSach(),
                item.getSoLg(),
                item.getDonGia(),
                item.getThanhTien()
            };
            modelCTPN.addRow(row1);
        }
        
    }
    
    public void updateCTPN(ArrayList<CTPN> rs){
        modelCTPN.setRowCount(0);
        for (int i = 0; i < rs.size(); i++) {
            CTPN item = rs.get(i);
            Object[] row1 = new Object[]{
                item.getMaPN(),
                item.getMaSach(),
                item.getSoLg(),
                item.getDonGia(),
                item.getThanhTien()
            };
            modelCTPN.addRow(row1);
        }
        
    }
    public void exportHoaDonPDF(String maPN, String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            String fontPath = "C:\\D\\New folder (7) Java\\bruh\\src\\img\\font-times-new-roman.ttf";
             BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            // Tiêu đề
            document.add(new Paragraph("HÓA ĐƠN BÁN SÁCH", font));
            document.add(new Paragraph("Mã hóa đơn: " + maPN, font));
            document.add(new Paragraph("Ngày in: " + new java.util.Date().toString(), font));
            document.add(new Paragraph(" ")); // dòng trắng

            // Bảng chi tiết hóa đơn
            PdfPTable pdfTable = new PdfPTable(5); // 5 cột: Mã sách, Tên sách, SL, Đơn giá, Thành tiền
            pdfTable.addCell("Mã PN");
            pdfTable.addCell("Mã sách");
            pdfTable.addCell("Số lượng");
            pdfTable.addCell("Đơn giá");
            pdfTable.addCell("Thành tiền");

            // Lấy chi tiết hóa đơn từ CTPNBUS hoặc CTPNDAL
            CTPNBLL ctpn= new CTPNBLL();
            ArrayList<CTPN> ds = ctpn.selectById(maPN);

            
            for (int i=0; i< ds.size(); i++) {
                CTPN ct= ds.get(i);
                pdfTable.addCell(ct.getMaPN());
                pdfTable.addCell(ct.getMaSach());
                pdfTable.addCell(String.valueOf(ct.getSoLg()));
                pdfTable.addCell(String.valueOf(ct.getDonGia()));
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
