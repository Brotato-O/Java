/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.dialog.QLPN;

import GUI.controller.QLPNController;
import GUI.view.QLPN;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLPNKH extends JDialog{
    public JButton xacNhan= new JButton("Xác nhận");
    public JTable table= new JTable();
    QLPNController controller;
    
    public QLPNKH(JFrame frame, QLPN view){
        super(frame, "Chọn khách hàng");
        String[] column= {"MÃ SÁCH","TÊN SÁCH","MÃ THỂ LOẠI", "TÊN TÁC GIẢ", "NĂM XUẤT BẢN", "SỐ LƯỢNG", "ĐƠN GIÁ"};
        controller= new QLPNController(view);
        DefaultTableModel model= (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        model.setRowCount(20);
        JScrollPane pane= new JScrollPane(table);
        setLayout(new BorderLayout());
        add(pane);
        add(xacNhan, BorderLayout.SOUTH);
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
