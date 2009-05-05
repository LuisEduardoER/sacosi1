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
public class InvalidNameException extends Exception {

	private static final long serialVersionUID = 3979213664745987544L;
	
	/**
	 * 
	 * @param msg
	 */
	public InvalidNameException(String msg) {
		super(msg);
	}
}
