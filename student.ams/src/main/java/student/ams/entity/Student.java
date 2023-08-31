package student.ams.entity;

import java.io.Serializable;

//學生實體類
public class Student implements Serializable{
	
	private static final long serialVersionUID = -3513242050807493373L;

	//學號
	private String id;
	
	//信箱
	private String email;
	
	//姓名
	private String name;
	
	//性別
	private char sex;
	
	//年級
	private String year;
	
	//科系
	private String major;
	
	
	public Student() {}
	
	public Student(String id, String email, String name, char sex, String year, String major) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.year = year;
		this.major = major;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
