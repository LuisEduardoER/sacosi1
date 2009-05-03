package LogicalSystem;

import Controller.RentController;
import Controller.UserController;
import Controller.VehiclesController;
import Exceptions.ClientNotRegisteredException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.PhoneException;

public class LogicOfTheCustomer {
	UserController userController;
	RentController rentController;
	VehiclesController vehicleController;
	
	public LogicOfTheCustomer(){
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		userController.addCustomer(name, email, phone);
	}
	
	public void removeCustomer(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException{
		userController.removeCustomer(email);
	}
	
	public void seeCars(){
		rentController.seeCars();
	}
}
