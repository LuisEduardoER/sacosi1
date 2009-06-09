package Exceptions;

/**
 * Excecao lancada quando um campo ja existe
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class AlreadyExistException extends Exception {
	
	/**
	 * 
	 * @param msg
	 */
	public AlreadyExistException(String msg) {
		super(msg);
	}

}
