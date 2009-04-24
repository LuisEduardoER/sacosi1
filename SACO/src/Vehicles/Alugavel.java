package Vehicles;

import Exceptions.ColorException;
import Exceptions.ModelException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.YearException;

public interface Alugavel {
	
	public void setType(String type) throws TypeException;
	public String getType();
	
	public void setYear(int year) throws YearException;
	public int getYear();
	
	public void setModel(String model) throws ModelException;
	public String getModel();
	
	public void setColor(String color) throws ColorException;
	public String getColor();
	
	public void setPrice(Double price) throws PriceException;
	public Double getPrice();
	
	public void setPlate(String plate) throws PlateException;
	public String getPlate();
}
