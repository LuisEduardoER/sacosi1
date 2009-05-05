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
public class NoCustomerOnDatabaseException extends Exception {
	
	/**
	 * 
	 * @param msg
	 */
	public NoCustomerOnDatabaseException(String msg){
		super(msg);
	}
}
