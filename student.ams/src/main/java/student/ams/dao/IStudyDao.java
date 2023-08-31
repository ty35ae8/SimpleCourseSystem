package student.ams.dao;

import java.util.List;

import student.ams.entity.Study;
import student.ams.util.Page;

//選課模塊持久層

public interface IStudyDao {
	
	//查詢該學生是否已經選過該門課
	Study check(String stuId,String clzId);
	
	//添加選課紀錄到選課表
	public void insert(Study studyInfo);
	
	//查看選擇該課程的所有學生
	Page<Study> selectByClzId(String clzId,Integer page,Integer rows);
	
	//查看我的選課列表
	Page<Study> selectByStuId(String stuId,Integer page,Integer rows);
	
	//刪除我的課程
	void delete(String stuId,String clzId);
	
	//根據學生id查看選課信息
	List<Study> getStudyByStuId(String stuId);
}
