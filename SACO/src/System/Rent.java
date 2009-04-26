package System;


import java.util.Calendar;
import java.util.GregorianCalendar;

import p1.aplic.geral.Data;
import Users.Alugadores;
import Vehicles.Alugavel;

public class Rent {

	private Alugavel vehicle;
	private Alugadores costumer;
	private String devolutionDate;
	private String rentDate;
	private String rentSituation;
	int diasAtraso;
	Calendar calendar;

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
	
	private int getDay(String date) {
		return Integer.valueOf(date.substring(0,2));
	}
	
	private int getMonth(String date) {
		return Integer.valueOf(date.substring(2,4));
	}
	
	private int getYear(String date) {
		return Integer.valueOf("20"+date.substring(4,6));
	}
	
	private void setCalendarDate(String date) {
		this.calendar.set(this.getYear(date), this.getMonth(date), this.getDay(date));
	}

	public void setVehicle(Alugavel vehicle) {
		this.vehicle = vehicle;
	}
	
	public String getRentSituation() {
		
		return this.rentSituation;
		
	}
	
	public Alugavel getVehicle() {
		return vehicle;
	}

	public String getVehiclePlate() {
		return this.getVehicle().getPlate();
	}
	
	public void setCostumer(Alugadores costumer) {
		this.costumer = costumer;
	}

	public Alugadores getCostumer() {
		return costumer;
	}

	public void setDevolutionData(String devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public String getDevolutionDate() {
		return devolutionDate;
	}

	private void setRentData(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getRentDate() {
		return rentDate;
	}

}
