package kh02.spring.Example012.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class QueryLogController {
	
	@ModelAttribute("command")
	public QueryLogCommand formBacking(){
		
		return new QueryLogCommand();
	}
		
	
	@RequestMapping("/log/query.do")
	public String query(@ModelAttribute("command") QueryLogCommand command,BindingResult result){
		
		return "log/logList";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
					
	}
}
