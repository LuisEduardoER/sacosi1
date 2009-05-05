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
public class ClientNotRegisteredException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public ClientNotRegisteredException(String message) {
		super(message);
	}

}
