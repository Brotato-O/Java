package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SupermarketUI {
	private static DefaultTableModel cartModel;
	private static CardLayout cardLayout;
	private static JPanel mainPanel;
	private static JButton activeButton = null;
	private static JFrame frame = new JFrame("QUẢN LÝ CỬA HÀNG SÁCH");

	public static void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
		frame.setLayout(new BorderLayout());

		// Left Sidebar Panel
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(220, 600));
		sidePanel.setBackground(Color.DARK_GRAY);

		// Top Logo
		ImageIcon icon = new ImageIcon("C:/Users/VIET/eclipse-workspace/btjavaswing/src/btjavaswing/logo.png");
		Image img1 = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img1));
		sidePanel.setLayout(new BorderLayout());
		sidePanel.add(imgLabel, BorderLayout.NORTH);

		// Menu Items
		String[] menuItems = { "Bán Hàng", "Quản Lý Sách", "Quản lý loại sách", "Quản Lý Nhân Viên",
				"Quản Lý Khách Hàng", "Nhập & Xuất Sách", "Nhập sách", "Xuất sách", "Giảm Giá", "Thống Kê",
				"Nhà Cung Cấp" };

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(menuItems.length, 1, 0, 0));

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		mainPanel.setBackground(Color.WHITE);

		JButton firstButton = null; // Nút đầu tiên (Bán Hàng)

		for (String menuItem : menuItems) {
			JButton button = new JButton(menuItem);
			button.setFocusPainted(false);
			button.setBackground(Color.DARK_GRAY);
			button.setForeground(Color.WHITE);

			// Tạo panel tương ứng
			JPanel panel = new JPanel();
			panel.add(new JLabel(menuItem));

			button.addActionListener(e -> {
				cardLayout.show(mainPanel, menuItem);
				if (activeButton != null) {
					activeButton.setBackground(Color.DARK_GRAY);
					activeButton.setForeground(Color.WHITE);
				}
				button.setBackground(Color.LIGHT_GRAY);
				button.setForeground(Color.BLACK);
				activeButton = button;
			});

			// Lưu nút "Bán Hàng" làm nút đầu tiên
			if (firstButton == null) {
				firstButton = button;
			}

			menuPanel.add(button);
			mainPanel.add(panel, menuItem);
		}

		// Tạo panel "Bán Hàng"
		JPanel banHangPanel = new JPanel();
		banHangPanel.add(new JLabel("Bán Hàng"));
		banHangPanel.setLayout(new BorderLayout());
		JPanel taoHoaDon = new JPanel();
		taoHoaDon.setLayout(null);
		banHangPanel.add(taoHoaDon, BorderLayout.NORTH);
		JLabel headTaoHD = new JLabel("TẠO HÓA ĐƠN BÁN HÀNG");
		headTaoHD.setForeground(Color.RED);
		headTaoHD.setBounds(250, 0, 250, 100);
		JLabel maHD = new JLabel("Mã HD");
		maHD.setBounds(25, 100, 50, 10);
		JLabel maNV = new JLabel("Mã NV");
		maNV.setBounds(200, 100, 50, 10);
		JLabel maKH = new JLabel("Mã KH");
		maKH.setBounds(400, 100, 50, 10);
		JLabel maGG = new JLabel("Mã GG");
		maGG.setBounds(600, 100, 50, 10);
		JLabel ngayLap = new JLabel("Ngày lập");
		ngayLap.setBounds(25, 160, 50, 20);
		JTextField txtMaHD = new JTextField();
		txtMaHD.setBounds(70, 95, 80, 20);
		JTextField txtMaNV = new JTextField();
		txtMaNV.setBounds(250, 95, 80, 20);
		JTextField txtMaKH = new JTextField();
		txtMaKH.setBounds(450, 95, 80, 20);
		JTextField txtMaGG = new JTextField();
		txtMaGG.setBounds(650, 95, 80, 20);
		JTextField txtNgayLap = new JTextField();
		txtNgayLap.setBounds(80, 160, 100, 20);

		taoHoaDon.add(headTaoHD);
		taoHoaDon.add(maHD);
		taoHoaDon.add(txtMaHD);
		taoHoaDon.add(maNV);
		taoHoaDon.add(txtMaNV);
		taoHoaDon.add(maKH);
		taoHoaDon.add(txtMaKH);
		taoHoaDon.add(maGG);
		taoHoaDon.add(txtMaGG);
		taoHoaDon.add(ngayLap);
		taoHoaDon.add(txtNgayLap);

		banHangPanel.add(taoHoaDon);

		// Tạo panel "Quản Lý Sách"
		JPanel quanLySachPanel = new JPanel();
		quanLySachPanel.add(new JLabel());
		JPanel quanLySach = new JPanel(new GridLayout(4, 1, 2, 20));
		JPanel QLS1 = new JPanel();
		JLabel titelQLS = new JLabel("QUẢN LÝ SÁCH");
		titelQLS.setForeground(Color.RED);
		titelQLS.setBounds(250, 0, 250, 100);
		QLS1.add(titelQLS);
		JPanel QLS2 = new JPanel(new GridLayout(1, 4, 30, 2));
		JPanel txt_QLS2 = new JPanel(new GridLayout(8, 2));
		JLabel maSach = new JLabel("mã sách: ");
		JLabel maNXB = new JLabel("mã NXB: ");
		JLabel maTheLoai = new JLabel("mã thể loại: ");
		JLabel maTacGia = new JLabel("mã tác giả: ");
		JLabel tenSach = new JLabel("tên sách: ");
		JLabel namXuatBan = new JLabel("năm xuất bản: ");
		JLabel soLuong = new JLabel("số lượng: ");
		JLabel donGia = new JLabel("đơn giá: ");
		JTextField txtMaSach = new JTextField();
		JTextField txtMaNXB = new JTextField();
		JTextField txtMaTheLoai = new JTextField();
		JTextField txtMaTacGia = new JTextField();
		JTextField txtTenSach = new JTextField();
		JTextField txtNamXuatBan = new JTextField();
		JTextField txtSoLuong = new JTextField();
		JTextField txtDonGia = new JTextField();

		txt_QLS2.add(maSach);
		txt_QLS2.add(txtMaSach);
		txt_QLS2.add(maNXB);
		txt_QLS2.add(txtMaNXB);
		txt_QLS2.add(maTheLoai);
		txt_QLS2.add(txtMaTheLoai);
		txt_QLS2.add(maTacGia);
		txt_QLS2.add(txtMaTacGia);
		txt_QLS2.add(tenSach);
		txt_QLS2.add(txtTenSach);
		txt_QLS2.add(namXuatBan);
		txt_QLS2.add(txtNamXuatBan);
		txt_QLS2.add(soLuong);
		txt_QLS2.add(txtSoLuong);
		txt_QLS2.add(donGia);
		txt_QLS2.add(txtDonGia);
		QLS2.add(txt_QLS2);

		JPanel btn_QLS2 = new JPanel(new GridLayout(4, 1, 2, 10));
		JButton btnThem = new JButton("Thêm");
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.CYAN);
		JButton btnSua = new JButton("Sửa");
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.blue);
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.red);
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setBackground(Color.green);
		btn_QLS2.add(btnThem);
		btn_QLS2.add(btnSua);
		btn_QLS2.add(btnXoa);
		btn_QLS2.add(btnLamMoi);
		QLS2.add(btn_QLS2);

		JPanel img_QLS2 = new JPanel();
		img_QLS2.setBackground(Color.gray);
		QLS2.add(img_QLS2);

		JPanel in_out_QLS2 = new JPanel(new GridLayout(2, 1, 2, 10));
		JButton inputQLS = new JButton("Nhập Excel");
		JButton outputQLS = new JButton("Xuất Excel");
		in_out_QLS2.add(outputQLS);
		in_out_QLS2.add(inputQLS);
		QLS2.add(in_out_QLS2);

		JPanel QLS3 = new JPanel(new GridLayout(4, 6, 5, 5));
		TitledBorder blackline = BorderFactory.createTitledBorder("Tìm kiếm");
		blackline.setTitleJustification(TitledBorder.CENTER);
		QLS3.setBorder(blackline);
		JLabel maSach1 = new JLabel("mã sách: ");
		JLabel maNXB1 = new JLabel("mã NXB: ");
		JLabel maTheLoai1 = new JLabel("mã thể loại: ");
		JLabel maTacGia1 = new JLabel("mã tác giả: ");
		JLabel tenSach1 = new JLabel("tên sách: ");
		JLabel namXuatBan1 = new JLabel("năm xuất bản: ");
		JLabel khoangGia = new JLabel("Khoảng giá: ");
		JLabel khoangGiaTu = new JLabel("Từ: ");
		JLabel khoangGiaDen = new JLabel("Đến: ");
		JLabel namXBTu = new JLabel("NXB Từ: ");
		JLabel namXBDen = new JLabel("NXB Đến: ");
		JLabel space = new JLabel();
		JLabel space1 = new JLabel();
		JLabel space2 = new JLabel();
		JLabel space3 = new JLabel();

		JTextField txtMaSach1 = new JTextField();
		JTextField txtMaNXB1 = new JTextField();
		JTextField txtMaTheLoai1 = new JTextField();
		JTextField txtMaTacGia1 = new JTextField();
		JTextField txtTenSach1 = new JTextField();
		JTextField txtNamXuatBan1 = new JTextField();
		JTextField txtKhoangGia = new JTextField();
		JTextField txtKhoangGiaTu = new JTextField();
		JTextField txtKhoangGiaDen = new JTextField();
		JTextField txtNXBTu = new JTextField();
		JTextField txtNXBDen = new JTextField();
		QLS3.add(maSach1);
		QLS3.add(txtMaSach1);
		QLS3.add(maNXB1);
		QLS3.add(txtMaNXB1);
		QLS3.add(maTacGia1);
		QLS3.add(txtMaTacGia1);
		QLS3.add(maTheLoai1);
		QLS3.add(txtMaTheLoai1);
		QLS3.add(tenSach1);
		QLS3.add(txtTenSach1);
		QLS3.add(space);
		QLS3.add(space1);
		QLS3.add(khoangGia);
		QLS3.add(space2);
		QLS3.add(khoangGiaTu);
		QLS3.add(txtKhoangGiaTu);
		QLS3.add(khoangGiaDen);
		QLS3.add(txtKhoangGiaDen);
		QLS3.add(namXuatBan1);
		QLS3.add(space3);
		QLS3.add(namXBTu);
		QLS3.add(txtNXBTu);
		QLS3.add(namXBDen);
		QLS3.add(txtNXBDen);

		quanLySach.add(QLS1);
		quanLySach.add(QLS2);
		quanLySach.add(QLS3);
		quanLySachPanel.add(quanLySach);

		// Thêm vào CardLayout
		mainPanel.add(new CustomerManagement(), "Quản Lý Khách Hàng");
		//		mainPanel.add(banHangPanel, "Bán Hàng");
		mainPanel.add(quanLySachPanel, "Quản Lý Sách");
		mainPanel.add(new QLHD(), "Nhập sách");

		sidePanel.add(menuPanel);

		// **Hiển thị trang "Bán Hàng" khi khởi động**
		cardLayout.show(mainPanel, "Bán Hàng");

		// **Làm nổi bật nút "Bán Hàng" khi khởi động**
		if (firstButton != null) {
			firstButton.setBackground(Color.LIGHT_GRAY);
			firstButton.setForeground(Color.BLACK);
			activeButton = firstButton;
		}

		frame.add(sidePanel, BorderLayout.WEST);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public JFrame getSuperMarketUI() {
		return frame;
	}
}
