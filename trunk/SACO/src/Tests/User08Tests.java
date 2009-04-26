package Tests;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;
import Users.UserController;
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

public class User08Tests {
	
	
	private UserController userController;
	private VehiclesController vehController;
	private RentController reController;
	
	public User08Tests() {
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
	
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("tst8.txt");

		//Instantiate the Monopoly Game façade

		User08Tests test = new User08Tests();

		//Instantiate EasyAccept façade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());

	}
}
