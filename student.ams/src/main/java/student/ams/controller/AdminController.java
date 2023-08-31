package student.ams.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.service.IAdminService;
import student.ams.util.Page;

@Controller
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	
	//添加課程
	@RequestMapping(value="/addCourse",method=RequestMethod.POST)
	public String addCourse(HttpServletRequest request) {
		String msg = null;
		try {
			request.setCharacterEncoding("utf-8");
			Course course = new Course();
			course.setId(request.getParameter("id"));
			course.setName(request.getParameter("name"));
			course.setSemester(request.getParameter("semester"));
			course.setTime1(Integer.parseInt(request.getParameter("time1")));
			course.setTime2(Integer.parseInt(request.getParameter("time2")));
			course.setCredit(request.getParameter("credit"));
			course.setBelong(request.getParameter("belong"));
			course.setPlace(request.getParameter("place"));
			course.setAmount(Integer.parseInt(request.getParameter("amount")));
			course.setDetail(request.getParameter("detail"));
			course.setSelected(0);
			
			if(adminService.addCourse(course)){
				msg="新增課程成功";
			}else {
				msg="新增課程失敗";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			msg="新增課程失敗";
		}finally {
			request.getSession().setAttribute("msg", msg);
			return "redirect:/courseManage";
		}
	}
	
	//根據id獲取課程信息
	@RequestMapping(value="/getCourseById",method=RequestMethod.GET)
	public String getCourseById(@RequestParam String id,HttpServletRequest request) {
		Course course = adminService.getCourseById(id);
		request.getSession().setAttribute("course", course);
		return "editCourse";
	}
	
	
	//編輯課程
	@RequestMapping(value="/editCourse",method=RequestMethod.POST)
	public String editCourse(HttpServletRequest request) {
		String msg = null;
		try {
			request.setCharacterEncoding("utf-8");
			String id = (String)request.getSession().getAttribute("id");
			ServletContext sc = request.getSession().getServletContext();
			Course course = adminService.getCourseById(id);
			course.setId(id);
			course.setName(request.getParameter("name"));
			course.setSemester(request.getParameter("semester"));
			course.setTime1(Integer.parseInt(request.getParameter("time1")));
			course.setTime2(Integer.parseInt(request.getParameter("time2")));
			course.setCredit(request.getParameter("credit"));
			course.setBelong(request.getParameter("belong"));
			course.setPlace(request.getParameter("place"));
			course.setAmount(Integer.parseInt(request.getParameter("amount")));
			course.setDetail(request.getParameter("detail"));
			course.setSelected(0);
			
			if(adminService.updateCourse(course)){
				msg="更新課程成功";
			}else {
				msg="更新課程失敗";
			}
		}catch(Exception e) {
			e.printStackTrace();
			msg="更新課程失敗";
		}finally {
			request.getSession().setAttribute("msg", msg);
			return "redirect:/courseManage";
		}
	}
	
	//刪除課程
	@RequestMapping("adminDelCourse")
	public String adminDelCourse(@RequestParam String id,HttpServletRequest request) {
		if(id!=null) {
			adminService.delCourse(id);
			request.getSession().setAttribute("msg", "刪除課程成功");
		}else {
			request.getSession().setAttribute("msg", "刪除課程失敗");
		}
		
		return "redirect:/courseManage";
	}
	
	
	//新增學生
	@RequestMapping("addStudent")
	public String addStudent(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Student student = new Student();
		student.setId(request.getParameter("id"));
		student.setName(request.getParameter("name"));
		student.setEmail(request.getParameter("email"));
		student.setMajor(request.getParameter("major"));
		student.setYear(request.getParameter("year"));
		student.setSex(request.getParameter("sex").charAt(0));
		String msg = null;
		if(adminService.addStudent(student)) {
			msg = "註冊成功";
			request.getSession().setAttribute("user", student.getName());
		}else {
			msg = "註冊失敗";
		}
		
		request.getSession().setAttribute("msg", msg);
		return "redirect:/index";
	}
	
	
	//更新學生信息
	@RequestMapping(value="/changeStudent",method=RequestMethod.POST)
	public String changeStudent(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Student student = new Student();
		student.setId(request.getParameter("id"));
		student.setName(request.getParameter("name"));
		student.setEmail(request.getParameter("email"));
		student.setMajor(request.getParameter("major"));
		student.setYear(request.getParameter("year"));
		student.setSex(request.getParameter("sex").charAt(0));
		String msg = null;
		if(adminService.updateStudent(student)) {
			msg = "更新學生成功";
		}else {
			msg = "更新學生失敗";
		}
		
		request.getSession().setAttribute("msg", msg);
		return "redirect:/studentManage";
	}
	
	//修改學生信息頁面
	@RequestMapping(value="changeStudent")
	public String changeStudent(@RequestParam String id,HttpServletRequest request) {
		if(id==null) {
			return "";
		}
		Student student = adminService.getStudentById(id);
		request.getSession().setAttribute("student", student);
		return "changeStudent";
	}
	
	//刪除學生
	@RequestMapping("delStudent")
	public String delStudent(@RequestParam String id,HttpServletRequest request) {
		if(id!=null) {
			adminService.delStudent(id);
			request.getSession().setAttribute("msg", "刪除學生成功");
		}else {
			request.getSession().setAttribute("msg", "刪除學生失敗");
		}
		return "redirect:/studentManage";
	}
	
	
	//獲取選課列表
	@RequestMapping("/chooseManage")
	public String chooseManage(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="5") Integer rows,HttpServletRequest request,Model model) {
		Page<Study> records = adminService.getAllStudyInfo(page,rows);
		request.getSession().setAttribute("students",null);
		model.addAttribute("page",records);
		model.addAttribute("pageName","chooseManage");
		request.getSession().setAttribute("records",records.getRows());
		return "allChoose";
	}
	
	//根據選課id刪除選課紀錄
	@RequestMapping("/delStudyInfo")
	public String delStudyInfo(@RequestParam String id,HttpServletRequest request) {
		String msg = null;
		
		try {
			Integer Id = Integer.parseInt(id);
			Study info = adminService.getStudyById(Id);
			//更新課程已選人數
			Course c = adminService.getCourseById(info.getC_id());
			c.setSelected(c.getSelected()-1);
			adminService.updateCourse(c);
			//刪除選課紀錄
			adminService.delStudyInfo(Id);
			msg = "刪除成功";
		}catch(Exception e) {
			msg = "刪除失敗";
			e.printStackTrace();
		}finally {
			request.getSession().setAttribute("msg", msg);
			return "redirect:/chooseManage";
		}
	}
	
	
	
}






