package System;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.PhoneException;

public class RentCollection {

	private static RentCollection instance;
	private List<Rent> rents;

	private RentCollection() {
		rents = new ArrayList<Rent>();
	}

	public static synchronized RentCollection getInstance() {
		if (instance == null)
			return instance = new RentCollection();
		return instance;
	}

	public void add() {

	}

	public boolean removeRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rents.remove(rent);
				return true;
			}
		}
		return false;
	}

	public int getAllActiveRents() {
		int cont = 0;
		for (Rent rent : rents) {
			if (rent.getRentSituation().equals("active")) {
				cont++;
			}
		}
		return cont;
	}

	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws InvalidDateException,
			InvalidParameterException, EmailException, InvalidNameException,
			PhoneException, CustomerAlreadyExistException,
			InvalidFieldException {

		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rent.setRentSituation("late");
				break;
			}
		}
	}

	public String getVehicleSituation(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate))
				return "unavailable";
		}
		return "available";
	}

	/**
	 * Retorna a situcao do aluguel
	 * 
	 * @param email
	 * @param plate
	 * @param inicialDate
	 * @param finalDate
	 * @return situcao do aluguel
	 */
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) {
		for (Rent rent : rents) {
			if (rent.getCostumer().getEmail().equals(email)
					&& rent.getVehiclePlate().equals(plate))
				return rent.getRentSituation();
		}
		return null;

	}

	public String listAllPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) < 1)
				output += rent.toString();
		}
		return output;
	}

	public String listAllNonPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) >= 1)
				output += rent.toString();
		}
		return output;
	}

	public int getRentsByCustomer(String email) {
		int cont = 0;
		if (rents != null)
			for (Rent rent : rents) {
				if (rent.getCostumer().getEmail().equals(email)) {
					cont++;
				}
			}
		return cont;
	}

	public int getRentsByVehicle(String plate) {
		int cont = 0;
		if (rents != null)
			for (Rent rent : rents) {
				if (rent.getVehiclePlate().equals(plate)) {
					cont++;
				}
			}
		return cont;
	}

	public boolean vehicleIsRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equals(plate)) {
				return true;
			}
		}
		return false;
	}

	public void remove(Rent rent) {
		this.rents.remove(rent);
	}

	public Iterator<Rent> iterator() {
		return rents.iterator();
	}

	public void emptyList() {
		this.rents = new ArrayList<Rent>();
	}

	public int size() {
		return rents.size();
	}

	public void add(Rent rent) {
		this.rents.add(rent);
	}
}
