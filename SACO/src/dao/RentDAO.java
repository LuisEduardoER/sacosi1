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
			InvalidFieldException, MessagingException, Exception;
	
	public void registerRent(String plate, String email, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException, Exception;
	
	public boolean releaseVehicle(String plate) throws Exception;
	
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) throws Exception;
	
	public int getAllActiveRents() throws Exception;
	
	public String getVehicleSituation(String plate) throws Exception;
	
	public void cleanBD() throws FileNotFoundException;
	
	public String listAllPendingRents(Calendar date) throws Exception;
	
	public String listAllNonPendingRents(Calendar date) throws Exception;
	
	public int getAllRents();
	
	public int getRentsByCustomer(String email) throws Exception;
	
	public int getRentsByVehicle(String plate) throws Exception;
	
	public int getAllPendentRentRequests();
	
	public void requestRent(String clientEmail, String plate)
	throws EmptyFieldException;
	
	public String listAllRequests();
	
	public String getAllVehiclesSituation() throws Exception;
	
	public String getAllAvailablesVehicles() throws Exception;
	
	public void addManyRents(String email, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException, Exception;
	
	public void notifyCostumerAboutRelease(String plate)
	throws MessagingException;
	
	public void notifyAboutRequestRelease() throws MessagingException;

	public String printRequestList() throws Exception;
	
	
}
