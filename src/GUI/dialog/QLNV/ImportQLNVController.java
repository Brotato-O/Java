package GUI.dialog.QLNV;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import BLL.EmpBLL;
import DTO.EmployeeManagementDTO;
import GUI.controller.EmpController;
import javax.swing.JOptionPane;

public class ImportQLNVController {

    private ImportQLNVDialog dialog;
    private DefaultTableModel model;
    private JButton btnThemTatCa, btnHuy;
    private EmpBLL empBLL; // Lớp BLL để xử lý logic nghiệp vụ
    private EmpController empController;

    public ImportQLNVController(ImportQLNVDialog dialog, DefaultTableModel modelEx,EmpController empController) {
        this.dialog = dialog;
        this.model = modelEx;
        this.empBLL = new EmpBLL(); // Khởi tạo lớp BLL
        this.empController = empController; // Nhận đối tượng EmpController từ bên ngoài
        this.btnThemTatCa = dialog.getBtnThemTatCa();
        this.btnHuy = dialog.getBtnHuy();

        // Xử lý sự kiện nút "Thêm tất cả"
        this.btnThemTatCa.addActionListener(e -> {
            System.out.println("Nút Thêm tất cả được nhấn!");
            int soLuongThem = 0;
            int soLuongBoQua = 0;
            ArrayList<String> danhSachThemThanhCong = new ArrayList<>(); 

            for (int i = 0; i < model.getRowCount(); i++) {
                String maNV = model.getValueAt(i, 0).toString(); // Cột 1: Mã NV
                String tenNV = model.getValueAt(i, 1).toString(); // Cột 2: Tên NV
                String sdt = model.getValueAt(i, 2).toString(); // Cột 3: SĐT
                String email = model.getValueAt(i, 3).toString(); // Cột 4: Email
                boolean gioiTinh = model.getValueAt(i, 4).toString().equalsIgnoreCase("Nam"); // Cột 5: Giới tính
                String chucVu = model.getValueAt(i, 5).toString(); // Cột 6: Chức vụ
                float luong = Float.parseFloat(model.getValueAt(i, 6).toString()); // Cột 7: Lương
                String ngaySinh = model.getValueAt(i, 7).toString(); // Cột 8: Ngày sinh

                // Tạo đối tượng EmployeeManagementDTO
                EmployeeManagementDTO employee = new EmployeeManagementDTO(maNV, tenNV, sdt, email, chucVu, luong, ngaySinh, gioiTinh);

                // Thêm nhân viên vào cơ sở dữ liệu
                if (empBLL.addNV(employee)) {
                    soLuongThem++;
                    danhSachThemThanhCong.add(maNV);
                } else {
                    soLuongBoQua++;
                }
            }

            String danhSachID = String.join(", ", danhSachThemThanhCong);

            // Hiển thị thông báo kết quả
            JOptionPane.showMessageDialog(dialog,
                    "Thêm thành công: " + soLuongThem + " nhân viên\n" +
                    "ID nhân viên được thêm: " + danhSachID + "\n" +
                    "Bỏ qua: " + soLuongBoQua + " nhân viên (ID trùng)",
                    "Kết quả", JOptionPane.INFORMATION_MESSAGE);

            empController.refreshTable();

            // Tải lại bảng
            dialog.dispose(); // Đóng dialog
        });

        // Xử lý sự kiện nút "Hủy"
        this.btnHuy.addActionListener(e -> {
        System.out.println("Nút Hủy được nhấn!");
        dialog.dispose();});
    }
}