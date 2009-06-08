package LogicalSystem;

import java.io.IOException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Exceptions.EmptyFieldException;
import Interface.InterfaceText;

/**
 * Logica do cliente
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 */
public class LogicOfTheCustomer {
	
	Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheCustomer() {
		try {
			facade = new Facade();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// FIXME colocar todo o procedimento igual ao que foi feito em
	// LogicOfTheAdmin
	// Em cada metodo utiliza a interface para ler os dados, e aqui nesse metodo
	// fica um case para as opçoes do menu
	public void inicia() {
		int opcao;
		try {
			opcao = InterfaceText.exibeMenuDoCliente();
			switch (opcao) {
			case InterfaceText.REMOVER_CLIENTE_CUSTOMER:
				removeCustomer();
				break;
			case InterfaceText.CONSULTAR_DISPONIVEIS_CUSTOMER:
				getAllAvailableVehicles();
				inicia();
				break;
			case InterfaceText.RESERVA_ALUGUEL_CUSTOMER:
				reservaAluguel();
				inicia();
				break;
			case InterfaceText.INTERESSE_CARRO_CUSTOMER:
				mostraInteresse();
				inicia();
				break;
			}
		} catch (IOException e) {
			inicia();
		}
	}


	private void mostraInteresse() {
		String [] dados = InterfaceText.menuReservarVeiculo();
		try {
			facade.requestRent(dados[0], dados[1]);
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	private void reservaAluguel() {
		String [] dados = InterfaceText.menuReservarVeiculo();
		try {
			facade.requestRent(dados[0], dados[1]);
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * 
	 */
	private void getAllAvailableVehicles() {
		try {
			InterfaceText.printRequestList(facade.getAllAvailablesVehicles());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Remove cliente
	 * 
	 * @param email
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeCustomer() {
		try {
			facade.removeCustomer(InterfaceText.leDados());
		} catch (NotExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 */
	public void vehicleSituation() {
		try {
			facade.getVehicleSituation(InterfaceText.leDados());
		} catch (Exception e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void reserveAVehicle() {
		String[] dados = InterfaceText.menuReservarVeiculo();
		try {
			facade.requestRent(dados[0], dados[1]);
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void showInterestOnAVehicle() {
		
	}

}
