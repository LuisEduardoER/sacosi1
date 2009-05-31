package Vehicles;

import Exceptions.InvalidFieldException;

/**
 * Esta interface representa um veiculo no sistema
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 * 
 */
public interface Alugavel {

	/**
	 * set Type
	 * 
	 * @param type
	 * @throws TypeException
	 */
	public void setType(String type) throws InvalidFieldException;

	/**
	 * get Type
	 * 
	 * @return type
	 */
	public String getType();

	/**
	 * set Year
	 * 
	 * @param year
	 * @throws YearException
	 */
	public void setYear(int year) throws InvalidFieldException;

	/**
	 * get year
	 * 
	 * @return year
	 */
	public int getYear();

	/**
	 * set model
	 * 
	 * @param model
	 * @throws ModelException
	 */
	public void setModel(String model) throws InvalidFieldException;

	/**
	 * get model
	 * 
	 * @return model
	 */
	public String getModel();

	/**
	 * set color
	 * 
	 * @param color
	 * @throws ColorException
	 */
	public void setColor(String color) throws InvalidFieldException;

	/**
	 * get color
	 * 
	 * @return color
	 */
	public String getColor();

	/**
	 * set price
	 * 
	 * @param price
	 * @throws PriceException
	 */
	public void setPrice(Double price) throws InvalidFieldException;

	/**
	 * get price
	 * 
	 * @return price
	 */
	public Double getPrice();

	/**
	 * set plate
	 * 
	 * @param plate
	 * @throws PlateException
	 */
	public void setPlate(String plate) throws InvalidFieldException;

	/**
	 * get plate
	 * 
	 * @return plate
	 */
	public String getPlate();
}
