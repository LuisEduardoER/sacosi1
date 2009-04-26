package Vehicles;


public abstract class Vehicle implements Alugavel {

	private String type;

	private String model;

	private String color;

	private String plate;

	private int year;

	private Double price;

	public Vehicle(String type, String model, String color, String plate,
			String year, String price) {

		this.setType(type);
		this.setYear(Integer.parseInt(year));
		this.setModel(model);
		this.setColor(color);
		this.setPlate(plate);
		this.setPrice(Double.parseDouble(price));
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public String getModel() {
		return model;
	}

	public String getPlate() {
		return plate;
	}

	public Double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public int getYear() {
		return year;
	}

}
