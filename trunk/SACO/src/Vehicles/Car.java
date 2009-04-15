package Vehicles;


import Exceptions.InvalidFieldException;
import Exceptions.NoFieldException;
import Exceptions.PriceException;
import Exceptions.ColorException;
import Exceptions.PlateException;
import Exceptions.TypeException;
import Exceptions.YearException;
import Exceptions.ModelException;

public class Car extends Vehicle{

	public Car(String type, String model, String color, String plate, int year,
			Double price) throws InvalidFieldException, NoFieldException, TypeException, ModelException, 
						  ColorException, PlateException, PriceException, YearException {
		super(type, model, color, plate, year, price);
		
	}
	
	
	
	
}
