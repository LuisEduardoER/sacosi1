package LogicalSystem;

import javax.security.auth.login.LoginException;

import Commands.Facade;
import Exceptions.ClientNotRegisteredException;
import Exceptions.ColorException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidLoginException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.ModelException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.NoFieldException;
import Exceptions.NoSuchVehicleException;
import Exceptions.NoUserOnDatabaseException;
import Exceptions.NoVehicleOnDatabaseException;
import Exceptions.PhoneException;
import Exceptions.PlateAlreadyExistsException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Exceptions.YearException;

/**
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
	
	public LogicOfTheAdmin() throws Exception {
		this.facade = new Facade();
	}
	
	public void addUser(String login, String name, String email, String phone) throws InvalidLoginException, EmailException, InvalidNameException, PhoneException, InvalidFieldException, UserAlreadyExistException{
		facade.addUser(login, name, email, phone);
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		facade.addCustomer(name, email, phone);
	}
	
	public void removeCustomer(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException{
		facade.removeCustomer(email);
	}
	
	public void removeUser(String emailOrLogin) throws LoginException, UserNotFoundException, EmailException, NoUserOnDatabaseException, InvalidParameterException{
		facade.removeUser(emailOrLogin);
	}
	
	public void addVehicle(String type, String model, String color, String year, String plate, String price) throws InvalidFieldException, NoFieldException, TypeException, ModelException, ColorException, PlateException, PriceException, YearException, PlateAlreadyExistsException{
		facade.addVehicle(type, model, color, plate, year, price);
	}
	
	public void removeVehicle(String plate) throws NoSuchVehicleException, NoVehicleOnDatabaseException{
		facade.removeVehicle(plate);
	}
}
