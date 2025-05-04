package GUI.dialog.QLKH;

import BLL.CustomerBLL;
import DTO.CustomerDTO;
import GUI.controller.CustomerController;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ImportQLKHController {

    private ImportQLKHDialog dialog;
    private DefaultTableModel model;
    private JButton btnThemTatCa, btnHuy;
    private CustomerBLL customerBLL; 
    private CustomerController customerController;

    public ImportQLKHController(ImportQLKHDialog dialog, DefaultTableModel modelEx, CustomerController customerController) {
        this.dialog = dialog;
        this.model = modelEx;
        this.customerBLL = new CustomerBLL(); 
        this.customerController = customerController; 
        this.btnThemTatCa = dialog.getBtnThemTatCa();
        this.btnHuy = dialog.getBtnHuy();

        this.btnThemTatCa.addActionListener(e -> {
            System.out.println("Nút Thêm tất cả được nhấn!");
            int soLuongThem = 0;
            int soLuongBoQua = 0;
            ArrayList<String> danhSachThemThanhCong = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                String maKH = model.getValueAt(i, 0).toString(); 
                String tenKH = model.getValueAt(i, 1).toString(); 
                String sdt = model.getValueAt(i, 2).toString(); 
                String email = model.getValueAt(i, 3).toString(); 
                String gioiTinh = model.getValueAt(i, 4).toString(); 
                String ngaySinh = model.getValueAt(i, 5).toString(); 

                // Tạo đối tượng CustomerDTO
                CustomerDTO customer = new CustomerDTO(maKH, tenKH, sdt, email, gioiTinh, ngaySinh);

                if (customerBLL.addKH(customer)) {
                    soLuongThem++;
                    danhSachThemThanhCong.add(maKH);
                } else {
                    soLuongBoQua++;
                }
            }

            String danhSachID = String.join(", ", danhSachThemThanhCong);

            JOptionPane.showMessageDialog(dialog,
                    "Thêm thành công: " + soLuongThem + " khách hàng\n" +
                    "ID khách hàng được thêm: " + danhSachID + "\n" +
                    "Bỏ qua: " + soLuongBoQua + " khách hàng (ID trùng)",
                    "Kết quả", JOptionPane.INFORMATION_MESSAGE);

            customerController.refreshTable();

            dialog.dispose(); 
        });

        // Xử lý sự kiện nút "Hủy"
        this.btnHuy.addActionListener(e -> {
            System.out.println("Nút Hủy được nhấn!");
            dialog.dispose();
        });
    }
}