package student.ams.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import student.ams.dao.IStudentDao;
import student.ams.dao.sql.SqlAdminDao;
import student.ams.entity.Student;

@Repository
public class StudentDao extends SqlAdminDao implements IStudentDao {

	private static final String SQL_NAMESPACE = "Student";
	
	//根據學生id獲取學生信息
	@Override
	public Student selectById(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectById",map);
	}
	
	//根據學生email獲取學生信息
	@Override
	public Student selectByEmail(String email) {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		return this.getSqlSession().selectOne(SQL_NAMESPACE+".selectByEmail",map);
	}

}
