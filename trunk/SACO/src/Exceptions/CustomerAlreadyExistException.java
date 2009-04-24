package Exceptions;

public class CustomerAlreadyExistException extends Exception {

	private static final long serialVersionUID = 363326863105318054L;

	public CustomerAlreadyExistException(String message) {
		super(message);
	}
}
