package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;

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
import System.FieldSystemVerification;
import System.VehiclesCollection;
import Vehicles.Vehicle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class VehiclesController {
	/***/
	private static VehiclesController instance;
	/***/
	private VehiclesCollection registeredVehicles;

	private FieldSystemVerification verification;

	private VehiclesController() {
		registeredVehicles = new VehiclesCollection();
		verification = new FieldSystemVerification();
	}

	/**
	 * Padrao Singleton para a classe VehiclesController
	 * 
	 * @return uma unica instancia da classe
	 */
	public static VehiclesController getInstance() {
		if (instance == null) {
			instance = new VehiclesController();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Vehicle> getRegisteredVehicles() {
		return this.registeredVehicles.getVehiclesList();
	}

	/**
	 * Adiciona um veiculo ao sistema de controlador de veiculos
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 * @throws InvalidFieldException
	 * @throws PlateAlreadyExistsException 
	 * @throws YearException 
	 * @throws PriceException 
	 * @throws PlateException 
	 * @throws ColorException 
	 * @throws ModelException 
	 * @throws TypeException 
	 * @throws NoFieldException 
	 * @throws NoFieldException
	 * @throws TypeException
	 * @throws ModelException
	 * @throws ColorException
	 * @throws PlateException
	 * @throws PriceException
	 * @throws YearException
	 * @throws PlateAlreadyExistsException
	 */
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price)
			throws InvalidFieldException, NoFieldException, TypeException, ModelException, ColorException, PlateException, PriceException, YearException, PlateAlreadyExistsException {
		
		if (verification.allShitVehiclesFieldsInvalids(type, model, color, plate,
				year, price))
			throw new InvalidFieldException("error: all fields are mandatory!");
		if (!verification.validateType(type))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.validateModel(model))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.validateColor(color))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.validatePlate(plate))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.validateYear(year))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.validatePrice(price))
			throw new InvalidFieldException("error: invalid field!");
		registeredVehicles.add(type, model, color, plate, year, price);
	}

	/**
	 * Obtem a quantidade de veiculos do sistema controlador
	 * 
	 * @return um inteiro com a quantidade de veiculos no sistema.
	 */
	public int getAllVehicles() {
		return registeredVehicles.size();
	}

	/**
	 * Remove um veiculo, atraves da placa, do sistema de controle
	 * 
	 * @param plate
	 * @throws NoSuchVehicleException
	 * @throws NoVehicleOnDatabaseException
	 */
	public void removeVehicle(String plate) throws NoSuchVehicleException,
			NoVehicleOnDatabaseException {
		registeredVehicles.remove(plate);
	}

	
	public void printCarsByYear(List<Vehicle> vehicles, int year){
		for (int i = 0; i < vehicles.size(); i++){
			if (vehicles.get(i).getYear() == year){
				System.out.println(vehicles.get(i).toString());
			}
		}
	}
	
	public List<Integer> getListOfYears(){
		return registeredVehicles.getListOfCarsYear();
	}
	
	public void writeVehicles() {
		if (registeredVehicles != null) {
			try {

				FileOutputStream vehiclesWriter = new FileOutputStream(
						"Vehicles.xml");
				XStream xmlEncoder = new XStream();
				String registeredVehiclesEmXML = xmlEncoder
						.toXML(registeredVehicles);
				byte[] registeredVehiclesByteArray = registeredVehiclesEmXML
						.getBytes();
				vehiclesWriter.write(registeredVehiclesByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void readVehicles() throws Exception {
		FileInputStream file = new FileInputStream("Vehicles.xml");

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() == 0) {
			throw new Exception("deu nao");
		}
		
		XStream xmlDecoder = new XStream(new DomDriver());
		VehiclesCollection vehiclesArchive = (VehiclesCollection) xmlDecoder
				.fromXML(new BufferedInputStream(file));
		
		registeredVehicles = vehiclesArchive;

	}

}
