package student.ams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import student.ams.entity.Course;
import student.ams.entity.Student;
import student.ams.entity.Study;
import student.ams.service.IAdminService;
import student.ams.service.IClzService;
import student.ams.service.IStudyService;
import student.ams.util.Page;

@Controller
public class DisplayController {
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IClzService clzService;
	
	@Autowired
	private IStudyService studyService;
	
	//後台首頁
	@RequestMapping("/adminIndex")
	public ModelAndView adminindex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		if(request.getSession().getAttribute("id")==null) {
			mv.setViewName("adminLogin");
			return mv;
		}
		
		List<Course> courses = clzService.getAllClz();
		List<String> listX = new ArrayList<>();
		List<Integer> listSelected = new ArrayList<>();
		List<Integer> listLeft = new ArrayList<>();
		
		for(Course course:courses) {
			listX.add(course.getName());
			listSelected.add(course.getSelected());
			listLeft.add(course.getAmount()-course.getSelected());
		}
		
		ObjectMapper objmapper = new ObjectMapper();
		try {
			request.getSession().setAttribute("listX", objmapper.writeValueAsString(listX));
			request.getSession().setAttribute("listSelected", objmapper.writeValueAsString(listSelected));
			request.getSession().setAttribute("listLeft", objmapper.writeValueAsString(listLeft));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		mv.setViewName("admin");
		return mv;
	}
	
	//後台課程管理，分頁獲取課程列表
	@RequestMapping("/courseManage")
	public String courseManage(@RequestParam(defaultValue="1") Integer page,
			                   @RequestParam(defaultValue="5") Integer rows, 
			                   HttpServletRequest request, Model model ) {
		if(request.getSession().getAttribute("id")==null) {
			return "adminLogin";
		}
		
		Page<Course> courses = adminService.getPageAllCourses(page,rows);
		request.getSession().setAttribute("courses", courses.getRows());
		model.addAttribute("page",courses);
		model.addAttribute("pageName","courseManage");
		
		return "allCourses";
	}
	
	//後台課程管理，分頁獲取搜尋課程列表
	@RequestMapping("/courseManageSearch")
	public String courseManageSearch(@RequestParam(defaultValue="1") Integer page,
			                   		 @RequestParam(defaultValue="5") Integer rows, 
			                   		 @RequestParam(value = "keyword", defaultValue = "") String keyword,
			                   		 HttpServletRequest request, Model model ) {
		if(request.getSession().getAttribute("id")==null) {
			return "adminLogin";
		}
		
		Page<Course> courses = adminService.getPageSearchCourses(page,rows,keyword);
		request.getSession().setAttribute("courses", courses.getRows());
		model.addAttribute("page",courses);
		model.addAttribute("pageName","courseManageSearch");
		
		return "allCourses";
	}
	
	
	//後台學生管理，分頁獲取學生列表
	@RequestMapping("/studentManage")
	public String studentManage(@RequestParam(defaultValue="1") Integer page,
			                   @RequestParam(defaultValue="5") Integer rows, 
			                   HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("id")==null) {
			return "adminLogin";
		}
		
		Page<Student> students = adminService.getPageAllStudents(page,rows);
		request.getSession().setAttribute("students", students.getRows());
		model.addAttribute("page",students);
		model.addAttribute("pageName","studentManage");
		
		return "allStudents";
	}
	
	//分頁獲取查詢學生列表
	@RequestMapping("/studentSearch")
	public String studentSearch(@RequestParam(defaultValue="1") Integer page,
            					@RequestParam(defaultValue="5") Integer rows, 
            					@RequestParam(value = "keyword", defaultValue = "") String keyword,
            					HttpServletRequest request, Model model) {
			if(request.getSession().getAttribute("id")==null) {
				return "adminLogin";
			}
			
			if(request.getSession().getAttribute("students")!=null) {
				Page<Student> students = adminService.getPageSearchStudents(page, rows, keyword);
				request.getSession().setAttribute("students", students.getRows());
				model.addAttribute("page",students);
				model.addAttribute("pageName","studentSearch");

				return "allStudents";
			}
			else {
				Page<Study> students = adminService.getSearchStudyInfo(page, rows, keyword);
				request.getSession().setAttribute("records", students.getRows());
				model.addAttribute("page",students);
				model.addAttribute("pageName","studentSearch");
				
				return "allChoose";
			}
			
	}

	
	
	//前端首頁:獲取所有課程
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="5") Integer rows,HttpServletRequest request,Model model) {
		Page<Course> coursePage = clzService.getAllPageClz(page,rows);
		request.getSession().setAttribute("courses", coursePage.getRows());
		model.addAttribute("page",coursePage);
		model.addAttribute("pageName","index");
		return "index";
	}
	
	
	//前端首頁:獲取查詢課程
	@RequestMapping("/search")
	public String search(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="5") Integer rows,@RequestParam(value = "keyword", defaultValue = "") String keyword,HttpServletRequest request,Model model) {
		Page<Course> coursePage = clzService.getSearchPageClz(page,rows,keyword);
		request.getSession().setAttribute("courses", coursePage.getRows());
		model.addAttribute("page",coursePage);
		model.addAttribute("pageName","search");
		return "index";
	}
	
	
	
	//查看課程詳情
	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam String id,HttpServletRequest request) {
		Course course = clzService.getClzById(id);
		request.getSession().setAttribute("course", course);
		return "detail";
	}
	
	//查看已選人數
	@RequestMapping("/showStudent")
	public String showStudents(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="5") Integer rows,HttpServletRequest request,Model model) {
		try {
			//獲取課程id
			String clzId = request.getParameter("id");
			Page<Study> students = studyService.getAllStuByClzId(clzId,page,rows);
			
			request.getSession().setAttribute("students", students.getRows());
			model.addAttribute("page",students);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return "404";
		}
		
		return "stulist";
	}
	
	//查看我的課程
	@RequestMapping("showMyClasses")
	public String showMyClasses(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="5") Integer rows,HttpServletRequest request,Model model) {
		String id = (String) request.getSession().getAttribute("userId");
		Page<Study> studyInfos = null;
		if(id!=null) {
			studyInfos = studyService.getAllClzByStuId(id,page,rows);
			request.getSession().setAttribute("clzs", studyInfos.getRows());
			model.addAttribute("page",studyInfos);
		}else {
			request.getSession().setAttribute("clzs", null);
		}
		
		return "myClzs";
	}
	
	
	//功課表頁面
	@RequestMapping("showMySheet")
	public String showMySheet(HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("userId");
		List<Study> studyInfos = null;
		List<Course> courses = new ArrayList<>();
		if(id!=null) {
			studyInfos = studyService.getStudyByStuId(id);
			for (Study study : studyInfos) {
				courses.add(clzService.getClzById(study.getC_id()));
			}
			request.getSession().setAttribute("studyinfos", courses);
		}else {
			request.getSession().setAttribute("studyinfos", null);
		}
		
		return "curriculum";
	}
	
}




