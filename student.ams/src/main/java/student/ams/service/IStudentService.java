package student.ams.service;

import student.ams.entity.Student;

//學生模塊 業務介面
public interface IStudentService {
	//學生登入
	Student login(String email);
	
	//學生選課
	public int selectCourse(String stuId,String clzId,String eventId);
}
