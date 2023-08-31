package student.ams.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//攔截器
public class MainInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object handler) {
		return false;
	}
	
	public void postHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) {
		
	}
	
	public void afterCompletion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object handler, Exception ex) {
		
	}
}
