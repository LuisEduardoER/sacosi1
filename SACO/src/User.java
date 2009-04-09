public class User {

	private String name;
	private String email;
	private String phone;
	private String login;
	private static int posicaoInicio = 0;
	private static int caracteresPhone = 10;

	public User(String name, String email, String phone, String login)
			throws EmailException, NameException, PhoneException,
			LoginException {

		if (name.equals(null) || name.equals(""))
			throw new NameException("error: name is a mandatory field!");
		if (email.equals(null) || email.equals(""))
			throw new EmailException("error: e-mail is a mandatory field!");
		if (phone.equals(null) || phone.equals(""))
			throw new PhoneException(
					"error: phone number is a mandatory field!");
		if (login.equals(null) || login.equals(""))
			throw new LoginException("error: login is a mandatory field!");
		this.setName(name);
		this.setEmail(email);
		this.setPhone(phone);
		this.setLogin(login);

	}

	public void setName(String name) throws NameException {
		if (!validName(name))
			throw new NameException("error: invalid field!");
		this.name = name;
	}

	private boolean isLetterDownCase(char letter) {
		if (letter < 'a' || letter > 'z')
			return false;
		return true;
	}

	private boolean isLetterUpperCase(char letter) {
		if (letter < 'A' || letter > 'Z')
			return false;
		return true;
	}

	private boolean isNumber(char number) {
		if (number < '0' || number > '9')
			return false;
		return true;
	}

	private boolean isPermitedChar(char simbol) {
		if (simbol != '_' && simbol != '.' && simbol != ',' && simbol != '-')
			return false;
		return true;
	}

	private boolean isSpace(char space) {
		if (space != ' ')
			return false;
		return true;
	}

	private boolean validName(String name2) {
		if (name2.charAt(posicaoInicio) == '_'
				|| name2.charAt(posicaoInicio) == '.'
				|| name2.charAt(posicaoInicio) == ','
				|| name2.charAt(posicaoInicio) == '-'
				|| name2.charAt(name2.length() - 1) == '_'
				|| name2.charAt(name2.length() - 1) == '.'
				|| name2.charAt(name2.length() - 1) == ','
				|| name2.charAt(name2.length() - 1) == '-') {
			return false;
		} else {
			for (int i = 0; i < name2.length(); i++) {
				if (!isPermitedChar(name2.charAt(i))
						&& !isNumber(name2.charAt(i))
						&& !isLetterDownCase(name2.charAt(i))
						&& !isLetterUpperCase(name2.charAt(i))
						&& !isSpace(name2.charAt(i)))
					return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) throws EmailException {
		if (!validEmail(email))
			throw new EmailException("error: invalid field!");
		this.email = email;
	}

	private boolean validEmail(String email2) {
		int arrobas = 0;
		if (email2.charAt(posicaoInicio) == '_'
				|| email2.charAt(posicaoInicio) == '.'
				|| email2.charAt(posicaoInicio) == ','
				|| email2.charAt(posicaoInicio) == '-'
				|| email2.charAt(email2.length() - 1) == '_'
				|| email2.charAt(email2.length() - 1) == '.'
				|| email2.charAt(email2.length() - 1) == ','
				|| email2.charAt(email2.length() - 1) == '-') {
			return false;
		} else {
			for (int i = 0; i < email2.length(); i++) {
				if (!isPermitedChar(email2.charAt(i))
						&& !isNumber(email2.charAt(i))
						&& !isLetterDownCase(email2.charAt(i))
						&& !isLetterUpperCase(email2.charAt(i))
						&& !isSpace(email2.charAt(i)))
					return false;
				if (email2.charAt(i) == '@')
					arrobas++;
			}
			if (arrobas != 1)
				return false;
		}
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) throws PhoneException {
		if (!validPhone(phone))
			throw new PhoneException("error: invalid field!");
		this.phone = phone;
	}

	private boolean validPhone(String phone2) {
		if (phone2.length() != caracteresPhone)
			return false;
		for (int i = 0; i < phone2.length(); i++) {
			if (phone2.charAt(i) < '0' || phone2.charAt(i) > '9')
				return false;
		}
		return true;
	}

	public String getPhone() {
		return phone;
	}

	public void setLogin(String login) throws LoginException {
		if (!validLogin(name))
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
