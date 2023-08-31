package student.ams.entity;

import java.io.Serializable;

//課程實體類
public class Course implements Serializable{

	private static final long serialVersionUID = -4695155697727651440L;
	//課程代碼
	private String id;
	//課程名稱
	private String name;
	//開課學期
	private String semester;
	//上課時間
	private Integer time1;
	//下課時間
	private Integer time2;
	//課程學分
	private String credit;
	//課程所屬
	private String belong;
	//上課地點
	private String place;
	//總人數
	private int amount;
	//課程介紹
	private String detail;
	//已選人數
	private int selected;
	
	
	public Course() {}
	
	
	public Course(String id, String name, String semester, Integer time1, Integer time2, String credit, String belong,
			String place, int amount, String detail, int selected) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.time1 = time1;
		this.time2 = time2;
		this.credit = credit;
		this.belong = belong;
		this.place = place;
		this.amount = amount;
		this.detail = detail;
		this.selected = selected;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getTime1() {
		return time1;
	}
	public void setTime1(int time1) {
		this.time1 = time1;
	}
	public int getTime2() {
		return time2;
	}
	public void setTime2(int time2) {
		this.time2 = time2;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	
}
