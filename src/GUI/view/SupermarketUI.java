package GUI.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import DTO.PhanQuyenDTO;
import main.main;

public class SupermarketUI {
	private static DefaultTableModel cartModel;
	private static CardLayout cardLayout;
	private static JPanel mainPanel;
	private static JButton activeButton = null;
	private static JFrame frame = new JFrame("QUẢN LÝ CỬA HÀNG SÁCH");
	private static Map<String, JButton> menuButtons = new HashMap<>();
	
	// Lưu trữ quyền hiện tại
	private static PhanQuyenDTO currentPermissions;
	
	// Map menu items to permission keys
	private static final Map<String, String> menuToPermissionMap = new HashMap<>();
	static {
		// NhapHang: Nhập xuất sách, nhà cung cấp
		menuToPermissionMap.put("Nhập & Xuất Sách", "NhapHang");
		menuToPermissionMap.put("Nhập sách", "NhapHang");
		menuToPermissionMap.put("Xuất sách", "NhapHang");
		menuToPermissionMap.put("Nhà Cung Cấp", "NhapHang");
		
		// QLSanPham: QL loại sách, sách, giảm giá, bán hàng
		menuToPermissionMap.put("Bán Hàng", "QLSanPham");
		menuToPermissionMap.put("Quản Lý Sách", "QLSanPham");
		menuToPermissionMap.put("Quản lý loại sách", "QLSanPham");
		menuToPermissionMap.put("Giảm Giá", "QLSanPham");
		
		// QLNhanVien: Nhân viên, phân quyền
		menuToPermissionMap.put("Quản Lý Nhân Viên", "QLNhanVien");
		menuToPermissionMap.put("Phân Quyền", "QLNhanVien");
		
		// QLKhachHang: Khách hàng
		menuToPermissionMap.put("Quản Lý Khách Hàng", "QLKhachHang");
		
		// ThongKe: Thống kê
		menuToPermissionMap.put("Thống Kê", "ThongKe");
	}

	// Phương thức kiểm tra quyền truy cập menu
	private static boolean hasPermission(String menuItem) {
		if (currentPermissions == null) {
			return false; // Không có quyền nếu chưa đăng nhập
		}
		
		String permissionKey = menuToPermissionMap.get(menuItem);
		if (permissionKey == null) {
			return true; // Menu item không yêu cầu quyền
		}
		
		switch (permissionKey) {
			case "NhapHang":
				return currentPermissions.getNhapHang() == 1;
			case "QLSanPham":
				return currentPermissions.getQlSanPham() == 1;
			case "QLNhanVien":
				return currentPermissions.getQlNhanVien() == 1;
			case "QLKhachHang":
				return currentPermissions.getQlKhachHang() == 1;
			case "ThongKe":
				return currentPermissions.getThongKe() == 1;
			default:
				return false;
		}
	}

	// Phương thức set quyền hiện tại
	public static void setCurrentPermissions(PhanQuyenDTO permissions) {
		currentPermissions = permissions;
		updateMenuVisibility();
	}

	// Phương thức cập nhật hiển thị menu dựa trên quyền
	private static void updateMenuVisibility() {
		for (Map.Entry<String, JButton> entry : menuButtons.entrySet()) {
			String menuItem = entry.getKey();
			JButton button = entry.getValue();
			button.setVisible(hasPermission(menuItem));
		}
	}

	public static void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
		frame.setLayout(new BorderLayout());

		// Left Sidebar Panel
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(220, 600));
		sidePanel.setBackground(Color.DARK_GRAY);

		// System.out.println(SupermarketUI.class.getResource("../../img/logo.png"));

		// Top Logo
		ImageIcon icon = new ImageIcon("src\\img\\logo.png");
		Image img1 = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img1));
		JPanel logoPanel = new JPanel(new BorderLayout());
		logoPanel.setBackground(Color.DARK_GRAY);
		logoPanel.add(imgLabel, BorderLayout.CENTER);
		sidePanel.add(logoPanel);

		// Menu Items
		String[] menuItems = { "Bán Hàng", "Quản Lý Sách", "Quản lý loại sách", "Quản Lý Nhân Viên",
				"Quản Lý Khách Hàng", "Nhập sách", "Xuất sách", "Giảm Giá", 
				"Nhà Cung Cấp","Tải Khoản Bị Khóa","Phân Quyền","Thống Kê"};

		// Menu Panel with BoxLayout	
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setBackground(Color.DARK_GRAY);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		mainPanel.setBackground(Color.WHITE);

		JButton firstButton = null; // Nút đầu tiên (Bán Hàng)
		JPanel thongKeSubPanel = new JPanel();
		thongKeSubPanel.setLayout(new BoxLayout(thongKeSubPanel, BoxLayout.Y_AXIS));
		thongKeSubPanel.setBackground(Color.DARK_GRAY);
		thongKeSubPanel.setVisible(false); // ẩn mặc định

		JButton btnThongKeBanSach = new JButton("Thống kê bán sách");
		btnThongKeBanSach.setFocusPainted(false);
		btnThongKeBanSach.setBackground(Color.GRAY);
		btnThongKeBanSach.setForeground(Color.WHITE);
		btnThongKeBanSach.setMaximumSize(new Dimension(220, 40));
		btnThongKeBanSach.setAlignmentX(JButton.CENTER_ALIGNMENT);
		btnThongKeBanSach.addActionListener(e -> {
			cardLayout.show(mainPanel, "Thống kê bán sách");
			if (activeButton != null) {
				activeButton.setBackground(Color.DARK_GRAY);
				activeButton.setForeground(Color.WHITE);
			}
			btnThongKeBanSach.setBackground(Color.LIGHT_GRAY);
			btnThongKeBanSach.setForeground(Color.BLACK);
			activeButton = btnThongKeBanSach;
		});
		thongKeSubPanel.add(btnThongKeBanSach);

		JButton btnThongKeNhapSach = new JButton("Thống kê nhập sách");
		btnThongKeNhapSach.setFocusPainted(false);
		btnThongKeNhapSach.setBackground(Color.GRAY);
		btnThongKeNhapSach.setForeground(Color.WHITE);
		btnThongKeNhapSach.setMaximumSize(new Dimension(220, 40));
		btnThongKeNhapSach.setAlignmentX(JButton.CENTER_ALIGNMENT);
		btnThongKeNhapSach.addActionListener(e -> {
			cardLayout.show(mainPanel, "Thống kê nhập sách");
			if (activeButton != null) {
				activeButton.setBackground(Color.DARK_GRAY);
				activeButton.setForeground(Color.WHITE);
			}
			btnThongKeNhapSach.setBackground(Color.LIGHT_GRAY);
			btnThongKeNhapSach.setForeground(Color.BLACK);
			activeButton = btnThongKeNhapSach;
		});
		thongKeSubPanel.add(btnThongKeNhapSach);

		JButton btnThongKeKH = new JButton("Thống kê KH");
		btnThongKeKH.setFocusPainted(false);
		btnThongKeKH.setBackground(Color.GRAY);
		btnThongKeKH.setForeground(Color.WHITE);
		btnThongKeKH.setMaximumSize(new Dimension(220, 40));
		btnThongKeKH.setAlignmentX(JButton.CENTER_ALIGNMENT);
		btnThongKeKH.addActionListener(e -> {
			cardLayout.show(mainPanel, "Thống kê KH");
			if (activeButton != null) {
				activeButton.setBackground(Color.DARK_GRAY);
				activeButton.setForeground(Color.WHITE);
			}
			btnThongKeKH.setBackground(Color.LIGHT_GRAY);
			btnThongKeKH.setForeground(Color.BLACK);
			activeButton = btnThongKeKH;
		});
		thongKeSubPanel.add(btnThongKeKH);

		for (String menuItem : menuItems) {
			if (menuItem.equals("Thống Kê")) {
        JButton button = new JButton(menuItem);
        button.setFocusPainted(false);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setMaximumSize(new Dimension(220, 50));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        button.addActionListener(e -> {
            
            thongKeSubPanel.setVisible(!thongKeSubPanel.isVisible());
        });

        menuPanel.add(button);
        //menuButtons.put(menuItem, button);
		menuPanel.add(thongKeSubPanel);
        continue;
    	}
			JButton button = new JButton(menuItem);
			button.setFocusPainted(false);
			button.setBackground(Color.DARK_GRAY);
			button.setForeground(Color.WHITE);
			button.setMaximumSize(new Dimension(220, 50));
			button.setPreferredSize(new Dimension(220, 50));
			button.setAlignmentX(JButton.CENTER_ALIGNMENT);
			
			// Lưu button vào map để quản lý quyền
			menuButtons.put(menuItem, button);

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
			mainPanel.add(new JPanel(), menuItem);
			
		}

		// Thêm vào CardLayout
		mainPanel.add(new TaiKhoanBiKhoa(), "Tải Khoản Bị Khóa");
		mainPanel.add(new EmployeeManagement(), "Quản Lý Nhân Viên");
		mainPanel.add(new CustomerManagement(), "Quản Lý Khách Hàng");
		mainPanel.add(new QLBH(), "Bán Hàng");
		mainPanel.add(new QLS(), "Quản Lý Sách");
		mainPanel.add(new QLLS(), "Quản lý loại sách");
		mainPanel.add(new QLHD(), "Xuất sách");
		mainPanel.add(new QLPN(), "Nhập sách");
		mainPanel.add(new GiamGia(), "Giảm Giá");
		mainPanel.add(new QLNCC(), "Nhà Cung Cấp");
		//mainPanel.add(new ThongKe(), "Thống Kê");
		mainPanel.add(new PhanQuyen(), "Phân Quyền");
		mainPanel.add(new ThongKeBanSach(), "Thống kê bán sách");
		mainPanel.add(new ThongKe(), "Thống kê nhập sách");
		mainPanel.add(new ThongKeTongChi(), "Thống kê KH");

		// Wrap menuPanel in a scroll pane
		JScrollPane scrollPane = new JScrollPane(menuPanel);
		scrollPane.setPreferredSize(new Dimension(220, 500));
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.getViewport().setBackground(Color.DARK_GRAY);
		
		sidePanel.add(menuPanel);

		// Hiển thị trang "Bán Hàng" khi khởi động
		cardLayout.show(mainPanel, "Bán Hàng");

		// Làm nổi bật nút "Bán Hàng" khi khởi động
		if (firstButton != null) {
			firstButton.setBackground(Color.LIGHT_GRAY);
			firstButton.setForeground(Color.BLACK);
			activeButton = firstButton;
		}

		frame.add(sidePanel, BorderLayout.WEST);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		// Cập nhật hiển thị menu dựa trên quyền
		updateMenuVisibility();
	}

	public JFrame getSuperMarketUI() {
		return frame;
	}
}
