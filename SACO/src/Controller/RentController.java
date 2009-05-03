package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.PhoneException;
import System.CustomerCollection;
import System.FieldSystemVerification;
import System.FunctionariesCollection;
import System.Rent;
import System.RequestRentCollection;
import Users.Customer;
import Vehicles.Vehicle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author
 *
 */
public class RentController {

	private Collection<Rent> rents;
	private UserController userController;
	private CustomerCollection customerCollection;
	private FunctionariesCollection functionariesCollection;
	private FieldSystemVerification verification;
	private VehiclesController vehicleCollection;
	private RequestRentCollection requestList;
	public static RentController instance;
	private Calendar calendar;


	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static RentController getInstance() throws Exception {
		return instance == null ? 
				instance = new RentController() : 
					instance;
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public RentController() throws Exception {
		calendar = Calendar.getInstance();
		this.rents = new ArrayList<Rent>();
		this.userController = UserController.getInstance();
		this.functionariesCollection = this.userController.getFunctionariesCollection();
		this.customerCollection = this.userController.getCustomerCollection();
		this.vehicleCollection = VehiclesController.getInstance(); 
		this.verification = new FieldSystemVerification();
		this.requestList = new RequestRentCollection();
		this.readRents();
		this.readRequestRents();
	}

	/**
	 * 
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @throws InvalidDateException
	 * @throws InvalidParameterException
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws CustomerAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void registerLateRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidDateException, InvalidParameterException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {

		if (!this.correctPeriod(initialDate, finalDate))
			throw new InvalidDateException("error: end date is greater than today date!");
		for(Rent rent: rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rent.setRentSituation("late");
				break;
			}
		}
	}

	/**
	 * 
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @throws InvalidParameterException
	 * @throws InvalidDateException
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws CustomerAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void registerRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {
		this.register(plate, email, initialDate, finalDate, "active");
	}

	/**
	 * 
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @param rentSituation
	 * @throws InvalidParameterException
	 * @throws InvalidDateException
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws CustomerAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void register(String plate, String email, String initialDate, 
			String finalDate, String rentSituation) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {

		if (!this.verification.validPlate(plate) || !this.verification.emailIsAMandatoryField(email) ||
				!this.verification.dateIsMandatoryField(initialDate) ||
				!this.verification.dateIsMandatoryField(finalDate))
			throw new InvalidParameterException("error: all parameters are mandatory!");

		if (!this.verification.validateEmail(email) ||
				!this.verification.isValidPlate(plate) || this.vehicleIsRent(plate))
			throw new InvalidParameterException("error: invalid parameter(s)"); 

		if (!this.vehicleIsRent(plate)) {
			if (!this.userExists(email))
				this.userController.addCustomer("name", email, "8388888888");
			Collection<Vehicle> vehicleList = this.vehicleCollection.getRegisteredVehicles();
			Vehicle rentVehicle = null;
			for (Vehicle vehicle : vehicleList) {
				if (vehicle.getPlate().equalsIgnoreCase(plate)) {
					rentVehicle = vehicle;
					break;
				}
			}
			List<Customer> customers = userController.getCustomerList();
			Customer rentCustomer = null;
			for (Customer customer : customers) {
				if (customer.getEmail().equals(email)) {
					rentCustomer = customer;
					break;
				}
			}
			try{
				this.rents.add(new Rent(rentVehicle, rentCustomer, initialDate ,finalDate, rentSituation));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}


	/**
	 * 
	 * @param plate
	 * @return
	 */
	public boolean releaseVehicle(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rents.remove(rent);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public int getAllActiveRents() {
		int cont = 0;
		for (Rent rent : rents) {
			if (rent.getRentSituation().equals("active")) {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * 
	 * @param plate
	 * @return
	 */
	public String getVehicleSituation(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate))
				return "unavailable";
		}
		return "available";
	}

	/**
	 * 
	 * @param email
	 * @param plate
	 * @param inicialDate
	 * @param finalDate
	 * @return
	 */
	public String getRentSituation(String email, String plate, String inicialDate,
			String finalDate){
		for(Rent rent : rents) {
			if (rent.getCostumer().getEmail().equals(email) &&
					rent.getVehiclePlate().equals(plate) )
				return rent.getRentSituation();
		}
		return null;

	}


	/**
	 * 
	 * @param init
	 * @param end
	 * @return
	 */
	private boolean correctPeriod(String init, String end) {
		int day1 = Integer.valueOf(init.substring(0,2));
		int day2 = Integer.valueOf(end.substring(0,2));
		int month1 = Integer.valueOf(init.substring(3,5));
		int month2 = Integer.valueOf(end.substring(3,5));
		int year1 = Integer.valueOf(init.substring(6,8));
		int year2 = Integer.valueOf(end.substring(6,8));
		if (year1 <= year2) {
			if (year1 == year2) {
				if (month1 <= month2) {
					if (month1 == month2) {
						if (day1 <= day2) {
							return true;
						}
					} else
						return true;
				}
			} else
				return true;
		}

		return false;

	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	private boolean userExists(String email) {
		if (this.customerCollection.customerAlreadyExist(email)) return true;
		if (this.functionariesCollection.functionarieAlreadyExists(email)) return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String listAllPendingRents(Calendar date) {
		String output = "";
		for (Rent rent: rents) {
			if (rent.compareTo(date) < 1)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public String listAllNonPendingRents(Calendar date) {
		String output = "";
		for (Rent rent: rents) {
			if (rent.compareTo(date) >= 1)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * 
	 * @return
	 */
	public int getAllRents() {
		return rents.size();
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public int getRentsByCustomer(String email) {
		int cont = 0;
		if (rents != null)
			for(Rent rent: rents) {
				if (rent.getCostumer().getEmail().equals(email)) {
					cont++;
				}
			}
		return cont;
	}

	/**
	 * 
	 * @param plate
	 * @return
	 */
	public int getRentsByVehicle(String plate) {
		int cont = 0;
		if (rents != null)
			for(Rent rent: rents) {
				if (rent.getVehiclePlate().equals(plate)) {
					cont++;
				}
			}
		return cont;
	}

	/**
	 * 
	 * @param plate
	 * @return
	 */
	public boolean vehicleIsRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equals(plate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public int getAllPendentRentRequests(){
		return this.requestList.size();
	}

	/**
	 * 
	 * @param clientEmail
	 * @param plate
	 * @throws InvalidParameterException
	 */
	public void requestRent(String clientEmail, String plate) throws InvalidParameterException{
		if (!verification.emailIsAMandatoryField(clientEmail) ||
				!verification.plateIsAMandatoryField(plate))
			throw new InvalidParameterException("error: all fields are mandatory!");
		requestList.add(clientEmail, plate, calendar.getTime());
	}

	/**
	 * 
	 * @return
	 */
	public String listAllRequests() {
		return requestList.toString();
	}

	/**
	 * 
	 */
	public void seeCars(){
		List<Vehicle> list = new ArrayList<Vehicle>();
		Iterator<Vehicle> it = vehicleCollection.getRegisteredVehicles().iterator();
		while (it.hasNext()){
			Vehicle vehicle = it.next();
			if (!vehicleIsRent(vehicle.getPlate()))
				list.add(vehicle);
		}
		List<Integer> listOfYears = vehicleCollection.getListOfYears();
		for (int i = 0; i < list.size(); i++)
			vehicleCollection.printCarsByYear(list, listOfYears.get(i));
	}

	/**
	 * 
	 */
	public void writeXML() {
		writeRents();
		writeRequestRents();
	}

	/**
	 * 
	 */
	private void writeRents() {
		if (rents != null) {
			try {
				FileOutputStream rentsWriter = new FileOutputStream(
				"Rents.xml");
				XStream xmlEncoder = new XStream();
				String rentsEmXML = xmlEncoder
				.toXML(rents);
				byte[] rentsByteArray = rentsEmXML
				.getBytes();
				rentsWriter.write(rentsByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void readRents() throws Exception {
		FileInputStream file = new FileInputStream("Rents.xml");

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {
			
		
		XStream xmlDecoder = new XStream(new DomDriver());
		Collection<Rent> rentsArchive = (Collection<Rent>) xmlDecoder
		.fromXML(new BufferedInputStream(file));

		rents = rentsArchive;
		}
	}
	
	public void emptyXML() throws FileNotFoundException {
		FileOutputStream rentsWriter = new FileOutputStream(
		"Rents.xml");
		FileOutputStream requesListWriter = new FileOutputStream(
		"RequestRents.xml");
	}

	/**
	 * 
	 */
	private void writeRequestRents() {
		if (requestList != null) {
			try {

				FileOutputStream requesListWriter = new FileOutputStream(
				"RequestRents.xml");
				XStream xmlEncoder = new XStream();
				String requestListEmXML = xmlEncoder
				.toXML(requestList);
				byte[] requestListByteArray = requestListEmXML
				.getBytes();
				requesListWriter.write(requestListByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void readRequestRents() throws Exception {
		FileInputStream file = new FileInputStream("RequestRents.xml");

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {
			

		
		XStream xmlDecoder = new XStream(new DomDriver());
		RequestRentCollection requestListArchive = (RequestRentCollection) xmlDecoder
		.fromXML(new BufferedInputStream(file));

		requestList = requestListArchive;
		}
	}
}
