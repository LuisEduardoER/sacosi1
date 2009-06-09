package LogicalSystem;

import java.io.FileNotFoundException;
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
public class LogicOfTheAdmin implements LogicalInterface {
	
	private Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheAdmin() throws Exception {
		this.facade = new Facade();
	}
	
	public void inicia() {
		int opcao;
		try {
			opcao = InterfaceText.exibeMenuDoAdministrador();
			switch (opcao) {
			case InterfaceText.ADICIONAR_USUARIO_ADMIN:
				addUser();
				inicia();
				break;
			case InterfaceText.ADICIONAR_CLIENTE_ADMIN:
				addCustomer();
				inicia();
				break;
			case InterfaceText.ADICIONAR_VEICULO_ADMIN:
				addVehicle();
				inicia();
				break;
			case InterfaceText.REMOVER_CLIENTE_ADMIN:
				removeCustomer();
				inicia();
				break;
			case InterfaceText.REMOVER_USUARIO_ADMIN:
				removeUser();
				inicia();
				break;
			case InterfaceText.REMOVER_VEICULO_ADMIN:
				removeVehicle();
				inicia();
				break;
			case InterfaceText.LIMPAR_BD:
				cleanDB();
				inicia();
				break;
			case InterfaceText.SAIR:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}

	/**
	 * 
	 */
	private void cleanDB() {
		try {
			facade.cleanDB();
		} catch (FileNotFoundException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Adiciona usuario ao sistema. Para isso eh necessario ler todos os dados 
	 * (nome, login, email e telefone) sem que nenhum desses campos esteja em 
	 * formato incorreto (invalido, nulo). Nao se pode adicionar mais de um 
	 * usuario com o mesmo email.
	 */
	private void addUser() {
		String[] data = InterfaceText.DadosDoUsuario();
		try {
			facade.addUser(data[0], data[1], data[2], data[3]);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Adiciona cliente ao sistema, para isso eh necessario  o seu nome, email 
	 * e telefone. Nenhum desses dados devem ser invalidos ou nulos. Clientes
	 * com emails duplicados nao serao cadastrados ao sistema.
	 */
	private void addCustomer() {
		String[] data = InterfaceText.DadosDoCliente();
		try {
			facade.addCustomer(data[0], data[1], data[2]);
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Remove cliente
	 * 
	 */
	private void removeCustomer() {
		try {
			facade.removeCustomer(InterfaceText.menuRemocaoCliente());
		} catch (NotExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * Remove usuario
	 * 
	 */
	private void removeUser() {
		try {
			facade.removeUser(InterfaceText.menuRemocaoDoFuncionario());
		} catch (LoginException e) {
			InterfaceText.printError(e.getMessage());
		} catch (NotExistException e) {
			InterfaceText.printError(e.getMessage());
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		}
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
	private void addVehicle() {
		String[] vehicleData = InterfaceText.DadosDoVeiculo();
		try {
			facade.addVehicle(vehicleData[0], vehicleData[1], vehicleData[2], 
					vehicleData[3], vehicleData[4], vehicleData[5]);
		} catch (InvalidFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (EmptyFieldException e) {
			InterfaceText.printError(e.getMessage());
		} catch (AlreadyExistException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * remove veiculo
	 * 
	 */
	private void removeVehicle() {
		String dado = InterfaceText.menuRemocaoDeVeiculo();
		try {
			facade.removeVehicle(dado);
		} catch (NotExistException e) {
			InterfaceText.printError(e.getMessage());
		}
	}

	/**
	 * 
	 * @param dataOne
	 * @param dataTwo
	 * @return
	 */
	private boolean validateLogin(String dataOne, String dataTwo) {
		return facade.validateLogin(dataOne, dataTwo);
	}
	
	/**
	 * 
	 * @return
	 */
	private int getAllUsers() {
		return facade.getAllUsers();
	}
}
