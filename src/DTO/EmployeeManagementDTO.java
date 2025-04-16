package DTO;

public class EmployeeManagementDTO {

	//1. Fields
	private String id_emp;
	private String name_emp;
	private String phone_emp;
	private float salary_emp;
	private int status_emp;

	public EmployeeManagementDTO() {

	}

	public EmployeeManagementDTO(String id_emp, String name_emp, String phone_emp, float salary_emp, int status_emp) {
		this.id_emp = id_emp;
		this.name_emp = name_emp;
		this.phone_emp = phone_emp;
		this.salary_emp = salary_emp;
		this.status_emp = status_emp;
	}

	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public String getName_emp() {
		return name_emp;
	}

	public void setName_emp(String name_emp) {
		this.name_emp = name_emp;
	}

	public String getPhone_emp() {
		return phone_emp;
	}

	public void setPhone_emp(String phone_emp) {
		this.phone_emp = phone_emp;
	}

	public float getSalary_emp() {
		return salary_emp;
	}

	public void setSalary_emp(float salary_emp) {
		this.salary_emp = salary_emp;
	}

	public int getStatus_emp() {
		return status_emp;
	}

	public void setStatus_emp(int status_emp) {
		this.status_emp = status_emp;
	}

}
