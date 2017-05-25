package smboard.member.service;

import smboard.member.model.MemberModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		MemberModel memberModel =(MemberModel) target;
		
	
		
		errorCheck(memberModel.getUserId(),"userId",errors);
		errorCheck(memberModel.getUserPw(),"userPw",errors);			
		errorCheck(memberModel.getUserName(),"userName",errors);	
		
	}

	
	public void errorCheck(String str,String name,Errors error){
		
		if(str == null || str.trim().isEmpty() ){
			
			error.rejectValue(name, "required");
		}
		
	}
}
