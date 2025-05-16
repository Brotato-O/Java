package BLL;

import DAL.EmpDAL;
import DTO.EmployeeManagementDTO;
import java.util.ArrayList;

public class EmpBLL {

	private EmpDAL empDAL = new EmpDAL(); //Lấy sử lí database
	private ArrayList<EmployeeManagementDTO> listEmpDTO = null; //Thay đổi dữ liệu đồ

	public EmpBLL() {
		this.listEmpDTO = getDS();
	}

	public boolean checkTaiKhoanKhoa(String maNV) {
		return this.empDAL.checkTaiKhoanKhoa(maNV);
	}

	public boolean checkTaiKhoanXoa(String maNV) {
		return this.empDAL.checkTaiKhoanXoa(maNV);
	}

	public boolean xoaVinhVien(String maNV) {
		return this.empDAL.xoaVinhVien(maNV);
	}

	public boolean khoiPhucTaiKhoan(String maNV) {
		return this.empDAL.khoiPhucTaiKhoan(maNV);
	}

	public ArrayList<EmployeeManagementDTO> getListNhanVienBiKhoa() {
		return this.empDAL.getListNhanVienBiKhoa();
	}

	public ArrayList<String> getListChucVu() {
		return this.empDAL.getListChucVu();
	}

	public ArrayList<EmployeeManagementDTO> getDS() {
		return this.empDAL.getDanhSachNhanVien();
	}

	public EmployeeManagementDTO getNV(String maNV) {
		return this.empDAL.getNhanVien(maNV);
	}

	public boolean addNV(EmployeeManagementDTO empDTO) {
		if (this.empDAL.checkMaNV(empDTO.getId_emp())) {
			// System.out.println("Mã nhân viên đã tồn tại: " + empDTO.getId_emp());
			return false; 
		}
		return this.empDAL.addNhanVien(empDTO);
	}

	public boolean checkID(String maNV) {
		return this.empDAL.checkMaNV(maNV);
	}

	public boolean checkLogin(String maNV, String password) {
		EmployeeManagementDTO emp = this.empDAL.getNhanVien(maNV);
		if (emp != null && emp.getPassword_emp().equals(password)) {
			return true;
		}
		return false;
	}

	public String getChucVu(String maNV) {
		EmployeeManagementDTO emp = this.empDAL.getNhanVien(maNV);
		if (emp != null) {
			return emp.getPosition_emp();
		}
		return null;
	}

	public boolean updateNV(EmployeeManagementDTO empDTO) {
		return this.empDAL.updateNV(empDTO);
	}

	public boolean deleteNV(String maNV) {
		return this.empDAL.deleteNV(maNV);
	}

	public boolean resetChucVuByQuyen(String quyen) {
		return this.empDAL.resetChucVuByQuyen(quyen);
	}

	public ArrayList<EmployeeManagementDTO> searchComboBox(String item,String value) {
		return this.empDAL.searchSelectBox(item,value);
	}

	public ArrayList<EmployeeManagementDTO> searchLuong(float min, float max) {
		return this.empDAL.searchSalary(min, max);
	}

	public ArrayList<EmployeeManagementDTO> searchAll(String item,String value, float min, float max) {
		return this.empDAL.searchAll(item, value, min, max);
	}

	// Phương thức kiểm tra trạng thái của nhân viên theo mã
	public int getEmployeeStatus(String maNV) {
		EmployeeManagementDTO emp = this.empDAL.getNhanVien(maNV);
		if (emp != null) {
			return emp.getStatus_emp();
		}
		return -1; // Trả về -1 nếu không tìm thấy nhân viên
	}

}
