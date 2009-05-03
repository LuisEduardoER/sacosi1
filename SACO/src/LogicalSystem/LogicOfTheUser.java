package LogicalSystem;

import Commands.Facade;
import Controller.RentController;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.PhoneException;

public class LogicOfTheUser {
	
	private Facade facade;
	
	public LogicOfTheUser() throws Exception{
		this.facade = new Facade();
	}
	
	public void seeRequisitions(){
		facade.listAllRequests();
	}
	
	public void addRent(String email, String plate, String initialDate, String finalDate) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		facade.registerRent(plate, email, initialDate, finalDate);
	}
	
	public void addManyRents(){
		//Implementar um metodo que faca varias leituras para registrar aluguel
	}
	
	public void releaseRent(String plate){
		facade.releaseVehicle(plate);
	}
	
	public void notifyRelease(String plate){
		System.out.println("Email com confirmacao de liberacao enviado!");
	}
	
	public void registerLateRent(String email, String plate, String initialDate, String finalDate) throws InvalidDateException, InvalidParameterException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		facade.registerLateRent(plate, email, initialDate, finalDate);
	}
	
	public void notifyLateRent(){
		System.out.println("Email com notificacao sobre atraso no aluguel");
	}
	
	public void getVehicleSituation(String plate){
		facade.getVehicleSituation(plate);
	}
	
	public void getAllVehiclesSituation(){
		//Implementar um metodo que faca o relatorio da situacao de todos os carros
	}
	
	public void seeCurrentRent(String date){
		facade.listAllNonPendingRents(date);
	}
	
	public void seeLateRent(String date){
		facade.listAllPendingRents(date);
	}
}
