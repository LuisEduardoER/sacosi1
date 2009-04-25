package Exceptions;
public class InvalidNameException extends Exception {

	private static final long serialVersionUID = 3979213664745987544L;

	public InvalidNameException(String msg) {
		super(msg);
	}
}
