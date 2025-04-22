package GUI.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DTO.EmployeeManagementDTO;
import GUI.view.EmployeeManagement;

public class EmpController {

	private EmpBLL empBLL = new EmpBLL();
	private EmployeeManagement view = null;
	JTable table;
	DefaultTableModel model;

	public EmpController(EmployeeManagement view) {
		this.view = view;
		this.table = view.getTableListEmp();
		this.model = (DefaultTableModel) table.getModel();
		
		// Thêm sự kiện MouseListener vào JTable
		addTableMouseListener();
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
}
