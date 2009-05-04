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
public class User extends Person {

	private String login;

	/**
	 * Construtor de um funcionario
	 * 
	 * @param login
	 *            login do funcionario
	 * @param name
	 *            nome do funcionario
	 * @param email
	 *            email do funcionario
	 * @param phone
	 *            telefone do funcionario
	 */
	public User(String login, String name, String email, String phone) {
		super(name, email, phone);
		this.setLogin(login);
	}

	/**
	 * Metodo que altera o login do funcionario
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo que retorna o login do funcionario
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo que retorna uma string correspondendo a todos os dados do
	 * funcionario
	 */
	public String toString() {
		String output = "";
		output += "Nome: " + this.getName() + "\n";
		output += "Email: " + this.getEmail() + "\n";
		output += "Telefone: " + this.getPhone() + "\n";
		return output;
	}

}
