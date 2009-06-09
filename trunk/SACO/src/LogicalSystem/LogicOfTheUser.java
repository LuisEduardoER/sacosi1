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
public class LogicOfTheUser implements LogicalInterface {

	/**
	 * objeto facade
	 */
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
	 * inicia programa
	 */
	public void inicia() {
		try {
			int opcao = InterfaceText.exibeMenuDoFuncionario();
			switch (opcao) {
			case InterfaceText.REGISTRA_ALUGUEL_EMPLOYEE:
				registraAluguel();
				inicia();
				break;
			case InterfaceText.ALUGUEIS_REGISTRADOS_EMPLOYEE:
				alugueisRegistrados();
				inicia();
				break;
			case InterfaceText.CONSULTA_SITUACAO_EMPLOYEE:
				consultaSituacao();
				inicia();
				break;
			case InterfaceText.ALUGUEL_VIGENTE_EMPLOYEE:
				consultaVigente();
				inicia();
				break;
			case InterfaceText.CONSULTA_SITUACAO_ALL_VEHICLES_EMPLOYEE:
				listAllVehiclesSituation();
				inicia();
				break;
			case InterfaceText.ALUGUEL_ATRASADO_EMPLOYEE:
				lateRent();
				inicia();
				break;
			case InterfaceText.ADICIONA_VARIOS_ALUGUEIS_EMPLOYEE:
				addManyRents();
				inicia();
				break;
			case InterfaceText.REMOVER_ALUGUEL_EMPLOYEE:
				releaseRent();
				inicia();
				break;
			case InterfaceText.CONSULTAR_RESERVAS_EMPLOYEE:
				consultaReservas();
				inicia();
				break;
			case InterfaceText.CONSULTAR_ALUGUEIS_ATRASADOS_EMPLOYEE:
				consultaAtrasados();
				inicia();
				break;
			case InterfaceText.CONSULTAR_ALUGUEIS_NAO_ATRASADOS_EMPLOYEE:
				consultaNaoAtrasados();
				inicia();
				break;
			case InterfaceText.SAIR:
				break;
			}
		} catch (IOException e) {
			inicia();
		}
	}
	
	/**
	 * consulta alugueis em dia
	 */
	private void consultaNaoAtrasados() {
		Calendar date = InterfaceText.menuEscolherData();
		try {
			String impressao = facade.listAllNonPendingRents(date);
			InterfaceText.listAllNonPendingRents(impressao);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * consulta alugueis atrasados
	 */
	private void consultaAtrasados() {
		Calendar date = InterfaceText.menuEscolherData();
		try {
			String impressao = facade.listAllPendingRents(date);
			InterfaceText.listAllPendingRents(impressao);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * consulta reservas de veiculos
	 */
	private void consultaReservas() {
		try {
			String impressao = facade.printRequestList();
			InterfaceText.printRequestList(impressao);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	/**
	 * libera veiculo
	 */
	private void releaseRent() {
		String placa = InterfaceText.releaseVehicle();
		try {
			facade.releaseVehicle(placa);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * adiciona alugueis
	 */
	private void addManyRents() {
		String[][] ManyRentsData = InterfaceText.adicionaVariosAlugueis();
		try {
			facade.addManyRents(ManyRentsData[1][0], ManyRentsData[0],
					ManyRentsData[2], ManyRentsData[3]);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * lista situacoes de veiculos
	 */
	public void listAllVehiclesSituation() {
		try {
			String impressao = facade.getAllVehiclesSituation();
			InterfaceText.listAllVehiclesSituation(impressao);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	/**
	 * lista alugueis atrasados
	 */
	private void lateRent() {
		String[] atraso = InterfaceText.registerLateRent();
		try {
			facade.registerLateRent(atraso[0], atraso[1], atraso[2], atraso[3]);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * consulta aluguel vigente
	 */
	private void consultaVigente() {
		try {
			int quantidade = facade.getAllActiveRents();
			InterfaceText.alugueisVigentes(quantidade);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * consulta situacao
	 */
	private void consultaSituacao() {
		String placa = InterfaceText.getVehicleSituation();
		try {
			String situacao = facade.getVehicleSituation(placa);
			InterfaceText.showSituation(situacao, placa);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * mostra alugueis atrasados
	 */
	private void alugueisRegistrados() {
		InterfaceText.numeroDeAlugueis(facade.getAllRents());
	}
/**
 * registra aluguel
 */
	private void registraAluguel() {
		String[] dadosAluguel = InterfaceText.dadosDoAluguel();
		try {
			facade.registerRent(dadosAluguel[0], dadosAluguel[1],
					dadosAluguel[2], dadosAluguel[3]);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
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
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void addRent(String email, String plate, String initialDate,
			String finalDate) {
		try {
			facade.registerRent(plate, email, initialDate, finalDate);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
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
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void addManyRents(String email, String[] plates,
			String[] initialDates, String[] devolutionDates) {
		try {
			facade.addManyRents(email, plates, initialDates, devolutionDates);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Libera aluguel
	 * 
	 * @param plate
	 * @throws MessagingException
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
	public void releaseRent(String plate) {
		try {
			facade.releaseVehicle(plate);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Registra aluguel atrasado
	 * 
	 * @param email
	 * @param plate
	 * @param initialDate
	 * @param finalDate
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException
	 */
	public void registerLateRent(String email, String plate,
			String initialDate, String finalDate) {
		try {
			facade.registerLateRent(plate, email, initialDate, finalDate);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * obtem situação do veiculo
	 * 
	 * @param plate
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
/*	public void getVehicleSituation(String plate) {
		try {
			facade.getVehicleSituation(plate);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}*/

	/**
	 * obtem situação de todos os veiculos
	 * 
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
	public void getAllVehiclesSituation() {
		try {
			facade.getAllVehiclesSituation();
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Visualiza aluguel corrente
	 * 
	 * @param date
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
	public void seeCurrentRent(Calendar date) {
		try {
			facade.listAllNonPendingRents(date);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Visualiza aluguel atrasado
	 * 
	 * @param date
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
	public void seeLateRent(Calendar date) {
		try {
			facade.listAllPendingRents(date);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (MessagingException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * notifica sobre requisicao de liberacao
	 * @throws MessagingException
	 */
	public void notifyAboutRequestRelease() throws MessagingException {
		facade.notifyAboutRequestRelease();
	}
}
