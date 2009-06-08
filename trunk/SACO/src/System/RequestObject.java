package System;

import java.util.Calendar;
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
	private Calendar calendario = Calendar.getInstance();

	/**
	 * Construtor
	 * 
	 * @param clientEmail
	 * @param plate
	 * @param date
	 */
	public RequestObject(String clientEmail, String plate) {
		this.clientEmail = clientEmail;
		this.plateOfVehicle = plate;
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
	 * Metodo que obtem a data da reserva.
	 * @return
	 */
	public Calendar getDate(){
		return this.calendario;
	}
	/**
	 * toString do aluguel
	 */
	public String toString() {
		String output = "\n";
		int mes = calendario.get(calendario.MONTH);
		mes += 1;
		output += "=======================Requisicao==========================\n";
		output += "Data: " + calendario.get(calendario.DAY_OF_MONTH)+ "/" + mes + "/" + calendario.get(calendario.YEAR) + "\n";
		output += "Email do cliente: " + this.clientEmail + "\n";
		output += "Placa do veiculo: " + this.plateOfVehicle + "\n";
		output += "===========================================================\n";
		return output;
	}
}
