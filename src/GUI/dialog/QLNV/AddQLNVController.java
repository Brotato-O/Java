package GUI.dialog.QLNV;

import GUI.controller.EmpController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AddQLNVController {
    private EmpController mainController = null;
    private AddQLNVDialog addQLNVDialog;
    private JButton btnXacNhan;
    private JButton btnHuy;

    //Constructor
    public AddQLNVController(AddQLNVDialog dialog) {
       this.addQLNVDialog = dialog;
       //Lấy nút xác nhận và hủy từ dialog
        this.btnXacNhan = dialog.getBtnXacNhan();
        this.btnHuy = dialog.getBtnHuy();
        clickBtnXacNhan();
    }

    // Phương thức để thiết lập EmpController
    public void setMainController(EmpController mainController) {
        this.mainController = mainController;
    }

    // Phương thức ví dụ để tương tác với EmpController
    public void notifyMainController() {
        if (mainController != null) {
            // Gọi phương thức refreshTable() từ EmpController
            mainController.refreshTable();
        }
    }

    private void clickBtnXacNhan() {
    this.btnXacNhan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Xác nhận nút được nhấn!");

            if (mainController == null) {
                System.err.println("mainController chưa được thiết lập!");
                return;
            }

            // Lấy dữ liệu từ dialog
            String maNV = addQLNVDialog.getTxtMaNV().getText();
            String tenNV = addQLNVDialog.getTxtTenNV().getText();
            String sdt = addQLNVDialog.getTxtSDT().getText();
            String email = addQLNVDialog.getTxtEmail().getText();
            String mk = addQLNVDialog.getPassword().getText();
            String chucVu = addQLNVDialog.getTxtChucVu().getText();
            String luongStr = addQLNVDialog.getTxtLuong().getText();
            String ngaySinh = addQLNVDialog.getTxtNgaySinh().getText();
            boolean gioiTinh = addQLNVDialog.getRdiNam().isSelected();

            // Kiểm tra giá trị nhập liệu
            if (maNV.isEmpty() || tenNV.isEmpty() || sdt.isEmpty() || email.isEmpty() || chucVu.isEmpty() || luongStr.isEmpty() || ngaySinh.isEmpty() || mk.isEmpty()) {
                JOptionPane.showMessageDialog(addQLNVDialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(mainController.getEmpBLL().checkID(maNV)) {
                JOptionPane.showMessageDialog(addQLNVDialog, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(checkBirthDay(ngaySinh) == false) {
                return;
            }

            try {
                // Chuyển đổi lương từ chuỗi sang số
                float luong = Float.parseFloat(luongStr);

                // Gọi phương thức thêm nhân viên từ EmpController
                mainController.addEmployee(maNV, tenNV, sdt, email,mk, chucVu, luong, ngaySinh, gioiTinh);
                addQLNVDialog.dispose(); // Đóng dialog sau khi thêm nhân viên thành công
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addQLNVDialog, "Lương phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    }
    

    private boolean checkBirthDay(String birthDate) {

        // Kiểm tra tính hợp lệ của ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Không cho phép ngày không hợp lệ (ví dụ: 30/02)
        try {
            Date date = sdf.parse(birthDate);

            // Kiểm tra ngày không được là ngày trong tương lai
            if (date.after(new Date())) {
                JOptionPane.showMessageDialog(addQLNVDialog, "Ngày sinh không lớn hay ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true; // Ngày hợp lệ
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(addQLNVDialog, "Ngày sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
