package com.david.test.exception;

/**
 * 用户异常
 * @author jia ji
 *
 */
public class UserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println("USER-EXCEPTION: "+message);
	}

}
