package dao;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.mail.MessagingException;

import Users.Alugadores;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;

public interface RentDAO {
	
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException;
	
	public void registerRent(String plate, String email, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException;
	
	public boolean releaseVehicle(String plate);
	
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate);
	
	public int getAllActiveRents();
	
	public String getVehicleSituation(String plate);
	
	public void cleanBD() throws FileNotFoundException;
	
	public void seeCars();
	
	public String listAllPendingRents(Calendar date);
	
	public String listAllNonPendingRents(Calendar date);
	
	public int getAllRents();
	
	public int getRentsByCustomer(String email);
	
	public int getRentsByVehicle(String plate);
	
	public int getAllPendentRentRequests();
	
	public void requestRent(String clientEmail, String plate)
	throws EmptyFieldException;
	
	public String listAllRequests();
	
	public String getAllVehiclesSituation();
	
	public void addManyRents(Alugadores customer, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException;
	
	public void notifyCostumerAboutRelease(String plate)
	throws MessagingException;
	
	public void notifyAboutRequestRelease() throws MessagingException;
	
	
}
