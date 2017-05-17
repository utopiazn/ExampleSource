package kh02.spring.Example001.controller;


import kh02.spring.Example001.service.ArticleService;
import kh02.spring.Example001.service.NewArticleCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	@Autowired
	private ArticleService articleService;
	
/*	
	NewArticleController Class의 form() 메서드나 submit() 메서드는 둘 다 /article/newArticle.do로 요청을 처리하도록 설정하였다.
	단, GET 요청이 들어올 경우 form() 메서드가 처리하고 POST 요청이 들어올 경우 submit() 메서드가 처리하게 된다.

*/	
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(){		
		return "article/newArticleForm";
	}
	
/*	
	HTML 폼에 입력한 데이터를 Java빈 객체로 전달받는 방법은 매우 간단하다. 단지 @RequestMapping Annotation이 적용된
	메서드의 파라미터로 Java빈 타입을 추가해주기만 하면 된다. 예를 들어, 폼에 입력한 값을 NewArticleController Class로
	전달 받고 싶다면 다음과 같이 요청 처리 메서드에 NewArticleCommand 타입의 파라미터를 추가해주기만 하면 된다.
	
*/
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") NewArticleCommand command){
		
		//command.getTitle() : title 파라미터 값 저장 
		//command.getContent() : content 파라미터 값 저장
		//command.getParentId() : parentId 파라미터 값 저장
		
		//kh02\spring\Example001\service\ArticleService.java 에서 호출
		articleService.writeArticle(command);
		
		return "article/newArticleSubmitted";
	}
	
/*	 이 경우 컨트롤러의 처리 결과를 보여주는 뷰 Code에서는 커맨드 객체의 Class 이름을 이용해서 커맨드 객체에 접근할 수 
	   있다. 즉, 커맨드 객체는 자동으로 모델에 추가된다. (단, 첫 글자는 소문자이다.)*/
	
/*	위 Code의 경우 @RequestMapping Annotation을 이용해서 NewArticleController Class가 처리할 기본 URI를 '/article/newArticle.do'로
	지정하였다. 그리고, form() 메서드와 submit() 메서드의 @RequestMapping Annotation은 URI를 설정하지 않았고 method 속성을 
	이용해서 처리할 전송방식만 지정하였다. 이 경우 form() 메서드와 submit() 메서드는 NewArticleController Class의 @RequestMapping
	Annotation에서 지정한 URI인 '/article/newArticle.do'를 처리하게 된다.
		
	@RequestMapping Annotation에 method 속성을 설정하지 않을 경우 GET, POST, DELETE 등 모든 HTTP 전송 방식을 처리하게 된다.
     	
	*/

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	

	

}
