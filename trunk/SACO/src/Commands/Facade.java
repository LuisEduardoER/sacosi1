package Commands;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.security.auth.login.LoginException;

import Controller.RentController;
import Controller.UserController;
import Controller.VehiclesController;
import Exceptions.ClientNotRegisteredException;
import Exceptions.ColorException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
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






public class Facade {

	
	private UserController userController;
	private VehiclesController vehController;
	private RentController reController;
	
	public Facade() throws Exception {
		this.userController = UserController.getInstance();
		this.vehController = VehiclesController.getInstance();
		try {
			this.reController = RentController.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void removeVehicle(String plate) throws NoSuchVehicleException, NoVehicleOnDatabaseException{
		vehController.removeVehicle(plate);
		this.vehController.writeVehicles();
	}

	
	public int getAllUsers() {
		return userController.getAllUsers();
	}
	
	public void addUser(String login, String name, String email, String phone) throws InvalidLoginException, EmailException, InvalidNameException, PhoneException, UserAlreadyExistException, InvalidFieldException {
		userController.addUser(login, name, email, phone);
		this.userController.writeXML();
	}
	
	
	public int getAllCustomers(){
		return userController.getAllCustomers();
	}
	
	public void removeCustomer(String key) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException{
		userController.removeCustomer(key);
		this.userController.writeXML();
	}
	
	public void removeUser(String key) throws LoginException, UserNotFoundException, EmailException, NoUserOnDatabaseException, InvalidParameterException{
		userController.removeUser(key);
		this.userController.writeXML();
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, 
	InvalidNameException, 
	PhoneException, 
	CustomerAlreadyExistException, 
	InvalidFieldException {
		
		this.userController.addCustomer(name, email, phone);
		this.userController.writeXML();
	}
	

	public void addVehicle(String type, String model, String color,
			String plate, String year, String price) throws InvalidFieldException,
			NoFieldException, TypeException, ModelException, ColorException,
			PlateException, PriceException, YearException,
			PlateAlreadyExistsException {
		this.vehController.addVehicle(type, model, color, plate, year, price);
		this.vehController.writeVehicles();
	}
	
	public void registerRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {
		this.reController.registerRent(plate, email, initialDate, finalDate);
		this.reController.writeXML();
	}
	
	public void registerLateRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidDateException, InvalidParameterException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {
		this.reController.registerLateRent(plate, email, initialDate, finalDate);
		this.reController.writeXML();
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
	
	public int getAllPendentRentRequests(){
		return this.reController.getAllPendentRentRequests();
	}
	
	public int getAllActiveRents() {
		return this.reController.getAllActiveRents();
	}
	
	public boolean releaseVehicle(String plate) {
		boolean result = this.reController.releaseVehicle(plate); 
		this.reController.writeXML();
		return result;
	}
	
	public String getVehicleSituation(String plate) {
		return this.reController.getVehicleSituation(plate);
	}
	
	public void requestRent(String clientEmail, String plate) throws InvalidParameterException{
		this.reController.requestRent(clientEmail, plate);
		this.reController.writeXML();
	}
	
	public int getAllVehicles(){
		return this.vehController.getAllVehicles();
	}
	
	public void emptyXML() throws FileNotFoundException {
		this.reController.emptyXML();
		this.vehController.emptyXML();
		this.userController.emptyXML();
	}



	public void seeCars() {
		this.reController.seeCars();
		
	}



	public void listAllRequests() {
		this.reController.listAllRequests();
		
	}

	public void listAllNonPendingRents(Calendar date) {
		this.reController.listAllNonPendingRents(date);
		
	}

	public void listAllPendingRents(Calendar date) {
		this.reController.listAllPendingRents(date);
		
	}

}