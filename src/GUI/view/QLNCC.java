/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLNCC extends JPanel{
    public JTextField maNCC= new JTextField();
    public JTextField tenNCC= new JTextField();
    public JTextField diaChi= new JTextField();
    public JTextField email= new JTextField();
    
    public JButton them= new JButton("Thêm");
    public JButton sua= new JButton("Sửa");
    public JButton xoa= new JButton("Xóa");
    public JButton lamMoi= new JButton("Làm mới");
    public JButton timKiem= new JButton("Tìm kiếm");
    
    public JTextField timMaNCC= new JTextField();
    public JTextField timTenNCC= new JTextField();
    public JTextField timDiaChi= new JTextField();
    public JTextField timEmail= new JTextField();
    
    public JTable table= new JTable();
    
    public QLNCC(){
        setLayout(new BorderLayout());
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
        temp.add(inputFieldsQLS());
        temp.add(timKiemField());
        
        DefaultTableModel model= (DefaultTableModel) table.getModel();
        String[] column= {"Mã ncc", "Tên ncc", "Dia chi", "Email"};
        model.setColumnIdentifiers(column);
        model.setRowCount(20);
        JScrollPane pane= new JScrollPane(table);
        add(temp, BorderLayout.NORTH);
        add(pane);
    }
    
    public JPanel inputFieldsQLS(){
        JPanel p= new JPanel();
        p.setLayout(new GridLayout(1, 2));
        
        JPanel mot= new JPanel();
        
            mot.setLayout(new GridLayout(4, 2));
        
        mot.add(new JLabel("Mã NCC"));
        mot.add(maNCC);
        mot.add(new JLabel("Ten NCC"));
        mot.add(tenNCC);
        mot.add(new JLabel("Địa chỉ"));
        mot.add(diaChi);
        mot.add(new JLabel("Email"));
        mot.add(email);
        
        JPanel hai= new JPanel();
            hai.setLayout(new GridLayout(4, 1));
        hai.add(them);
        hai.add(sua);
        hai.add(xoa);
        hai.add(lamMoi);
        
        p.add(mot);
        p.add(hai);
        return p;
    }
    
    public JPanel timKiemField() {
        JPanel p = new JPanel(new GridLayout(1, 9));

        // Tạo các JLabel tương ứng và thêm cùng JTextField vào panel
        p.add(new JLabel("Mã NCC"));
        p.add(timMaNCC);

        p.add(new JLabel("Tên NCC"));
        p.add(timTenNCC);

        p.add(new JLabel("Địa chỉ"));
        p.add(timDiaChi);

        p.add(new JLabel("Email"));
        p.add(timEmail);

        // Thêm nút tìm kiếm cuối cùng
        p.add(timKiem);

        return p;
    }
    
   
}
