package System;

import java.util.Date;

/**
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
	
	public RequestObject(String clientEmail, String plate, Date date){
		this.clientEmail = clientEmail;
		this.plateOfVehicle = plate;
		this.date = date;
	}
	
	public String getEmail(){
		return this.clientEmail;
	}
	
	public String getPlate(){
		return this.plateOfVehicle;
	}
	
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
