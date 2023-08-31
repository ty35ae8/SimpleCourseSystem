package student.ams.service.impl;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.ams.dao.IStudyDao;
import student.ams.entity.Study;
import student.ams.service.IStudyService;
import student.ams.util.Page;


//選課業務介面實現類
@Service
public class StudyService implements IStudyService{
	
	@Autowired
	private IStudyDao studyDao;
	
	//查看選擇該課程的所有學生
	@Override
	public Page<Study> getAllStuByClzId(String clzId, Integer page, Integer rows) {
		return studyDao.selectByClzId(clzId,page,rows);
	}
	
	//查看我的選課列表
	@Override
	public Page<Study> getAllClzByStuId(String stuId, Integer page, Integer rows) {
		return studyDao.selectByStuId(stuId,page,rows);
	}
	
	//刪除我的課程
	@Override
	public void delCourse(String stuId, String clzId) {
		studyDao.delete(stuId,clzId);
	}
	
	//根據課程id和學生id查看學生的選課信息
	@Override
	public Study selectByStuIdAndClzId(String stuId, String clzId) {
		return studyDao.check(stuId, clzId);
	}
	
	
	//根據學生id查看選課信息
	@Override
	public List<Study> getStudyByStuId(String stuId) {
		return studyDao.getStudyByStuId(stuId);
	}
	
	
	@PreDestroy
    public void close() {
        System.out.println("StudyService is being destroyed...");
    }
	

}
