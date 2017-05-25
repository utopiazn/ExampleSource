package smboard.member.controller;

import smboard.member.model.MemberModel;
import smboard.member.service.MemberService;
import smboard.member.service.MemberValidatior;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/member")
public class MemberController {
	


	private ApplicationContext context;
	
	@RequestMapping("/join.do")
	public String memberJoin(){
		System.out.println("회원 가입 폼");
		
		return "/board/join";
	}
	
	@RequestMapping(value="/join.do", method = RequestMethod.POST)
	public ModelAndView AddMember(
				@ModelAttribute("MemberModel") MemberModel memberModel,
				BindingResult result){
		
		
		System.out.println("회원 가입 폼edd");
		
		
		ModelAndView mav = new ModelAndView();
		
		new MemberValidatior().validate(memberModel, result);
		
		if(result.hasErrors()){
			
			mav.setViewName("/board/join");
			
			return mav;
		}
		
		context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
		
		MemberService memberService = (MemberService) context.getBean("memberService");
		
		MemberModel checkMemberModel = memberService.findByUserId(memberModel.getUserId());
		
		
		if(checkMemberModel != null){
			
			mav.addObject("errCode",1); //userId already exist
			mav.setViewName("/board/join");
			
			return mav;
		}
		
		if(memberService.addMember(memberModel)){
			
			mav.addObject("errCode",3);
			mav.setViewName("/board/login"); // success of add new member move to login page
			
			return mav;
			
		}else{
			
			mav.addObject("errCode",3); //failed to add new member
			mav.setViewName("/board/join"); 
		
			return mav;
		}
	}
	
}
