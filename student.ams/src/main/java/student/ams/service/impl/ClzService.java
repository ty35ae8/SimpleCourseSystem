package student.ams.service.impl;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.ams.dao.ICourseDao;
import student.ams.entity.Course;
import student.ams.service.IClzService;
import student.ams.util.Page;

//前端課程業務實現類
@Service
public class ClzService implements IClzService{
	
	@Autowired
	private ICourseDao clzDao;
	
	//獲取所有課程
	@Override
	public List<Course> getAllClz() {
		return clzDao.selectAll();
	}
	
	//分頁獲取所有課程
	@Override
	public Page<Course> getAllPageClz(Integer page, Integer rows) {
		return clzDao.getAllPageClz(page,rows);
	}
	
	//分頁獲取搜尋課程
	@Override
	public Page<Course> getSearchPageClz(Integer page, Integer rows, String keyword) {
		return clzDao.getSearchPageClz(page, rows, keyword);
	}
	
	//根據課程id獲取課程信息
	@Override
	public Course getClzById(String id) {
		return clzDao.selectById(id);
	}
	
	//更新課程信息
	@Override
	public void update(Course course) {
		clzDao.update(course);
	}
	
	
	@PreDestroy
    public void close() {
        System.out.println("ClzService is being destroyed...");
    }
	
	

}


