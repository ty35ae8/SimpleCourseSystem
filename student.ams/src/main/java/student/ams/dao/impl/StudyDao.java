package student.ams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import student.ams.dao.IStudyDao;
import student.ams.dao.sql.SqlAdminDao;
import student.ams.entity.Study;
import student.ams.util.Page;

@Repository
public class StudyDao extends SqlAdminDao implements IStudyDao{

	private static final String SQL_NAMESPACE = "Study";
	
	//查詢該學生是否已經選過該門課
	@Override
	public Study check(String stuId, String clzId) {
		Map<String, Object> map = new HashMap<>();
		map.put("stuId", stuId);
		map.put("clzId", clzId);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectByStuIdAndClzId",map);
	}
	
	//添加選課紀錄到選課表
	@Override
	public void insert(Study studyInfo) {
		this.getSqlSession().insert(SQL_NAMESPACE+".insert",studyInfo);
	}
	
	//查看選擇該課程的所有學生
	@Override
	public Page<Study> selectByClzId(String clzId, Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		map.put("clzId", clzId);
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectByClzId",map).size();
		
		//分頁處理
		map.put("pageNo", (page-1)*rows);
		map.put("rows", rows);
		List<Study> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageByClzId",map);
		Page<Study> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	//查看我的選課列表
	@Override
	public Page<Study> selectByStuId(String stuId, Integer page, Integer rows) {
		Map<String,Object> map = new HashMap<>();
		map.put("stuId", stuId);
		int total = this.getSqlSession().selectList(SQL_NAMESPACE+".selectByStuId",map).size();
		
		//分頁處理
		map.put("pageNo", (page-1)*rows);
		map.put("rows", rows);
		List<Study> list = this.getSqlSession().selectList(SQL_NAMESPACE+".selectPageByStuId",map);
		Page<Study> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	
	//根據學生id查看選課信息
	@Override
	public List<Study> getStudyByStuId(String stuId) {
		Map<String,Object> map = new HashMap<>();
		map.put("stuId", stuId);
		return this.getSqlSession().selectList(SQL_NAMESPACE+".selectByStuId",map);
	}
	
	//刪除我的課程
	@Override
	public void delete(String stuId, String clzId) {
		Map<String,Object> map = new HashMap<>();
		map.put("stuId", stuId);
		map.put("clzId", clzId);
		this.getSqlSession().delete(SQL_NAMESPACE+".delete",map);
	}
	
	

}



