package Exceptions;


public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = 3698122668479070214L;

	public InvalidLoginException(String msg) {
		super(msg);
	}
}
