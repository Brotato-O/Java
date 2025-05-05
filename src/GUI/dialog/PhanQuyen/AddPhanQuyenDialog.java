package GUI.dialog.PhanQuyen;

import BLL.PhanQuyenBLL;
import java.awt.*;
import javax.swing.*;

public class AddPhanQuyenDialog extends javax.swing.JDialog {
    
    private JButton btnXacNhan, btnHuy;
    private JTextField txtQuyen = new JTextField();
    private PhanQuyenBLL phanQuyenBLL = new PhanQuyenBLL();
    
    // Thêm một interface để gọi lại khi thêm thành công
    public interface AddPhanQuyenListener {
        void onPhanQuyenAdded();
    }
    
    private AddPhanQuyenListener listener;
    
    public AddPhanQuyenDialog(java.awt.Frame parent, AddPhanQuyenListener listener) {
        super(parent, "Thêm quyền", true);
        this.listener = listener;
        this.setSize(400, 130);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout());
        add(addMiddle(), BorderLayout.CENTER);
        add(addFooter(), BorderLayout.SOUTH);
        
        addButtonListeners();
    }
    
    private JPanel addMiddle() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lbQuyen = new JLabel("Tên quyền: ");
        panel.add(lbQuyen);
        panel.add(txtQuyen);
        
        return panel;
    }
    
    private JPanel addFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnXacNhan = new JButton("Xác nhận");
        btnHuy = new JButton("Hủy");
        
        panel.add(btnXacNhan);
        panel.add(btnHuy);
        
        return panel;
    }
    
    private void addButtonListeners() {
        btnXacNhan.addActionListener(e -> {
            String quyen = txtQuyen.getText().trim();
            if (quyen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên quyền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean success = phanQuyenBLL.themQuyen(quyen);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Thêm quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Gọi phương thức callback khi thêm thành công
                    if (listener != null) {
                        listener.onPhanQuyenAdded();
                    }
                    
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm quyền thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnHuy.addActionListener(e -> {
            this.dispose();
        });
    }
}