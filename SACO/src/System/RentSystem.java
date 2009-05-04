package System;
import java.util.ArrayList;
import java.util.Collection;

import javax.security.auth.login.LoginException;

import Users.*;
import Vehicles.*;
import Exceptions.*;

/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class RentSystem {
	
	private Collection<User> listOfUsers;
	private Collection<Vehicle> listOfVehicles;
	
	public RentSystem(){
		listOfUsers = new ArrayList<User>();
		listOfVehicles = new ArrayList<Vehicle>();
	}
	
	public void addUser(User user) throws InvalidNameException, EmailException, LoginException, PhoneException {
		listOfUsers.add(user);
	}
	
	public void addVehicle(Vehicle vehicle){
		listOfVehicles.add(vehicle);
	}
	
	public int getAllUsers(){
		return listOfUsers.size();
	}
	
	
}
