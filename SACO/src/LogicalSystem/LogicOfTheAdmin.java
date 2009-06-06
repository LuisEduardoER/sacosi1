package LogicalSystem;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Exceptions.EmptyFieldException;
import Interface.InterfaceText;

/**
 * Logica do administrador
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 * 
 */
public class LogicOfTheAdmin {
	
	private Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheAdmin() throws Exception {
		this.facade = new Facade();
	}
	
	public void inicia() throws InvalidFieldException, AlreadyExistException, 
						EmptyFieldException, NotExistException, LoginException, IOException {
		int opcao = InterfaceText.exibeMenuDoAdministrador();
		switch (opcao) {
		case InterfaceText.ADICIONAR_USUARIO:
			addUser();
			System.out.println(getAllUsers());
			inicia();
			break;
		case InterfaceText.ADICIONAR_CLIENTE:
			addCustomer();
			inicia();
			break;
		case InterfaceText.ADICIONAR_VEICULO:
			addVehicle();
			inicia();
			break;
		case InterfaceText.REMOVER_CLIENTE:
			removeCustomer();
			inicia();
			break;
		case InterfaceText.REMOVER_USUARIO:
			removeUser();
			inicia();
			break;
		case InterfaceText.REMOVER_VEICULO:
			removeVehicle();
			inicia();
			break;
		case InterfaceText.SAIR:
			break;
		}
	}

	/**
	 * Adiciona usuario
	 * 
	 * @param login
	 * @param name
	 * @param email
	 * @param phone
	 * @throws InvalidFieldException
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException 
	 */
	private void addUser()
			throws InvalidFieldException, AlreadyExistException, EmptyFieldException {
		String[] data = InterfaceText.DadosDoUsuario();
		facade.addUser(data[0], data[1], data[2], data[3]);
	}

	/**
	 * Adiciona cliente
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException 
	 */
	public void addCustomer()
			throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		String[] data = InterfaceText.DadosDoUsuario();
		facade.addCustomer(data[1], data[2], data[3]);
	}

	/**
	 * Remove cliente
	 * 
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeCustomer()
			throws NotExistException, InvalidFieldException {
		facade.removeCustomer(InterfaceText.menuRemocaoCliente());
	}

	/**
	 * Remove usuario
	 * 
	 * @param emailOrLogin
	 * @throws LoginException
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeUser() throws LoginException,
			NotExistException, InvalidFieldException {
		facade.removeUser(InterfaceText.menuRemocaoDoFuncionario());
	}

	/**
	 * adiciona veiculo
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param year
	 * @param plate
	 * @param price
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 * @throws AlreadyExistsException
	 */
	public void addVehicle() throws InvalidFieldException, EmptyFieldException, AlreadyExistException {
		String[] vehicleData = InterfaceText.DadosDoVeiculo();
		facade.addVehicle(vehicleData[0], vehicleData[1], vehicleData[2], 
				vehicleData[3], vehicleData[4], vehicleData[5]);
	}

	/**
	 * remove veiculo
	 * 
	 * @param plate
	 * @throws NotExistException
	 */
	public void removeVehicle() throws NotExistException{
		facade.removeVehicle(InterfaceText.menuRemocaoDeVeiculo());
	}

	/**
	 * 
	 * @param dataOne
	 * @param dataTwo
	 * @return
	 */
	public boolean validateLogin(String dataOne, String dataTwo) {
		return facade.validateLogin(dataOne, dataTwo);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAllUsers() {
		return facade.getAllUsers();
	}
}
