package kh02.spring.Example005.controller;


/*: @RequestHeader Annotation을 이용하면 HTTP 요청 헤더의 값을 메서드의 파라미터로 전달 받을 수 있다.*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderController {
	
	@RequestMapping("/header/check.do")
	public String check(
			@RequestHeader("accept-Language") String languageHeader){
		
		System.out.println(languageHeader);
		
		return "header/pass";
	}
}
