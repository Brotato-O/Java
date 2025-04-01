package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import main.main;

public class CustomerManagement extends JPanel {

	Dimension d = new Dimension(Integer.MAX_VALUE, 25);
	int height = main.height;
	int width = main.width;
	//Fields	

	private JTextField textField;
	private JPanel headerContent, jlabelRight;
	private JTable tableCus;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField txtMaKH, txtHo, txtTen, txtEmail, txtSDT, txtTongChi, txtNgaySinh;
	private JRadioButton rdoNam, rdoNu;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					CustomerManagement window = new CustomerManagement();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public CustomerManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Set up the main panel layout
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(817, 523));

		JLabel titelLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titelLabel, BorderLayout.NORTH);

		JPanel middleJPanel = new JPanel();
		add(middleJPanel, BorderLayout.CENTER);
		middleJPanel.setLayout(new BorderLayout(0, 0));

		headerContent = new JPanel();
		middleJPanel.add(headerContent, BorderLayout.NORTH);
		headerContent.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel countCusJlb = new JLabel("Số lượng khách hàng: ");
		headerContent.add(countCusJlb);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("10");
		textField.setPreferredSize(new Dimension(50, 25)); // Chiều rộng 50px, chiều cao 25px
		textField.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa số
		textField.setFocusable(false); // Mất dấu nháy (không thể focus)

		headerContent.add(textField);

		String[] columnNames = { "Mã KH", "Tên KH", "SĐT", "Email", "Phái", "Tổng chi", "Ngày Sinh" };
		Object[][] data = { { "KH001", "Nguyễn Văn A", "0987654321", "a@gmail.com", "Nam", "100.000", "01/01/1990" },
				{ "KH002", "Trần Thị B", "0912345678", "b@gmail.com", "Nữ", "200.000", "02/02/1992" },
				{ "KH003", "Lê Văn C", "0933456789", "c@gmail.com", "Nam", "150.000", "03/03/1993" },
				{ "KH004", "Phạm Thị D", "0971234567", "d@gmail.com", "Nữ", "300.000", "04/04/1994" },
				{ "KH005", "Hoàng Văn E", "0956789012", "e@gmail.com", "Nam", "250.000", "05/05/1995" },
				{ "KH006", "Bùi Thị F", "0945678901", "f@gmail.com", "Nữ", "350.000", "06/06/1996" },
				{ "KH007", "Đỗ Văn G", "0934567890", "g@gmail.com", "Nam", "400.000", "07/07/1997" },
				{ "KH008", "Ngô Thị H", "0923456789", "h@gmail.com", "Nữ", "450.000", "08/08/1998" },
				{ "KH009", "Đặng Văn I", "0912345678", "i@gmail.com", "Nam", "500.000", "09/09/1999" },
				{ "KH010", "Trịnh Thị J", "0901234567", "j@gmail.com", "Nữ", "550.000", "10/10/2000" },
				{ "KH011", "Lý Văn K", "0981122334", "k@gmail.com", "Nam", "600.000", "11/11/2001" },
				{ "KH012", "Tô Thị L", "0972233445", "l@gmail.com", "Nữ", "650.000", "12/12/2002" },
				{ "KH013", "Dương Văn M", "0963344556", "m@gmail.com", "Nam", "700.000", "13/01/2003" },
				{ "KH014", "Tạ Thị N", "0954455667", "n@gmail.com", "Nữ", "750.000", "14/02/2004" },
				{ "KH015", "Hà Văn O", "0945566778", "o@gmail.com", "Nam", "800.000", "15/03/2005" }, };

		// Tạo bảng có tiêu đề
		tableCus = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(tableCus);
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
		middleJPanel.add(scrollPane, BorderLayout.CENTER);

		jlabelRight = new JPanel();
		middleJPanel.add(jlabelRight, BorderLayout.EAST);
		jlabelRight.setPreferredSize(new Dimension(150, 80)); // Để tránh bị kéo dài

		// Chuyển sang FlowLayout để tránh nút bị kéo dãn
		jlabelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton xuatExcelBtn = new JButton("Xuất Excel");
		JButton nhapExcelBtn = new JButton("Nhập Excel");

		// Đặt kích thước nhỏ hơn cho các nút
		xuatExcelBtn.setPreferredSize(new Dimension(120, 30));
		nhapExcelBtn.setPreferredSize(new Dimension(120, 30));

		jlabelRight.add(xuatExcelBtn);
		jlabelRight.add(nhapExcelBtn);

		JPanel footerJPanel = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder("NHẬP THÔNG TIN");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		footerJPanel.setBorder(titledBorder);
		footerJPanel.setLayout(new GridLayout(1, 2, 10, 0));

		//Đang code phần bên trái cái form
		JPanel footerLeft = new JPanel();
		footerJPanel.add(footerLeft, BorderLayout.WEST); // Thêm footerLeft vào footerJPanel
		footerLeft.setLayout(new BorderLayout());

		JPanel pnlInput = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		//Row 1
		addLabel(pnlInput, "Mã KH:", gbc, 0, 0);
		txtMaKH = addTextField(pnlInput, gbc, 1, 0);
		addLabel(pnlInput, "Phái:", gbc, 2, 0);
		JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JRadioButton rdNam = new JRadioButton("Nam");
		JRadioButton rdNu = new JRadioButton("Nữ");
		ButtonGroup bgGioiTinh = new ButtonGroup();
		bgGioiTinh.add(rdNam);
		bgGioiTinh.add(rdNu);
		pnlGender.add(rdNam);
		pnlGender.add(rdNu);
		gbc.gridx = 3;
		pnlInput.add(pnlGender, gbc);

		//Row 2
		addLabel(pnlInput, "Họ:", gbc, 0, 1);
		txtHo = addTextField(pnlInput, gbc, 1, 1);
		addLabel(pnlInput, "Tổng Chi:", gbc, 2, 1);
		txtTongChi = addTextField(pnlInput, gbc, 3, 1);

		//Row 3
		addLabel(pnlInput, "Tên:", gbc, 0, 2);
		txtTen = addTextField(pnlInput, gbc, 1, 2);
		addLabel(pnlInput, "Ngày sinh:", gbc, 2, 2);
		txtNgaySinh = new JTextField(15);
		gbc.gridx = 3;
		gbc.gridy = 2;
		pnlInput.add(txtNgaySinh, gbc);

		// Row 4
		addLabel(pnlInput, "Email:", gbc, 0, 3);
		txtEmail = addTextField(pnlInput, gbc, 1, 3);

		// Row 5
		addLabel(pnlInput, "SĐT:", gbc, 0, 4);
		txtSDT = addTextField(pnlInput, gbc, 1, 4);

		footerLeft.add(pnlInput, BorderLayout.WEST);

		JPanel jplEven = new JPanel();
		jplEven.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		footerLeft.add(jplEven, BorderLayout.SOUTH);
		JButton btnThem = new JButton("THÊM");
		JButton btnXoa = new JButton("XÓA");
		JButton btnSua = new JButton("SỬA");
		JButton btnLamMoi = new JButton("LÀM MỚI");
		jplEven.add(btnThem);
		jplEven.add(btnXoa);
		jplEven.add(btnSua);
		jplEven.add(btnLamMoi);

		//Phần bên phải footer
		JPanel footerRight = new JPanel();
		TitledBorder searchBorder = BorderFactory.createTitledBorder("TÌM KIẾM");
		searchBorder.setTitleJustification(TitledBorder.CENTER);
		footerRight.setBorder(searchBorder);
		footerRight.setLayout(new GridLayout(4, 1, 5, 5));

		// Dòng 1: ComboBox tìm kiếm
		JPanel pnlSearchRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JComboBox cboTimKiem = new JComboBox<>(new String[] { "Mã Khách Hàng" });
		cboTimKiem.setPreferredSize(new Dimension(200, 25));
		pnlSearchRow1.add(cboTimKiem);

		// Dòng 2: Radio buttons Nam/Nữ
		JPanel pnlSearchRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JRadioButton rdTimNam = new JRadioButton("Nam");
		JRadioButton rdTimNu = new JRadioButton("Nữ");
		ButtonGroup bgTimKiem = new ButtonGroup();
		bgTimKiem.add(rdTimNam);
		bgTimKiem.add(rdTimNu);
		pnlSearchRow2.add(rdTimNam);
		pnlSearchRow2.add(rdTimNu);

		// Dòng 3: TextField tìm kiếm
		JPanel pnlSearchRow3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextField txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(200, 25));
		pnlSearchRow3.add(txtTimKiem);

		// Dòng 4: Nút tìm kiếm
		JPanel pnlSearchRow4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnTimKiem = new JButton("Tìm kiếm");
		pnlSearchRow4.add(btnTimKiem);

		footerRight.add(pnlSearchRow1);
		footerRight.add(pnlSearchRow2);
		footerRight.add(pnlSearchRow3);
		footerRight.add(pnlSearchRow4);

		footerJPanel.add(footerRight);

		add(footerJPanel, BorderLayout.SOUTH);
	}

	private void addLabel(JPanel panel, String text, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(new JLabel(text), gbc);
	}

	private JTextField addTextField(JPanel panel, GridBagConstraints gbc, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		JTextField textField = new JTextField(15);
		panel.add(textField, gbc);
		return textField;
	}

}
