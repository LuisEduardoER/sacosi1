package Tests;

import javax.security.auth.login.LoginException;

import junit.framework.TestCase;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.NameException;
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
	
	public void addUser(String login, String name, String email, String phone) throws LoginException, EmailException, NameException, PhoneException, UserAlreadyExistException, InvalidFieldException {
		test.addUser(login, name, email, phone);
	}
}
