package kh05.spring.Example003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticleController {

	
	//읽기
	@RequestMapping(value="/article/{id}",method=RequestMethod.GET)
	public String read(
				@PathVariable("id") Integer id, Model model){	
		System.out.println("읽기");
		model.addAttribute("article",new Article(id));		
		
		return "article/read";
	}
	
	//삭제
	@RequestMapping(value="/article/{id}",method=RequestMethod.DELETE)
	public String delete(
				@PathVariable("id") Integer id, Model model){
		
		model.addAttribute("article", new Article(id));
		
		return "article/delet";
	}
	
	
	//수정
	@RequestMapping(value="/article/{id}", method=RequestMethod.PUT)
	public String modify(
				@PathVariable("id") Integer id, Model model) {
		System.out.println("수정");
		model.addAttribute("article", new Article(id));
		
		return "article/modify";
	}
	
	//쓰기
	@RequestMapping(value="/article", method=RequestMethod.POST)
	public String write(Model model) {
		
		System.out.println("쓰기");
		model.addAttribute("article", new Article(2));
		return "article/write";
	}
	
	
	//폼
	@RequestMapping("/articleForm.do")
	public String testForm(Model model) {
		
		System.out.println("폼");
		model.addAttribute("article", new Article(1));
		return "article/testForm";
	}
	
	
}
