package student.ams.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.Reminders;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import student.ams.entity.Course;
import student.ams.entity.Study;
import student.ams.service.IClzService;
import student.ams.service.IStudentService;
import student.ams.service.IStudyService;

@Controller
public class SelectController {
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IStudyService studyService;
	
	@Autowired
	private IClzService clzService;
	
	
	//學生選課
	@RequestMapping("/selectClz")
	public String selectClz(HttpServletRequest request) throws IOException{
		
		String msg = null;
		
		Map<String, String> weekdayMap = new HashMap<>(); // 建立星期幾映射表
	    weekdayMap.put("一", "Monday");
	    weekdayMap.put("二", "Tuesday");
	    weekdayMap.put("三", "Wednesday");
	    weekdayMap.put("四", "Thursday");
	    weekdayMap.put("五", "Friday");
	    weekdayMap.put("六", "Saturday");
	    weekdayMap.put("日", "Sunday");
	    String[] times1 = {"","08:00","09:00","10:00","11:00","13:00","14:00","15:00","16:00"};
	    String[] times2 = {"","09:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00"};
		
		//獲取學生id
		String sId = (String) request.getSession().getAttribute("userId");
		//獲取課程信息
		Course c = (Course) request.getSession().getAttribute("course");
		//課程id
		String cId = c.getId();
		//連接Google日曆api進行操作
		Credential credential = (Credential) request.getSession().getAttribute("credential");
		Calendar service = new Calendar.Builder(new NetHttpTransport(),GsonFactory.getDefaultInstance(), credential)
		            		.setApplicationName("addclass")
		            		.build();
		//建立事件
		Event event = new Event()
			    		.setSummary(c.getName())
			    		.setColorId(randomColorId())
			    		.setLocation("上課地點: "+c.getPlace())
			    		.setDescription("授課教師: "+c.getBelong());
		//設定事件
		String week = weekdayMap.get(c.getSemester());
		String time1 = times1[c.getTime1()];
		String time2 = times2[c.getTime2()];
		
		DateTime startDateTime = new DateTime(dayOfWeek(week)+"T"+time1+":00+08:00");
		EventDateTime start = new EventDateTime()
								.setDateTime(startDateTime)
								.setTimeZone("Asia/Taipei");
		event.setStart(start);
		
		DateTime endDateTime = new DateTime(dayOfWeek(week)+"T"+time2+":00+08:00");
		EventDateTime end = new EventDateTime()
			    				.setDateTime(endDateTime)
			    				.setTimeZone("Asia/Taipei");
		event.setEnd(end);

		String _week = week.toUpperCase().substring(0,2);
		String[] recurrence = new String[] {"RRULE:FREQ=WEEKLY;BYDAY="+_week};
		event.setRecurrence(Arrays.asList(recurrence));

		EventReminder reminder = new EventReminder().setMethod("popup").setMinutes(10);
		Reminders reminders = new Reminders();
		reminders.setUseDefault(false);
		List<EventReminder> list = new ArrayList<EventReminder>();
		list.add(reminder);
		reminders.setOverrides(list);
		event.setReminders(reminders);
		
		//事件啟動
		String calendarId = "primary";
		event = service.events().insert(calendarId, event).execute();
		
		//回傳選課結果
		String eventId = event.getId();
		int rst = studentService.selectCourse(sId,cId,eventId);
		
		if(rst==0)
			msg = "選課成功!";
		else if(rst==1) 
			msg = "已經選過此課";
		else if(rst==2)
			msg = "該課程人數已選滿";
		else if(rst==3) 
			msg = "已衝堂";
		else 
			msg = "未知錯誤";
		
		if(rst>=1||rst==-1)
			service.events().delete("primary", eventId).execute();
		
		request.getSession().setAttribute("msg",msg);
		
		return "detail";
	}
	
	//刪除我的課程
	@RequestMapping("/delCourse")
	public String delCourse(HttpServletRequest request) throws IOException{
		try {
			String stuId = (String) request.getSession().getAttribute("userId");
			String clzId = request.getParameter("id");
			
			
			//連接Google日曆api進行操作
			Credential credential = (Credential) request.getSession().getAttribute("credential");
			Calendar service = new Calendar.Builder(new NetHttpTransport(),GsonFactory.getDefaultInstance(), credential)
			            		.setApplicationName("delclass")
			            		.build();
			
			Study studyInfo = studyService.selectByStuIdAndClzId(stuId, clzId);
			String eventId = studyInfo.getEventId();
			service.events().delete("primary", eventId).execute();
			
			//刪除資料
			studyService.delCourse(stuId,clzId);
			
			//課程人數減1
			Course c = clzService.getClzById(clzId);
			c.setSelected(c.getSelected()-1);
			clzService.update(c);
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return "404";
		}
		
		request.getSession().setAttribute("msg", "退選成功");
		return "redirect:/showMyClasses";
	}
	
	
	//取得當週星期的日期
	static String dayOfWeek(String week) {
	    DayOfWeek inputDayOfWeek = DayOfWeek.valueOf(week.toUpperCase()); // 將輸入的星期幾轉換為DayOfWeek枚舉類型
	    LocalDate now = LocalDate.now(); // 獲取當前日期
	    LocalDate firstDayOfWeek = now.with(DayOfWeek.MONDAY); // 獲取當前週的星期一日期
	    LocalDate targetDate = firstDayOfWeek.with(inputDayOfWeek); // 獲取當前週的指定星期幾日期
	    return targetDate.toString();
	}
	
	//隨機colorId
	static String randomColorId() {
		Random random = new Random();
		int randomNumber = random.nextInt(11) + 1; // 生成1到11之間的隨機數
	    String randomString = String.valueOf(randomNumber); // 將隨機數轉換為字串
	    return randomString;
	}
	 
	
}

