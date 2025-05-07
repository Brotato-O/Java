package GUI.dialog.QLNV;

import java.awt.*;
import javax.swing.*;

public class AddQLNVDialog extends JDialog {
    // UI components
    public JButton btnXacNhan = new JButton("Xác nhận");
    public JButton btnHuy = new JButton("Hủy");
    public JTextField txtMaNV = new JTextField();
    public JTextField txtTenNV = new JTextField();
    public JTextField txtSDT = new JTextField();
    public JTextField txtEmail = new JTextField();
    public JTextField password = new JTextField(); 
    public JRadioButton rdiNam = new JRadioButton("Nam");
    public JRadioButton rdiNu = new JRadioButton("Nữ");
    public ButtonGroup genderGroup = new ButtonGroup();
    public JTextField txtChucVu = new JTextField();
    public JComboBox<String> cbChucVu = new JComboBox<>();
    public JTextField txtLuong = new JTextField();
    public JTextField txtNgaySinh = new JTextField();

    public AddQLNVDialog(JFrame frame) {    
        super(frame, "Thêm nhân viên", true);
        this.setLayout(new BorderLayout());
        add(addMiddle(), BorderLayout.CENTER);
        add(addFooter(), BorderLayout.SOUTH);
        
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private JPanel addMiddle() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbMaNV = new JLabel("Mã nhân viên: ");
        panel.add(lbMaNV);
        panel.add(txtMaNV);

        JLabel lbTenNV = new JLabel("Tên nhân viên: ");
        panel.add(lbTenNV);
        panel.add(txtTenNV);

        JLabel lbSDT = new JLabel("Số điện thoại: ");
        panel.add(lbSDT);
        panel.add(txtSDT);

        JLabel lbEmail = new JLabel("Email: ");
        panel.add(lbEmail);
        panel.add(txtEmail);

        JLabel lbMatKhau = new JLabel("Mật khẩu: ");
        panel.add(lbMatKhau);
        panel.add(password);

        JLabel lbGioiTinh = new JLabel("Giới tính: ");
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderGroup.add(rdiNam);
        genderGroup.add(rdiNu);
        rdiNam.setSelected(true);  // Default selection
        genderPanel.add(rdiNam);
        genderPanel.add(rdiNu);
        panel.add(lbGioiTinh);
        panel.add(genderPanel);

        JLabel lbChucVu = new JLabel("Chức vụ: ");
        panel.add(lbChucVu);
        panel.add(txtChucVu);

        JLabel lbLuong = new JLabel("Lương: ");
        panel.add(lbLuong);
        panel.add(txtLuong);

        JLabel lbNgaySinh = new JLabel("Ngày sinh (yyyy-MM-dd): ");
        panel.add(lbNgaySinh);
        panel.add(txtNgaySinh);

        return panel;
    }
    
    private JPanel addFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        btnXacNhan.setPreferredSize(new Dimension(100, 30));
        btnHuy.setPreferredSize(new Dimension(100, 30));

        panel.add(btnXacNhan);
        panel.add(btnHuy);

        return panel;
    }

    public JTextField getTxtMaNV() {
        return txtMaNV;
    }

    public JTextField getTxtTenNV() {
        return txtTenNV;
    }

    public JTextField getTxtSDT() {
        return txtSDT;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getTxtChucVu() {
        return txtChucVu;
    }

    public JTextField getTxtLuong() {
        return txtLuong;
    }

    public JTextField getTxtNgaySinh() {
        return txtNgaySinh;
    }

    //True là nam , false là nữ
    public JRadioButton getRdiNam() {
        return rdiNam;
    }

    public JButton getBtnXacNhan() {
        return btnXacNhan;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }


}
