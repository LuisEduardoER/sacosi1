package Exceptions;
/**
 * Excecao lancada quando um campo nao existe
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class NotExistException extends Exception {
	/**
	 * 
	 * @param msg
	 */
	public NotExistException(String msg) {
		super(msg);
	}

}
