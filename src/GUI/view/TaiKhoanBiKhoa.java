package GUI.view;

import BLL.CustomerBLL;
import BLL.EmpBLL;
import DTO.CustomerDTO;
import DTO.EmployeeManagementDTO;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TaiKhoanBiKhoa extends JPanel {
    
    private JTable tableKhachHang;
    private JTable tableNhanVien;
    private DefaultTableModel modelKhachHang;
    private DefaultTableModel modelNhanVien;
    private JLabel titleLabel;
    private JTabbedPane tabbedPane;
    private JButton btnXoaVinhVien;
    private JButton btnKhoiPhuc;
    private JButton btnLamMoi;
    private EmpBLL empBLL = new EmpBLL();
    private CustomerBLL customerBLL = new CustomerBLL();

    public TaiKhoanBiKhoa() {
        // Thiết lập layout cho JPanel
        setLayout(new BorderLayout());
        
        // Tạo tiêu đề
        titleLabel = new JLabel("TÀI KHOẢN BỊ KHÓA", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Thêm tiêu đề vào JPanel chính
        add(titleLabel, BorderLayout.NORTH);
        
        // Khởi tạo JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        // Tạo các tab và thêm vào JTabbedPane
        createNhanVienTab();
        createKhachHangTab();      
    
        // Thêm JTabbedPane vào JPanel chính
        add(tabbedPane, BorderLayout.CENTER);
        
        // Tạo panel chứa các nút ở footer
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
        
        showNhanVienData();
        showKhachHangData();

        //Action
        clickBtnKhoiPhuc();
        clickBtnXoaVinhVien();
        clickBtnLamMoi();
    }
    
    private JPanel createFooterPanel() {
        // Tạo panel cho footer với FlowLayout
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Tạo nút Xóa vĩnh viễn
        btnXoaVinhVien = new JButton("Xóa Vĩnh Viễn");
        btnXoaVinhVien.setFont(new Font("Arial", Font.BOLD, 14));
        btnXoaVinhVien.setBackground(new Color(220, 53, 69)); // Màu đỏ
        btnXoaVinhVien.setForeground(Color.WHITE);
        btnXoaVinhVien.setFocusPainted(false);
        
        // Tạo nút Khôi phục tài khoản
        btnKhoiPhuc = new JButton("Khôi Phục Tài Khoản");
        btnKhoiPhuc.setFont(new Font("Arial", Font.BOLD, 14));
        btnKhoiPhuc.setBackground(new Color(40, 167, 69)); // Màu xanh lá
        btnKhoiPhuc.setForeground(Color.WHITE);
        btnKhoiPhuc.setFocusPainted(false);
        
        //Tạo một nút làm mới
        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.setFont(new Font("Arial", Font.BOLD, 14));
        btnLamMoi.setBackground(new Color(0, 123, 255)); // Màu xanh dương
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setFocusPainted(false);

        // Thêm các nút vào panel footer
        footerPanel.add(btnXoaVinhVien);
        footerPanel.add(btnKhoiPhuc);
        footerPanel.add(btnLamMoi);

        return footerPanel;
    }
    
    private void createKhachHangTab() {
        // Tạo model cho bảng KhachHang
        String[] columnNames = {"ID", "Họ Tên", "Email", "Số Điện Thoại"};
        modelKhachHang = new DefaultTableModel(columnNames, 0);
        tableKhachHang = new JTable(modelKhachHang);
        
        // Tạo JScrollPane để chứa bảng
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        
        // Tạo panel để chứa scrollPane với lề xung quanh
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm panel vào tab "Khách Hàng"
        tabbedPane.addTab("Khách Hàng", null, panel, "Danh sách tài khoản khách hàng bị khóa");
    }
    
    private void createNhanVienTab() {
        // Tạo model cho bảng NhanVien
        String[] columnNames = {"ID", "Họ Tên", "Chức Vụ", "Email","Mật khẩu" };
        modelNhanVien = new DefaultTableModel(columnNames, 0);
        tableNhanVien = new JTable(modelNhanVien);
        
        // Tạo JScrollPane để chứa bảng
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        
        // Tạo panel để chứa scrollPane với lề xung quanh
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm panel vào tab "Nhân Viên"
        tabbedPane.addTab("Nhân Viên", null, panel, "Danh sách tài khoản nhân viên bị khóa");
    }
    
    public void showKhachHangData() {
        modelKhachHang.setRowCount(0);
        ArrayList<CustomerDTO> list = customerBLL.getListDSKhoa();
        if(list.size() == 0) {
            System.out.println("không có dữ liệu");
        }
        for (CustomerDTO cus : list) {
            Object[] rowData = {
                cus.getMaKH(),
                cus.getTenKH(),
                cus.getEmail(),
                cus.getSDT(),
            };
            modelKhachHang.addRow(rowData);
        }
    }
    
    public void showNhanVienData() {
        modelNhanVien.setRowCount(0);
        ArrayList<EmployeeManagementDTO> listEmp = empBLL.getListNhanVienBiKhoa();
        if(listEmp.size() == 0) {
            System.out.println("không có dữ liệu");
        }
        for (EmployeeManagementDTO emp : listEmp) {
            Object[] rowData = {
                emp.getId_emp(),
                emp.getName_emp(),
                emp.getPosition_emp(),
                emp.getEmail_emp(),
                emp.getPassword_emp(),
            };
            modelNhanVien.addRow(rowData);
        }
    }
    
    public void addNhanVienRow(Object[] row) {
        modelNhanVien.addRow(row);
    }
    
    // Phương thức để chuyển đến tab Khách Hàng
    public void showKhachHangTab() {
        tabbedPane.setSelectedIndex(1);
    }
    
    // Phương thức để chuyển đến tab Nhân Viên
    public void showNhanVienTab() {
        tabbedPane.setSelectedIndex(0);
    }

    public void clickBtnKhoiPhuc() {
        btnKhoiPhuc.addActionListener(e-> {
            int selectTable = tabbedPane.getSelectedIndex();
            if(selectTable == 0) {
                int selectedRow = tableNhanVien.getSelectedRow();
                if(selectedRow >= 0) {
                    String maNV = tableNhanVien.getValueAt(selectedRow, 0).toString();
                    int confirm  = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn khôi phục tài khoản này"
                        ,"Xác nhận", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        boolean res = empBLL.khoiPhucTaiKhoan(maNV);
                        if(res) {
                            JOptionPane.showMessageDialog(this, "Khôi phục tài khoản thành công.");
                            showNhanVienData(); //Cập nhật lại bảng
                        } else {
                            JOptionPane.showMessageDialog(this, "Khôi phục thất bại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng!");
                }
            } else if(selectTable == 1) {
                int selectedRow = tableKhachHang.getSelectedRow();
                if(selectedRow >= 0) {
                    String maKH = tableKhachHang.getValueAt(selectedRow, 0).toString();
                    int confirm  = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn khôi phục tài khoản này"
                        ,"Xác nhận", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        boolean res = customerBLL.khoiPhucTaiKhoan(maKH);
                        if(res) {
                            JOptionPane.showMessageDialog(this, "Khôi phục tài khoản thành công.");
                            showKhachHangData(); //Cập nhật lại bảng
                        } else {
                            JOptionPane.showMessageDialog(this, "Khôi phục thất bại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng!");
                }
            }
        });
    }
    
    public void clickBtnXoaVinhVien() {
        btnXoaVinhVien.addActionListener(e-> {
            int selectTable = tabbedPane.getSelectedIndex();
            if(selectTable == 0) {
                int selectedRow = tableNhanVien.getSelectedRow();
                if(selectedRow >= 0) {
                    String maNV = tableNhanVien.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hoàn toàn tài khoản này." 
                        , "Xác nhân", JOptionPane.YES_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        boolean res = empBLL.xoaVinhVien(maNV);
                        if(res) {
                            JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
                            showNhanVienData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa");
                }
            } else if(selectTable == 1) {
                int selectedRow = tableKhachHang.getSelectedRow();
                if(selectedRow >= 0) {
                    String maKH = tableKhachHang.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hoàn toàn tài khoản này." 
                        , "Xác nhân", JOptionPane.YES_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        boolean res = customerBLL.xoaVinhVien(maKH);
                        if(res) {
                            JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
                            showKhachHangData();
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa");
                }
            }
        });
    }
    public void clickBtnLamMoi() {
        btnLamMoi.addActionListener(e-> {
            int selectTable = tabbedPane.getSelectedIndex();
            if(selectTable == 0) {
                showNhanVienData();
            } else if(selectTable == 1) {
                showKhachHangData();
            }
        });
    }

}