package Users;

import Exceptions.EmailException;
import Exceptions.NameException;
import Exceptions.PhoneException;

public abstract class Person implements Alugadores {

	private String name;
	private String email;
	private String phone;
	private static final int posicaoInicio = 0;
	private static final int caracteresPhone = 10;

	public Person(String name, String email, String phone)
			throws EmailException, NameException, PhoneException {

		if (name.equals(null) || name.equals(""))
			throw new NameException("error: name is a mandatory field!");
		if (email.equals(null) || email.equals(""))
			throw new EmailException("error: e-mail is a mandatory field!");
		if (phone.equals(null) || phone.equals(""))
			throw new PhoneException(
					"error: phone number is a mandatory field!");
		this.setName(name);
		this.setEmail(email);
		this.setPhone(phone);

	}

	public void setName(String name) throws NameException {
		if (!validName(name))
			throw new NameException("error: invalid field!");
		this.name = name;
	}

	protected boolean isLetterDownCase(char letter) {
		if (letter < 'a' || letter > 'z')
			return false;
		return true;
	}

	protected boolean isLetterUpperCase(char letter) {
		if (letter < 'A' || letter > 'Z')
			return false;
		return true;
	}

	protected boolean isNumber(char number) {
		if (number < '0' || number > '9')
			return false;
		return true;
	}

	protected boolean isPermitedChar(char simbol) {
		if (simbol != '_' && simbol != '.' && simbol != ',' && simbol != '-')
			return false;
		return true;
	}

	protected boolean isSpace(char space) {
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
		}
		for (int i = 0; i < name2.length(); i++) {
			if (!isPermitedChar(name2.charAt(i)) && !isNumber(name2.charAt(i))
					&& !isLetterDownCase(name2.charAt(i))
					&& !isLetterUpperCase(name2.charAt(i))
					&& !isSpace(name2.charAt(i)))
				return false;
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
		int posicaoArroba = 0;
		if (email2.charAt(posicaoInicio) == '_'
				|| email2.charAt(posicaoInicio) == '.'
				|| email2.charAt(posicaoInicio) == ','
				|| email2.charAt(posicaoInicio) == '-'
				|| email2.charAt(email2.length() - 1) == '_'
				|| email2.charAt(email2.length() - 1) == '.'
				|| email2.charAt(email2.length() - 1) == ','
				|| email2.charAt(email2.length() - 1) == '-') {
			return false;
		}
		//Procura a posicao do arroba
		for (int i = 0; i < email2.length(); i++) {
			if (email2.charAt(i) == '@'){
				arrobas++;
				posicaoArroba = i;
			}
		}
		
		if (arrobas != 1)
			return false;
		
		for (int i = 0; i < email2.length(); i++) {
			if (i != posicaoArroba)
				if (!isPermitedChar(email2.charAt(i))
						&& !isNumber(email2.charAt(i))
						&& !isLetterDownCase(email2.charAt(i))
						&& !isLetterUpperCase(email2.charAt(i))
						&& !isSpace(email2.charAt(i)))
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

}
