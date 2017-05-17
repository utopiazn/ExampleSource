package kh02.spring.Example007.validator;


import kh02.spring.Example007.model.Address;
import kh02.spring.Example007.model.MemberInfo;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class MemberInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberInfo.class.isAssignableFrom(clazz);
		//return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		MemberInfo memberInfo = (MemberInfo) target;
		
		
		if(memberInfo.getId() == null || memberInfo.getId().trim().isEmpty()){
		
			errors.rejectValue("id", "required");
		}
		
		if(memberInfo.getName() == null || memberInfo.getName().trim().isEmpty()){
			
			errors.rejectValue("name", "required");
		}
		
		Address address = memberInfo.getAddress();
		
		if(address == null){
			errors.rejectValue("address", "required");
		}
		
		if(address != null){
			
			errors.pushNestedPath("address");
			
			try{
				
				if(address.getZipcode() == null || address.getZipcode().trim().isEmpty()){
					
					errors.rejectValue("zipcode", "required");
				}
				
				if(address.getAddress1() == null || address.getAddress1().trim().isEmpty()){
					
					errors.rejectValue("address1", "required");
				}
			}finally{
				errors.popNestedPath();
			}
		}
		
	}

}
