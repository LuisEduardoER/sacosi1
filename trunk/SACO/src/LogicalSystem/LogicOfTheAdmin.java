package LogicalSystem;

import javax.security.auth.login.LoginException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Exceptions.EmptyFieldException;

/**
 * Logica do administrador
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 * 
 */
public class LogicOfTheAdmin extends LogicOfTheUser {
	private Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheAdmin() throws Exception {
		this.facade = new Facade();
	}

	/**
	 * Adiciona usuario
	 * 
	 * @param login
	 * @param name
	 * @param email
	 * @param phone
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException 
	 */
	public void addUser(String login, String name, String email, String phone)
			throws InvalidFieldException, AlreadyExistException, EmptyFieldException {
		facade.addUser(login, name, email, phone);
	}

	/**
	 * Adiciona cliente
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException 
	 */
	public void addCustomer(String name, String email, String phone)
			throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		facade.addCustomer(name, email, phone);
	}

	/**
	 * Remove cliente
	 * 
	 * @param email
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeCustomer(String email)
			throws NotExistException, InvalidFieldException {
		facade.removeCustomer(email);
	}

	/**
	 * Remove usuario
	 * 
	 * @param emailOrLogin
	 * @throws LoginException
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeUser(String emailOrLogin) throws LoginException,
			NotExistException, InvalidFieldException {
		facade.removeUser(emailOrLogin);
	}

	/**
	 * adiciona veiculo
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param year
	 * @param plate
	 * @param price
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 * @throws AlreadyExistsException
	 */
	public void addVehicle(String type, String model, String color,
			String year, String plate, String price)
			throws InvalidFieldException, EmptyFieldException, AlreadyExistException {
		facade.addVehicle(type, model, color, plate, year, price);
	}

	/**
	 * remove veiculo
	 * 
	 * @param plate
	 * @throws NotExistException
	 */
	public void removeVehicle(String plate) throws NotExistException{
		facade.removeVehicle(plate);
	}
}
