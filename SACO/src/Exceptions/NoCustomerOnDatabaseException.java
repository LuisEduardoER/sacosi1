package Exceptions;

public class NoCustomerOnDatabaseException extends Exception {
	public NoCustomerOnDatabaseException(String msg){
		super(msg);
	}
}
