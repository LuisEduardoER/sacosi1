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
public class VehicleAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -2186727850394433501L;
	
	/**
	 * 
	 * @param msg
	 */
	public VehicleAlreadyExistsException(String msg) {
		super(msg);
	}
}
