package GUI.controller;

import BLL.*;
import DTO.EmployeeManagementDTO;
import GUI.dialog.QLNV.AddQLNVController;
import GUI.dialog.QLNV.AddQLNVDialog;
import GUI.view.EmployeeManagement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EmpController {

	private EmployeeManagement view = null;
	JTable table;
	JButton btnThem,btnSua,btnXoa,btnSeachCbb,btnSearchSalary,btnSearchAll;
	DefaultTableModel model;
	private EmpBLL empBLL = new EmpBLL();
	private boolean isLoadingData = false;//flag load dữ liệu

	public EmpController(EmployeeManagement view) {
		this.view = view;
		this.table = view.getTableListEmp();
		this.model = (DefaultTableModel) table.getModel();
		
		this.btnThem = view.getBtnThem();
		this.btnSua = view.getBtnSua();
		this.btnXoa = view.getBtnXoa();
		this.btnSeachCbb = view.getTimKiemComboBox();
		this.btnSearchSalary = view.getTimKiemLuong();
		this.btnSearchAll = view.getBtnTatca();

		// Thêm sự kiện MouseListener vào JTable
		addTableMouseListener();

		btnClickShowDialogAdd();
		btnClickUpdateEmp();
		btnClickDeleteEmp();

		//Tìm kiếm
		searchComboxBoxEmp();
		searchSalaryEmp();
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
				btnSua.setEnabled(false);
		
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
						btnXoa.setEnabled(true);
					}
				}
			}
		});
	}
	
	/**
	 * Xử lý dữ liệu nhân viên và gửi đến view để hiển thị
	 */
	private void displayEmployeeData(EmployeeManagementDTO emp) {
		isLoadingData = true; // Bắt đầu load dữ liệu
    	view.getMaNV().setText(emp.getId_emp());
    	view.getTenNV().setText(emp.getName_emp());
    	view.getSdt().setText(emp.getPhone_emp());
    	view.getEmail().setText(emp.getEmail_emp());
    	view.getChucVu().setText(emp.getPosition_emp());
    	view.getLuong().setText(String.valueOf(emp.getSalary_emp()));
    	view.getNgaySinh().setText(emp.getBirth_date());
		if ("Nam".equals(emp.getGender_emp())) {
			view.getRdiNam().setSelected(true); // Chọn "Nam"
		} else {
			view.getRdiNu().setSelected(true); // Chọn "Nữ"
		}
    	isLoadingData = false; // Kết thúc load dữ liệu
	}
	
	public void refreshTable() {
		try {
			ArrayList<EmployeeManagementDTO> listEmp = empBLL.getDS();
			loadDataModel(listEmp);
	
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

	private void updateEmployee(EmployeeManagementDTO emp) {
		if(empBLL.updateNV(emp)) {
			JOptionPane.showMessageDialog(view,"Cập nhật nhân viên thành công","Thông báo" ,JOptionPane.INFORMATION_MESSAGE);
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(view,"Cập nhật nhân viên thất bại","Lỗi" ,JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnClickUpdateEmp() {

		 // Thêm DocumentListener vào các JTextField
		 addFieldChangeListener(view.getMaNV());
		 addFieldChangeListener(view.getTenNV());
		 addFieldChangeListener(view.getSdt());
		 addFieldChangeListener(view.getEmail());
		 addFieldChangeListener(view.getChucVu());
		 addFieldChangeListener(view.getLuong());
		 addFieldChangeListener(view.getNgaySinh());

		btnSua.addActionListener(e -> {

			int confirm = JOptionPane.showConfirmDialog(
            	view,
            	"Bạn có chắc chắn muốn cập nhật thông tin nhân viên này không?",
            	"Xác nhận cập nhật",
            	JOptionPane.YES_NO_OPTION
        	);

        	// Nếu người dùng chọn "No", dừng quá trình
        	if (confirm == JOptionPane.NO_OPTION) {
            	return;
        	}

			String maNV = view.getMaNV().getText();
			String tenNV = view.getTenNV().getText();
			String sdt = view.getSdt().getText();
			String email = view.getEmail().getText();
			String chucVu = view.getChucVu().getText();
			float luong = Float.parseFloat(view.getLuong().getText());
			String ngaySinh = view.getNgaySinh().getText();
			boolean gioiTinh = view.getRdiNam().isSelected();

			if(!checkBirthDay(ngaySinh)) {
				return;
			}

			// Tạo đối tượng EmployeeManagementDTO với thông tin đã lấy
			EmployeeManagementDTO emp = new EmployeeManagementDTO(maNV, tenNV, sdt, email, chucVu, luong, ngaySinh, gioiTinh);

			// Gọi phương thức updateEmployee để cập nhật thông tin nhân viên
			updateEmployee(emp);

			btnSua.setEnabled(false); // Vô hiệu hóa nút btnSua sau khi cập nhật	
		});
	}

	//DocumentListener lắng nghe sự kiện thay đổi nội dung của các JTextField 
	private void addFieldChangeListener(JTextField textField) {
		textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				if (!isLoadingData) {
					btnSua.setEnabled(true); // Kích hoạt nút btnSua khi có thay đổi
				}
			}
	
			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				if (!isLoadingData) {
					btnSua.setEnabled(true); // Kích hoạt nút btnSua khi có thay đổi
				}
			}
	
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				if (!isLoadingData) {
					btnSua.setEnabled(true); // Kích hoạt nút btnSua khi có thay đổi
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
                JOptionPane.showMessageDialog(this.view, "Ngày sinh không lớn hay ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true; // Ngày hợp lệ
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this.view, "Ngày sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

	private void deleteEmpDialog(String maNV) {
		if(empBLL.deleteNV(maNV)) {
			JOptionPane.showMessageDialog(view,"Xóa nhân viên thành công","Thông báo" ,JOptionPane.INFORMATION_MESSAGE);
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(view,"Xóa nhân viên thất bại","Lỗi" ,JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnClickDeleteEmp() {
		btnXoa.addActionListener(e -> {
			String maNV = this.view.getMaNV().getText();
			int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhân viên này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);			
			if (confirm == JOptionPane.YES_OPTION) {
				deleteEmpDialog(maNV);
			}
		});
	}

	private void searchComboxBoxEmp() {
		btnSeachCbb.addActionListener(e -> {
			String selectedItem = view.getTimKiemMaNV().getSelectedItem().toString();
			String value = view.getTimKiemMaNVTextField().getText();
			ArrayList<EmployeeManagementDTO> listSearch = empBLL.searchComboBox(selectedItem, value);
			if (listSearch != null) {
				loadDataModel(listSearch);
			}	
		});
	}

	private void searchSalaryEmp() {
		this.btnSearchSalary.addActionListener(e -> {
			String luong1 = view.getTimKiemLuong1().getText();
			String luong2 = view.getTimKiemLuong2().getText();
			float luongMin = Float.parseFloat(luong1);
			float luongMax = Float.parseFloat(luong2);
			ArrayList<EmployeeManagementDTO> listSearch = empBLL.searchLuong(luongMin, luongMax);
			if (listSearch != null) {
				loadDataModel(listSearch);
			}
		});
	}

	public EmpBLL getEmpBLL() {
		return empBLL;
	}

	private void loadDataModel(ArrayList<EmployeeManagementDTO> list) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ trong bảng
			for (EmployeeManagementDTO emp : list) {
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
	}

}
