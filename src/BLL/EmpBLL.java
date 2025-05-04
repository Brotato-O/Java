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

	public ArrayList<EmployeeManagementDTO> getDS() {
		return this.empDAL.getDanhSachNhanVien();
	}

	public EmployeeManagementDTO getNV(String maNV) {
		return this.empDAL.getNhanVien(maNV);
	}

	public boolean addNV(EmployeeManagementDTO empDTO) {
		if (this.empDAL.checkMaNV(empDTO.getId_emp())) {
			System.out.println("Mã nhân viên đã tồn tại: " + empDTO.getId_emp());
			return false; 
		}
		return this.empDAL.addNhanVien(empDTO);
	}

	public boolean checkID(String maNV) {
		return this.empDAL.checkMaNV(maNV);
	}

	public boolean updateNV(EmployeeManagementDTO empDTO) {
		return this.empDAL.updateNV(empDTO);
	}

	public boolean deleteNV(String maNV) {
		return this.empDAL.deleteNV(maNV);
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

}
