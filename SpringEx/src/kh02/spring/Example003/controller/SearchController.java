package kh02.spring.Example003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

	@RequestMapping("/search/internal.do")
	public ModelAndView searchInternal(
			@RequestParam("query") String query,
			@RequestParam(value="p",defaultValue="1") int pageNumber){
		
		System.out.println("query="+query+",pageNumber="+pageNumber);
		
		return new ModelAndView("search/internal");
		
	}
	
	/*  필수가 아닌 파라미터인 경우 required 속성 값을 false로 지정해 주면 된다. 아래 Code는 적용 예이다. 참고로, requred 속성의
	   기본 값은 true이다.
	*/
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(
			@RequestParam(value="query",required=false) String query,
			@RequestParam(value="p",defaultValue="1") int pageNumber){
		
		System.out.println("query="+query+",pageNumber="+pageNumber);
		
		
		return new ModelAndView("/search/external");
	}
	
	 /* defaultValue 속성을 이용해서 기본 값을 지정하면 해당 요청 파라미터를 지정하지 않을 경우 defaultValue 속성에 지정한 문자열을
	   값으로 이용하게 된다. 예를 들어, 위와 같이 "p" 요청 파라미터의 기본 값을 1로 지정했다면, 
		
		http://localhost:8080/SpringEx/search/internal.do?query=spring
			
	   "p" 파라미터가 존재하지 않으므로 기본 값으로 지정한 "1"을 "p"파라미터의 값으로 사용하게 되고, 따라서 pageNumber 파라미터의
	   값은 1이 된다.
*/	
	
}
