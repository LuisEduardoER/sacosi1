package LogicalSystem;

import Commands.Facade;
import Exceptions.ClientNotRegisteredException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.PhoneException;

/**
 * Logica do cliente
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 */
public class LogicOfTheCustomer {
	Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheCustomer() throws Exception {
		facade = new Facade();
	}

	/**
	 * Adiciona cliente
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws CustomerAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void addCustomer(String name, String email, String phone)
			throws EmailException, InvalidNameException, PhoneException,
			CustomerAlreadyExistException, InvalidFieldException {
		facade.addCustomer(name, email, phone);
	}

	/**
	 * Remove cliente
	 * 
	 * @param email
	 * @throws ClientNotRegisteredException
	 * @throws NoCustomerOnDatabaseException
	 * @throws InvalidParameterException
	 */
	public void removeCustomer(String email)
			throws ClientNotRegisteredException, NoCustomerOnDatabaseException,
			InvalidParameterException {
		facade.removeCustomer(email);
	}

	/**
	 * Visualiza carros
	 */
	public void seeCars() {
		facade.seeCars();
	}
}
