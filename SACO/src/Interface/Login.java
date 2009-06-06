package Interface;

import java.io.IOException;

import LogicalSystem.LogicOfTheAdmin;
import LogicalSystem.LogicOfTheCustomer;
import LogicalSystem.LogicOfTheUser;

public abstract class Login {
	
	protected int choice;
	private String[] dataLogin;
	private Object logic;
	private LogicOfTheAdmin adminsLogic;
	
	protected Login() throws Exception {
		adminsLogic = new LogicOfTheAdmin();
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
		return adminsLogic.validateLogin(dataLogin[0], dataLogin[1]);
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
		dataLogin = autenticate();
		if (success(dataLogin)) {
			switch(choice) {
			case InterfaceText.ADMINISTRADOR:
				logic = new LogicOfTheAdmin();
				break;
			case InterfaceText.FUNCIONARIO:
				logic = new LogicOfTheUser();
				break;
			case InterfaceText.CLIENTE:;
				logic = new LogicOfTheCustomer();
				break;
			}
		} else {
			InterfaceText.menuInsucesso();
			login();
		}
		visualWarning();
		return logic;
	}
}
