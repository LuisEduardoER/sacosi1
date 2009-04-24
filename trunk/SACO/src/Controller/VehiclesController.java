package Controller;

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

public class VehiclesController {
	/***/
	private VehiclesController instance;
	/***/
	private VehiclesCollection registeredVehicles;

	private VehiclesController() {
		registeredVehicles = new VehiclesCollection();
	}

	/**
	 * Padrao Singleton para a classe VehiclesController
	 * 
	 * @return uma unica instancia da classe
	 */
	public VehiclesController getInstance() {
		if (instance == null) {
			instance = new VehiclesController();
		}
		return instance;
	}

	/**
	 * Adiciona um veiculo ao sistema de controlador de veiculos
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
	 * @throws PlateAlreadyExistsException
	 */
	public void addVehicle(String type, String model, String color,
			String plate, int year, double price) throws InvalidFieldException,
			NoFieldException, TypeException, ModelException, ColorException,
			PlateException, PriceException, YearException,
			PlateAlreadyExistsException {
		
		registeredVehicles.add(type, model, color, plate, year, price);
	}
	
	/**
	 * Obtem a quantidade de veiculos do sistema controlador
	 * @return	
	 * 			um inteiro com a quantidade de veiculos no sistema.
	 */
	public int getAllVehicles(){
		return registeredVehicles.size();
	}

	/**
	 * Remove um veiculo, atraves da placa, do sistema de controle
	 * @param plate
	 * @throws NoSuchVehicleException
	 * @throws NoVehicleOnDatabaseException
	 */
	public void removeVehicle (String plate) throws NoSuchVehicleException, NoVehicleOnDatabaseException{
		registeredVehicles.remove(plate);
	}
}
