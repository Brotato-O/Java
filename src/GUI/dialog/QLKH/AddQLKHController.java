package GUI.dialog.QLKH;

import BLL.CustomerBLL;
import DTO.CustomerDTO;
import GUI.controller.CustomerController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AddQLKHController {
    
    public AddQLKHDialog addQLKHDialog;
    private JButton btnXacNhan;
    private JButton btnHuy;
    private CustomerController mainController;
    private CustomerBLL customerBLL = new CustomerBLL();

    public AddQLKHController(AddQLKHDialog dialog,CustomerController mainController) {
        this.addQLKHDialog = dialog;
        this.mainController = mainController;

        this.btnXacNhan = dialog.getBtnXacNhan();
        this.btnHuy = dialog.getBtnHuy();
        clickBtnXacNhan();
        clickBtnHuy();
    }

    private void clickBtnXacNhan() {
        this.btnXacNhan.addActionListener(e -> {
            System.out.println("hello");
            CustomerDTO cus = new CustomerDTO();
            // Lấy dữ liệu từ dialog
            cus.setMaKH(addQLKHDialog.getTxtMaKH().getText());
            cus.setTenKH(addQLKHDialog.getTxtTenKH().getText());
            cus.setSDT(addQLKHDialog.getTxtSDT().getText());
            cus.setEmail(addQLKHDialog.getTxtEmail().getText());
            cus.setGioiTinh(addQLKHDialog.getRdiNam().isSelected() ? "Nam" : "Nu");
            cus.setNgaySinh(addQLKHDialog.getTxtNgaySinh().getText());

            // Kiểm tra dữ liệu và thực hiện thêm khách hàng
            if (cus.getMaKH().isEmpty() || cus.getTenKH().isEmpty() || cus.getSDT().isEmpty() || cus.getEmail().isEmpty()) {
                JOptionPane.showMessageDialog(addQLKHDialog, "Vui lòng điền đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            } 

            if(checkBirthday(cus.getNgaySinh()) == false) {
                return;
            }

            if(isValidEmail(cus.getEmail()) == false) {
                return;
            }

            if(isValidPhoneNumber(cus.getSDT()) == false) {
                return;
            }

            if(customerBLL.addKH(cus)) {
                JOptionPane.showMessageDialog(addQLKHDialog, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                mainController.refreshTable();
                addQLKHDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(addQLKHDialog, "Không được thêm trùng mã nhân viên!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }


        });
    }

    private boolean checkBirthday(String birthDate) {
        // Kiểm tra tính hợp lệ của ngày
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Không cho phép ngày không hợp lệ (ví dụ: 30/02)
        try {
            Date date = sdf.parse(birthDate);

            // Kiểm tra ngày không được là ngày trong tương lai
            if (date.after(new Date())) {
                JOptionPane.showMessageDialog(addQLKHDialog, "Ngày sinh không được lớn hơn ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true; // Ngày hợp lệ
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(addQLKHDialog, "Ngày sinh không hợp lệ! Định dạng phải là yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|edu|yahoo\\.com)$";
        if(email.matches(emailRegex) == true) {
            return true;
        } else {
            JOptionPane.showMessageDialog(addQLKHDialog, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(0|\\+84)[3-9][0-9]{8}$";
        if(phoneNumber.matches(phoneRegex) == true) {
            return true;
        } else {
            JOptionPane.showMessageDialog(addQLKHDialog, "Số điện thoại không hợp lệ! Chỉ chấp nhận số điện thoại Việt Nam", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void clickBtnHuy() {
        this.btnHuy.addActionListener(e -> {
            addQLKHDialog.dispose();
        });
    }
}
