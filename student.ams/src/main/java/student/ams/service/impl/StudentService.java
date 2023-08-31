package student.ams.service.impl;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.ams.dao.ICourseDao;
import student.ams.dao.IStudentDao;
import student.ams.dao.IStudyDao;
import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.service.IStudentService;

//學生模塊 業務實現類
@Service
public class StudentService implements IStudentService{
	
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private ICourseDao courseDao;
	
	@Autowired
	private IStudyDao studyDao;
	
	//學生登入
	@Override
	public Student login(String email) {
		
		Student student = studentDao.selectByEmail(email);
		
		if(student!=null) {
			return student;
		}
		
		return null;
	}
	
	
	//學生選課
	@Override
	public int selectCourse(String stuId, String clzId, String eventId) {
		Course course = courseDao.selectById(clzId);
		Student student = studentDao.selectById(stuId);
		List<Study> studyInfos = studyDao.getStudyByStuId(stuId);
		
		if(course!=null && student!=null) {
			//判斷人數是否已滿
			if(course.getSelected()<course.getAmount()) {
				//查詢該學生是否已經選過該門課
				Study checkIfExist = studyDao.check(stuId,clzId);
				
				if(checkIfExist==null) {
					//判斷有無衝堂
					if( isConflict(course, studyInfos) ) {
						return 3;
					}
					Study studyInfo = new Study();
					try {
						studyInfo.setC_id(course.getId());
						studyInfo.setC_name(course.getName());
						studyInfo.setC_belong(course.getBelong());
						studyInfo.setC_credit(course.getCredit());
						studyInfo.setC_semester(course.getSemester());
						studyInfo.setTime1(course.getTime1());
						studyInfo.setTime2(course.getTime2());
						studyInfo.setS_id(stuId);
						studyInfo.setS_name(student.getName());
						studyInfo.setS_major(student.getMajor());
						studyInfo.setEventId(eventId);
						studyDao.insert(studyInfo);		
						course.setSelected(course.getSelected()+1);
						courseDao.update(course);
					}catch(Exception e) {
						e.printStackTrace();
						return -1;
					}
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				return 2;
			}
		}
		
		return -1;
	}
	
	
	//判斷有無衝堂
	static boolean  isConflict(Course course, List<Study> studyInfos) {
	    int time1 = course.getTime1();
	    int time2 = course.getTime2();
	    String semester = course.getSemester();
		for (Study studyInfo : studyInfos) {
	        if (studyInfo.getC_semester().equals(semester)) { // 如果同一天
	            int s1 = studyInfo.getTime1();
	            int e1 = studyInfo.getTime2();
	            // 判斷是否時段有重疊
	            if ((s1 <= time1 && e1 > time1) || (s1 < time2 && e1 >= time2) || (time1 <= s1 && time2 > s1)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	
	@PreDestroy
    public void close() {
        System.out.println("StudentService is being destroyed...");
    }
	

}





