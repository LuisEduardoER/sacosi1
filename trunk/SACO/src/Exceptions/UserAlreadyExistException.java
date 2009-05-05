package Exceptions;
/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class UserAlreadyExistException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param message
	 */
	public UserAlreadyExistException(String message) {
		super(message);
	}

}
