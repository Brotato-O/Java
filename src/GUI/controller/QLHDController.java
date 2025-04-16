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
    public ActionListener deleteCTHD;
    public ActionListener addCTHD;
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

                    modelCTHD.setRowCount(0);

                    for (int i = 0; i < rs.size(); i++) {
                        CTHD item = rs.get(i);
                        Object[] row = new Object[]{
                            item.getMaHD(),
                            item.getMaSach(),
                            item.getSoLuong(),
                            item.getGiaTien(),
                            item.getTongTien(),
                            item.getGiamGia(),
                            item.getThanhTien()
                        };
                        modelCTHD.addRow(row);
                    }
                }
            }
        };
        
        showCTHD= new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                CTHDBLL cthd = new CTHDBLL();
                ArrayList<CTHD> rs = cthd.selectAll();
                modelCTHD.setRowCount(0);
                for (int i=0; i< rs.size(); i++){
                    CTHD item= rs.get(i);
                    Object[] row= new Object[]{item.getMaHD(), 
                        item.getMaSach(), 
                        item.getSoLuong(), 
                        item.getGiaTien(),
                        item.getTongTien(),
                        item.getGiamGia(),
                        item.getThanhTien()};
                    modelCTHD.addRow(row);
                }
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

                                if (rowHD > 0){
                                    updateHD(hdbll);
                                }
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
    }
    
    public void updateHD(HDBLL hdbll){
        ArrayList<HD> listHD= hdbll.selectAll();
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
}
