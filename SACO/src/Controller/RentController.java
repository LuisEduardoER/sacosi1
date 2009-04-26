package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import p1.aplic.geral.Data;
import Exceptions.InvalidDateException;
import Exceptions.InvalidParameterException;
import System.FieldSystemVerification;
import System.Rent;
import Users.Customer;
import Users.CustomerCollection;
import Users.FunctionariesCollection;
import Users.UserController;
import Vehicles.Vehicle;

public class RentController {
	
	private Collection<Rent> rents;
	private UserController userController;
	private CustomerCollection customerCollection;
	private FunctionariesCollection functionariesCollection;
	private String status;
	private FieldSystemVerification verification;
	private VehiclesController vehicleCollection;
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
		this.status = "active";
		this.vehicleCollection = VehiclesController.getInstance(); 
		this.verification = new FieldSystemVerification();
	}
	
	
	
	public void registerRent(String plate, String email, String initialDate, 
			String finalDate) throws InvalidParameterException, InvalidDateException {
		this.register(plate, email, initialDate, finalDate, "active");
	}
	
	public void register(String plate, String email, String initialDate, 
			String finalDate, String rentSituation) throws InvalidParameterException, InvalidDateException {
		
		if (!this.verification.validPlate(plate) || !this.verification.emailIsAMandatoryField(email) ||
				!this.verification.dateIsMandatoryField(initialDate) ||
				!this.verification.dateIsMandatoryField(finalDate))
			throw new InvalidParameterException("error: all parameters are mandatory!");
		
		if (!this.verification.validateEmail(email) ||
				!this.verification.isValidPlate(plate) || this.vehicleIsRent(plate))
			throw new InvalidParameterException("error: invalid parameter(s)"); 
			
		if (!this.vehicleIsRent(plate)) {
			if (this.userExists(email)) {
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
		int day2 = Integer.valueOf(init.substring(0,2));
		int month1 = Integer.valueOf(init.substring(2,4));
		int month2 = Integer.valueOf(init.substring(2,4));
		int year1 = Integer.valueOf(init.substring(4,6));
		int year2 = Integer.valueOf(init.substring(4,6));
		if (year1 <= year2 && month1 <= month2 && day1 < day2) {
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
}
