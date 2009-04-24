package System;

import java.util.Date;

import Users.Alugadores;
import Vehicles.Alugavel;

public class Rent {

	private Alugavel vehicle;
	private Alugadores costumer;
	private Date devolutionDate;
	private Date rentDate;

	public Rent(Alugavel vehicle, Alugadores costumer, Date devolutionDate) {
		this.setVehicle(vehicle);
		this.setCostumer(costumer);
		this.setDevolutionDate(devolutionDate);
		this.setRentDate(new Date());
	}

	public void setVehicle(Alugavel vehicle) {
		this.vehicle = vehicle;
	}

	public Alugavel getVehicle() {
		return vehicle;
	}

	public void setCostumer(Alugadores costumer) {
		this.costumer = costumer;
	}

	public Alugadores getCostumer() {
		return costumer;
	}

	public void setDevolutionDate(Date devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public Date getDevolutionDate() {
		return devolutionDate;
	}

	private void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getRentDate() {
		return rentDate;
	}
}
