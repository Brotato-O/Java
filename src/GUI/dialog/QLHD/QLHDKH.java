/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.dialog.QLHD;

import GUI.controller.QLHDController;
import GUI.view.QLHD;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLHDKH extends JDialog{
    public JButton xacNhan= new JButton("Xác nhận");
    public JTable table= new JTable();
    QLHDController controller;
    
    public QLHDKH(JFrame frame, QLHD view){
        super(frame, "Chọn khách hàng");
        String[] column= {"MÃ SÁCH","TÊN SÁCH", "TÊN NXB","MÃ THỂ LOẠI", "TÊN TÁC GIẢ", "NĂM XUẤT BẢN", "SỐ LƯỢNG", "ĐƠN GIÁ","HÌNH ẢNH"};
        controller= new QLHDController(view);
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
