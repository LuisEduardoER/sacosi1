package Tests;

import Controller.VehiclesController;
import Exceptions.ColorException;
import Exceptions.InvalidFieldException;
import Exceptions.ModelException;
import Exceptions.NoFieldException;
import Exceptions.NoSuchVehicleException;
import Exceptions.NoVehicleOnDatabaseException;
import Exceptions.PlateAlreadyExistsException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.YearException;

public class VehiclesControllerTest {
	
	private VehiclesController test;
	
	public VehiclesControllerTest() {
		test = VehiclesController.getInstance();
	}
	
	public int getAllVehicles() {
		return test.getAllVehicles();
	}
	
	public void addVehicle(String type, String model, String color, String plate, String year, String price) throws InvalidFieldException, NoFieldException, TypeException, ModelException, ColorException, PlateException, PriceException, YearException, PlateAlreadyExistsException {
		test.addVehicle(type, model, color, plate, year, price);
	}
	
	public void removeVehicle(String plate) throws NoSuchVehicleException, NoVehicleOnDatabaseException{
		test.removeVehicle(plate);
	}

}