package Vehicles;


import Exceptions.InvalidFieldException;
import Exceptions.NoFieldException;
import Exceptions.PriceException;
import Exceptions.ColorException;
import Exceptions.PlateException;
import Exceptions.TypeException;
import Exceptions.YearException;
import Exceptions.ModelException;

/**
 * Esta classe representa um carro no sistema
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 *
 */
public class Car extends Vehicle{
	
	/**
	 * Contrutor
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 * @throws InvalidFieldException
	 * @throws NoFieldException
	 * @throws TypeException
	 * @throws ModelException
	 * @throws ColorException
	 * @throws PlateException
	 * @throws PriceException
	 * @throws YearException
	 */
	public Car(String type, String model, String color, String plate, String year,
			String price) throws InvalidFieldException, NoFieldException, TypeException, ModelException, 
						  ColorException, PlateException, PriceException, YearException {
		super(type, model, color, plate, year, price);
		
	}
	
	
	
	
}
