package LogicalSystem;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Interface.InterfaceText;
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

	// FIXME o mesmo que deve-se fazer em LogicOfTheAdmin
	public void inicia() throws AlreadyExistException, InvalidFieldException,
			MessagingException, EmptyFieldException, IOException {
		int opcao = InterfaceText.exibeMenuDoFuncionario();
		switch (opcao) {
		case InterfaceText.REGISTRA_ALUGUEL:
			registraAluguel();
			inicia();
			break;
		case InterfaceText.ALUGUEIS_REGISTRADOS:
			alugueisRegistrados();
			inicia();
			break;
		case InterfaceText.CONSULTA_SITUACAO:
			consultaSituacao();
			inicia();
			break;
		case InterfaceText.ALUGUEL_VIGENTE:
			consultaVigente();
			inicia();
			break;
		case InterfaceText.ALUGUEL_ATRASADO:
			lateRent();
			inicia();
			break;
		case InterfaceText.ADICIONAR_VEICULO:
			addVehicle();
			inicia();
			break;
		case InterfaceText.SAIR:
			break;
		}
	}

	public void addVehicle() throws InvalidFieldException, EmptyFieldException,
			AlreadyExistException {
		String[] vehicleData = InterfaceText.DadosDoVeiculo();
		facade.addVehicle(vehicleData[0], vehicleData[1], vehicleData[2],
				vehicleData[3], vehicleData[4], vehicleData[5]);
	}

	private void lateRent() throws AlreadyExistException,
			InvalidFieldException, MessagingException {
		String[] atraso = InterfaceText.registerLateRent();
		facade.registerLateRent(atraso[0], atraso[1], atraso[2], atraso[3]);
	}

	private void consultaVigente() {
		InterfaceText.alugueisVigentes(facade.getAllActiveRents());
	}

	private void consultaSituacao() {
		String[] consulta = InterfaceText.getRentSituation();
		facade.getRentSituation(consulta[0], consulta[1], consulta[2],
				consulta[3]);
	}

	private void alugueisRegistrados() {
		InterfaceText.numeroDeAlugueis(facade.getAllRents());
	}

	private void registraAluguel() throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		String[] dadosAluguel = InterfaceText.dadosDoAluguel();
		facade.registerRent(dadosAluguel[0], dadosAluguel[1], dadosAluguel[2], dadosAluguel[3]);
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
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
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

	public void notifyAboutRequestRelease() throws MessagingException {
		facade.notifyAboutRequestRelease();
	}
}
