package kh01.spring.controller;
/*
2.2 단계2 : 컨트롤러 구현 및 설정 추가

 : 컨트롤러를 구현하려면 먼저 @Controller Annotation을 Class에 적용한다. 그리고, @RequestMapping Annotation을
   이용해서 클라이언트의 요청을 처리할 메서드를 지정한다. 아래 Code는 간단한 컨트롤러를 구현한 Class의 예를 보여주고 있다.

*/


import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/* 
@Controller Annotation은 해당 Class가 SPRING MVC의 컨트롤러를 구현한 Class라는 것을 지정한다.
또한, @RequestMapping Annotation은 값으로 지정한 요청 경로를 처리할 메서드를 설정한다. 라인12의 경우
처리할 경로의 값으로 "/hello.do"를 지정했는데, 이 경우 http://host:port[/컨텍스트 경로]/hello.do 요청을
HelloController Class의 hello() 메서드가 처리하게 된다.
*/
@Controller
public class HelloController {


/*	
ModelAndView를 생성하고 있는데, ModelAndView는 컨트롤러의 처리 결과를 보여줄 뷰와 뷰에서
출력할 모델을 지정할 때 사용된다. 위의 Code의 경우 사용할 뷰 이름으로 "hello"를 지정하였고, 모델에 "greeting"이라는
이름으로 String 타입의 값을 추가하였다.

*/	
@RequestMapping("/hello.do")
public ModelAndView hello(){
	
	ModelAndView mav = new ModelAndView();
	
	mav.setViewName("hello");
	
	//0인자:greeting 는 jsp 호출시 동일하게 해야한다.
	mav.addObject("greeting",getGreeting());
	
	return mav;		
}

	
private String getGreeting(){
	
	int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	
	if(hour >= 6  &&  hour <= 15){
		
		return "좋은 아침입니다";
		
	}else if(hour >=12  &&  hour <= 15 ){
		
		return "점심 식사는 하셨나요?";
		
	}else if(hour >=15  &&  hour <=22){
		
		return "좋은밤 되세요";
	}
	
	return "안녕하세요";
}


}
