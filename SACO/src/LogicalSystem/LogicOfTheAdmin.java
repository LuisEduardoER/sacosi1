package LogicalSystem;

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

public class LogicOfTheAdmin {
	private UserController userController;
	private RentController rentController;
	private VehiclesController vehicleController;
	
	public LogicOfTheAdmin(){
		
	}
	
	public void addUser(String login, String name, String email, String phone) throws InvalidLoginException, EmailException, InvalidNameException, PhoneException, InvalidFieldException, UserAlreadyExistException{
		userController.addUser(login, name, email, phone);
	}
	
	public void addCustomer(String name, String email, String phone) throws EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		userController.addCustomer(name, email, phone);
	}
	
	public void removeCustomer(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException{
		userController.removeCustomer(email);
	}
	
	public void removeUser(String emailOrLogin) throws LoginException, UserNotFoundException, EmailException, NoUserOnDatabaseException, InvalidParameterException{
		userController.removeUser(emailOrLogin);
	}
	
	public void addVehicle(String type, String model, String color, String year, String plate, String price) throws InvalidFieldException, NoFieldException, TypeException, ModelException, ColorException, PlateException, PriceException, YearException, PlateAlreadyExistsException{
		vehicleController.addVehicle(type, model, color, plate, year, price);
	}
	
	public void removeVehicle(String plate) throws NoSuchVehicleException, NoVehicleOnDatabaseException{
		vehicleController.removeVehicle(plate);
	}
	
	public void seeRequisitions(){
		//Implementar .toString() das requisicoes
	}
	
	public void addRent(String email, String plate, String initialDate, String finalDate) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		rentController.registerRent(plate, email, initialDate, finalDate);
	}
	
	public void addManyRents(){
		//Implementar um metodo que faca varias leituras para registrar aluguel
	}
	
	public void releaseRent(String plate){
		rentController.releaseVehicle(plate);
	}
	
	public void notifyRelease(String plate){
		System.out.println("Email com confirmacao de liberacao enviado!");
	}
	
	public void registerLateRent(String email, String plate, String initialDate, String finalDate) throws InvalidDateException, InvalidParameterException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		rentController.registerLateRent(plate, email, initialDate, finalDate);
	}
	
	public void notifyLateRent(){
		System.out.println("Email com notificacao sobre atraso no aluguel");
	}
	
	public void getVehicleSituation(String plate){
		rentController.getVehicleSituation(plate);
	}
	
	public void getAllVehiclesSituation(){
		//Implementar um metodo que faca o relatorio da situacao de todos os carros
	}
	
	public void seeCurrentRent(){
		//Implementar metodo de acordo com a especificacao 4.16
	}
	
	public void seeLateRent(){
		//Implementar metodo de acordo com a especificacao 4.17
	}
}
