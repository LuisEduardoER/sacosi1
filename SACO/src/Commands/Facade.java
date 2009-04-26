package Commands;

import Controller.RentController;
import Controller.VehiclesController;
import Exceptions.ColorException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.ModelException;
import Exceptions.NoFieldException;
import Exceptions.PhoneException;
import Exceptions.PlateAlreadyExistsException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.YearException;
import Users.UserController;





public class Facade {

	
	private UserController userController;
	private VehiclesController vehController;
	private RentController reController;
	
	public Facade() {
		this.userController = UserController.getInstance();
		this.vehController = VehiclesController.getInstance();
		this.reController = RentController.getInstance();
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, 
	InvalidNameException, 
	PhoneException, 
	CustomerAlreadyExistException, 
	InvalidFieldException {
		
		this.userController.addCustomer(name, email, phone);
	}
	
	public void registerLateRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException {
		this.reController.registerLateRent(plate, email, initialDate, finalDate);
	}
	
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price) throws InvalidFieldException,
			NoFieldException, TypeException, ModelException, ColorException,
			PlateException, PriceException, YearException,
			PlateAlreadyExistsException {
		this.vehController.addVehicle(type, model, color, plate, year, price);
	}
	
	public void registerRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException {
		this.reController.registerRent(plate, email, initialDate, finalDate);
	}
	
	public String getRentSituation(String email, String plate, String inicialDate,
			String finalDate){
		return this.reController.getRentSituation(email, plate, inicialDate, finalDate);
	}
	
	public int getAllRents() {
		return this.reController.getAllRents();
	}
	
	public int getRentsByCustomer(String email) {
		return this.reController.getRentsByCustomer(email);
	}
	
	public int getRentsByVehicle(String plate) {
		return this.reController.getRentsByVehicle(plate);
	}
}