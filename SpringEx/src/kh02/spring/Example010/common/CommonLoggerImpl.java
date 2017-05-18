package kh02.spring.Example010.common;

public class CommonLoggerImpl implements CommonLogger {

	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		
		System.out.println("CommonLogger -" +message);
	}

}
