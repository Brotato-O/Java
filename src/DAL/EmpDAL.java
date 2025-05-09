package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.EmployeeManagementDTO;

public class EmpDAL {
	getConnection get= new getConnection();

	public boolean xoaVinhVien(String maNV) {
		try {
			Connection conn = get.getConnection();
			String sql = "UPDATE NHANVIEN SET STATUS = 2 WHERE MANV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, maNV);
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean khoiPhucTaiKhoan(String maNV) {
		try {
			Connection conn = get.getConnection();
			String sql = "UPDATE NHANVIEN SET STATUS = 0 WHERE MANV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,	maNV);
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<EmployeeManagementDTO> getListNhanVienBiKhoa() {
		ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<EmployeeManagementDTO>();
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE STATUS = 1";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				EmployeeManagementDTO emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setPassword_emp(res.getString("MK"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}

	public ArrayList<String> getListChucVu() {
		ArrayList<String> listChucVu = new ArrayList<String>();
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT Quyen FROM PHANQUYEN";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				listChucVu.add(res.getString("Quyen"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listChucVu;
	}

	public ArrayList<EmployeeManagementDTO> getDanhSachNhanVien() {
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT * FROM NHANVIEN where STATUS = 0";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<EmployeeManagementDTO>();
			while (res.next()) {
				EmployeeManagementDTO emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setPassword_emp(res.getString("MK"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				emp.setBirth_date(res.getString("ngaysinh"));
				listEmp.add(emp);
			}
			return listEmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EmployeeManagementDTO getNhanVien(String MANV) {
		EmployeeManagementDTO emp = null;
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, MANV);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setPassword_emp(res.getString("MK"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				emp.setBirth_date(res.getString("ngaysinh"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public boolean addNhanVien(EmployeeManagementDTO emp) {
		try {
			Connection conn= get.getConnection();
			String sql = "INSERT INTO NHANVIEN (MANV, TENNV, SDT, LUONG, STATUS, email,mk,  phai, chucvu, ngaysinh) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, emp.getId_emp());
			pre.setString(2, emp.getName_emp());
			pre.setString(3, emp.getPhone_emp());
			pre.setFloat(4, emp.getSalary_emp());
			pre.setInt(5, emp.getStatus_emp());
			pre.setString(6, emp.getEmail_emp());
			pre.setString(7, emp.getPassword_emp());
			pre.setString(8, emp.getGender_emp());
			pre.setString(9, emp.getPosition_emp());
			pre.setString(10, emp.getBirth_date());
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkMaNV(String maNV) {
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT * FROM nhanvien WHERE maNV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, maNV);
			ResultSet res = pre.executeQuery();
			return res.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateNV(EmployeeManagementDTO emp) {
		try {
			Connection conn= get.getConnection();
			String sql = "UPDATE nhanvien SET TENNV = ?, SDT = ?, LUONG = ?, email = ?,MK=?, phai = ?, chucvu = ?, ngaysinh = ? WHERE MANV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, emp.getName_emp());
			pre.setString(2, emp.getPhone_emp());
			pre.setFloat(3, emp.getSalary_emp());
			pre.setString(4, emp.getEmail_emp());
			pre.setString(5, emp.getPassword_emp());
			pre.setString(6, emp.getGender_emp());
			pre.setString(7, emp.getPosition_emp());
			pre.setString(8, emp.getBirth_date());
			pre.setString(9, emp.getId_emp());
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteNV(String maNV) {
		try {
			Connection conn= get.getConnection();
			String sql = "UPDATE NHANVIEN SET STATUS = 1 WHERE MANV = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, maNV);
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<EmployeeManagementDTO> searchSelectBox(String item,String value) {
		ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<EmployeeManagementDTO>();
		try {
			Connection conn= get.getConnection();
			String sql;
			if(item.equals("TENNV")){
				sql = "SELECT * FROM NHANVIEN WHERE SUBSTRING_INDEX(TENNV, ' ', -1) LIKE ?";
			}else {
				sql = "SELECT * FROM NHANVIEN WHERE " + item + " LIKE ?"; 
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,"%" + value + "%");
			ResultSet res = pre.executeQuery();
			while(res.next()) {
				EmployeeManagementDTO emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				emp.setBirth_date(res.getString("ngaysinh"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEmp;
	}

	public ArrayList<EmployeeManagementDTO> searchSalary(float min, float max) {
		ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<>();
		try {
			Connection conn= get.getConnection();
			String sql = "SELECT * FROM NHANVIEN WHERE LUONG BETWEEN ? AND ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setFloat(1, min);
			pre.setFloat(2, max);
			ResultSet res = pre.executeQuery();
			while(res.next()) {
				EmployeeManagementDTO emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setPassword_emp(res.getString("MK"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				emp.setBirth_date(res.getString("ngaysinh"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listEmp;
	}

	public ArrayList<EmployeeManagementDTO> searchAll(String item, String value, float min,float max) {
		ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<>();
		try {
			Connection conn= get.getConnection();
			String sql;
			if(item.equals("TENNV")) {
				sql = "SELECT * FROM NHANVIEN WHERE SUBSTRING_INDEX(TENNV, ' ', -1) LIKE ? AND LUONG BETWEEN ? AND ?";
			}else {
				sql = "SELECT * FROM NHANVIEN WHERE " + item + " LIKE ? AND LUONG BETWEEN ? AND ?"; 
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,"%" + value + "%");
			pre.setFloat(2, min);
			pre.setFloat(3, max);
			ResultSet res = pre.executeQuery();
			while(res.next()) {
				EmployeeManagementDTO emp = new EmployeeManagementDTO();
				emp.setId_emp(res.getString("MANV"));
				emp.setName_emp(res.getString("TENNV"));
				emp.setPhone_emp(res.getString("SDT"));
				emp.setSalary_emp(res.getFloat("LUONG"));
				emp.setStatus_emp(res.getInt("STATUS"));
				emp.setEmail_emp(res.getString("email"));
				emp.setPassword_emp(res.getString("MK"));
				emp.setGender_emp(res.getString("phai"));
				emp.setPosition_emp(res.getString("chucvu"));
				emp.setBirth_date(res.getString("ngaysinh"));
				listEmp.add(emp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listEmp;
	}

}
