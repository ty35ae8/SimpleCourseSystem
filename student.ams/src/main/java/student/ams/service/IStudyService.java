package student.ams.service;

import java.util.List;

import student.ams.entity.Study;
import student.ams.util.Page;

//選課業務介面
public interface IStudyService {
	
	//查看選擇該課程的所有學生
	Page<Study> getAllStuByClzId(String clzId,Integer page,Integer rows);
	
	//查看我的選課列表
	Page<Study> getAllClzByStuId(String stuId,Integer page,Integer rows);

	//刪除我的課程
	void delCourse(String stuId,String clzId);
	
	//根據課程id和學生id查看學生的選課信息
	Study selectByStuIdAndClzId(String stuId,String clzId);
	
	//根據學生id查看選課信息
	List<Study> getStudyByStuId(String stuId);
}
