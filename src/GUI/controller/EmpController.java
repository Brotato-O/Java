package GUI.controller;

import BLL.*;
import DTO.EmployeeManagementDTO;
import GUI.dialog.QLNV.AddQLNVController;
import GUI.dialog.QLNV.AddQLNVDialog;
import GUI.view.EmployeeManagement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EmpController {

	private EmployeeManagement view = null;
	JTable table;
	JButton btnThem;
	DefaultTableModel model;
	private EmpBLL empBLL = new EmpBLL();

	public EmpController(EmployeeManagement view) {
		this.view = view;
		this.table = view.getTableListEmp();
		this.model = (DefaultTableModel) table.getModel();
		
		this.btnThem = view.getBtnThem();

		// Thêm sự kiện MouseListener vào JTable
		addTableMouseListener();
		btnClickShowDialogAdd();
	}
	
	private void btnClickShowDialogAdd() {
    this.btnThem.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
			System.out.println("Thêm nhân viên được nhấn!");
            // Lấy JFrame chứa JPanel
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            if (parentFrame != null) {
                // Hiển thị dialog thêm nhân viên
                AddQLNVDialog addQLNVDialog = new AddQLNVDialog(parentFrame);

                // Tạo controller cho dialog
                AddQLNVController addQLNVController = new AddQLNVController(addQLNVDialog);

                // Liên kết EmpController (mainController) với AddQLNVController
                addQLNVController.setMainController(EmpController.this);

                // Hiển thị dialog
                addQLNVDialog.setVisible(true);
            } else {
                System.err.println("Không tìm thấy JFrame chứa JPanel!");
            }
        }
    });
}


	private void addTableMouseListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lấy dòng được chọn
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					// Lấy mã nhân viên từ dòng được chọn
					String maNV = model.getValueAt(selectedRow, 0).toString();
					
					// Sử dụng BLL để lấy thông tin nhân viên từ DB dựa vào mã NV
					EmployeeManagementDTO emp = empBLL.getNV(maNV);
					
					// Kiểm tra nếu lấy được thông tin nhân viên
					if (emp != null) {
						// Xử lý dữ liệu và gửi đến view để hiển thị
						displayEmployeeData(emp);
					}
				}
			}
		});
	}
	
	/**
	 * Xử lý dữ liệu nhân viên và gửi đến view để hiển thị
	 */
	private void displayEmployeeData(EmployeeManagementDTO emp) {
		// Lấy tên đầy đủ của nhân viên
		String fullName = emp.getName_emp();
		
		// Lấy các thông tin khác
		String gender = emp.getGender_emp();
		
		// Gọi phương thức trong view để hiển thị dữ liệu
		view.displayEmployeeData(
			emp.getId_emp(),           // Mã NV
			fullName,                  // Tên đầy đủ
			emp.getPhone_emp(),        // SĐT
			emp.getEmail_emp(),        // Email
			gender,                    // Giới tính
			emp.getPosition_emp(),     // Chức vụ
			String.valueOf(emp.getSalary_emp()), // Lương
			emp.getBirth_date()        // Ngày sinh
		);
	}
	public void refreshTable() {
		try {
			ArrayList<EmployeeManagementDTO> listEmp = empBLL.getDS();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ trong bảng
				for (EmployeeManagementDTO emp : listEmp) {
				model.addRow(new Object[]{
					emp.getId_emp(),
					emp.getName_emp(),
					emp.getPhone_emp(),
					emp.getEmail_emp(),
					emp.getGender_emp(),
					emp.getPosition_emp(),
					emp.getSalary_emp(),
					emp.getBirth_date()
				});
			}
	
			System.out.println("Bảng đã được làm mới!");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void addEmployee(String maNV, String tenNV, String sdt, String email, String chucVu, float luong, String ngaySinh, boolean gioiTinh) {
		EmployeeManagementDTO emp = new EmployeeManagementDTO(maNV, tenNV, sdt, email, chucVu, luong, ngaySinh, gioiTinh);
		if (empBLL.addNV(emp)) {
			System.out.println("Thêm nhân viên thành công!");
			refreshTable(); // Cập nhật bảng hiển thị
		} else {
			System.out.println("Thêm nhân viên thất bại!");
		}
	}

	public EmpBLL getEmpBLL() {
		return empBLL;
	}
}
