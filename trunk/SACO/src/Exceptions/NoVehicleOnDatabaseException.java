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
public class NoVehicleOnDatabaseException extends Exception{
	/**
	 * 
	 * @param msg
	 */
	public NoVehicleOnDatabaseException(String msg) {
		super(msg);
	}
}
