package kh02.spring.Example010.service;

public interface Authenticator {
	
	void authenticate(LoginCommand loginCommand)
			throws AuthenticationException;

}
