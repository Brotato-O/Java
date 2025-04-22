package DTO;

public class EmployeeManagementDTO {

	//1. Fields
	private String id_emp;        // MANV
	private String name_emp;      // TENNV
	private String phone_emp;     // SDT
	private float salary_emp;     // LUONG
	private int status_emp;       // STATUS
	private String email_emp;     // email
	private String gender_emp;    // phai
	private String position_emp;  // chucvu
	private String birth_date;    // ngaysinh

	public EmployeeManagementDTO() {

	}

	public EmployeeManagementDTO(String id_emp, String name_emp, String phone_emp, 
			float salary_emp, int status_emp, String email_emp, 
			String gender_emp, String position_emp, String birth_date) {
		this.id_emp = id_emp;
		this.name_emp = name_emp;
		this.phone_emp = phone_emp;
		this.salary_emp = salary_emp;
		this.status_emp = status_emp;
		this.email_emp = email_emp;
		this.gender_emp = gender_emp;
		this.position_emp = position_emp;
		this.birth_date = birth_date;
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
	
	public String getEmail_emp() {
		return email_emp;
	}

	public void setEmail_emp(String email_emp) {
		this.email_emp = email_emp;
	}

	public String getGender_emp() {
		return gender_emp;
	}

	public void setGender_emp(String gender_emp) {
		this.gender_emp = gender_emp;
	}

	public String getPosition_emp() {
		return position_emp;
	}

	public void setPosition_emp(String position_emp) {
		this.position_emp = position_emp;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
}
