package kh02.spring.Example007.controller;

import javax.servlet.http.HttpServletRequest;

import kh02.spring.Example007.model.Address;
import kh02.spring.Example007.model.MemberInfo;

import kh02.spring.Example007.validator.MemberInfoValidator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account/create.do")
public class CreateAccountController {
	
	@ModelAttribute("command")
	public MemberInfo formBacking(HttpServletRequest request){
		
		if(request.getMethod().equalsIgnoreCase("GET")){
			
			MemberInfo mi = new MemberInfo();			
			Address address = new Address();
			
			
			address.setAddress1(autoDetectZipcode(request.getRemoteAddr()));
			mi.setAddress(address);
			
			return mi;
			
		}else{
			
			return new MemberInfo();
		}
	}
	
	private String autoDetectZipcode(String remoteAddr) {
		return "000000";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "account/creationForm";
		                	}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(
				@ModelAttribute("command") MemberInfo MemberInfo,
				BindingResult result){
		
		new MemberInfoValidator().validate(MemberInfo, result);
		
		if(result.hasErrors()){
			
			return "account/creationForm";
			              
		}
		
		return "account/created";
		
	}


}