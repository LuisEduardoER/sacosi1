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
public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = 3698122668479070214L;
	
/**
 * 
 * @param msg
 */
	public InvalidLoginException(String msg) {
		super(msg);
	}
}
