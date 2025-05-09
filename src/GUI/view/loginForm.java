package GUI.view;

import BLL.EmpBLL;
import BLL.PhanQuyenBLL;
import DTO.PhanQuyenDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import main.main;

public class loginForm extends JPanel {
    private int width = main.width;
    private int height = main.height;
    private JTextField account = new JTextField();
    private JPasswordField password = new JPasswordField();

    private JButton loginButton = new JButton("Đăng nhập");
    private JButton exitButton = new JButton("Thoát");

    public loginForm() {
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#cdffff"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.decode("#333333"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Account label and field
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Tên đăng nhập:"), gbc);
        account.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        add(account, gbc);

        // Password label and field
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Mật khẩu:"), gbc);
        password.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        add(password, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.setBackground(Color.decode("#009900"));
        loginButton.setForeground(Color.WHITE);
        exitButton.setPreferredSize(new Dimension(120, 35));
        exitButton.setBackground(Color.decode("#ff0100"));
        exitButton.setForeground(Color.WHITE);
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Action listeners
        loginButton.addActionListener(this::onLogin);
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void onLogin(ActionEvent e) {
        String maNV = account.getText().trim();
        String mk = new String(password.getPassword());

        EmpBLL empbll = new EmpBLL();
        if (!empbll.checkLogin(maNV, mk)) {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Xóa trước
        if (empbll.checkTaiKhoanXoa(maNV)) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã bị xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Khóa sau
        if (empbll.checkTaiKhoanKhoa(maNV)) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã bị khóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String chucVu = empbll.getChucVu(maNV);
        if (chucVu == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin chức vụ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PhanQuyenBLL pqbll = new PhanQuyenBLL();
        pqbll.kiemTraQuyen(chucVu);
        PhanQuyenDTO permissions = PhanQuyenBLL.quyenTK;
        if (permissions == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy quyền cho chức vụ này", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Launch main UI
        SupermarketUI sm = new SupermarketUI();
        sm.createAndShowGUI();
        SupermarketUI.setCurrentPermissions(permissions);

        // Close login window
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) window.dispose();
    }
}
