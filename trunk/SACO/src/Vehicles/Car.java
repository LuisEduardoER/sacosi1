package Vehicles;

import Exceptions.InvalidFieldException;
import Exceptions.EmptyFieldException;

/**
 * Esta classe representa um carro no sistema
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 * 
 */
public class Car extends Vehicle {

	/**
	 * Contrutor
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 * @throws InvalidFieldException
	 * @throws NoFieldException
	 */
	public Car(String type, String model, String color, String plate,
			String year, String price) throws InvalidFieldException,
			EmptyFieldException {
		super(type, model, color, plate, year, price);

	}
}
