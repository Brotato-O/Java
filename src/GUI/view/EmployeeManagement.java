package GUI.view;

import BLL.EmpBLL;
import BLL.PhanQuyenBLL;
import DTO.EmployeeManagementDTO;
import GUI.controller.EmpController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import GUI.view.PhanQuyen;


public class EmployeeManagement extends JPanel {

	private JTextField idEmp, txtEmployeeName, txtEmail, txtSDT, txtLuong, txtPassword;
	private JRadioButton rdiNam, rdiNu;
	private JTextField txtNgaySinh;
	private JComboBox<String> jcbChucVu;
	//Fields Button
	private JButton jbThem, jbSua, jbXoa, jbNhapExcel, jbXuatExcel,jblammoi;
	//Fields Form Search
	private JComboBox<String> jcbMaNV;
	private JTextField jtfMaNV, jtfLuong1, jtfLuong2;
	private JButton jbSearchMaNV, jbSearchLuong, jbAll;
	//Tabel
	private JTable table;
	private JScrollPane scrollPane;

	private PhanQuyenBLL phanQuyenBLL = new PhanQuyenBLL();
	private PhanQuyen phanQuyen;

	public EmployeeManagement() {
		setLayout(new BorderLayout(10, 10)); // Add gap between components
		setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
		add(Header(), BorderLayout.NORTH);
		add(Middle(), BorderLayout.CENTER);
		add(Footer(), BorderLayout.SOUTH);
		
		idEmp.setEditable(false);
		jbSua.setEnabled(false);
		jbXoa.setEnabled(false);
		// Khởi tạo controller
		
		new EmpController(this);
    	phanQuyen = new PhanQuyen();
    	phanQuyen.setEmployeeManagement(this);
		
		setVisible(true);
	}

	private JPanel Header() {
		JPanel res = new JPanel(new BorderLayout(10, 0));
		res.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Thêm tiêu đề
		JLabel titleLabel = new JLabel("QUẢN LÝ NHÂN VIÊN", JLabel.CENTER);
		titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Thêm khoảng cách dưới tiêu đề
		res.add(titleLabel, BorderLayout.NORTH);

		// Panel chứa form và buttons
		JPanel contentPanel = new JPanel(new BorderLayout(10, 0));

		//Add header left
		JPanel formPanel = FormInformation();
		formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông Tin Nhân Viên",
				TitledBorder.CENTER, // Căn giữa tiêu đề
				TitledBorder.TOP));
		contentPanel.add(formPanel, BorderLayout.CENTER);

		//Add header right
		JPanel buttonPanel = ButtonHeader();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		contentPanel.add(buttonPanel, BorderLayout.EAST);

		res.add(contentPanel, BorderLayout.CENTER);
		return res;
	}

	private JPanel Middle() {
		JPanel res = new JPanel(new GridBagLayout());
		res.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tìm Kiếm Nâng Cao",
				TitledBorder.CENTER, // Căn giữa tiêu đề
				TitledBorder.TOP));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Tăng khoảng cách giữa các components
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Dòng 1
		String[] maNVOptions = { "MANV", "TENNV", "CHUCVU" };
		jcbMaNV = new JComboBox<>(maNVOptions);
		jcbMaNV.setPreferredSize(new Dimension(100, 25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		res.add(jcbMaNV, gbc);

		jtfMaNV = addTextField(res, gbc, 1, 0);
		res.add(jtfMaNV, gbc);

		jbSearchMaNV = addButton(res, gbc, 3, 0, "Tìm Kiếm", 100);
		jbAll = addButton(res, gbc, 4, 0, "Tất Cả", 100);

		// Dòng 2
		addLabel(res, "Lương :", gbc, 0, 1);

		jtfLuong1 = addTextField(res, gbc, 1, 1);
		res.add(jtfLuong1, gbc);

		jtfLuong2 = addTextField(res, gbc, 2, 1);
		res.add(jtfLuong2, gbc);
		jbSearchLuong = addButton(res, gbc, 3, 1, "Tìm Kiếm", 100);

		jbSearchMaNV.setBackground(new java.awt.Color(0, 102, 204)); // Xanh dương (#0066CC)
		jbSearchMaNV.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

		jbAll.setBackground(new java.awt.Color(0, 153, 0)); // Xanh lá (#009900)
		jbAll.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

		jbSearchLuong.setBackground(new java.awt.Color(0, 102, 204)); // Xanh dương (#0066CC)
		jbSearchLuong.setForeground(new java.awt.Color(255, 255, 255)); // Trắng-

		return res;
	}

	private JPanel Footer() {
		JPanel res = new JPanel(new BorderLayout());
		res.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		//separator
		JSeparator separator = new JSeparator();
		res.add(separator, BorderLayout.NORTH);
		
		//Table
		String[] columnNames = { "Mã NV", "Tên nhân viên", "Số điện thoại", "Email", "Password","Phái", "Chức vụ", "Lương",
				"Ngày sinh" };

		EmpBLL empBLL = new EmpBLL();
		ArrayList<EmployeeManagementDTO> listNV = empBLL.getDS();
		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		for (EmployeeManagementDTO nv : listNV) {
			Object[] row = new Object[] { 
					nv.getId_emp(), 
					nv.getName_emp(), 
					nv.getPhone_emp(), 
					nv.getEmail_emp(),
					nv.getPassword_emp(),
					nv.getGender_emp(),
					nv.getPosition_emp(),
					nv.getSalary_emp(),
					nv.getBirth_date()
			};
			model.addRow(row);
		}

		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(800, 200));
		res.add(scrollPane, BorderLayout.CENTER);
		return res;
	}

	//Left header
	public JPanel FormInformation() {
		JPanel res = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	
		// Dòng 1
		addLabel(res, "Mã Nhân Viên:", gbc, 0, 0);
		this.idEmp = addTextField(res, gbc, 1, 0);
		addLabel(res, "SĐT:", gbc, 2, 0);
		this.txtSDT = addTextField(res, gbc, 3, 0);
	
		// Dòng 2
		addLabel(res, "Tên Nhân Viên:", gbc, 0, 1);
		this.txtEmployeeName = addTextField(res, gbc, 1, 1);
		addLabel(res, "Lương:", gbc, 2, 1);
		this.txtLuong = addTextField(res, gbc, 3, 1);
	
		// Dòng 3
		addLabel(res, "Chức Vụ:", gbc, 0, 2);

		this.jcbChucVu = new JComboBox<String>();
		gbc.gridx = 1;
		gbc.gridy = 2;
		res.add(jcbChucVu, gbc);
		
		// Khởi tạo dữ liệu cho combobox chức vụ
		loadDanhSachQuyen();

		addLabel(res, "Giới Tính:", gbc, 2, 2);
		JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.rdiNam = new JRadioButton("Nam");
		this.rdiNu = new JRadioButton("Nữ");
		ButtonGroup bgGioiTinh = new ButtonGroup();
		bgGioiTinh.add(rdiNam);
		bgGioiTinh.add(rdiNu);
		pnlGender.add(rdiNam);
		pnlGender.add(rdiNu);
		gbc.gridx = 3;
		res.add(pnlGender, gbc);
	
		// Dòng 4
		addLabel(res, "Email:", gbc, 0, 3);
		this.txtEmail = addTextField(res, gbc, 1, 3);
		addLabel(res, "Ngày Sinh:", gbc, 2, 3);
		this.txtNgaySinh = addTextField(res, gbc, 3, 3);
	
		// Dòng 5: Thêm trường mật khẩu
		addLabel(res, "Mật Khẩu:", gbc, 0, 4);
		this.txtPassword = addTextField(res, gbc, 1, 4);
	
		return res;
	}

	//Right header
	public JPanel ButtonHeader() {
		JPanel res = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 10, 8, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		int col1Width = 100;
		int col2Width = 130;
		int col3Width = 150;

		//Dòng 1
		jbThem = addButton(res, gbc, 0, 0, "Thêm", col1Width);
		jbNhapExcel = addButton(res, gbc, 1, 0, "Nhập Excel", col2Width);

		//Dòng 2
		jbSua = addButton(res, gbc, 0, 1, "Sửa", col1Width);
		jbXuatExcel = addButton(res, gbc, 1, 1, "Xuất Excel", col2Width);

		//Dòng 3
		jbXoa = addButton(res, gbc, 0, 2, "Xóa", col1Width);
		jblammoi = addButton(res, gbc, 1, 2, "Làm Mới", col3Width);		

		jbXuatExcel.setPreferredSize(new Dimension(150, 35));
		
		// Nút Thêm - màu xanh lá cây
jbThem.setBackground(new java.awt.Color(0, 153, 0));     // Xanh lá (#009900)
jbThem.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

// Nút Sửa - màu xanh dương
jbSua.setBackground(new java.awt.Color(0, 102, 204));    // Xanh dương (#0066CC)
jbSua.setForeground(new java.awt.Color(255, 255, 255));  // Trắng

// Nút Xóa - màu đỏ (giữ nguyên)
jbXoa.setBackground(new java.awt.Color(255, 0, 0));      // Đỏ (#FF0000)
jbXoa.setForeground(new java.awt.Color(255, 255, 255));  // Trắng

// Nút Nhập Excel - màu tím
jbNhapExcel.setBackground(new java.awt.Color(102, 0, 153)); // Tím (#660099)
jbNhapExcel.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

// Nút Xuất Excel - màu cam
jbXuatExcel.setBackground(new java.awt.Color(255, 153, 0)); // Cam (#FF9900)
jbXuatExcel.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

// Nút Làm mới - màu xanh ngọc
jblammoi.setBackground(new java.awt.Color(0, 153, 153)); // Xanh ngọc (#009999)
jblammoi.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

		return res;
	}

	private void addLabel(JPanel panel, String text, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		JLabel label = new JLabel(text);
		label.setPreferredSize(new Dimension(100, 25));
		panel.add(label, gbc);
	}

	private JTextField addTextField(JPanel panel, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		JTextField textField = new JTextField(15);
		textField.setMinimumSize(new Dimension(100, 25));
		panel.add(textField, gbc);
		return textField;
	}

	private JButton addButton(JPanel panel, GridBagConstraints gbc, int x, int y, String text, int width) {
		gbc.gridx = x;
		gbc.gridy = y;
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(width, 35));
		panel.add(button, gbc);
		return button;
	}

	public JTable getTableListEmp() {
		return this.table;
	}
	
	public JTextField getMaNV() {
		return idEmp;
	}

	public JTextField getTenNV() {
		return txtEmployeeName;
	}

	public JTextField getSdt() {
		return txtSDT;
	}

	public JTextField getEmail() {
		return txtEmail;
	}

	public JTextField getPassword() {
		return txtPassword;
	}

	public JTextField getLuong() {
		return txtLuong;
	}

	public JTextField getNgaySinh() {
		return txtNgaySinh;
	}

	//True là nam, false là nữ
	public JRadioButton getRdiNam() {
		return rdiNam;
	}

	public JRadioButton getRdiNu()	{
		return rdiNu;
	}

	public JButton getBtnThem() {
		return jbThem;
	}

	public JButton getBtnSua() {
		return jbSua;
	}

	public JButton getBtnXoa() {
		return jbXoa;
	}

	//Tìm kiếm
	public JComboBox<String> getTimKiemMaNV() {
		return jcbMaNV;
	}

	public JButton getTimKiemComboBox() {
		return jbSearchMaNV;
	}

	public JTextField getTimKiemMaNVTextField() {
		return jtfMaNV;
	}

	public JButton getTimKiemLuong() {
		return jbSearchLuong;
	}
	public JButton getbtnLammoi() {
		return jblammoi;
	}
	public JTextField getTimKiemLuong1() {
		return jtfLuong1;
	}	
	public JTextField getTimKiemLuong2() {
		return jtfLuong2;
	}

	public JButton getBtnTatca(){
		return jbAll;
	}

	public JButton getBtnXuatExcel() {
		return jbXuatExcel;
	}

	public JButton getBtnNhapExcel() {
		return jbNhapExcel;
	}

	//Thêm mới
	public JComboBox<String> getJcbChucVu() {
		return jcbChucVu;
	}

	public void loadDanhSachQuyen() {
        // Lưu lại quyền đang chọn
        String selectedQuyen = jcbChucVu.getItemCount() > 0 ? (String) jcbChucVu.getSelectedItem() : null;
        
        // Đọc lại danh sách quyền từ database
        ArrayList<String> dsQuyen = phanQuyenBLL.getDSQuyen();
        
        // Xóa tất cả item trong combo box
        jcbChucVu.removeAllItems();
        
        // Thêm lựa chọn mặc định
        jcbChucVu.addItem("Chọn quyền");
        
        // Load dữ liệu vào combo box
        if (dsQuyen != null && !dsQuyen.isEmpty()) {
            for (String quyen : dsQuyen) {
                jcbChucVu.addItem(quyen);
            }
        }
        
        // Nếu có quyền đã chọn trước đó, chọn lại
        if (selectedQuyen != null) {
            for (int i = 0; i < jcbChucVu.getItemCount(); i++) {
                if (selectedQuyen.equals(jcbChucVu.getItemAt(i))) {
                    jcbChucVu.setSelectedIndex(i);
                    return;
                }
            }
        }
        
        // Mặc định chọn item đầu tiên
        jcbChucVu.setSelectedIndex(0);

    }

}