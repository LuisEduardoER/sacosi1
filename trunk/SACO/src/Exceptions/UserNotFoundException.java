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
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
