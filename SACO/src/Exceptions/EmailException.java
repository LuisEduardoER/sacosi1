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
public class EmailException extends Exception {

	private static final long serialVersionUID = -3575720609773917150L;
	
	/**
	 * 
	 * @param msg
	 */
	public EmailException(String msg) {
		super(msg);
	}
}
