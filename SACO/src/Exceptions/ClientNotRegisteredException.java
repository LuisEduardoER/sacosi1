package Exceptions;

public class ClientNotRegisteredException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ClientNotRegisteredException(String message) {
		super(message);
	}

}
