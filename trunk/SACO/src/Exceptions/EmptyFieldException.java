package Exceptions;
/**
 * Excecao gerada quando um campo esta vazio
  * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class EmptyFieldException extends Exception {
	/**
	 * 
	 * @param msg
	 */
	public EmptyFieldException(String msg) {
		super(msg);
	}

}
