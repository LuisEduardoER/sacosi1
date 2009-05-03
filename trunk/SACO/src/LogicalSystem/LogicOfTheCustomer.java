package LogicalSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import Vehicles.Vehicle;

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
		List<Vehicle> list = new ArrayList<Vehicle>();
		Iterator<Vehicle> it = vehicleController.getRegisteredVehicles().iterator();
		while (it.hasNext()){
			Vehicle vehicle = it.next();
			if (!rentController.vehicleIsRent(vehicle.getPlate()))
				list.add(vehicle);
		}
		List<Integer> listOfYears = vehicleController.getListOfYears();
		for (int i = 0; i < list.size(); i++)
			vehicleController.printCarsByYear(list, listOfYears.get(i));
	}
}
