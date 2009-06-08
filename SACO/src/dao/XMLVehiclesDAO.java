package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.EmptyFieldException;
import Exceptions.NotExistException;
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
public class XMLVehiclesDAO implements VehiclesDAO{
	
	private static XMLVehiclesDAO instance;
	private VehiclesCollection registeredVehicles;
	private FieldSystemVerification verification;
	private static final String VEHICLES_FILE = "Vehicles.xml";

	private XMLVehiclesDAO() throws Exception {
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
	public static synchronized XMLVehiclesDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new XMLVehiclesDAO();
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
	 * @throws EmptyFieldException
	 * @throws AlreadyExistException
	 */
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price)
			throws InvalidFieldException, EmptyFieldException, AlreadyExistException {

		if (verification.allVehiclesFieldsInvalids(type, model, color,
				plate, year, price))
			throw new InvalidFieldException("error: all fields are mandatory!");
		if (!verification.validateType(type)
				|| !verification.validateModel(model)
				|| !verification.validateColor(color)
				|| !verification.validatePlate(plate)
				|| !verification.validateYear(year)
				|| !verification.validatePrice(price))
			throw new InvalidFieldException("error: invalid field!");
		registeredVehicles.add(type, model, color, plate, year, price);
		this.writeVehicles();
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
	 * @throws NotExistException
	 */
	public void removeVehicle(String plate) throws NotExistException {
		registeredVehicles.remove(plate);
		this.writeVehicles();
	}

	/**
	 * Imprime todos os carro por um dado ano
	 * 
	 * @param vehicles
	 * @param year
	 */
	public String printCarsByYear(List<Vehicle> vehicles, int year) {
		String output = "";
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getYear() == year) {
				output += vehicles.get(i).toString();
			}
		}
		return output;
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
	public void cleanXML() throws FileNotFoundException {
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
