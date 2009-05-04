package System;


import java.util.Calendar;
import java.util.GregorianCalendar;

import Users.Alugadores;
import Vehicles.Alugavel;

/**
 * 
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
	 * 
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
	 * 
	 * @param date
	 * @return
	 */
	private int getDay(String date) {
		return Integer.valueOf(date.substring(0,2));
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private int getMonth(String date) {
		return Integer.valueOf(date.substring(3,5));
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private int getYear(String date) {
		return Integer.valueOf("20" + date.substring(6,8));
	}
	
	/**
	 * 
	 * @param date
	 */
	@SuppressWarnings("unused")
	private void setCalendarDate(String date) {
		this.calendar.set(this.getYear(date), this.getMonth(date), this.getDay(date));
	}

	/**
	 * 
	 * @param vehicle
	 */
	public void setVehicle(Alugavel vehicle) {
		this.vehicle = vehicle;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRentSituation() {
		return this.rentSituation;
	}
	
	/**
	 * 
	 * @param rentSit
	 */
	public void setRentSituation(String rentSit) {
		this.rentSituation = rentSit;
	}
	
	/**
	 * 
	 * @return
	 */
	public Alugavel getVehicle() {
		return vehicle;
	}

	/**
	 * 
	 * @return
	 */
	public String getVehiclePlate() {
		return this.getVehicle().getPlate();
	}

	/**
	 * 
	 * @param costumer
	 */
	public void setCostumer(Alugadores costumer) {
		this.costumer = costumer;
	}

	/**
	 * 
	 * @return
	 */
	public Alugadores getCostumer() {
		return costumer;
	}

	/**
	 * 
	 * @param devolutionDate
	 */
	public void setDevolutionData(String devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getDevolutionDate() {
		return devolutionDate;
	}

	/**
	 * 
	 * @param rentDate
	 */
	private void setRentData(String rentDate) {
		this.rentDate = rentDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getRentDate() {
		return rentDate;
	}
	
	/**
	 * 
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
	 * 
	 */
	public int compareTo(Calendar o) {
		return this.getCalendar().compareTo(o);
	}

	/**
	 * 
	 * @return
	 */
	private Calendar getCalendar() {
		return this.calendar;
	}

}
