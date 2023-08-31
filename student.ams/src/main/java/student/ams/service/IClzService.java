package student.ams.service;

import java.util.List;

import student.ams.entity.Course;
import student.ams.util.Page;

//前端課程業務介面
public interface IClzService {
	
	//獲取所有課程
	List<Course> getAllClz();

	//分頁獲取所有課程
	Page<Course> getAllPageClz(Integer page,Integer rows);
	
	//分頁獲取搜尋課程
	Page<Course> getSearchPageClz(Integer page,Integer rows,String keyword);
	
	//根據課程id獲取課程信息
	Course getClzById(String id);
	
	//更新課程
	void update(Course course);
}
