package student.ams.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import student.ams.entity.Student;
import student.ams.service.IAdminService;
import student.ams.service.IStudentService;

//系統用戶登入 控制層
@Controller
public class LoginController {

	@Autowired
	private IAdminService adminService;
	
	@Autowired 
	private IStudentService studentService; 
	
	
	//學生登入
	@RequestMapping(value="/sigin")
	public ModelAndView userLogin(HttpServletRequest request)  throws  IOException{
		Student student = null;
		//String url = request.getHeader("Referer");
		Credential credential = (Credential) request.getSession().getAttribute("credential");
		Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(),GsonFactory.getDefaultInstance(), credential).setApplicationName("Oauth2").build();
		Userinfo userinfo = oauth2.userinfo().get().execute();

		String email = userinfo.getEmail();
		String name = userinfo.getFamilyName()+userinfo.getGivenName();
		
		if(email!=null) {
			student = studentService.login(email);
		}
		
		//使用者登入google帳戶後，如果在資料庫找不此使用者，就會出現註冊畫面，否則就順利登入，顯示使用者資訊。
		if(student!=null) {
			request.getSession().setAttribute("user", student.getName());
			request.getSession().setAttribute("userId", student.getId());
		}else {
			request.getSession().setAttribute("userId", generateId());
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("name", name);
			return  new ModelAndView(new RedirectView("/addStudent.jsp"));
		}

		
		return new ModelAndView(new RedirectView("/index"));
		
		//return new ModelAndView(new RedirectView(url));
	}
	
	public static String generateId() {
        long currentTime = new Date().getTime(); 
        Random random = new Random(currentTime); 
        long randomNum = random.nextLong(); 
        String id = String.valueOf(Math.abs(randomNum)).substring(0, 10); 
        return id;
    }
	
	
	//學生登出
	@RequestMapping("/logout")
	public String userLogout(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("userId", null);
		request.getSession().setAttribute("email", null);
		request.getSession().setAttribute("credential",null);
		return "redirect:/index";
	}
	
	
	
	//管理員登入
	@RequestMapping("/adminLogin")
	public String adminLogin(@RequestParam("username") String username,
			@RequestParam("pwd") String pwd,
			HttpServletRequest request) {
		
		if(username!=null && pwd!=null) {
			if(adminService.login(username,pwd)) {
				request.getSession().setAttribute("id",username);
				return "redirect:adminIndex";
			}
		}
		
		return "adminLogin";
	}
}
