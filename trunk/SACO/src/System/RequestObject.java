package System;

import java.util.Date;

/**
 * Esta classe tem acesso a alguns dados do sistema e contem metodos para
 * retorna-los
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class RequestObject {

	private String clientEmail;
	private String plateOfVehicle;
	private Date date;

	/**
	 * Construtor
	 * 
	 * @param clientEmail
	 * @param plate
	 * @param date
	 */
	public RequestObject(String clientEmail, String plate, Date date) {
		this.clientEmail = clientEmail;
		this.plateOfVehicle = plate;
		this.date = date;
	}

	/**
	 * get email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.clientEmail;
	}

	/**
	 * get plate
	 * 
	 * @return plate
	 */
	public String getPlate() {
		return this.plateOfVehicle;
	}

	/**
	 * toString do aluguel
	 */
	public String toString() {
		String output = "\n";
		output += "=======================Requisicao==========================\n";
		output += "Data: " + date + "\n";
		output += "Email do cliente: " + this.clientEmail + "\n";
		output += "Placa do veiculo: " + this.plateOfVehicle + "\n";
		output += "===========================================================\n";
		return output;
	}
}