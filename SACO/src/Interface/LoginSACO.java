package Interface;

import java.io.IOException;

/**
 * Classe do login
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class LoginSACO extends Login {
	
	public LoginSACO() throws Exception {
		super();
	}


	/**
	 * @throws IOException 
	 * 
	 */
	protected String[] autenticate() throws IOException {
		return InterfaceText.exibeLogar();
	}

	
	/**
	 * @throws Exception 
	 * 
	 */
	public Object login() throws Exception {
		return super.login();
	}

	/**
	 * 
	 */
	protected void prompt() throws IOException  {
		choice = InterfaceText.exibeMenuDeIdentificacao();
		if (choice == 0) System.exit(0);
	}

	/**
	 * 
	 */
	protected void visualWarning() {
		InterfaceText.mensagemDeLoginComSucesso();
	}
}
