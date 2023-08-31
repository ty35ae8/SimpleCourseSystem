package student.ams.dao;


import student.ams.entity.Admin;
import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.util.Page;

public interface IAdminDao {
	//根據用戶名獲取用戶信息
	public Admin selectById(String id);
	
	//添加課程信息
	public void addCourse(Course course);
	
	//分頁獲取課程列表
	Page<Course> getPageAllCourses(Integer page,Integer rows);
	
	//分頁獲取搜尋課程列表
	Page<Course> getPageSearchCourses(Integer page, Integer rows, String keyword);

	//刪除課程
	void delCourse(String id);
	
	//分頁獲取學生列表
	Page<Student> getPageAllStudents(Integer page,Integer rows);
	
	//分頁獲取查詢學生列表
	Page<Student> getPageSearchStudents(Integer page,Integer rows,String keyword);
	
	//添加學生信息
    public void addStudent(Student student);
    
    //更新學生信息
    public void updateStudent(Student student);
    
    //根據id獲取學生信息
    Student getStudentById(String id);
    
    //刪除學生
    void delStudent(String id);
    
    //刪除該學生的所有選課信息
    void delStudyInfoById(String id);
    
    //分頁獲取選課列表
    Page<Study> selectAllStudyInfo(Integer page,Integer rows);
    
    //分頁獲取查詢選課列表
    Page<Study> selectSearchStudyInfo(Integer page,Integer rows,String keyword);

    //根據id獲取選課信息
    Study selectById(Integer id);
    
    //根據id刪除選課信息
  	void delStudyInfo(Integer id);
    
}
