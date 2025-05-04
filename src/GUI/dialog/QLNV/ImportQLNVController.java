package GUI.dialog.QLNV;

import BLL.EmpBLL;
import DTO.EmployeeManagementDTO;
import GUI.controller.EmpController;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ImportQLNVController {

    private ImportQLNVDialog dialog;
    private DefaultTableModel model;
    private JButton btnThemTatCa, btnHuy;
    private EmpBLL empBLL; // Lớp BLL để xử lý logic nghiệp vụ
    private EmpController empController;

    public ImportQLNVController(ImportQLNVDialog dialog, DefaultTableModel modelEx,EmpController empController) {
        this.dialog = dialog;
        this.model = modelEx;
        this.empBLL = new EmpBLL(); 
        this.empController = empController; 
        this.btnThemTatCa = dialog.getBtnThemTatCa();
        this.btnHuy = dialog.getBtnHuy();

        // Xử lý sự kiện nút "Thêm tất cả"
        this.btnThemTatCa.addActionListener(e -> {
            System.out.println("Nút Thêm tất cả được nhấn!");
            int soLuongThem = 0;
            int soLuongBoQua = 0;
            ArrayList<String> danhSachThemThanhCong = new ArrayList<>(); 

            for (int i = 0; i < model.getRowCount(); i++) {
                String maNV = model.getValueAt(i, 0).toString(); 
                String tenNV = model.getValueAt(i, 1).toString(); 
                String sdt = model.getValueAt(i, 2).toString(); 
                String email = model.getValueAt(i, 3).toString(); 
                boolean gioiTinh = model.getValueAt(i, 4).toString().equalsIgnoreCase("Nam"); 
                String chucVu = model.getValueAt(i, 5).toString(); 
                float luong = Float.parseFloat(model.getValueAt(i, 6).toString()); 
                String ngaySinh = model.getValueAt(i, 7).toString(); 

                EmployeeManagementDTO employee = new EmployeeManagementDTO(maNV, tenNV, sdt, email, chucVu, luong, ngaySinh, gioiTinh);
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

            dialog.dispose(); 
        });

        this.btnHuy.addActionListener(e -> {
        System.out.println("Nút Hủy được nhấn!");
        dialog.dispose();});
    }
}