package student.ams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import student.ams.dao.ICourseDao;
import student.ams.dao.sql.SqlAdminDao;
import student.ams.entity.Course;
import student.ams.util.Page;

//課程模塊 持久層
@Repository
public class CourseDao extends SqlAdminDao implements ICourseDao{
	
	private static final String SQL_NAMESPACE = "Course";
	
	//根據課程id獲取課程信息
	@Override
	public Course selectById(String id) {
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectById",id);
	}
	
	//編輯課程訊息
	@Override
	public void update(Course course) {
		this.getSqlSession().selectOne(SQL_NAMESPACE+".update",course);
	}
	
	//獲取所有課程
	@Override
	public List<Course> selectAll() {
		return this.getSqlSession().selectList(SQL_NAMESPACE+".selectAll");
	}
	
	//分頁獲取所有課程
	@Override
	public Page<Course> getAllPageClz(Integer page, Integer rows) {
		
		//分頁處理
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo",(page-1)*rows);
		map.put("rows", rows);
		List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageAll",map);
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectAll").size();
		Page<Course> result = new Page<Course>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	
	//分頁獲取搜尋課程
	@Override
	public Page<Course> getSearchPageClz(Integer page, Integer rows,String keyword) {
		//分頁處理
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo",(page-1)*rows);
		map.put("rows", rows);
		map.put("keyword", keyword);
		
		List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageSearch",map);
		int total = this.getSqlSession().selectOne(SQL_NAMESPACE+".selectPageSearchCount",map);
		Page<Course> result = new Page<Course>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;

	}
	
	
	
}
