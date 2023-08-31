package student.ams.dao;


import java.util.List;


import student.ams.entity.Course;
import student.ams.util.Page;

//課程模塊 業務介面
public interface ICourseDao {
	//根據課程id獲取課程信息
	Course selectById(String id);
	
	//編輯課程訊息
	void update(Course course);
	
	//獲取所有課程
	List<Course> selectAll();
	
	//分頁獲取所有課程
	Page<Course> getAllPageClz(Integer page,Integer rows);
	
	//分頁獲取搜尋課程
	Page<Course> getSearchPageClz(Integer page,Integer rows,String keyword);
}
