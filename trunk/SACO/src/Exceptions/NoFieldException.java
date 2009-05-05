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
public class NoFieldException extends Exception {
	
	private static final long serialVersionUID = -7017646333565985665L;
	
	/**
	 * 
	 * @param msg
	 */
	public NoFieldException(String msg) {
		super(msg);
	}
}
