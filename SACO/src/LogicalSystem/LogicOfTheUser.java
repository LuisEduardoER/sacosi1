package LogicalSystem;

import Controller.RentController;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.PhoneException;

public class LogicOfTheUser {
	
	private RentController rentController;
	
	public LogicOfTheUser(){
	}
	
	public void seeRequisitions(){
		rentController.listAllRequests();
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
	
	public void seeCurrentRent(String date){
		rentController.listAllNonPendingRents(date);
	}
	
	public void seeLateRent(String date){
		rentController.listAllPendingRents(date);
	}
}
