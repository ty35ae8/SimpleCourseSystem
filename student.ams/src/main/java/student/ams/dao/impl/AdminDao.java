package student.ams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import student.ams.dao.IAdminDao;
import student.ams.dao.sql.SqlAdminDao;
import student.ams.entity.Admin;
import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.util.Page;

//系統用戶模塊 持久層 實現類
@Repository
public class AdminDao extends SqlAdminDao implements IAdminDao{
	private static final String SQL_NAMESPACE = "Admin";
	
	//根據用戶名獲取用戶信息
	@Override
	public Admin selectById(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectById",map);
	}
	
	//添加課程信息
	@Override
	public void addCourse(Course course) {
		this.getSqlSession().insert(SQL_NAMESPACE+".insertClz",course);
	}
	
	//分頁獲取課程列表
	@Override
	public Page<Course> getPageAllCourses(Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<>();
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectAllCourses").size();
		//分頁處理
		map.put("pageNo", (page-1)*rows);
		map.put("rows", rows);
		List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageCourses",map);
		
		Page<Course> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	//分頁獲取搜尋課程列表
	@Override
	public Page<Course> getPageSearchCourses(Integer page, Integer rows, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo",(page-1)*rows);
		map.put("rows", rows);
		map.put("keyword", keyword);
		
		List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectSearchPageCourses",map);
		int total = this.getSqlSession().selectOne(SQL_NAMESPACE+".selectCoursesPageSearchCount",map);
		Page<Course> result = new Page<Course>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;		
	}	
	
	
	//刪除課程
	@Override
	public void delCourse(String id) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		this.getSqlSession().delete(SQL_NAMESPACE+".delCourse",map);
	}
	
	
	//分頁獲取學生列表
	@Override
	public Page<Student> getPageAllStudents(Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectAllStudents").size();
		
		//分頁處理
		map.put("pageNo", (page-1)*rows);
		map.put("rows", rows);
		List<Student> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageAllStudents",map);
		Page<Student> result = new Page<Student>();
		result.setPage(page);
		result.setSize(rows);
		result.setTotal(total);
		result.setRows(list);
		return result;
	}
	
	//分頁獲取查詢學生列表
	@Override
	public Page<Student> getPageSearchStudents(Integer page, Integer rows, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo",(page-1)*rows);
		map.put("rows", rows);
		map.put("keyword", keyword);
		
		List<Student> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectSearchPageStudents",map);
		int total = this.getSqlSession().selectOne(SQL_NAMESPACE+".selectStudentsPageSearchCount",map);
		Page<Student> result = new Page<Student>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;		
	}
	
	
	//添加學生信息
	@Override
	public void addStudent(Student student) {
		this.getSqlSession().insert(SQL_NAMESPACE+".insertStudent",student);
	}
	
	
	//更新學生信息
	@Override
	public void updateStudent(Student student) {
		Map<String,Student> map = new HashMap<>();
		map.put("s", student);
		this.getSqlSession().update(SQL_NAMESPACE+".updateStudent",map);
	}
	
	//根據id獲取學生信息
	@Override
	public Student getStudentById(String id) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectStuById",map);
	}
	
	//刪除學生
	@Override
	public void delStudent(String id) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		this.getSqlSession().delete(SQL_NAMESPACE+".delStudent",map);
	}

	//刪除該學生的所有選課信息
	@Override
	public void delStudyInfoById(String id) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		this.getSqlSession().delete(SQL_NAMESPACE+".delStudyInfoById",map);
	}
	
	//分頁獲取選課列表
	@Override
	public Page<Study> selectAllStudyInfo(Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectAllStudyInfo",map).size();
		//分頁處理
		map.put("pageNo", (page-1)*rows);
		map.put("rows", rows);
		List<Study> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageAllStudyInfo",map);
		Page<Study> result = new Page<Study>();
		result.setPage(page);
		result.setSize(rows);
		result.setTotal(total);
		result.setRows(list);
		return result;
	}
	
	//分頁獲取查詢選課列表
	@Override
	public Page<Study> selectSearchStudyInfo(Integer page, Integer rows, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo",(page-1)*rows);
		map.put("rows", rows);
		map.put("keyword", keyword);
		
		List<Study> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectSearchPageStudyInfo",map);
		int total = this.getSqlSession().selectOne(SQL_NAMESPACE+".selectStudyInfoPageSearchCount",map);
		Page<Study> result = new Page<Study>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;	
	}
	

	 //根據id獲取選課信息
	@Override
	public Study selectById(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectByStudyId",map);
	}
	
	//根據id刪除選課信息
	@Override
	public void delStudyInfo(Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		this.getSqlSession().delete(SQL_NAMESPACE+".delByStudyId",map);
	}



	


	
	
}




