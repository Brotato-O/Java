/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.view;

import BLL.BLLNCC;
import DTO.NCC;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
    public JTextField sdt= new JTextField();
    
    public JButton them= new JButton("Thêm");
    public JButton sua= new JButton("Sửa");
    public JButton xoa= new JButton("Xóa");
    public JButton lamMoi= new JButton("Làm mới");
    public JButton timKiem= new JButton("Tìm kiếm");
    
    public JTextField timMaNCC= new JTextField();
    public JTextField timTenNCC= new JTextField();
    public JTextField timDiaChi= new JTextField();
    public JTextField timEmail= new JTextField();
    public ArrayList<NCC> list = new ArrayList<>();
     public BLLNCC bllncc = new BLLNCC();
    
    public JTable table= new JTable();
    DefaultTableModel model= (DefaultTableModel) table.getModel();
    
    public QLNCC(){
        setLayout(new BorderLayout());
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
        temp.add(inputFieldsQLS());
        temp.add(timKiemField());
        list = bllncc.getAllNCC();
        String[] column= {"Mã ncc", "Tên ncc", "Dia chi", "Email", "SĐT"};
        model.setColumnIdentifiers(column);
        showTable();
        JScrollPane pane= new JScrollPane(table);
        add(temp, BorderLayout.NORTH);
        add(pane);
        table.getSelectionModel().addListSelectionListener(even -> {
            if (!even.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    maNCC.setText(table.getValueAt(selectedRow, 0).toString());
                    tenNCC.setText(table.getValueAt(selectedRow, 1).toString());
                    diaChi.setText(table.getValueAt(selectedRow, 2).toString());
                    email.setText(table.getValueAt(selectedRow, 3).toString());
                   sdt.setText(table.getValueAt(selectedRow, 4).toString());
                   
                }
            }
        });
        // SỰ KIỆN THÊM
them.addActionListener(e -> {
    NCC ncc = new NCC(
        maNCC.getText(),
        tenNCC.getText(),
        diaChi.getText(),
        email.getText(),
        sdt.getText()
    );

    if (bllncc.addNCC(ncc)) {
        JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
        refreshTable();
    } else {
        JOptionPane.showMessageDialog(this, "Thêm thất bại. Kiểm tra dữ liệu!");
    }
});

// SỰ KIỆN SỬA
sua.addActionListener(e -> {
    NCC ncc = new NCC(
        maNCC.getText(),
        tenNCC.getText(),
        diaChi.getText(),
        email.getText(),
        sdt.getText()
    );

    if (bllncc.updateNCC(ncc)) {
        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        refreshTable();
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
    }
});

// SỰ KIỆN XÓA
xoa.addActionListener(e -> {
    String ma = maNCC.getText();
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa NCC " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        if (bllncc.deleteNCC(ma)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }
});
timKiem.addActionListener(e -> {
    list = timKiemNCC();
    showTable();  // Hiển thị lại bảng sau khi tìm kiếm
});

    }
    
    public JPanel inputFieldsQLS(){
        JPanel p= new JPanel();
        p.setLayout(new GridLayout(1, 2));
        
        JPanel mot= new JPanel();
        
        mot.setLayout(new GridLayout(5, 2));
        
        mot.add(new JLabel("Mã NCC"));
        mot.add(maNCC);
        mot.add(new JLabel("Ten NCC"));
        mot.add(tenNCC);
        mot.add(new JLabel("Địa chỉ"));
        mot.add(diaChi);
        mot.add(new JLabel("Email"));
        mot.add(email);
        mot.add(new JLabel("Số điện thoại"));
        mot.add(sdt);
        
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
    public void showTable() {
    model.setRowCount(0); 
    for (NCC ncc : list) {
        model.addRow(new Object[]{
            ncc.getMaNCC(),
            ncc.getTenNCC(),
            ncc.getDiaChi(),
            ncc.getEmail(),
            ncc.getSoDienThoai()
        });
    }
}
public void refreshTable() {
    list = bllncc.getAllNCC();
    showTable();
}
public ArrayList<NCC> timKiemNCC() {
    String maNCC = timMaNCC.getText().trim();
    String tenNCC = timTenNCC.getText().trim();
    String diaChi = timDiaChi.getText().trim();
    String email = timEmail.getText().trim();

    ArrayList<NCC> ketQua = new ArrayList<>();
    for (NCC ncc : bllncc.getAllNCC()) {
        // Kiểm tra mã NCC
        if (!maNCC.isEmpty() && !ncc.getMaNCC().contains(maNCC)) continue;

        // Kiểm tra tên NCC
        if (!tenNCC.isEmpty() && !ncc.getTenNCC().toLowerCase().contains(tenNCC.toLowerCase())) continue;

        // Kiểm tra địa chỉ
        if (!diaChi.isEmpty() && !ncc.getDiaChi().toLowerCase().contains(diaChi.toLowerCase())) continue;

        // Kiểm tra email
        if (!email.isEmpty() && !ncc.getEmail().toLowerCase().contains(email.toLowerCase())) continue;

        // Nếu tất cả điều kiện đều hợp lệ, thêm NCC vào kết quả
        ketQua.add(ncc);
    }
    return ketQua;
}


   
}
