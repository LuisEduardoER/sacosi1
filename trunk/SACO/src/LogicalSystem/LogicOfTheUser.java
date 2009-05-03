package LogicalSystem;

import javax.security.auth.login.LoginException;

import Controller.RentController;
import Controller.UserController;
import Controller.VehiclesController;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.NoUserOnDatabaseException;
import Exceptions.PhoneException;
import Exceptions.UserNotFoundException;

public class LogicOfTheUser {
	private UserController userController;
	private RentController rentController;
	private VehiclesController vehicleController;
	
	public LogicOfTheUser(){
		
	}
	
	public void removeUser(String emailOrLogin) throws LoginException, UserNotFoundException, EmailException, NoUserOnDatabaseException, InvalidParameterException{
		userController.removeUser(emailOrLogin);
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
