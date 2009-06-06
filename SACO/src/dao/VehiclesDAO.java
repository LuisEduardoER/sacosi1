package dao;

import java.io.FileNotFoundException;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;

public interface VehiclesDAO {
	
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price)
			throws InvalidFieldException, EmptyFieldException, AlreadyExistException;
	
	public int getAllVehicles();

	public void removeVehicle(String plate) throws NotExistException;
	
	public void cleanXML() throws FileNotFoundException;
}
