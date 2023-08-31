package student.ams.service.impl;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.ams.dao.IAdminDao;
import student.ams.dao.ICourseDao;
import student.ams.entity.Admin;
import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.service.IAdminService;
import student.ams.util.Page;

//系統用戶模塊 業務實現類
@Service
public class AdminService implements IAdminService{

	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private ICourseDao courseDao;
	
	//根據用戶名和密碼登入
	@Override
	public boolean login(String username, String pwd) {
		
		Admin admin = adminDao.selectById(username);
		if(admin!=null && admin.getPwd().equals(pwd)) {
			return true;
		}
		
		return false;
	}
	
	//添加課程信息
	@Override
	public boolean addCourse(Course course) {
		
		String name = course.getName();
		if(name!=null && name.length()<=100) {
			adminDao.addCourse(course);
			return true;
		}
		
		return false;
	}
	
	//根據id獲取課程信息
	@Override
	public Course getCourseById(String id) {
		return courseDao.selectById(id);
	}
	
	//更新課程信息
	@Override
    public boolean updateCourse(Course course) {
    	
		if(course!=null && course.getId()!=null) {
			courseDao.update(course);
			return true;
		}
		
		return false;
    }
	
	//分頁獲取課程列表
	@Override
	public Page<Course> getPageAllCourses(Integer page, Integer rows) {
		return adminDao.getPageAllCourses(page,rows);
	}
	
	//分頁獲取搜尋課程列表
	@Override
	public Page<Course> getPageSearchCourses(Integer page, Integer rows, String keyword) {
		return adminDao.getPageSearchCourses(page, rows, keyword);
	}
	
	
	//刪除課程
	@Override
	public void delCourse(String id) {
		adminDao.delCourse(id);
	}
	
	
	//分頁獲取學生列表
	@Override
	public Page<Student> getPageAllStudents(Integer page, Integer rows) {
		return adminDao.getPageAllStudents(page,rows);
	}
	
	//分頁獲取查詢學生列表
	@Override
	public Page<Student> getPageSearchStudents(Integer page, Integer rows, String keyword) {
		return adminDao.getPageSearchStudents(page,rows,keyword);
	}
	
	
	//添學生程信息
	@Override
	public boolean addStudent(Student student) {
		if(student!=null) {
			adminDao.addStudent(student);
			return true;
		}
		return false;
	}
	
	//更新學生信息
	@Override
	public boolean updateStudent(Student student) {
		if(student!=null && student.getId()!=null) {
			adminDao.updateStudent(student);
			return true;
		}
		return false;
	}
	
	//根據id獲取學生信息
	@Override
	public Student getStudentById(String id) {	
		return adminDao.getStudentById(id);
	}
	
	//刪除學生
	@Override
	public void delStudent(String id) {
		adminDao.delStudent(id);
		
		//刪除該學生的所有選課信息
		adminDao.delStudyInfoById(id);
	}
	
	
	//分頁獲取選課列表
	@Override
	public Page<Study> getAllStudyInfo(Integer page, Integer rows) {
		return adminDao.selectAllStudyInfo(page,rows);
	}
	
	//分頁獲取查詢選課列表
	@Override
	public Page<Study> getSearchStudyInfo(Integer page, Integer rows, String keyword) {
		return adminDao.selectSearchStudyInfo(page,rows,keyword);
	}
	
	
	//根據id獲取選課信息
	@Override
	public Study getStudyById(Integer id) {
		return adminDao.selectById(id);
	}
	
	//根據id刪除選課信息
	@Override
	public void delStudyInfo(Integer id) {
		adminDao.delStudyInfo(id);
	}


	@PreDestroy
    public void close() {
        System.out.println("AdminService is being destroyed...");
    }

	
	
	
	
}





