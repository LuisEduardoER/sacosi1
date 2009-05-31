package LogicalSystem;

import java.util.Calendar;

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
	 */
	public void releaseRent(String plate) {
		facade.releaseVehicle(plate);
	}

	/**
	 * Notifica liberação de aluguel
	 * 
	 * @param plate
	 */
	public void notifyRelease(String plate) {
		System.out.println("Email com confirmacao de liberacao enviado!");
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
	 */
	public void registerLateRent(String email, String plate,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException {
		facade.registerLateRent(plate, email, initialDate, finalDate);
	}

	/**
	 * Notifica aluguel atrasado
	 */
	public void notifyLateRent() {
		System.out.println("Email com notificacao sobre atraso no aluguel");
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
}
