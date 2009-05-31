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
public class InvalidFieldException extends Exception{
	
	private static final long serialVersionUID = 1822756954718319480L;
	
	/**
	 * 
	 * @param msg
	 */
	public InvalidFieldException(String msg) {
		super(msg);
	}
}
