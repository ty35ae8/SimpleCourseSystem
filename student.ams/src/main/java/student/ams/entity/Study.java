package student.ams.entity;

import java.io.Serializable;

//選課詳情實體類
public class Study implements Serializable{

	private static final long serialVersionUID = -2730306583678048443L;

	//選課信息編號
	private Integer id;
	
	//學生學號
	private String s_id;
	
	//學生姓名
	private String s_name;
	
	//學生科系
	private String s_major;
	
	//課程代碼
	private String c_id;
	
	//課程名稱
	private String c_name;
	
	//課程所屬
	private String c_belong;
	
	//課程學分
	private String c_credit;
	
	//課程學期
	private String c_semester;
	
	//上課時間
	private Integer time1;
	//下課時間
	private Integer time2;
	
	//google日曆事件id
	private String eventId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_major() {
		return s_major;
	}

	public void setS_major(String s_major) {
		this.s_major = s_major;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_belong() {
		return c_belong;
	}

	public void setC_belong(String c_belong) {
		this.c_belong = c_belong;
	}

	public String getC_credit() {
		return c_credit;
	}

	public void setC_credit(String c_credit) {
		this.c_credit = c_credit;
	}

	public String getC_semester() {
		return c_semester;
	}

	public void setC_semester(String c_semester) {
		this.c_semester = c_semester;
	}

	public Integer getTime1() {
		return time1;
	}

	public void setTime1(Integer time1) {
		this.time1 = time1;
	}

	public Integer getTime2() {
		return time2;
	}

	public void setTime2(Integer time2) {
		this.time2 = time2;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
}
