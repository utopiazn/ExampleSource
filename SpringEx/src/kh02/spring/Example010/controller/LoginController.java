package kh02.spring.Example010.controller;

import java.sql.Date;
import java.text.DateFormat;

import javax.validation.Valid;

import kh02.spring.Example010.service.AuthenticationException;
import kh02.spring.Example010.service.Authenticator;
import kh02.spring.Example010.service.LoginCommand;
import kh02.spring.Example010.validation.LoginCommandValidator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;  //에러 받는 빈
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/long/login.do")
public class LoginController {
	
	private String formViewName="login/form";
	
	@Autowired
	private Authenticator authenticator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String from(){
		return formViewName;
	}
	
	@ModelAttribute
	public LoginCommand formBacking(){
		
		return new LoginCommand();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(
				@Valid LoginCommand loginCommand,
				BindingResult result){
		
		if(result.hasErrors()){
		
			return formViewName;
		}
		
		try{
			
			authenticator.authenticate(loginCommand);
			
			return "redirect:/index.jsp";
			
		}catch(AuthenticationException e){
			
			result.reject("invalidIdOrPassword", new Object[] { loginCommand.getUserId() }, null);
			
			return formViewName;
			
		}
		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
	
		binder.setValidator(new LoginCommandValidator());
		
	}
	
	public void setAuthenticator(Authenticator loginService) {
		
		this.authenticator = loginService;
	}

}
