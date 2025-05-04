package GUI.dialog.QLKH;

import java.awt.*;
import javax.swing.*;

public class AddQLKHDialog extends JDialog {
    // UI components
    public JButton btnXacNhan = new JButton("Xác nhận");
    public JButton btnHuy = new JButton("Hủy");
    public JTextField txtMaKH = new JTextField(); // Thêm trường nhập cho Mã khách hàng
    public JTextField txtTenKH = new JTextField();
    public JTextField txtSDT = new JTextField();
    public JTextField txtEmail = new JTextField();
    public JRadioButton rdiNam = new JRadioButton("Nam");
    public JRadioButton rdiNu = new JRadioButton("Nữ");
    public ButtonGroup genderGroup = new ButtonGroup();
    public JTextField txtNgaySinh = new JTextField();

    public AddQLKHDialog(JFrame frame) {
        super(frame, "Thêm khách hàng", true);
        this.setLayout(new BorderLayout());
        add(addMiddle(), BorderLayout.CENTER);
        add(addFooter(), BorderLayout.SOUTH);

        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private JPanel addMiddle() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5)); // Tăng số hàng lên 6
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mã khách hàng
        JLabel lbMaKH = new JLabel("Mã khách hàng: ");
        panel.add(lbMaKH);
        panel.add(txtMaKH);

        // Tên khách hàng
        JLabel lbTenKH = new JLabel("Tên khách hàng: ");
        panel.add(lbTenKH);
        panel.add(txtTenKH);

        // Số điện thoại
        JLabel lbSDT = new JLabel("Số điện thoại: ");
        panel.add(lbSDT);
        panel.add(txtSDT);

        // Email
        JLabel lbEmail = new JLabel("Email: ");
        panel.add(lbEmail);
        panel.add(txtEmail);

        // Giới tính
        JLabel lbGioiTinh = new JLabel("Giới tính: ");
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderGroup.add(rdiNam);
        genderGroup.add(rdiNu);
        rdiNam.setSelected(true); // Default selection
        genderPanel.add(rdiNam);
        genderPanel.add(rdiNu);
        panel.add(lbGioiTinh);
        panel.add(genderPanel);

        // Ngày sinh
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

    public JTextField getTxtMaKH() {
        return txtMaKH;
    }

    public JTextField getTxtTenKH() {
        return txtTenKH;
    }

    public JTextField getTxtSDT() {
        return txtSDT;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtNgaySinh() {
        return txtNgaySinh;
    }

    public JRadioButton getRdiNam() {
        return rdiNam;
    }

    public JRadioButton getRdiNu() {
        return rdiNu;
    }

    public JButton getBtnXacNhan() {
        return btnXacNhan;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }
}