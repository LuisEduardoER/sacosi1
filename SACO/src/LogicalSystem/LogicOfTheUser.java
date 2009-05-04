package LogicalSystem;

import java.util.Calendar;

import Commands.Facade;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.PhoneException;
import Users.Alugadores;

/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
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
	
	public void addManyRents(Alugadores customer, String[] plates, String[] initialDates, String[] devolutionDates) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException{
		facade.addManyRents(customer, plates, initialDates, devolutionDates);
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
		facade.getAllVehiclesSituation();
	}
	
	public void seeCurrentRent(Calendar date){
		facade.listAllNonPendingRents(date);
	}
	
	public void seeLateRent(Calendar date){
		facade.listAllPendingRents(date);
	}
}
