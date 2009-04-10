public class Administrator extends Costumer {

	private String login;
	private static int posicaoInicio = 0;

	public Administrator(String name, String email, String phone, String login)
			throws EmailException, NameException, PhoneException,
			LoginException {

		super(name, email, phone);
		if (login.equals(null) || login.equals(""))
			throw new LoginException("error: login is a mandatory field!");
		this.setLogin(login);

	}

	public void setLogin(String login) throws LoginException {
		if (!validLogin(login))
			throw new LoginException("error: invalid field!");
		this.login = login;
	}

	private boolean validLogin(String login2) {
		if (login2.charAt(posicaoInicio) == '_'
				|| login2.charAt(posicaoInicio) == '.'
				|| login2.charAt(posicaoInicio) == ','
				|| login2.charAt(posicaoInicio) == '-'
				|| login2.charAt(login2.length() - 1) == '_'
				|| login2.charAt(login2.length() - 1) == '.'
				|| login2.charAt(login2.length() - 1) == ','
				|| login2.charAt(login2.length() - 1) == '-') {
			return false;
		} else {
			for (int i = 0; i < login2.length(); i++) {
				if (!isPermitedChar(login2.charAt(i))
						&& !isNumber(login2.charAt(i))
						&& !isLetterDownCase(login2.charAt(i))
						&& !isLetterUpperCase(login2.charAt(i)))
					return false;
			}
		}
		return true;
	}

	public String getLogin() {
		return login;
	}

}
