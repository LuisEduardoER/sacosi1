package Users;

public class Administrator extends Person {

	private String login;

	public Administrator(String name, String email, String phone, String login) {
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
