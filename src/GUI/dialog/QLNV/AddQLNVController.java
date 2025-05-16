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

    private boolean checkQuyen(String chucVu) {
        if (chucVu == null || chucVu.trim().isEmpty() || chucVu.equals("Chọn quyền")) {
            JOptionPane.showMessageDialog(addQLNVDialog, 
                "Vui lòng chọn quyền cho nhân viên!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|edu|yahoo\\.com)$";
        if(email.matches(emailRegex) == true) {
            return true;
        } else {
            JOptionPane.showMessageDialog(addQLNVDialog, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(0|\\+84)[3-9][0-9]{8}$";
        if(phoneNumber.matches(phoneRegex) == true) {
            return true;
        } else {
            JOptionPane.showMessageDialog(addQLNVDialog, "Số điện thoại không hợp lệ! Chỉ chấp nhận số điện thoại Việt Nam", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
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
                String chucVu = addQLNVDialog.jcbChucVu.getSelectedItem().toString();
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

                if(checkQuyen(chucVu) == false) {
                    return;
                }

                if(isValidEmail(email) == false) {
                    return;
                }

                if(isValidPhoneNumber(sdt) == false) {
                    return;
                }

                try {
                    // Chuyển đổi lương từ chuỗi sang số
                    float luong = Float.parseFloat(luongStr);

                    try {
                        // Gọi phương thức thêm nhân viên từ EmpController
                        mainController.addEmployee(maNV, tenNV, sdt, email, mk, chucVu, luong, ngaySinh, gioiTinh);
                        
                        // Thông báo thành công
                        JOptionPane.showMessageDialog(addQLNVDialog, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Refresh bảng
                        mainController.refreshTable();
                        
                        // Đóng dialog
                        addQLNVDialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(addQLNVDialog, 
                            "Thêm nhân viên thất bại!\nLỗi: " + ex.getMessage(), 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                    }
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
            
            // Đảm bảo chuỗi ngày có đúng định dạng yyyy-MM-dd
            String formattedDate = sdf.format(date);
            addQLNVDialog.getTxtNgaySinh().setText(formattedDate);
            
            return true; // Ngày hợp lệ
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(addQLNVDialog, "Ngày sinh không hợp lệ! Hãy nhập theo định dạng yyyy-MM-dd (VD: 2000-12-31)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
