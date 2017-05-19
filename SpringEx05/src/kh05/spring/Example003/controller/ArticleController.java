package kh05.spring.Example003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticleController {

	@RequestMapping(value="/article/{id}",method=RequestMethod.GET)
	public String read(){
		
		return "article/read";
	}
}
