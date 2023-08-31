package student.ams.service;

import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.util.Page;

//系統用戶模塊 業務介面
public interface IAdminService {
	//系統用戶登入
	public boolean login(String username,String pwd);
	
	//添加課程信息
    public boolean addCourse(Course course);
    
    //根據id獲取課程信息
    Course getCourseById(String id);
    
    //更新課程信息
    public boolean updateCourse(Course course);
    
    //分頁獲取課程列表
    Page<Course> getPageAllCourses(Integer page,Integer rows);
    
    //分頁獲取搜尋課程列表
    Page<Course> getPageSearchCourses(Integer page,Integer rows,String keyword);

    //刪除課程
    void delCourse(String id);
    
    //分頁獲取學生列表
    Page<Student> getPageAllStudents(Integer page,Integer rows);
    
    //分頁獲取查詢學生列表
    Page<Student> getPageSearchStudents(Integer page,Integer rows,String keyword);
    
    //添加學生信息
    public boolean addStudent(Student student);
    
    //更新學生信息
    public boolean updateStudent(Student student);
    
    //根據id獲取學生信息
    Student getStudentById(String id);
    
    //刪除學生
    void delStudent(String id);
    
    //分頁獲取選課列表
    Page<Study> getAllStudyInfo(Integer page,Integer rows);
    
    //分頁獲取查詢選課列表
    Page<Study> getSearchStudyInfo(Integer page,Integer rows,String keyword);
    
    
    //根據id獲取選課信息
    Study getStudyById(Integer id);
    
    //根據id刪除選課信息
    void delStudyInfo (Integer id);
    
}


