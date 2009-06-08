package Interface;

import java.io.IOException;

import Commands.Facade;
import LogicalSystem.LogicOfTheAdmin;
import LogicalSystem.LogicOfTheCustomer;
import LogicalSystem.LogicOfTheUser;
import LogicalSystem.LogicalInterface;

public abstract class Login {

	protected int choice;
	private String[] dataLogin;
	private Object logic;
	private Facade facade;

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
	 * 
	 */
	protected boolean success(String[] dataLogin) {
		return facade.validateLogin(dataLogin[0], dataLogin[1]);
	}

	//TODO metodo fabrica pra fazer new da logica
	private void usersChoice() throws Exception {
		System.out.println(choice);
		if (choice == InterfaceText.REGISTRAR_SE) {
			
			String[] dados = InterfaceText.DadosDoCliente();
			facade.addCustomer(dados[0], dados[1], dados[2]);
			logic = logicalCreator();
			
		} else {
			dataLogin = autenticate();
			if (success(dataLogin)) {
				switch(choice) {
				case InterfaceText.ADMINISTRADOR:
					logic = logicalCreator();
					break;
				case InterfaceText.FUNCIONARIO:
					logic = logicalCreator();
					break;
				case InterfaceText.CLIENTE:;
				logic = logicalCreator();
				break;
				case InterfaceText.SAIR:
					logic = null;
					break;
				}
			} else {
				InterfaceText.menuInsucesso();
				login();
			}
		}
	}

	/**
	 * 
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
		else return new LogicOfTheCustomer();
	}
}
