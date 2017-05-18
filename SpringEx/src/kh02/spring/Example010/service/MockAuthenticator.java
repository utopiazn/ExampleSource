package kh02.spring.Example010.service;

import kh02.spring.Example010.common.CommonLogger;


public class MockAuthenticator implements Authenticator {

	private CommonLogger commonLogger;
	
	@Override
	public void authenticate(LoginCommand loginCommand) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		if(!loginCommand.getUserId().equals(loginCommand.getPassword())){
			
			commonLogger.log("인증 에러-"+loginCommand.getUserId());
			throw new AuthenticationException();
		}
		
		
	}

	public void setCommonLogger(CommonLogger commonLogger) {
		this.commonLogger = commonLogger;
	}
	
	

}
