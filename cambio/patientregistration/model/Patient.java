package cambio.patientregistration.model;

import java.awt.GridBagConstraints;

public class Patient {
	private String name;
	private String age;
	private String address;
	private String empStatus;
	private String gender;
	private String monthOrYear;
	private String tp;
	private String birthday;

	public Patient(String name,String age, String address, String empStatus, String gender,
			String monthOrYear,String tp, String birthday){
		this.name = name;
		this.age = age;
		this.address = address;
		this.empStatus = empStatus;
		this.gender = gender;
		this.monthOrYear = monthOrYear;
		this.tp = tp;
		this.birthday = birthday;
	}

	public String getName(){
		return name;
	}
	public String getAge(){
		return age;
	}
	public String getAddress(){
		return address;
	}
	public String getEmpStatus(){
		return empStatus;
	}
	public String getGender(){
		return gender;
	}
	public String getMonthOrYear(){
		return monthOrYear;
	}
	public String getTP(){
		return tp;
	}
	public String getBirthday(){
		return birthday;
	}

	public void setAddress(Object value) {
		this.address = value.toString();

	}

	public void setEmpStatus(Object value) {
		this.empStatus = value.toString();

	}
}
