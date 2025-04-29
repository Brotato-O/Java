package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.EmployeeManagementDTO;

public class EmpDAL {

	public ArrayList<EmployeeManagementDTO> getDanhSachNhanVien() {
		try {
			String sql = "SELECT * FROM NHANVIEN";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			ArrayList<EmployeeManagementDTO> listEmp = new ArrayList<EmployeeManagementDTO>();
			while (res.next()) {
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
			return listEmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EmployeeManagementDTO getNhanVien(String MANV) {
		EmployeeManagementDTO emp = null;
		try {
			String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
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
			String sql = "INSERT INTO NHANVIEN (MANV, TENNV, SDT, LUONG, STATUS, email, phai, chucvu, ngaysinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
			pre.setString(1, emp.getId_emp());
			pre.setString(2, emp.getName_emp());
			pre.setString(3, emp.getPhone_emp());
			pre.setFloat(4, emp.getSalary_emp());
			pre.setInt(5, emp.getStatus_emp());
			pre.setString(6, emp.getEmail_emp());
			pre.setString(7, emp.getGender_emp());
			pre.setString(8, emp.getPosition_emp());
			pre.setString(9, emp.getBirth_date());
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkMaNV(String maNV) {
		try {
			String sql = "SELECT * FROM nhanvien WHERE maNV = ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
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
			String sql = "UPDATE nhanvien SET TENNV = ?, SDT = ?, LUONG = ?, email = ?, phai = ?, chucvu = ?, ngaysinh = ? WHERE MANV = ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
			pre.setString(1, emp.getName_emp());
			pre.setString(2, emp.getPhone_emp());
			pre.setFloat(3, emp.getSalary_emp());
			pre.setString(4, emp.getEmail_emp());
			pre.setString(5, emp.getGender_emp());
			pre.setString(6, emp.getPosition_emp());
			pre.setString(7, emp.getBirth_date());
			pre.setString(8, emp.getId_emp());
			return pre.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteNV(String maNV) {
		try {
			String sql = "DELETE FROM nhanvien WHERE MANV = ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
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
			String sql = "SELECT * FROM NHANVIEN WHERE " + item + " LIKE ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
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
			String sql = "SELECT * FROM NHANVIEN WHERE LUONG BETWEEN ? AND ?";
			PreparedStatement pre = Connect.getConnection().prepareStatement(sql);
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
