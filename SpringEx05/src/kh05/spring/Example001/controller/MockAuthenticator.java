package kh05.spring.Example001.controller;

public class MockAuthenticator implements Authenticator{

	@Override
	public void authenticate(String id, String password) {
		// TODO Auto-generated method stub
		
		if(!id.equals("utopiazn")){
			
			throw new AuthenticationException("invalid id" +id );
		}
			
	}

}
