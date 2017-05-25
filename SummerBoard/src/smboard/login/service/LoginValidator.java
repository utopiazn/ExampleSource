package smboard.login.service;

import smboard.login.model.LoginSessionModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LoginSessionModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LoginSessionModel loginModel = (LoginSessionModel) target;
		
		//check userId field
		if(loginModel.getUserId() == null || loginModel.getUserId().trim().isEmpty()){
			
			errors.rejectValue("userId","required");
		}
		
		//check userPw field
		if(loginModel.getUserPw() == null || loginModel.getUserPw().trim().isEmpty()){
			
			errors.rejectValue("userPw", "required");
		}
	}

}
