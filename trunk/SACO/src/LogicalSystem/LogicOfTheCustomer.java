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

public class LogicOfTheCustomer {
	Facade facade;
	
	public LogicOfTheCustomer() throws Exception{
		facade = new Facade();
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		facade.addCustomer(name, email, phone);
	}
	
	public void removeCustomer(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException{
		facade.removeCustomer(email);
	}
	
	public void seeCars(){
		facade.seeCars();
	}
}
