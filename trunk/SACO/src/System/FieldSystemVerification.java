package System;

public class FieldSystemVerification {
	
	private final int INITIAL_POSITION = 0;
	private static final int  PHONE_CARACTERES = 10;
	
	/*
	 * 
	 */
	public boolean validateName(String name) {
		return this.isPermitedChar(name.charAt(INITIAL_POSITION)) && 
			   this.isPermitedChar(name.charAt(name.length() - 1)) &&
			   findInvalidChar(name);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean nameIsAMandatoryField(String name) {
		return name != null && !name.equals("");
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean emailIsAMandatoryField(String email) {
		return email != null && !email.equals("");
	}
	
	/**
	 * 
	 * @param login
	 * @return
	 */
	public boolean validateLogin(String login) {
		return this.isPermitedChar(login.charAt(INITIAL_POSITION)) && 
				this.isPermitedChar(login.charAt(login.length() - 1));
	}
	
	/*
	 * 
	 */
	public boolean validateEmail(String email) {
		int arrobas = 0;
		int posicaoArroba = 0;
		if (!this.isPermitedChar(email.charAt(INITIAL_POSITION)) 
				|| !this.isPermitedChar(email.charAt(email.length() - 1))) {
			return false;
		}
		
		//Procura a posicao do arroba
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@'){
				arrobas++;
				posicaoArroba = i;
			}
		}
		if (arrobas != 1) {
			return false;
		}
		for (int i = 0; i < email.length(); i++) {
			if (i != posicaoArroba)
				if (!isPermitedChar(email.charAt(i)) && 
					!isNumber(email.charAt(i)) && 
					isLetterUpperCase(email.charAt(i)) &&
					!isSpace(email.charAt(i)))
					return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param login
	 * @return
	 */
	public boolean loginIsAMandatoryField(String login) {
		return login != null && !login.equals("");
	}
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public boolean phoneNumberIsAMandatoryField(String phone) {
		return phone != null && !phone.equals("");
	}
	
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public boolean validatePhoneNumber(String phone) {
		if (phone.length() != PHONE_CARACTERES)	return false;
		for (int i = 0; i < phone.length(); i++) {
			if (!isNumber(phone.charAt(i))) return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @return
	 */
	public boolean allCustomerFieldsInvalids(String name, String email, String phone) {
		return !nameIsAMandatoryField(name) && 
			   !emailIsAMandatoryField(email) && 
			   !phoneNumberIsAMandatoryField(phone);
	}
	
	/**
	 * 
	 * @param login
	 * @param name
	 * @param email
	 * @param phone
	 * @return
	 */
	public boolean allUserFieldsInvalids(String login, String name, String email, String phone) {
		return !loginIsAMandatoryField(login) && 
			   !nameIsAMandatoryField(name) && 
			   !emailIsAMandatoryField(email) &&
			   !phoneNumberIsAMandatoryField(phone);
	}
	
	/*
	 * 
	 */
	private boolean findInvalidChar(String name) {
		for (int i = INITIAL_POSITION; i < name.length(); i++) {
			if (!isPermitedChar(name.charAt(i)) && !isNumber(name.charAt(i))
					&& !isLetterDownCase(name.charAt(i))
					&& !isLetterUpperCase(name.charAt(i))
					&& !isSpace(name.charAt(i)))
				return false;
		}
		return true;
	}
	
	/*
	 * 
	 */
	private boolean isLetterDownCase(char letter) {
		return letter >= 'a' && letter <= 'z';
	}

	/*
	 * 
	 */
	private boolean isLetterUpperCase(char letter) {
		return letter >= 'A' && letter <= 'Z';
	}

	/*
	 * 
	 */
	private boolean isNumber(char number) {
		return number >= '0' && number <= '9';
	}

	/*
	 * 
	 */
	private boolean isPermitedChar(char simbol) {
		return simbol != '@' && simbol != '_' && 
		       simbol != '.' && simbol != ',' && simbol != '-';
	}

	/*
	 * 
	 */
	private boolean isSpace(char space) {
		return space == ' ';
	}

}
