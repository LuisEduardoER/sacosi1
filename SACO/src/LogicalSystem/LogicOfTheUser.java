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
		case InterfaceText.ADICIONA_VARIOS_ALUGUEIS:
			addManyRents();
			inicia();
			break;
		case InterfaceText.REMOVER_ALUGUEL:
			releaseRent();
			inicia();
			break;
		case InterfaceText.CONSULTAR_RESERVAS:
			consultaReservas();
			inicia();
			break;
		case InterfaceText.CONSULTAR_ALUGUEIS_ATRASADOS:
			consultaAtrasados();
			inicia();
			break;
		case InterfaceText.CONSULTAR_ALUGUEIS_NAO_ATRASADOS:
			consultaNaoAtrasados();
			inicia();
			break;
		case InterfaceText.SAIR:
			break;
		}
	}

	private void consultaNaoAtrasados() {
		Calendar date = InterfaceText.menuEscolherData();
		InterfaceText.listAllNonPendingRents(facade.listAllNonPendingRents(date));
		
	}

	private void consultaAtrasados() {
		Calendar date = InterfaceText.menuEscolherData();
		InterfaceText.listAllPendingRents(facade.listAllPendingRents(date));
		
	}

	private void consultaReservas() {
		InterfaceText.printRequestList(facade.printRequestList());
		
	}

	private void releaseRent() throws MessagingException {
		String placa = InterfaceText.releaseVehicle();
		facade.releaseVehicle(placa);
		
	}

	private void addManyRents() throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		String [][] ManyRentsData = InterfaceText.adicionaVariosAlugueis();
		facade.addManyRents(ManyRentsData[1][0], ManyRentsData[0], ManyRentsData[2], ManyRentsData[3]);
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
		String situacao = facade.getRentSituation(consulta[0], consulta[1], consulta[2],
				consulta[3]);
		System.out.println("--------------------------------------------------");
		if (situacao.equalsIgnoreCase("active")){
			System.out.println("O veiculo de placa " + consulta[1] + " esta atualmente alugado.");
		}
		else{
			System.out.println("O veiculo de placa " + consulta[1] + " esta atualmente disponivel.");
		}
	}

	private void alugueisRegistrados() {
		InterfaceText.numeroDeAlugueis(facade.getAllRents());
	}

	private void registraAluguel() throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		String[] dadosAluguel = InterfaceText.dadosDoAluguel();
		facade.registerRent(dadosAluguel[0], dadosAluguel[1], dadosAluguel[2], dadosAluguel[3]);
	}

	/**
	 * Visualiza requisi��es
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
	public void addManyRents(String email, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
		facade.addManyRents(email, plates, initialDates, devolutionDates);
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
	 * obtem situa��o do veiculo
	 * 
	 * @param plate
	 */
	public void getVehicleSituation(String plate) {
		facade.getVehicleSituation(plate);
	}

	/**
	 * obtem situa��o de todos os veiculos
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
