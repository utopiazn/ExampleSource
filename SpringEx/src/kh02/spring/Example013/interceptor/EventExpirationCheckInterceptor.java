package kh02.spring.Example013.interceptor;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class EventExpirationCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("preHandle 호출 ");
		
		if (checkEvent(request) && checkEventExpiration()) {
			
			//이벤트 종료
			displayEventExpirationPage(request, response);
			return false;
		}
		
		return true;
	}
	
	private boolean checkEvent(HttpServletRequest request) {
		
		System.out.println("이벤트 체크1 :"+request.getRequestURI().equals(request.getContextPath() + "/event/list.do"));
		
		return request.getRequestURI().equals(request.getContextPath() + "/event/list.do");
	}
	
	private boolean checkEventExpiration() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 05, 15);
		
		Date now = new Date();
		System.out.println("이벤트 체크 :"+now.after(calendar.getTime()));
		return now.after(calendar.getTime());
	}
		
	private void displayEventExpirationPage(
			HttpServletRequest request,		
			HttpServletResponse response) throws IOException {
		
		response.sendRedirect("eventExpired.do");
	}
	
}
