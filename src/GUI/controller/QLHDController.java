package GUI.controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import DTO.CTHD;
import BLL.CTHDBLL;
import GUI.view.QLHD;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class QLHDController {
    private QLHD view;
    public MouseAdapter cthdAdapter;

    public QLHDController(QLHD view) {
        this.view = view;
        JTable tableCTHD = this.view.getTableCTHD();
        DefaultTableModel modelCTHD = (DefaultTableModel) tableCTHD.getModel();

        cthdAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                int rowSl = table.getSelectedRow();
                if (rowSl != -1) {
                    String id = table.getValueAt(rowSl, 0).toString();
                    CTHDBLL cthd = new CTHDBLL();
                    ArrayList<CTHD> rs = cthd.getCTHĐALById(id);

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
                    tableCTHD.setModel(modelCTHD);
                    System.out.println(modelCTHD.getRowCount());
                }
            }
        };
    }
}
