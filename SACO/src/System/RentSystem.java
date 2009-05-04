package System;
import java.util.ArrayList;
import java.util.Collection;

import javax.security.auth.login.LoginException;

import Users.*;
import Vehicles.*;
import Exceptions.*;

/**
 * Esta classe é um sistema de alugueis
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
	
	/**
	 * Contrutor
	 */
	public RentSystem(){
		listOfUsers = new ArrayList<User>();
		listOfVehicles = new ArrayList<Vehicle>();
	}
	
	/**
	 * Adiciona um usuario
	 * @param user
	 * @throws InvalidNameException
	 * @throws EmailException
	 * @throws LoginException
	 * @throws PhoneException
	 */
	public void addUser(User user) throws InvalidNameException, EmailException, LoginException, PhoneException {
		listOfUsers.add(user);
	}
	
	/**
	 * Adiciona um veiculo
	 * @param vehicle
	 */
	public void addVehicle(Vehicle vehicle){
		listOfVehicles.add(vehicle);
	}
	
	/**
	 * Retorna a quantidade de usuarios no sistema
	 * @return quantidade de usuarios no sistema
	 */
	public int getAllUsers(){
		return listOfUsers.size();
	}
	
	
}
