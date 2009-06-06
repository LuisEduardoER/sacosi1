package LogicalSystem;

import java.util.Calendar;

import javax.mail.MessagingException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Users.Alugadores;

/**
 * Logica do usuario
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

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheUser() throws Exception {
		this.facade = new Facade();
	}
	
	
	//FIXME o mesmo que deve-se fazer em LogicOfTheAdmin
	public void inicia() {
		
	}

	/**
	 * Visualiza requisições
	 */
	public void seeRequisitions() {
		facade.listAllRequests();
	}

	/**
	 * Adiciona aluguel
	 * 
	 * @param email
	 * @param plate
	 * @param initialDate
	 * @param finalDate
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException 
	 */
	public void addRent(String email, String plate, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException {
		facade.registerRent(plate, email, initialDate, finalDate);
	}

	/**
	 * Adiciona varios alugueis
	 * 
	 * @param customer
	 * @param plates
	 * @param initialDates
	 * @param devolutionDates
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException 
	 */
	public void addManyRents(Alugadores customer, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		facade.addManyRents(customer, plates, initialDates, devolutionDates);
	}

	/**
	 * Libera aluguel
	 * 
	 * @param plate
	 * @throws MessagingException 
	 */
	public void releaseRent(String plate) throws MessagingException {
		facade.releaseVehicle(plate);
	}

	/**
	 * Registra aluguel atrasado
	 * 
	 * @param email
	 * @param plate
	 * @param initialDate
	 * @param finalDate
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException 
	 */
	public void registerLateRent(String email, String plate,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException {
		facade.registerLateRent(plate, email, initialDate, finalDate);
	}

	/**
	 * obtem situação do veiculo
	 * 
	 * @param plate
	 */
	public void getVehicleSituation(String plate) {
		facade.getVehicleSituation(plate);
	}

	/**
	 * obtem situação de todos os veiculos
	 */
	public void getAllVehiclesSituation() {
		facade.getAllVehiclesSituation();
	}

	/**
	 * Visualiza aluguel corrente
	 * 
	 * @param date
	 */
	public void seeCurrentRent(Calendar date) {
		facade.listAllNonPendingRents(date);
	}

	/**
	 * Visualiza aluguel atrasado
	 * 
	 * @param date
	 */
	public void seeLateRent(Calendar date) {
		facade.listAllPendingRents(date);
	}
	
	public void notifyAboutRequestRelease() throws MessagingException{
		facade.notifyAboutRequestRelease();
	}
}
