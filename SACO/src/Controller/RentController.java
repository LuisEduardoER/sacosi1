package Controller;

import java.util.ArrayList;
import java.util.Collection;
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

public class RentController {
	
	private Collection<Rent> rents;
	private UserController userController;
	private CustomerCollection customerCollection;
	private FunctionariesCollection functionariesCollection;
	private FieldSystemVerification verification;
	private VehiclesController vehicleCollection;
	private RequestRentCollection requestList;
	public static RentController instance;
	
	
	public static RentController getInstance() {
		return instance == null ? 
			   instance = new RentController() : 
			   instance;
	}
	
	public RentController() {
		this.rents = new ArrayList<Rent>();
		this.userController = UserController.getInstance();
		this.functionariesCollection = this.userController.getFunctionariesCollection();
		this.customerCollection = this.userController.getCustomerCollection();
		this.vehicleCollection = VehiclesController.getInstance(); 
		this.verification = new FieldSystemVerification();
		this.requestList = new RequestRentCollection();
	}
	
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
	
	public void registerRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException, EmailException, InvalidNameException, PhoneException, CustomerAlreadyExistException, InvalidFieldException {
		this.register(plate, email, initialDate, finalDate, "active");
	}
	
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
	
	
	public boolean releaseVehicle(String plate) {
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
	
	public String getVehicleSituation(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate))
				return "unavailable";
		}
		return "available";
	}
	
	
	public String getRentSituation(String email, String plate, String inicialDate,
			String finalDate){
		for(Rent rent : rents) {
			if (rent.getCostumer().getEmail().equals(email) &&
				rent.getVehiclePlate().equals(plate) )
					return rent.getRentSituation();
		}
		return null;
		
	}
	
	
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
	
	private boolean userExists(String email) {
		if (this.customerCollection.customerAlreadyExist(email)) return true;
		if (this.functionariesCollection.functionarieAlreadyExists(email)) return true;
		return false;
	}
	
	public int getAllRents() {
		return rents.size();
	}
	
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
	
	private boolean vehicleIsRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equals(plate)) {
				return true;
			}
		}
		return false;
	}
	
	public int getAllPendentRentRequests(){
		return this.requestList.size();
	}
	
	public void requestRent(String clientEmail, String plate) throws InvalidParameterException{
		if (!verification.emailIsAMandatoryField(clientEmail) ||
				!verification.plateIsAMandatoryField(plate))
			throw new InvalidParameterException("error: all fields are mandatory!");
		requestList.add(clientEmail, plate);
	}
}
