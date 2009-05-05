package Vehicles;

/**
 * Esta classe representa um veiculo no sistema
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 * 
 */
public abstract class Vehicle implements Alugavel {

	private int year;

	private Double price;

	private String type;

	private String model;

	private String color;

	private String plate;

	/**
	 * Contrutor
	 * 
	 * @param type
	 * @param model
	 * @param color
	 * @param plate
	 * @param year
	 * @param price
	 */
	public Vehicle(String type, String model, String color, String plate,
			String year, String price) {
		this.setType(type);
		this.setYear(Integer.parseInt(year));
		this.setModel(model);
		this.setColor(color);
		this.setPlate(plate);
		this.setPrice(Double.parseDouble(price));
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the plate
	 */
	public String getPlate() {
		return plate;
	}

	/**
	 * @param plate
	 *            the plate to set
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * toSTring
	 */
	public String toString() {
		String output = "\n";
		output += "===================================================\n";
		output += "Tipo: " + this.getType() + "\n";
		output += "Modelo: " + this.getModel() + "\n";
		output += "Cor: " + this.getColor() + "\n";
		output += "Placa: " + this.getPlate() + "\n";
		output += "Ano: " + this.getYear() + "\n";
		output += "Preço de aluguel: " + this.getPrice() + "\n";
		return output;
	}

}
