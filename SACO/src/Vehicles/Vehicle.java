package Vehicles;

import Exceptions.InvalidFieldException;
import Exceptions.NoFieldException;

public abstract class Vehicle {
	
	private String type;
	
	private String model;
	
	private String color;
	
	private String plate;
	
	private int year;
	
	private Double price;
	
	public Vehicle(String type, String model, String color, String plate,
			int year, Double price) throws InvalidFieldException, NoFieldException {
		
		if (!(type.equals("motorcycle") || type.equals("car"))) {
			throw new InvalidFieldException("error: invalid field!");
		}
		if (type == null || model == null || color == null || price == null || plate == null) {
			throw new NoFieldException("error: all fields are mandatory!");
		}
		
		if (!isValid(model) || !isValid(color) || !isValid(plate)) {
			throw new InvalidFieldException("error: invalid field!");
		}
		if (model.equals("") || color.equals("") || plate.equals("")) {
			throw new NoFieldException("error: all fields are mandatory!");
		}
		
		this.type = type;
		this.year = year;
		this.model = model;
		this.color = color;
		this.plate = plate;
		this.price = price;
	}
	
	boolean isValid(String str) {
		String character;
		for (int i = 0; i < str.length(); i++) {
			character = str.substring(i,i+1);
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
		if ((hashCode >= 97 && hashCode <= 122) || (hashCode >= 65 && hashCode <= 90))
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("9".hashCode());
	}
}
