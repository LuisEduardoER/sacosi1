package Users;

/**
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 * 
 */
public class Administrator extends Person {

	private String login;

	/**
	 * Construtor de um Administrador
	 */
	public Administrator(String name, String email, String phone, String login) {
		super(name, email, phone);
		this.setLogin(login);
	}

	/**
	 * Recebe o login do administrador para ser guardado
	 * 
	 * @param login
	 *            login do administrador
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo para pegar o login do administrador
	 * 
	 * @return login do administrador
	 */
	public String getLogin() {
		return login;
	}

}
