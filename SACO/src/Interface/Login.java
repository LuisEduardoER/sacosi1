package Interface;

import java.io.IOException;

import Commands.Facade;
import LogicalSystem.LogicOfTheAdmin;
import LogicalSystem.LogicOfTheCustomer;
import LogicalSystem.LogicOfTheUser;
import LogicalSystem.LogicalInterface;

/**
 * Classe do login
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public abstract class Login {
	
	/**
	 * escolha
	 */
	protected int choice;
	/**
	 * logins
	 */
	private String[] dataLogin;
	
	/**
	 * logica
	 */
	private Object logic;
	
	/**
	 * objeto facade
	 */
	private Facade facade;
	
	/**
	 * faz um login
	 * @throws Exception
	 */
	protected Login() throws Exception {
		facade = new Facade();
	}

	/**
	 * @throws IOException 
	 * 
	 */
	protected abstract void prompt() throws IOException;

	/**
	 * 
	 * @return
	 */
	protected abstract String[] autenticate() throws IOException;

	/**
	 * retorna sucesso
	 */
	protected boolean success(String[] dataLogin) {
		return facade.validateLogin(dataLogin[0], dataLogin[1]);
	}

	
	/**
	 *  metodo fabrica pra fazer new da logica
	 */
	private void usersChoice() throws Exception {
		System.out.println(choice);
		if (choice == InterfaceText.REGISTRAR_SE) {
			
			String[] dados = InterfaceText.DadosDoCliente();
			facade.addCustomer(dados[0], dados[1], dados[2]);
			logic = logicalCreator();
			
		} else {
			dataLogin = autenticate();
			if (success(dataLogin)) {
				logic = logicalCreator();
			} else {
				InterfaceText.menuInsucesso();
				login();
			}
		}
	}

	/**
	 * visualiza warning
	 */
	protected abstract void visualWarning();

	/**
	 * @throws Exception 
	 * 
	 */
	protected Object login() throws Exception {
		prompt();
		usersChoice();
		visualWarning();
		return logic;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private LogicalInterface logicalCreator() throws Exception {
		if (choice == InterfaceText.ADMINISTRADOR) return new LogicOfTheAdmin();
		if (choice == InterfaceText.FUNCIONARIO) return new LogicOfTheUser();
		if (choice == InterfaceText.CLIENTE) return new LogicOfTheCustomer();
		if (choice == InterfaceText.REGISTRAR_SE) return new LogicOfTheCustomer();
		else return null;
	}
}
