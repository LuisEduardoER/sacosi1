package dao;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.mail.MessagingException;

import Users.Alugadores;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;

/**
 * Interface do DAO
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 *
 */
public interface RentDAO {
	
	/**
	 * registra aluguel atrasaod
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException
	 * @throws Exception
	 */
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException, Exception;
	
	/**
	 * registra aluguel
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 * @throws Exception
	 */
	public void registerRent(String plate, String email, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException, Exception;
	
	/**
	 * libera um veiculo
	 * @param plate
	 * @return
	 * @throws Exception
	 */
	public boolean releaseVehicle(String plate) throws Exception;
	
	/**
	 * obtem situacao do alugel
	 * @param email
	 * @param plate
	 * @param inicialDate
	 * @param finalDate
	 * @return
	 * @throws Exception
	 */
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) throws Exception;
	
	/**
	 * retorna o numero de alugueis ativos
	 * @return
	 * @throws Exception
	 */
	public int getAllActiveRents() throws Exception;
	
	/**
	 * retorna a situacao de um veiculo
	 * @param plate
	 * @return
	 * @throws Exception
	 */
	public String getVehicleSituation(String plate) throws Exception;
	
	/**
	 * limpa o banco de dados
	 * @throws FileNotFoundException
	 */
	public void cleanBD() throws FileNotFoundException;
	
	/**
	 * lista todos os alugueis pedentes
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String listAllPendingRents(Calendar date) throws Exception;
	
	/**
	 * lista todos os alugueis nao pendentes
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String listAllNonPendingRents(Calendar date) throws Exception;
	
	/**
	 * retorna a quantidade de alugueis
	 * @return
	 */
	public int getAllRents();
	
	/**
	 * retorna um aluguel por cliente
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public int getRentsByCustomer(String email) throws Exception;
	
	/**
	 * retorna aluguel por veiculo
	 * @param plate
	 * @return
	 * @throws Exception
	 */
	public int getRentsByVehicle(String plate) throws Exception;
	
	/**
	 * retorna a quantidade de algueis pendentes
	 * @return
	 */
	public int getAllPendentRentRequests();
	
	/**
	 * faz uma requisicao de aluguel
	 * @param clientEmail
	 * @param plate
	 * @throws EmptyFieldException
	 */
	public void requestRent(String clientEmail, String plate)
	throws EmptyFieldException;
	
	/**
	 * lista todas as requisicoes de alugueis
	 * @return
	 */
	public String listAllRequests();
	
	/**
	 * retorna  todas as situacoes dos veiculos
	 * @return
	 * @throws Exception
	 */
	public String getAllVehiclesSituation() throws Exception;
	
	/**
	 * retorna todos os veiculos disponiveis
	 * @return
	 * @throws Exception
	 */
	public String getAllAvailablesVehicles() throws Exception;
	
	/**
	 * adiciona alugueis
	 * @param email
	 * @param plates
	 * @param initialDates
	 * @param devolutionDates
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 * @throws Exception
	 */
	public void addManyRents(String email, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException, Exception;
	
	/**
	 * notifica um cliente sobre uma liberacao de aluguel
	 * @param plate
	 * @throws MessagingException
	 */
	public void notifyCostumerAboutRelease(String plate)
	throws MessagingException;
	
	/**
	 * notifica sobre uma liberacao de aluguel
	 * @throws MessagingException
	 */
	public void notifyAboutRequestRelease() throws MessagingException;
	
	/**
	 * imprime lista de requisicoes de alugueis
	 * @return
	 * @throws Exception
	 */
	public String printRequestList() throws Exception;
	
	
}
