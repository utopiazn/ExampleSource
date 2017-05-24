package utils;

import logic.User;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserEntryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		// TODO Auto-generated method stub
		
		User user = (User) command;
		
		//hasLength :0
		stringUtils(user.getUserId()	,"userId"	,errors	,0);		
		stringUtils(user.getPassword()	,"password"	,errors	,0);
		stringUtils(user.getUserName()	,"userName"	,errors	,0);

		//hasText:1
		stringUtils(user.getPostCode()	,"postCode"	,errors	,1);
		stringUtils(user.getAddress()	,"address"	,errors	,1);
		stringUtils(user.getEmail()		,"email"	,errors	,1);
		
	
		if(errors.hasErrors()){
			
			// 오류가 있으면 메세지 입력 오류 메세지를 추가
			errors.reject("error.inpurt.user");
		}
		
	
		
	}
	
	public void stringUtils(String value, String name , Errors errors ,int type){
		
		if(type== 0){
		
			if(!StringUtils.hasLength(value)){
				
				errors.rejectValue(name, "error.required");
			}
		}else if(type== 1){
			
			if(!StringUtils.hasText(value)){
				
				errors.rejectValue(name, "error.required");
			}
			
		}
			
		
	}

}
