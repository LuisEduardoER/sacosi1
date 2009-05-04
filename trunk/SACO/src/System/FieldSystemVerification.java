package System;

/**
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
	private static final int  PHONE_CARACTERES = 10;
		
	public boolean nameIsAMandatoryField(String name) {
		return name != null && !name.equals("");
	}
	
	public boolean emailIsAMandatoryField(String email) {
		return email != null && !email.equals("");
	}
	
	public boolean dateIsMandatoryField(String date) {
		if (date == null || date.equals(""))
			return false;
		return true;
	}
	
	public boolean loginIsAMandatoryField(String login) {
		return login != null && !login.equals("");
	}
	
	public boolean phoneNumberIsAMandatoryField(String phone) {
		return phone != null && !phone.equals("");
	}
	
	public boolean typeIsAMandatoryField(String type){
		return type != null && !type.equals("");
	}
	
	public boolean modelIsAMandatoryField(String model){
		return model != null && !model.equals("");
	}
	
	public boolean colorIsAMandatoryField(String color){
		return color != null && !color.equals("");
	}
	
	public boolean yearIsAMandatoryField(String year){
		return year != null && !year.equals("");
	}
	
	public boolean plateIsAMandatoryField(String plate){
		return plate != null && !plate.equals("");
	}
	
	public boolean priceIsAMandatoryField(String price){
		return price != null && !price.equals("");
	}

	public boolean validateName(String name) {
		return this.isPermitedChar(name.charAt(INITIAL_POSITION)) && 
			   this.isPermitedChar(name.charAt(name.length() - 1)) &&
			   findInvalidChar(name);
	}
	
	public boolean validateLogin(String login) {
		return this.isPermitedChar(login.charAt(INITIAL_POSITION)) && 
				this.isPermitedChar(login.charAt(login.length() - 1));
	}
	
	public boolean validPlate(String plate) {
		if (plate == null || plate.equals(""))
			return false;
		return true;
	}
	
	
	public boolean isValidPlate(String plate){
		if (plate.length() != 7) return false; 
		if (!validLetterPlate(plate.substring(0,3)))
			return false;
		if (!validNumberPlate(plate.substring(3,plate.length())))
			return false;
		return true;
	}

	/**
	 * Verifica se os tres primeiros 'chars' sao letras
	 * @param word
	 * @return
	 */
	public boolean validLetterPlate(String word){
		char letra;
		for (int i = 0; i < word.length(); i++){
			letra = word.charAt(i);
			if (!(letra >= 'A' && letra <= 'Z') && !(letra>= 'a' && letra <= 'z'))
				return false;
		}
		return true;
	}
	
	/**
	 * Verifica se os quatro ultimos 'chars' sao numeros
	 * @param word
	 * @return
	 */
	boolean validNumberPlate(String word){
		char numero;
		for (int i = 0; i < word.length(); i++){
			numero = word.charAt(i);
			if (numero < '0' || numero > '9'){
				return false;
			}
		}
		return true;
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
	
	public boolean validateType(String type){
		return type.equals("car") || type.equals("motorcycle");
	}
	
	public boolean validateModel(String model){
		return isValid(model);
	}
	
	public boolean validateColor(String color){
		return isValid(color);
	}
	
	public boolean validatePlate(String plate){
		return isValid(plate) && isValidPlate(plate) ;
	}
	
	public boolean validateYear(String year){
		return isValidYear(Integer.parseInt(year));
	}
	
	public boolean validatePrice(String price){
		return isValidPrice(Double.valueOf(price));
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
	
	public boolean allShitVehiclesFieldsInvalids(String type, String model, String color,
											 String plate, String year, String price){
		return !typeIsAMandatoryField(type) || 
			   !modelIsAMandatoryField(model) || 
			   !colorIsAMandatoryField(color) ||
			   !plateIsAMandatoryField (plate)|| 
			   !yearIsAMandatoryField(year) || 
			   !priceIsAMandatoryField(price);
	}
	

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

	boolean isValid(String str) {
		String character;
		for (int i = 0; i < str.length(); i++) {
			character = str.substring(i, i + 1);
			if (!(isLetter(character) || isNumber(character)))
				return false;
		}
		return true;
	}
	
	boolean isLetter(String str) {
		int hashCode = str.hashCode();
		if ((hashCode >= 97 && hashCode <= 122)
				|| (hashCode >= 65 && hashCode <= 90))
			return true;
		return false;
	}
	
	boolean isNumber(String str) {
		if (str.hashCode() >= 48 && str.hashCode() <= 57)
			return true;
		return false;
	}
	
	boolean isValidYear(int number){
		if (number <= 0){
			return false;
		}
		return true;
	}
	
	boolean isValidPrice(double price){
		if (price <= 0){
			return false;
		}
		return true;
	}
}
