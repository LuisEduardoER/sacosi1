package dao;

import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;

public interface UserDAO {

	public void addCustomer(String name, String email, String phone)
	throws AlreadyExistException, InvalidFieldException, EmptyFieldException;
	
	public int getAllCustomers();
	
	public void removeCustomer(String email)
	throws InvalidFieldException, NotExistException;
	
	public void addUser(String login, String name, String email, String phone)
	throws InvalidFieldException, AlreadyExistException, EmptyFieldException;
	
	public int getAllUsers();
	
	public void removeUser(String emailOrLogin) throws LoginException, 
	InvalidFieldException, NotExistException;
	
	public void cleanXML() throws FileNotFoundException;
	
	public boolean find(String dataOne, String dataTwo);
}
