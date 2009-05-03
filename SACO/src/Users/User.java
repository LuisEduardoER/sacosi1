package Users;

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

}