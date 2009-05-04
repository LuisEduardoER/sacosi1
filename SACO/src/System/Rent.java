package System;


import java.util.Calendar;
import java.util.GregorianCalendar;

import Users.Alugadores;
import Vehicles.Alugavel;

/**
 * Esta classe representa um aluguel de um veiculo
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class Rent implements Comparable<Calendar>{

	private Alugavel vehicle;
	private Alugadores costumer;
	private String devolutionDate;
	private String rentDate;
	private String rentSituation;
	private int diasAtraso;
	private Calendar calendar;

	/**
	 * Construtor
	 * @param vehicle
	 * @param costumer
	 * @param initialDate
	 * @param devolutionDate
	 * @param status
	 */
	public Rent(Alugavel vehicle, Alugadores costumer, 
			String initialDate, String devolutionDate, String status) {
		this.setVehicle(vehicle);
		this.setCostumer(costumer);
		this.setDevolutionData(devolutionDate);
		this.setRentData(initialDate);
		this.rentSituation = status;
		this.diasAtraso = 0;
		this.calendar = GregorianCalendar.getInstance();
	}
	
	/**
	 * get day
	 * @param date
	 * @return dia
	 */
	private int getDay(String date) {
		return Integer.valueOf(date.substring(0,2));
	}
	
	/**
	 * get month
	 * @param date
	 * @return mês
	 */
	private int getMonth(String date) {
		return Integer.valueOf(date.substring(3,5));
	}
	
	/**
	 * get year
	 * @param date
	 * @return ano
	 */
	private int getYear(String date) {
		return Integer.valueOf("20" + date.substring(6,8));
	}
	
	/**
	 * se Calendar Date
	 * @param date
	 */
	@SuppressWarnings("unused")
	private void setCalendarDate(String date) {
		this.calendar.set(this.getYear(date), this.getMonth(date), this.getDay(date));
	}

	/**
	 * set vehicle
	 * @param vehicle
	 */
	public void setVehicle(Alugavel vehicle) {
		this.vehicle = vehicle;
	}
	
	/**
	 * get Rent Situation
	 * @return situacao do aluguel
	 */
	public String getRentSituation() {
		return this.rentSituation;
	}
	
	/**
	 * set rent situation
	 * @param rentSit
	 */
	public void setRentSituation(String rentSit) {
		this.rentSituation = rentSit;
	}
	
	/**
	 * get vehicle
	 * @return veiculo
	 */
	public Alugavel getVehicle() {
		return vehicle;
	}

	/**
	 * get vehicle plate
	 * @return placa do veiculo
	 */
	public String getVehiclePlate() {
		return this.getVehicle().getPlate();
	}

	/**
	 * set costumer
	 * @param costumer
	 */
	public void setCostumer(Alugadores costumer) {
		this.costumer = costumer;
	}

	/**
	 * get costumer
	 * @return costumer
	 */
	public Alugadores getCostumer() {
		return costumer;
	}

	/**
	 * set devolution date
	 * @param devolutionDate
	 */
	public void setDevolutionData(String devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	/**
	 * get devolution date
	 * @return
	 */
	public String getDevolutionDate() {
		return devolutionDate;
	}

	/**
	 * set rent date
	 * @param rentDate
	 */
	private void setRentData(String rentDate) {
		this.rentDate = rentDate;
	}

	/**
	 * get rent date
	 * @return
	 */
	public String getRentDate() {
		return rentDate;
	}
	
	/**
	 * toString do aluguel
	 */
	public String toString() {
		String output = "\n";
		output += "===========================================================\n";
		output += this.costumer.toString() + "\n";
		output += this.vehicle.toString() + "\n";
		output += "Data de locacao: " + this.rentDate + "\n";
		output += "Data de entrega: " + this.devolutionDate + "\n";
		output += "Dias atrasados: " + this.diasAtraso + "\n";
		output += "===========================================================\n";
		return output;
	}

	/**
	 * Compara as datas
	 */
	public int compareTo(Calendar o) {
		return this.getCalendar().compareTo(o);
	}

	/**
	 * get Calendar
	 * @return calendar
	 */
	private Calendar getCalendar() {
		return this.calendar;
	}

}
