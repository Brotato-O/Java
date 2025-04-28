package GUI.controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import DTO.CTHD;
import BLL.CTHDBLL;
import BLL.HDBLL;
import DTO.HD;
import GUI.dialog.QLHD.addQLHD;
import GUI.view.QLHD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLHDController {
    private QLHD view;
    public MouseAdapter cthdAdapter;
    public ActionListener showCTHD;
    public ActionListener confirmDelete;
    public ActionListener deleteHD;
    public ActionListener deleteCTHD;
    public ActionListener addCTHD;
    public ActionListener findHD;
    public ActionListener showHD;
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
                    String id = table.getValueAt(rowSl, table.getColumnModel().getColumnIndex("Mã HD")).toString();
                    CTHDBLL cthd = new CTHDBLL();
                    ArrayList<CTHD> rs = cthd.selectById(id);
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
                JFrame parent= view.frame;
                new addQLHD(parent, view);
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
