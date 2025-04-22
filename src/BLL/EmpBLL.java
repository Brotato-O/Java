package BLL;

import java.util.ArrayList;

import DAL.EmpDAL;
import DTO.EmployeeManagementDTO;

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

}
