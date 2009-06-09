package dao;

import java.io.FileNotFoundException;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public interface VehiclesDAO {
	
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
			throws InvalidFieldException, EmptyFieldException, AlreadyExistException;
	
	/**
	 * Obtem a quantidade de veiculos do sistema controlador
	 * 
	 * @return um inteiro com a quantidade de veiculos no sistema.
	 */
	public int getAllVehicles();
	
	/**
	 * Remove um veiculo, atraves da placa, do sistema de controle
	 * 
	 * @param plate
	 * @throws NotExistException
	 */
	public void removeVehicle(String plate) throws NotExistException;
	
	/**
	 * Apaga o conteudo dos arquivos .xml
	 * 
	 * @throws FileNotFoundException
	 */
	public void cleanXML() throws FileNotFoundException;
}
