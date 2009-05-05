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
public class NoUserOnDatabaseException extends Exception{
	/**
	 * 
	 * @param msg
	 */
	public NoUserOnDatabaseException(String msg){
		super(msg);
	}

}
