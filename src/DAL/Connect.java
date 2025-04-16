package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	// Phương thức kết nối đến cơ sở dữ liệu
	public static Connection getConnection() {
		Connection res = null;
		try {
			// Đăng ký JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/QLBS";
			String userName = "root";
			String password = "3123410278";

			res = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			System.err.println("Không tìm thấy JDBC driver! Lỗi: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Không thể kết nối đến cơ sở dữ liệu! Lỗi: " + e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	// Phương thức đóng kết nối
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = getConnection();
		if (conn != null) {
			System.out.println("Kết nối thành công!");
			closeConnection(conn);
		} else {
			System.err.println("Kết nối thất bại!");
		}
	}
}
