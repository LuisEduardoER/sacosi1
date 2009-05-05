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
public class CustomerAlreadyExistException extends Exception {

	private static final long serialVersionUID = 363326863105318054L;
	/**
	 * 
	 * @param message
	 */
	public CustomerAlreadyExistException(String message) {
		super(message);
	}
}
