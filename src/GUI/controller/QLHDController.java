package GUI.controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import DTO.CTHD;
import BLL.CTHDBLL;
import BLL.HDBLL;
import DTO.HD;
import GUI.dialog.QLHD.deleteQLHD;
import GUI.view.QLHD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class QLHDController {
    private QLHD view;
    public MouseAdapter cthdAdapter;
    public ActionListener showCTHD;
    public ActionListener confirmDelete;
    public ActionListener deleteCTHD;

    public QLHDController(QLHD view) {
        this.view = view;
        JTable tableCTHD = this.view.getTableCTHD();
        DefaultTableModel modelCTHD = (DefaultTableModel) tableCTHD.getModel();

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
                JFrame parent= view.frame;
                new deleteQLHD(parent, view);
            }
        };
        
        deleteCTHD= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row= tableCTHD.getSelectedRow();
                int columnMaHD= tableCTHD.getColumnModel().getColumnIndex("Mã HĐ");
                int columnMaSach= tableCTHD.getColumnModel().getColumnIndex("Mã sách");
                
                if (row != -1){
                    String maHD=  modelCTHD.getValueAt(row, columnMaHD).toString();
                    String maSach=  modelCTHD.getValueAt(row, columnMaSach).toString();
                    
                    CTHDBLL cthdbll= new CTHDBLL();
                    ArrayList<CTHD> listCTHD= cthdbll.selectById(maHD, maSach);
                    
                    int rowCTHD= cthdbll.delete(maHD, maSach);
                    
                    if (rowCTHD > 0){
                        ArrayList<CTHD> rs = cthdbll.selectById(maHD);
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
                        
                        HDBLL hdbll= new HDBLL();
                        CTHD cthd= listCTHD.get(0);
                        int rowHD= hdbll.updateTongTien(cthd);
                        
                        if (rowHD > 0){
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
                    }
                }
            }
        };
    }
}
