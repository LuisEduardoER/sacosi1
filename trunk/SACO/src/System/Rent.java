package System;


import p1.aplic.geral.Data;
import Users.Alugadores;
import Vehicles.Alugavel;

public class Rent {

	private Alugavel vehicle;
	private Alugadores costumer;
	private Data devolutionDate;
	private Data rentDate;

	public Rent(Alugavel vehicle, Alugadores costumer, Data devolutionDate) {
		this.setVehicle(vehicle);
		this.setCostumer(costumer);
		this.setDevolutionData(devolutionDate);
		this.setRentData(new Data());
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

	public void setDevolutionData(Data devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public Data getDevolutionDate() {
		return devolutionDate;
	}

	private void setRentData(Data rentDate) {
		this.rentDate = rentDate;
	}

	public Data getRentDate() {
		return rentDate;
	}
}
