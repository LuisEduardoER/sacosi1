package Tests;

import javax.security.auth.login.LoginException;

import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.NameException;
import Exceptions.PhoneException;
import Exceptions.UserAlreadyExistException;
import Users.UserController;

public class UserControllerTest {
	
	private UserController test;
	
	public UserControllerTest() {
		UserController.getInstance();
	}
	
	public int getAllUsers() {
		return test.getAllUsers();
	}
	
	public void addUser(String login, String name, String email, String phone) throws LoginException, EmailException, NameException, PhoneException, InvalidFieldException, UserAlreadyExistException {
		test.addUser(login, name, email, phone);
	}
	
}
