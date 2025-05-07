package GUI.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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


		// Thêm vào CardLayout

//		mainPanel.add(new EmployeeManagement(), "Quản Lý Nhân Viên");
//		mainPanel.add(new CustomerManagement(), "Quản Lý Khách Hàng");
		mainPanel.add(new QLBH(), "Bán Hàng");
		mainPanel.add(new QLS(), "Quản Lý Sách");
		mainPanel.add(new QLLS(), "Quản lý loại sách");
		mainPanel.add(new QLHD(), "Xuất sách");
		mainPanel.add(new QLPN(), "Nhập sách");
		mainPanel.add(new GiamGia(), "Giảm Giá");
		mainPanel.add(new QLNCC(), "Nhà Cung Cấp");
		mainPanel.add(new TK(), "Thống Kê");

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
