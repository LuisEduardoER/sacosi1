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

	public User(String login, String name, String email, String phone) {
		super(name, email, phone);
		this.setLogin(login);
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
	
	public String toString() {
		String output = "";
		output += "Nome: " + this.getName() + "\n";
		output += "Email: " + this.getEmail() + "\n";
		output += "Telefone: " + this.getPhone() + "\n";
		return output;
	}

}
