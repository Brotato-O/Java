package GUI.view;

import BLL.PhanQuyenBLL;
import DTO.PhanQuyenDTO;
import GUI.view.EmployeeManagement;
import GUI.dialog.PhanQuyen.AddPhanQuyenDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PhanQuyen extends JPanel implements AddPhanQuyenDialog.AddPhanQuyenListener {
    
    private JComboBox<String> jcbQuyen;
    private JButton btnThemQuyen, btnSuaQuyen, btnXoaQuyen;
    private PhanQuyenBLL phanQuyenBLL = new PhanQuyenBLL();
    private EmployeeManagement employeeManagement;
    // Các checkbox cho các quyền cụ thể
    private JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe;
    
    public PhanQuyen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header: Tiêu đề "QUẢN LÝ PHÂN QUYỀN"
        JLabel lblTitle = new JLabel("QUẢN LÝ PHÂN QUYỀN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);
        
        // Middle: Form chọn quyền và bảng danh sách quyền
        JPanel pnlMiddle = new JPanel(new BorderLayout(10, 10));
        pnlMiddle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlMiddle.add(addForm(), BorderLayout.NORTH);
        pnlMiddle.add(addCheckboxes(), BorderLayout.CENTER);
        add(pnlMiddle, BorderLayout.CENTER);
        
        // Footer: Các nút thêm, sửa, xóa quyền
        JPanel footerPanel = addFooter();
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(footerPanel, BorderLayout.SOUTH);
        
        // Load dữ liệu ban đầu
        loadDanhSachQuyen();
        
        // Thêm sự kiện cho combobox
        addComboBoxListener();
    }
    
    private JPanel addForm() {
        JPanel pnlForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Chọn Quyền",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        
        JLabel lblQuyen = new JLabel("Chọn quyền:");
        lblQuyen.setFont(new Font("Arial", Font.PLAIN, 14));
        
        jcbQuyen = new JComboBox<>();
        jcbQuyen.setPreferredSize(new Dimension(250, 35));
        jcbQuyen.setFont(new Font("Arial", Font.PLAIN, 14));
        
        pnlForm.add(lblQuyen);
        pnlForm.add(jcbQuyen);
        
        return pnlForm;
    }
    
    private JPanel addCheckboxes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JPanel pnlCheckboxes = new JPanel();
        pnlCheckboxes.setLayout(new BoxLayout(pnlCheckboxes, BoxLayout.Y_AXIS));
        pnlCheckboxes.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Phân Quyền Chi Tiết",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        
        Font font = new Font("Arial", Font.PLAIN, 14);
        Dimension ckbSize = new Dimension(350, 35);
        
        // Tạo panel chứa tất cả các checkbox
        JPanel checkboxContainer = new JPanel();
        checkboxContainer.setLayout(new BoxLayout(checkboxContainer, BoxLayout.Y_AXIS));
        checkboxContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Tạo các checkbox với style mới và căn giữa
        checkboxContainer.add(Box.createVerticalStrut(15));
        checkboxContainer.add(createUniformCheckboxPanel("Quản lý nhập hàng", ckbNhapHang = new JCheckBox(), font, ckbSize));
        checkboxContainer.add(Box.createVerticalStrut(15));
        checkboxContainer.add(createUniformCheckboxPanel("Quản lý sản phẩm", ckbQLSanPham = new JCheckBox(), font, ckbSize));
        checkboxContainer.add(Box.createVerticalStrut(15));
        checkboxContainer.add(createUniformCheckboxPanel("Quản lý nhân viên", ckbQLNhanVien = new JCheckBox(), font, ckbSize));
        checkboxContainer.add(Box.createVerticalStrut(15));
        checkboxContainer.add(createUniformCheckboxPanel("Quản lý khách hàng", ckbQLKhachHang = new JCheckBox(), font, ckbSize));
        checkboxContainer.add(Box.createVerticalStrut(15));
        checkboxContainer.add(createUniformCheckboxPanel("Quản lý thống kê", ckbThongKe = new JCheckBox(), font, ckbSize));
        checkboxContainer.add(Box.createVerticalStrut(15));
        
        pnlCheckboxes.add(checkboxContainer);
        mainPanel.add(pnlCheckboxes, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    private JPanel createUniformCheckboxPanel(String label, JCheckBox checkbox, Font font, Dimension size) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(size);
        panel.setMaximumSize(size);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Tạo panel cho checkbox
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        checkboxPanel.setPreferredSize(new Dimension(30, 30));
        checkbox.setFont(font);
        checkboxPanel.add(checkbox);
        
        // Tạo panel cho label
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel lbl = new JLabel(label);
        lbl.setFont(font);
        labelPanel.add(lbl);
        
        // Thêm các panel vào panel chính
        panel.add(checkboxPanel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(labelPanel);
        
        return panel;
    }
    
    private JPanel addFooter() {
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnThemQuyen = createStyledButton("Thêm quyền");
        btnSuaQuyen = createStyledButton("Sửa quyền");
        btnXoaQuyen = createStyledButton("Xóa quyền");
        
        pnlFooter.add(btnThemQuyen);
        pnlFooter.add(btnSuaQuyen);
        pnlFooter.add(btnXoaQuyen);
        
        addButtonListeners();
        
        return pnlFooter;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }
    
    // Phương thức load danh sách quyền từ database
    public void loadDanhSachQuyen() {
        // Lưu lại quyền đang chọn
        String selectedQuyen = jcbQuyen.getItemCount() > 0 ? (String) jcbQuyen.getSelectedItem() : null;
        
        // Đọc lại danh sách quyền từ database
        ArrayList<String> dsQuyen = phanQuyenBLL.getDSQuyen();
        
        // Xóa tất cả item trong combo box
        jcbQuyen.removeAllItems();
        
        // Thêm lựa chọn mặc định
        jcbQuyen.addItem("Chọn quyền");
        
        // Load dữ liệu vào combo box
        if (dsQuyen != null) {
            for (String quyen : dsQuyen) {
                jcbQuyen.addItem(quyen);
            }
        }
        
        // Nếu có quyền đã chọn trước đó, chọn lại
        if (selectedQuyen != null) {
            for (int i = 0; i < jcbQuyen.getItemCount(); i++) {
                if (selectedQuyen.equals(jcbQuyen.getItemAt(i))) {
                    jcbQuyen.setSelectedIndex(i);
                    return;
                }
            }
        }
        
        // Mặc định chọn item đầu tiên
        jcbQuyen.setSelectedIndex(0);
    }
    
    // Sự kiện khi chọn một quyền từ combobox
    private void addComboBoxListener() {
        jcbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = jcbQuyen.getSelectedItem();
                
                // Kiểm tra null trước khi sử dụng
                if (selectedItem == null) {
                    resetCheckboxes(); // Reset khi không có lựa chọn
                    return;
                }
                
                String selectedQuyen = selectedItem.toString();
                
                // Nếu lựa chọn là "Chọn quyền" thì reset tất cả các checkbox
                if (selectedQuyen.equals("Chọn quyền")) {
                    resetCheckboxes();
                    return;
                }
                
                // Load thông tin chi tiết của quyền đã chọn
                loadQuyenDetails(selectedQuyen);
            }
        });
    }
    
    // Reset tất cả các checkbox
    private void resetCheckboxes() {
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
    }
    
    // Load thông tin chi tiết của quyền đã chọn
    private void loadQuyenDetails(String quyen) {
        // Gọi phương thức kiểm tra quyền từ BLL
        phanQuyenBLL.kiemTraQuyen(quyen);
        
        // Lấy thông tin quyền từ BLL
        PhanQuyenDTO pq = PhanQuyenBLL.quyenTK;
        
        if (pq != null) {
            // Cập nhật trạng thái của các checkbox dựa trên dữ liệu từ cơ sở dữ liệu
            ckbNhapHang.setSelected(pq.getNhapHang() == 1);
            ckbQLSanPham.setSelected(pq.getQlSanPham() == 1);
            ckbQLNhanVien.setSelected(pq.getQlNhanVien() == 1);
            ckbQLKhachHang.setSelected(pq.getQlKhachHang() == 1);
            ckbThongKe.setSelected(pq.getThongKe() == 1);
        } else {
            resetCheckboxes();
        }

        revalidate();
        repaint();
    }
    
    // Thêm sự kiện cho các button
    private void addButtonListeners() {
        // Thêm sự kiện cho nút Thêm quyền
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở dialog thêm quyền mới
                AddPhanQuyenDialog dialog = new AddPhanQuyenDialog(
                    (Frame) SwingUtilities.getWindowAncestor(PhanQuyen.this),
                    PhanQuyen.this
                );
                dialog.setVisible(true);
            }
        });
        
        // Thêm sự kiện cho nút Sửa quyền
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQuyen = (String) jcbQuyen.getSelectedItem();
                
                // Kiểm tra xem đã chọn quyền hay chưa
                if (selectedQuyen.equals("Chọn quyền")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Tạo đối tượng PhanQuyenDTO từ trạng thái các checkbox
                PhanQuyenDTO pq = new PhanQuyenDTO();
                pq.setQuyen(selectedQuyen);
                pq.setNhapHang(ckbNhapHang.isSelected() ? 1 : 0);
                pq.setQlSanPham(ckbQLSanPham.isSelected() ? 1 : 0);
                pq.setQlNhanVien(ckbQLNhanVien.isSelected() ? 1 : 0);
                pq.setQlKhachHang(ckbQLKhachHang.isSelected() ? 1 : 0);
                pq.setThongKe(ckbThongKe.isSelected() ? 1 : 0);
                
                // Gọi phương thức cập nhật quyền
                boolean result = phanQuyenBLL.suaQuyen(pq);
                
                if (result) {
                    JOptionPane.showMessageDialog(null, "Sửa quyền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    // Load lại dữ liệu
                    loadDanhSachQuyen();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa quyền thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Thêm sự kiện cho nút Xóa quyền
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQuyen = (String) jcbQuyen.getSelectedItem();
                
                // Kiểm tra xem đã chọn quyền hay chưa
                if (selectedQuyen.equals("Chọn quyền")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền cần xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Hiển thị dialog xác nhận
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Bạn có chắc chắn muốn xóa quyền \"" + selectedQuyen + "\"?", 
                    "Xác nhận xóa", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    // Gọi phương thức xóa quyền
                    boolean result = phanQuyenBLL.xoaQuyen(selectedQuyen);
                    
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xóa quyền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        // Load lại dữ liệu
                        loadDanhSachQuyen();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa quyền thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    
    //Thêm mới
    // Setter method
    public void setEmployeeManagement(EmployeeManagement employeeManagement) {
        this.employeeManagement = employeeManagement;
    }

    public void openAddPhanQuyenDialog(JFrame parent) {
        // Truyền this làm listener vì PhanQuyen implements AddPhanQuyenListener
        AddPhanQuyenDialog dialog = new AddPhanQuyenDialog(parent, this);
        dialog.setVisible(true);
    }

    // Interface implementation cho AddPhanQuyenListener
    @Override
    public void onPhanQuyenAdded() {
        loadDanhSachQuyen();
        // employeeManagement.loadDanhSachQuyen();
    }
}