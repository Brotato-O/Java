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
        setLayout(new BorderLayout());
        
        // Header: Tiêu đề "QUẢN LÝ PHÂN QUYỀN"
        JLabel lblTitle = new JLabel("QUẢN LÝ PHÂN QUYỀN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);
        
        // Middle: Form chọn quyền và bảng danh sách quyền
        JPanel pnlMiddle = new JPanel(new BorderLayout());
        pnlMiddle.add(addForm(), BorderLayout.NORTH);
        pnlMiddle.add(addCheckboxes(), BorderLayout.CENTER);
        add(pnlMiddle, BorderLayout.CENTER);
        
        // Footer: Các nút thêm, sửa, xóa quyền
        add(addFooter(), BorderLayout.SOUTH);
        
        // Load dữ liệu ban đầu
        loadDanhSachQuyen();
        
        // Thêm sự kiện cho combobox
        addComboBoxListener();
    }
    
    private JPanel addForm() {
        JPanel pnlForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JLabel lblQuyen = new JLabel("Chọn quyền:");
        jcbQuyen = new JComboBox<>();
        jcbQuyen.setPreferredSize(new Dimension(200, 30));
        
        pnlForm.add(lblQuyen);
        pnlForm.add(jcbQuyen);
        
        return pnlForm;
    }
    
    private JPanel addCheckboxes() {
        // Tạo panel chính với BorderLayout để có thể căn giữa nội dung
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Tạo panel chứa các checkbox
        JPanel pnlCheckboxes = new JPanel();
        pnlCheckboxes.setLayout(new BoxLayout(pnlCheckboxes, BoxLayout.Y_AXIS));
        TitledBorder border = BorderFactory.createTitledBorder("Phân quyền chi tiết");
        border.setTitleJustification(TitledBorder.CENTER);
        pnlCheckboxes.setBorder(border);


        
        // Tạo các checkbox cho từng quyền
        Font font = new Font("Arial", Font.PLAIN, 14);
        
        // Panel cho từng hàng checkbox có label
        JPanel pnCheckNhapHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ckbNhapHang = new JCheckBox();
        JLabel lblNhapHang = new JLabel("Quản lý nhập hàng");
        lblNhapHang.setFont(font);
        ckbNhapHang.setFont(font);
        pnCheckNhapHang.add(ckbNhapHang);
        pnCheckNhapHang.add(lblNhapHang);
        
        JPanel pnCheckQLSanPham = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ckbQLSanPham = new JCheckBox();
        JLabel lblQLSanPham = new JLabel("Quản lý sản phẩm");
        lblQLSanPham.setFont(font);
        ckbQLSanPham.setFont(font);
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnCheckQLSanPham.add(lblQLSanPham);
        
        JPanel pnCheckQLNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ckbQLNhanVien = new JCheckBox();
        JLabel lblQLNhanVien = new JLabel("Quản lý nhân viên");
        lblQLNhanVien.setFont(font);
        ckbQLNhanVien.setFont(font);
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnCheckQLNhanVien.add(lblQLNhanVien);
        
        JPanel pnCheckQLKhachHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ckbQLKhachHang = new JCheckBox();
        JLabel lblQLKhachHang = new JLabel("Quản lý khách hàng");
        lblQLKhachHang.setFont(font);
        ckbQLKhachHang.setFont(font);
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnCheckQLKhachHang.add(lblQLKhachHang);
        
        JPanel pnCheckQLThongKe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ckbThongKe = new JCheckBox();
        JLabel lblThongKe = new JLabel("Quản lý thống kê");
        lblThongKe.setFont(font);
        ckbThongKe.setFont(font);
        pnCheckQLThongKe.add(ckbThongKe);
        pnCheckQLThongKe.add(lblThongKe);
        
        // Thêm tất cả các panel vào panel chính
        pnlCheckboxes.add(pnCheckNhapHang);
        pnlCheckboxes.add(pnCheckQLSanPham);
        pnlCheckboxes.add(pnCheckQLNhanVien);
        pnlCheckboxes.add(pnCheckQLKhachHang);
        pnlCheckboxes.add(pnCheckQLThongKe);
        
        // Đặt kích thước đồng nhất
        Dimension ckbSize = new Dimension(300, 30);
        pnCheckNhapHang.setPreferredSize(ckbSize);
        pnCheckQLSanPham.setPreferredSize(ckbSize);
        pnCheckQLNhanVien.setPreferredSize(ckbSize);
        pnCheckQLKhachHang.setPreferredSize(ckbSize);
        pnCheckQLThongKe.setPreferredSize(ckbSize);
        
        // Thêm panel chính vào CENTER của mainPanel để căn giữa
        mainPanel.add(pnlCheckboxes, BorderLayout.CENTER);
        
        // Thêm các panel trống ở trái và phải để căn giữa
        mainPanel.add(Box.createHorizontalStrut(350), BorderLayout.WEST);
        mainPanel.add(Box.createHorizontalStrut(350), BorderLayout.EAST);
        
        return mainPanel;
    }
    
    private JPanel addFooter() {
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xóa quyền");
        
        // Tăng kích thước các JButton
        Dimension buttonSize = new Dimension(150, 40);
        btnThemQuyen.setPreferredSize(buttonSize);
        btnSuaQuyen.setPreferredSize(buttonSize);
        btnXoaQuyen.setPreferredSize(buttonSize);
        
        pnlFooter.add(btnThemQuyen);
        pnlFooter.add(btnSuaQuyen);
        pnlFooter.add(btnXoaQuyen);
        
        // Thêm event cho các button
        addButtonListeners();
        
        return pnlFooter;
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