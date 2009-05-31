package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
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

/**
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * @author Melina
 * 
 */
public class VehiclesController {
	
	private static VehiclesController instance;
	private VehiclesCollection registeredVehicles;
	private FieldSystemVerification verification;
	private static final String VEHICLES_FILE = "Vehicles.xml";

	private VehiclesController() throws Exception {
		registeredVehicles = VehiclesCollection.getInstance();
		verification = new FieldSystemVerification();
		this.readVehicles();
	}

	/**
	 * Padrao Singleton para a classe VehiclesController
	 * 
	 * @return uma unica instancia da classe
	 * @throws Exception
	 */
	public static synchronized VehiclesController getInstance() throws Exception {
		if (instance == null) {
			instance = new VehiclesController();
		}
		return instance;
	}

	/**
	 * Retorna um iterador de todos os veiculos registrados
	 * 
	 * @return um iterador de todos os veiculos registrados
	 */
	public Iterator<Vehicle> iterator() {
		return this.registeredVehicles.iterator();
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
			throws InvalidFieldException, NoFieldException, TypeException,
			ModelException, ColorException, PlateException, PriceException,
			YearException, PlateAlreadyExistsException {

		if (verification.allShitVehiclesFieldsInvalids(type, model, color,
				plate, year, price))
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

	/**
	 * Imprime todos os carro por um dado ano
	 * 
	 * @param vehicles
	 * @param year
	 */
	public void printCarsByYear(List<Vehicle> vehicles, int year) {
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getYear() == year) {
				System.out.println(vehicles.get(i).toString());
			}
		}
	}

	/**
	 * Retorna lista dos anos dos carros
	 * 
	 * @return lista dos anos dos carros
	 */
	public List<Integer> getListOfYears() {
		return registeredVehicles.getListOfCarsYear();
	}

	/**
	 * Escreve todos os veiculos em um arquivo .xml
	 */
	public void writeVehicles() {
		if (registeredVehicles != null) {
			try {

				FileOutputStream vehiclesWriter = new FileOutputStream(
						VEHICLES_FILE);
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

	/**
	 * Apaga o conteudo dos arquivos .xml
	 * 
	 * @throws FileNotFoundException
	 */
	public void emptyXML() throws FileNotFoundException {
		FileOutputStream vehiclesWriter = new FileOutputStream(VEHICLES_FILE);
		this.registeredVehicles.emptyList();
	}

	/**
	 * Faz a leitura de todos os veiculos de um arquivo .xml
	 * 
	 * @throws Exception
	 */
	public void readVehicles() throws Exception {
		FileInputStream file = new FileInputStream(VEHICLES_FILE);

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {

			XStream xmlDecoder = new XStream(new DomDriver());
			VehiclesCollection vehiclesArchive = (VehiclesCollection) xmlDecoder
					.fromXML(new BufferedInputStream(file));

			registeredVehicles = vehiclesArchive;
		}
	}

}
