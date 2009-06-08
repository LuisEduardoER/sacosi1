package System;

import java.util.Calendar;

/**
 * Esta classe é responsavel por verificar a validade dos campos de alguns
 * metodos.
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class FieldSystemVerification {

	private final int INITIAL_POSITION = 0;
	private static final int PHONE_CARACTERES = 10;

	/**
	 * Verifica se o nome é valido
	 * 
	 * @param name
	 * @return true se é valido ou false caso contrario
	 */
	public boolean nameIsAMandatoryField(String name) {
		return name != null && !name.equals("");
	}

	/**
	 * Verifica se o email é valido
	 * 
	 * @param email
	 * @return true se é valido ou false caso contrario
	 */
	public boolean emailIsAMandatoryField(String email) {
		return email != null && !email.equals("");
	}

	/**
	 * Verifica se a data é valida
	 * 
	 * @param date
	 * @return true se é valido ou false caso contrario
	 */
	public boolean dateIsMandatoryField(String date) {
		if (date == null || date.equals(""))
			return false;
		return true;
	}

	/**
	 * Verifica se o login é valido
	 * 
	 * @param login
	 * @return true se é valido ou false caso contrario
	 */
	public boolean loginIsAMandatoryField(String login) {
		return login != null && !login.equals("");
	}

	/**
	 * Verifica se o telefone é valido
	 * 
	 * @param phone
	 * @return true se é valido ou false caso contrario
	 */
	public boolean phoneNumberIsAMandatoryField(String phone) {
		return phone != null && !phone.equals("");
	}

	/**
	 * Verifica se o tipo é valido
	 * 
	 * @param type
	 * @return true se é valido ou false caso contrario
	 */
	public boolean typeIsAMandatoryField(String type) {
		return type != null && !type.equals("");
	}

	/**
	 * Verifica se o modelo é valido
	 * 
	 * @param model
	 * @return true se é valido ou false caso contrario
	 */
	public boolean modelIsAMandatoryField(String model) {
		return model != null && !model.equals("");
	}

	/**
	 * Verifica se a cor é valida
	 * 
	 * @param color
	 * @return true se é valido ou false caso contrario
	 */
	public boolean colorIsAMandatoryField(String color) {
		return color != null && !color.equals("");
	}

	/**
	 * Verifica se o ano é valido
	 * 
	 * @param ano
	 * @return true se é valido ou false caso contrario
	 */
	public boolean yearIsAMandatoryField(String year) {
		return year != null && !year.equals("");
	}

	/**
	 * Verifica se a placa é valida
	 * 
	 * @param plate
	 * @return true se é valido ou false caso contrario
	 */
	public boolean plateIsAMandatoryField(String plate) {
		return plate != null && !plate.equals("");
	}

	/**
	 * Verifica se o preço é valido
	 * 
	 * @param price
	 * @return true se é valido ou false caso contrario
	 */
	public boolean priceIsAMandatoryField(String price) {
		return price != null && !price.equals("");
	}

	/**
	 * Verifica se o nome é valido
	 * 
	 * @param name
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateName(String name) {
		return this.isPermitedChar(name.charAt(INITIAL_POSITION))
				&& this.isPermitedChar(name.charAt(name.length() - 1))
				&& findInvalidChar(name);
	}

	/**
	 * Verifica se o login é valido
	 * 
	 * @param login
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateLogin(String login) {
		return this.isPermitedChar(login.charAt(INITIAL_POSITION))
				&& this.isPermitedChar(login.charAt(login.length() - 1));
	}

	/**
	 * Verifica se a placa é valida
	 * 
	 * @param plate
	 * @return true se é valido ou false caso contrario
	 */
	public boolean isValidPlate(String plate) {
		if (plate.length() != 7)
			return false;
		if (!validLetterPlate(plate.substring(0, 3)))
			return false;
		if (!validNumberPlate(plate.substring(3, plate.length())))
			return false;
		return true;
	}
	
	/**
	 * Verifica se uma data final de entrega de veiculo eh maior que a data
	 * atual, para que seja registrado um aluguel atrasado.
	 * 
	 * @param end
	 *            a data final de entrega
	 * @return true se a data for valida (nao excedeu o dia de entrega), false
	 *         caso contrario
	 */
	public boolean validateRegisterLateRent(String end) {

		int day2 = Integer.valueOf(end.substring(0, 2));
		int month2 = Integer.valueOf(end.substring(3, 5));
		int year2 = Integer.valueOf(end.substring(6, 8));
		year2 += 2000;
		month2 = month2 - 1;
		Calendar hoje = Calendar.getInstance();
		Calendar entrega = Calendar.getInstance();
		entrega.set(year2, month2, day2);
		if (entrega.after(hoje)) {
			return false;
		}
		return true;
	}
	



	/**
	 * Verifica se os tres primeiros 'chars' sao letras
	 * 
	 * @param word
	 * @return true se são ou false caso contrario
	 */
	public boolean validLetterPlate(String word) {
		char letra;
		for (int i = 0; i < word.length(); i++) {
			letra = word.charAt(i);
			if (!(letra >= 'A' && letra <= 'Z')
					&& !(letra >= 'a' && letra <= 'z'))
				return false;
		}
		return true;
	}

	/**
	 * Verifica se os quatro ultimos 'chars' sao numeros
	 * 
	 * @param word
	 * @return true se são ou false caso contrario
	 */
	boolean validNumberPlate(String word) {
		char numero;
		for (int i = 0; i < word.length(); i++) {
			numero = word.charAt(i);
			if (numero < '0' || numero > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se o email é valido
	 * 
	 * @param email
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateEmail(String email) {
		int arrobas = 0;
		int posicaoArroba = 0;
		if (!this.isPermitedChar(email.charAt(INITIAL_POSITION))
				|| !this.isPermitedChar(email.charAt(email.length() - 1))) {
			return false;
		}

		// Procura a posicao do arroba
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				arrobas++;
				posicaoArroba = i;
			}
		}
		if (arrobas != 1) {
			return false;
		}
		for (int i = 0; i < email.length(); i++) {
			if (i != posicaoArroba)
				if (!isPermitedChar(email.charAt(i))
						&& !isNumber(email.charAt(i))
						&& isLetterUpperCase(email.charAt(i))
						&& !isSpace(email.charAt(i)))
					return false;
		}
		return true;
	}

	/**
	 * Verifica se o telefone é valido
	 * 
	 * @param phone
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validatePhoneNumber(String phone) {
		if (phone.length() != PHONE_CARACTERES)
			return false;
		for (int i = 0; i < phone.length(); i++) {
			if (!isNumber(phone.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Verifica se o tipo é valido
	 * 
	 * @param type
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateType(String type) {
		return type.equals("car") || type.equals("motorcycle");
	}

	/**
	 * Verifica se o model é valido
	 * 
	 * @param model
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateModel(String model) {
		return isValid(model);
	}

	/**
	 * Verifica se a cor é valida
	 * 
	 * @param color
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateColor(String color) {
		return isValid(color);
	}

	/**
	 * Verifica se a placa é valida
	 * 
	 * @param plate
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validatePlate(String plate) {
		return isValid(plate) && isValidPlate(plate);
	}

	/**
	 * Verifica se o ano é valido
	 * 
	 * @param year
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validateYear(String year) {
		return isValidYear(Integer.parseInt(year));
	}

	/**
	 * Verifica se o preço é valido
	 * 
	 * @param price
	 * @return true se é valido ou false caso contrario
	 */
	public boolean validatePrice(String price) {
		return isValidPrice(Double.valueOf(price));
	}

	/**
	 * Verifica se todos os campos de um cliente são validos
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @return true se sao validos ou false caso contrario
	 */
	public boolean allCustomerFieldsInvalids(String name, String email,
			String phone) {
		return !nameIsAMandatoryField(name) && !emailIsAMandatoryField(email)
				&& !phoneNumberIsAMandatoryField(phone);
	}

	/**
	 * Verifica se todos os campos de um veiculo são validos
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 * @return true se sao validos ou false caso contrario
	 */
	public boolean allVehiclesFieldsInvalids(String type, String model,
			String color, String plate, String year, String price) {
		return !typeIsAMandatoryField(type) || !modelIsAMandatoryField(model)
				|| !colorIsAMandatoryField(color)
				|| !plateIsAMandatoryField(plate)
				|| !yearIsAMandatoryField(year)
				|| !priceIsAMandatoryField(price);
	}

	/**
	 * Verifica se todos os campos de um usuario são validos
	 * 
	 * @param login
	 * @param name
	 * @param email
	 * @param phone
	 * @return true se sao validos ou false caso contrario
	 */
	public boolean allUserFieldsInvalids(String login, String name,
			String email, String phone) {
		return !loginIsAMandatoryField(login) && !nameIsAMandatoryField(name)
				&& !emailIsAMandatoryField(email)
				&& !phoneNumberIsAMandatoryField(phone);
	}

	/**
	 * Verifica se o nome contem algum caracter invalido
	 * 
	 * @param name
	 * @return true se contem ou false caso contrario
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

	/**
	 * Verifica se a letra é minuscula
	 * 
	 * @param letter
	 * @return true se for ou false caso contrario
	 */
	private boolean isLetterDownCase(char letter) {
		return letter >= 'a' && letter <= 'z';
	}

	/**
	 * Verifica se a letra é maiuscula
	 * 
	 * @param letter
	 * @return true se for ou false caso contrario
	 */
	private boolean isLetterUpperCase(char letter) {
		return letter >= 'A' && letter <= 'Z';
	}

	/**
	 * Verifica se o caracter é um numero
	 * 
	 * @param number
	 * @return true se for ou false caso contrario
	 */
	private boolean isNumber(char number) {
		return number >= '0' && number <= '9';
	}

	/**
	 * Verifica se o caracter é permitido
	 * 
	 * @param simbol
	 * @return true se for ou false caso contrario
	 */
	private boolean isPermitedChar(char simbol) {
		return simbol != '@' && simbol != '_' && simbol != '.' && simbol != ','
				&& simbol != '-';
	}

	/**
	 * Verifica se o caracter é um espaço
	 * 
	 * @param space
	 * @return true se for ou false caso contrario
	 */
	private boolean isSpace(char space) {
		return space == ' ';
	}

	/**
	 * Verifica se a string é valida
	 * 
	 * @param str
	 * @return true se for ou false caso contrario
	 */
	boolean isValid(String str) {
		String character;
		for (int i = 0; i < str.length(); i++) {
			character = str.substring(i, i + 1);
			if (!(isLetter(character) || isNumber(character)))
				return false;
		}
		return true;
	}

	/**
	 * Verifica se a string é composta somente por letras
	 * 
	 * @param str
	 * @return true se for ou false caso contrario
	 */
	boolean isLetter(String str) {
		int hashCode = str.hashCode();
		if ((hashCode >= 97 && hashCode <= 122)
				|| (hashCode >= 65 && hashCode <= 90))
			return true;
		return false;
	}

	/**
	 * Verifica se a string é composta somente por numeros
	 * 
	 * @param str
	 * @return true se for ou false caso contrario
	 */
	boolean isNumber(String str) {
		if (str.hashCode() >= 48 && str.hashCode() <= 57)
			return true;
		return false;
	}

	/**
	 * Verifica se o ano é valido
	 * 
	 * @param number
	 * @return true se for ou false caso contrario
	 */
	boolean isValidYear(int number) {
		if (number <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se o preço é valido
	 * 
	 * @param price
	 * @return true se for ou false caso contrario
	 */
	boolean isValidPrice(double price) {
		if (price <= 0) {
			return false;
		}
		return true;
	}
}
