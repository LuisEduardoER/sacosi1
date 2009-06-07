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
		try {
			String impressao = facade.listAllNonPendingRents(date);
			InterfaceText.listAllNonPendingRents(impressao);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultaAtrasados() {
		Calendar date = InterfaceText.menuEscolherData();
		try {
			String impressao = facade.listAllPendingRents(date);
			InterfaceText.listAllPendingRents(impressao);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultaReservas() {
		try {
			String impressao = facade.printRequestList();
			InterfaceText.printRequestList(impressao);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void releaseRent() {
		String placa = InterfaceText.releaseVehicle();
		try {
			facade.releaseVehicle(placa);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void addManyRents() {
		String[][] ManyRentsData = InterfaceText.adicionaVariosAlugueis();
		try {
			facade.addManyRents(ManyRentsData[1][0], ManyRentsData[0],
					ManyRentsData[2], ManyRentsData[3]);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addVehicle() throws InvalidFieldException, EmptyFieldException,
			AlreadyExistException {
		String[] vehicleData = InterfaceText.DadosDoVeiculo();
		facade.addVehicle(vehicleData[0], vehicleData[1], vehicleData[2],
				vehicleData[3], vehicleData[4], vehicleData[5]);
	}

	private void lateRent() {
		String[] atraso = InterfaceText.registerLateRent();
		try {
			facade.registerLateRent(atraso[0], atraso[1], atraso[2], atraso[3]);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultaVigente() {
		try {
			int quantidade = facade.getAllActiveRents();
			InterfaceText.alugueisVigentes(quantidade);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultaSituacao()  {
		String placa = InterfaceText.getVehicleSituation();
		String situacao;
		try {
			situacao = facade.getVehicleSituation(placa);
			InterfaceText.showSituation(situacao, placa);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void alugueisRegistrados() {
		InterfaceText.numeroDeAlugueis(facade.getAllRents());
	}

	private void registraAluguel() {
		String[] dadosAluguel = InterfaceText.dadosDoAluguel();
		try {
			facade.registerRent(dadosAluguel[0], dadosAluguel[1],
					dadosAluguel[2], dadosAluguel[3]);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void addRent(String email, String plate, String initialDate,
			String finalDate) {
		try {
			facade.registerRent(plate, email, initialDate, finalDate);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * obtem situa��o do veiculo
	 * 
	 * @param plate
	 * @throws IOException
	 * @throws EmptyFieldException
	 * @throws MessagingException
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 */
	public void getVehicleSituation(String plate) {
		try {
			facade.getVehicleSituation(plate);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * obtem situa��o de todos os veiculos
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		} catch (InvalidFieldException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void notifyAboutRequestRelease() throws MessagingException {
		facade.notifyAboutRequestRelease();
	}
}
