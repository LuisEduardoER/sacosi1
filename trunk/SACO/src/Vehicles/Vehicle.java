package Vehicles;

import Exceptions.InvalidFieldException;
import Exceptions.NoFieldException;
import Exceptions.PriceException;
import Exceptions.ColorException;
import Exceptions.PlateException;
import Exceptions.TypeException;
import Exceptions.YearException;
import Exceptions.ModelException;

public abstract class Vehicle implements Alugavel {

	private String type;

	private String model;

	private String color;

	private String plate;

	private int year;

	private Double price;

	public Vehicle(String type, String model, String color, String plate,
			String year, String price) throws InvalidFieldException,
			NoFieldException, TypeException, ModelException, ColorException,
			PlateException, PriceException, YearException {

		if (type == null || model == null || color == null || price == null
				|| plate == null || year == null || price == null) {
			throw new NoFieldException("error: all fields are mandatory!");
		}
		
		if (!(type.equals("motorcycle") || type.equals("car"))) {
			throw new InvalidFieldException("error: invalid field!");
		}

		if (!isValid(model) || !isValid(color) || !isValid(plate)) {
			throw new InvalidFieldException("error: invalid field!");
		}
		if (model.equals("") || color.equals("") || plate.equals("")) {
			throw new NoFieldException("error: all fields are mandatory!");
		}

		this.setType(type);
		this.setYear(Integer.parseInt(year));
		this.setModel(model);
		this.setColor(color);
		this.setPlate(plate);
		this.setPrice(Double.parseDouble(price));
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

	boolean isNumber(String str) {
		if (str.hashCode() >= 48 && str.hashCode() <= 57)
			return true;
		return false;
	}

	boolean isLetter(String str) {
		int hashCode = str.hashCode();
		if ((hashCode >= 97 && hashCode <= 122)
				|| (hashCode >= 65 && hashCode <= 90))
			return true;
		return false;
	}

	public void setType(String type) throws TypeException {
		if (!validType(type))
			throw new TypeException("error: all fields are mandatory!");
		this.type = type;
	}

	boolean validType(String type) {
		if (type.equals(null) || type.equals(""))
			return false;
		return true;
	}

	public void setYear(int year) throws YearException {
		if (!validYear(year))
			throw new YearException("error: all fields are mandatory!");
		if (!isValidYear(year))
			throw new YearException("error: invalid field!");
		this.year = year;
	}

	boolean validYear(int number) {
		if (String.valueOf(number).equals(null)
				|| String.valueOf(number).equals(""))
			return false;
		return true;
	}

	boolean isValidYear(int number){
		if (number <= 0){
			return false;
		}
		return true;
	}
	public void setModel(String model) throws ModelException {
		if (!validModel(model))
			throw new ModelException("error: all fields are mandatory!");
		this.model = model;
	}

	boolean validModel(String model) {
		if (model.equals(null) || model.equals(""))
			return false;
		return true;
	}

	public void setColor(String color) throws ColorException {
		if (!validColor(color))
			throw new ColorException("error: all fields are mandatory!");
		this.color = color;
	}

	boolean validColor(String color) {
		if (color.equals(null) || color.equals(""))
			return false;
		return true;
	}

	public void setPlate(String plate) throws PlateException {
		if (!validPlate(plate))
			throw new PlateException("error: all fields are mandatory!");
		if (!isValidPlate(plate))
			throw new PlateException("error: invalid field!");
		this.plate = plate;
	}

	boolean validPlate(String plate2) {
		if (plate2 == null || plate2.equals(""))
			return false;
		return true;
	}
	boolean isValidPlate(String plate){
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
	boolean validLetterPlate(String word){
		char letra;
		for (int i = 0; i < word.length(); i++){
			letra = word.charAt(i);
			if (letra < 'A' || letra > 'Z')
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
	public void setPrice(Double price) throws PriceException {
		if (!validPrice(price))
			throw new PriceException("error: all fields are mandatory!");
		if (!isValidPrice(price))
			throw new PriceException("error: invalid field!");
		this.price = price;
	}

	boolean validPrice(Double price2) {
		if (String.valueOf(price2) == null || String.valueOf(price2).equals(""))
			return false;
		return true;
	}

	boolean isValidPrice(double price){
		if (price <= 0){
			return false;
		}
		return true;
	}
	public String getColor() {
		return color;
	}

	public String getModel() {
		return model;
	}

	public String getPlate() {
		return plate;
	}

	public Double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public int getYear() {
		return year;
	}

}
