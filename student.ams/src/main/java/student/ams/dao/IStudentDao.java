package student.ams.dao;


import student.ams.entity.Student;


public interface IStudentDao {
	//根據學生id獲取學生信息
	Student selectById(String id);
	
	//根據學生email獲取學生信息
	Student selectByEmail(String email);
}
