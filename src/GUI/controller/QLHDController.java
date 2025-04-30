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
import GUI.dialog.QLHD.QLHDNV;
import GUI.dialog.QLHD.addQLHD;
import GUI.view.QLHD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLHDController {
    private QLHD view;
    private addQLHD addDialog;
    private QLHDNV qlhdnv;
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
    public ActionListener confirmKH;
    public ActionListener ctKH;
    public FocusAdapter ctSolg; //ko biết là gì nhưng sẽ ôn sau
    public ActionListener confirmAdd;
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
                    addDialog.maSach.addActionListener(ctKH);
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
        
        ctNV= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (qlhdnv == null) {
                    qlhdnv = new QLHDNV(view);
                    qlhdnv.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            qlhdnv = null;
                        }
                    });
                }
                qlhdnv.xacNhan.addActionListener(confirmNV);
                JTable table= qlhdnv.table;
                DefaultTableModel model= (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                table.setModel(model);
                HDBLL hd= new HDBLL();
                ArrayList<HD> rs= hd.selectAll();
                for (int i=0; i< rs.size(); i++){
                    HD item= rs.get(i);
                    Object[] row1= new Object[] {item.getMaHD(), 
                        item.getMaKH(), 
                        item.getMaNV(), 
                        item.getNgayLap(), 
                        item.getTongTien(), 
                        item.getTongGG(), 
                        item.getTongSL(), 
                        item.getPhuongThuc(), 
                        item.getThanhTien() };
                    model.addRow(row1);
                }
            }
        };
        ctKH= new ActionListener() {
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
                qlhdkh.xacNhan.addActionListener(confirmKH);
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
        confirmNV= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table= qlhdnv.table;
                int row= table.getSelectedRow();
                int column= table.getColumnModel().getColumnIndex("Mã HD");
                if (row !=-1){
                    String maNV= table.getValueAt(row, column).toString();
                    addDialog.ctMaSach.setText(maNV);
                }
                qlhdnv.dispose();
            }
        };
        confirmKH= new ActionListener() {
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
                    float giamGia= s* (Float.parseFloat(donGia1)/100);
                    addDialog.ctGiamGia.setText(String.valueOf(giamGia));
                }
                qlhdkh.dispose();
            }
        };
        ctSolg= new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try{
                    int solg= Integer.parseInt(addDialog.ctSolg.getText());
                    float donGia= Float.parseFloat(addDialog.ctDonGia.getText());
                    float giamGia= Float.parseFloat(addDialog.ctGiamGia.getText());
                    float tongTien= solg* donGia;
                    float thanhTien= tongTien- giamGia;
                    addDialog.ctTongtien.setText(String.valueOf(tongTien));
                    addDialog.ctThanhTien.setText(String.valueOf(thanhTien));
                }
                catch(Exception er){
                    JOptionPane.showMessageDialog(view.frame, "Nhập số lượng hợp lệ");
                }
            }
        }; 
        confirmAdd= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD= addDialog.ctMaHD.getText();
                String maSach= addDialog.ctMaSach.getText();
                int soLuong= Integer.parseInt(addDialog.ctSolg.getText());
                float donGia= Float.parseFloat(addDialog.ctDonGia.getText());
                float tongTien= Float.parseFloat(addDialog.ctTongtien.getText());
                float giamGia= Float.parseFloat(addDialog.ctGiamGia.getText());
                float thanhTien= Float.parseFloat(addDialog.ctThanhTien.getText());
                CTHD cthd= new CTHD(maHD, maSach, soLuong, donGia, tongTien, giamGia, thanhTien);
                HDBLL hd= new HDBLL();
                hd.updateAdd(cthd);
                CTHDBLL cthdbll= new CTHDBLL();
                cthdbll.add(cthd);
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
}
