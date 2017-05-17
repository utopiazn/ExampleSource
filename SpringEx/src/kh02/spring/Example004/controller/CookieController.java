package kh02.spring.Example004.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {

	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse response){
		
		//쿠키 생성
		response.addCookie(new Cookie("auth","1"));
		
		return "cookie/make";
	}
		
	
	@RequestMapping("/cookie/view.do/")
	public String view(
			@CookieValue(value="auth",defaultValue="0") String auth){
		
		System.out.println("auth 쿠키:"+ auth);
				
		return "cookie/view";
			
	}

}



/*
: @CookieValue Annotation을 이용하면 쿠키 값을 파라미터로 전달받을 수 있다. 다음은 "auth" 쿠키의 값을 authValue 파라미터를 
통해서 전달받도록 작성한 Code의 예를 보여주고 있다.
	import org.springframework.web.bind.annotation.CookieValue;
	...
	...
	@RequestMapping("/cookie/view.do")
public String view(@CookieValue(value = "auth") String authValue) {
	...
	return "cookie/view";
}
	
@CookieValue Annotation은 해당 쿠키가 존재하지 않으면 기본적으로 500 에러를 발생시킨다. 따라서, 쿠키가 필수가 아닌 경우에는
다음과 같이 required 속성의 값을 false로 지정해 주어야 한다. 참고로, required 속성의 기본 값은 true이다.
@RequestMapping("/cookie/view.do")
public String view(@CookieValue(value = "auth", required=false) String auth) {
	...
	return "cookie/view";
}
	
required 속성의 값을 false로 지정할 경우, 해당 쿠키가 존재하지 않으면 null을 값으로 전달받게 된다.
	
@RequestParam Annotation과 마찬가지로 defalutValue 속성을 이용해서 기본 값을 설정할 수도 있다. 아래 Code는 "auth" 쿠키가
존재하지 않을 경우 auth 파라미터는 "0"을 값으로 갖게 된다.
@RequestMapping("/cookie/view.do")
public String view(
	@CookieValue(value = "auth", defaultValue = "0") String auth) {
	...	
	return "cookie/view";
}

*/






