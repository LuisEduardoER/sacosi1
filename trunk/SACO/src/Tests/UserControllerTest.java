package Tests;

import junit.framework.TestCase;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidLoginException;
import Exceptions.InvalidNameException;
import Exceptions.PhoneException;
import Exceptions.UserAlreadyExistException;
import Users.UserController;

public class UserControllerTest extends TestCase {
	
	private UserController test;
	
	public UserControllerTest() {
		test = UserController.getInstance();
	}
	
	public int getAllUsers() {
		return test.getAllUsers();
	}
	
	public void addUser(String login, String name, String email, String phone) throws InvalidLoginException, EmailException, InvalidNameException, PhoneException, UserAlreadyExistException, InvalidFieldException {
		test.addUser(login, name, email, phone);
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		test.addCustomer(name, email, phone);
	}
	
	public int getAllCustomers(){
		return test.getAllCustomers();
	}
}
