package cambio.patientregistration.model;

public class SearchPatient {
	private String name;
	private String age;
	private String gender;

	public SearchPatient(String name,String age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName(){
		return this.name;
	}

	public String getAge(){
		return this.age;
	}

	public String getGender(){
		return gender;
	}
}
