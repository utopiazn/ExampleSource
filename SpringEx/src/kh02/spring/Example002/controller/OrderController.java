package kh02.spring.Example002.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
폼의 <input>이나 <select>등의 name에 인덱스 값을 포함시키면 List 타입의 프로퍼티에 값을 전달 받을 수 있다. 컨트롤러 
Code에서는 다음과 같이 커맨드 객체를 @RequestMapping 메서드에 지정해주기만 하면 된다.

*/

@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(){
		
		return "order/orderForm";
	}

	@RequestMapping(method =  RequestMethod.POST)
	public String submit(OrderCommand orderCommand){
		
		return "order/orderCompletion";
	}
}
