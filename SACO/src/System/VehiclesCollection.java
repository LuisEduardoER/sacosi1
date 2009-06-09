package System;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Vehicles.Car;
import Vehicles.Motorcycle;
import Vehicles.Vehicle;

/**
 * Classe que armazena todos os veiculos do sistema.
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class VehiclesCollection {
	/**
	 * instancia da classe
	 */
	private static VehiclesCollection instance;
	
	/**
	 * lista de veiculos
	 */
	private List<Vehicle> vehiclesList;

	/**
	 * Construtor da classe
	 */
	private VehiclesCollection() {
		vehiclesList = new ArrayList<Vehicle>();
	}

	/**
	 * Metodo que retorna a instancia da classe
	 * @return a instancia da classe
	 */
	public static synchronized VehiclesCollection getInstance() {
		if (instance == null)
			return instance = new VehiclesCollection();
		return instance;
	}
	
	/**
	 * Metodo que retorna a quantidade de veiculos adicionada ao sistema
	 * 
	 * @return quantidade de veiculos adicionados
	 */
	public int size() {
		return vehiclesList.size();
	}

	/**
	 * Adiciona um carro ou uma moto ao sistema.
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 * @throws InvalidFieldException
	 * @throws NoFieldException
	 * @throws AlreadyExistException
	 */
	public void add(String type, String model, String color, String plate,
			String year, String price) throws InvalidFieldException,
			EmptyFieldException, AlreadyExistException {

		if (type.equals("car")) {
			Car carro = new Car(type, model, color, plate, year, price);
			if (plateAlreadyExists(plate)) {
				throw new AlreadyExistException(
						"error: this vehicle already exists!");
			}
			vehiclesList.add(carro);
		} else if (type.equals("motorcycle")) {
			Motorcycle moto = new Motorcycle(type, model, color, plate, year,
					price);
			if (plateAlreadyExists(plate)) {
				throw new AlreadyExistException(
						"error: this vehicle already exists!");
			}
			vehiclesList.add(moto);
		}

	}

	/**
	 * Verifica se uma determinada placa ja existe no sistema
	 * 
	 * @param plate
	 * @return
	 */
	private boolean plateAlreadyExists(String plate) {
		Iterator<Vehicle> it = vehiclesList.iterator();
		while (it.hasNext()) {
			Vehicle veiculo = it.next();
			if (veiculo.getPlate().equalsIgnoreCase(plate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove um veiculo do sistema
	 * 
	 * @param plate
	 * @throws NotExistException
	 */
	public void remove(String plate) throws NotExistException {
		if (size() == 0) {
			throw new NotExistException(
					"error: there is no vehicles on database");
		}
		if (plate == null || plate.equals("")) {
			throw new InvalidParameterException("error: invalid parameter!");
		}
		if (findVehicle(plate) == null) {
			throw new NotExistException("error: no such vehicle!");
		}
		vehiclesList.remove(findVehicle(plate));
	}
	
	
	/**
	 * Procura uma placa no array de veiculos
	 * 
	 * @param plate
	 * @return
	 */
	public Vehicle findVehicle(String plate) {
		Iterator<Vehicle> it = vehiclesList.iterator();
		while (it.hasNext()) {
			Vehicle veiculo = it.next();
			if (veiculo.getPlate().equalsIgnoreCase(plate)) {
				return veiculo;
			}
		}
		return null;
	}

	/**
	 * get lista dos anos dos carros cadastrados no sistema
	 * 
	 * @return lista dos anos dos carros cadastrados no sistema
	 */
	public List<Integer> getListOfCarsYear() {
		List<Integer> list = new ArrayList<Integer>();
		Iterator<Vehicle> it = vehiclesList.iterator();
		while (it.hasNext()) {
			Vehicle vehicle = it.next();
			if (!list.contains(vehicle.getYear()))
				list.add(vehicle.getYear());
		}
		sortByYear(list);
		return list;
	}

	/**
	 * Ordenar por ano
	 * 
	 * @param year
	 */
	private void sortByYear(List<Integer> year) {
		Collections.sort(year);
	}

	/**
	 * Zera lista
	 */
	public void emptyList() {
		this.vehiclesList = new ArrayList<Vehicle>();
	}

	/**
	 * toString
	 */
	public String toString() {
		String output = "";
		for (Vehicle vehicle : vehiclesList) {
			output += vehicle.toString();
		}
		return output;
	}

	/**
	 * Retorna um iterador da lista de veiculos
	 * 
	 * @return iterador da lista de veiculos
	 */
	public Iterator<Vehicle> iterator() {
		return vehiclesList.iterator();
	}

}
