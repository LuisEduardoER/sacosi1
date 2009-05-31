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
import Users.Alugadores;
import Users.Customer;
import Vehicles.Vehicle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
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
	private static RentController instance;
	private Calendar calendar;
	private static final String REQUEST_RENTS_FILE = "RequestRents.xml";
	private static final String RENTS_FILE = "Rents.xml";

	/**
	 * 
	 * @return uma unica instancia da classe
	 * @throws Exception
	 */
	public static RentController getInstance() throws Exception {
		return instance == null ? instance = new RentController() : instance;
	}

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 * 
	 */
	private RentController() throws Exception {
		calendar = Calendar.getInstance();
		this.rents = new ArrayList<Rent>();
		this.userController = UserController.getInstance();
		this.functionariesCollection = this.userController
				.getFunctionariesCollection();
		this.customerCollection = this.userController.getCustomerCollection();
		this.vehicleCollection = VehiclesController.getInstance();
		this.verification = new FieldSystemVerification();
		this.requestList = new RequestRentCollection();
		this.readRents();
		this.readRequestRents();
	}

	/**
	 * Registra um aluguel atrasado
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
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws InvalidDateException,
			InvalidParameterException, EmailException, InvalidNameException,
			PhoneException, CustomerAlreadyExistException,
			InvalidFieldException {

		if (!this.verification.validateRegisterLateRent(finalDate)) {
			throw new InvalidDateException(
					"error: end date is greater than today date!");
		}
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rent.setRentSituation("late");
				break;
			}
		}
	}

	/**
	 * Registra um aluguel
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
			String finalDate) throws InvalidParameterException,
			InvalidDateException, EmailException, InvalidNameException,
			PhoneException, CustomerAlreadyExistException,
			InvalidFieldException {
		this.register(plate, email, initialDate, finalDate, "active");
	}

	/**
	 * Registra um aluguel
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
			String finalDate, String rentSituation)
			throws InvalidParameterException, InvalidDateException,
			EmailException, InvalidNameException, PhoneException,
			CustomerAlreadyExistException, InvalidFieldException {

		if (!this.verification.plateIsAMandatoryField(plate)
				|| !this.verification.emailIsAMandatoryField(email)
				|| !this.verification.dateIsMandatoryField(initialDate)
				|| !this.verification.dateIsMandatoryField(finalDate))
			throw new InvalidParameterException(
					"error: all parameters are mandatory!");

		if (!this.verification.validateEmail(email)
				|| !this.verification.isValidPlate(plate)
				|| this.vehicleIsRent(plate))
			throw new InvalidParameterException("error: invalid parameter(s)");

		if (!this.vehicleIsRent(plate)) {
			if (!this.userExists(email))
				this.userController.addCustomer("name", email, "8388888888");
			Collection<Vehicle> vehicleList = this.vehicleCollection
					.getRegisteredVehicles();
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
			try {
				this.rents.add(new Rent(rentVehicle, rentCustomer, initialDate,
						finalDate, rentSituation));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	/**
	 * LIbera um veiculo
	 * 
	 * @param plate
	 * @return uma confirmacao
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
	 * Retorna a quantidade de algueis ativos
	 * 
	 * @return quantidade de algueis ativos
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
	 * Retorna a situacao do veiculo.
	 * 
	 * @param plate
	 * @return situacao do veiculo.
	 */
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

	
	/**
	 * Verifica se o email existe no sistema.
	 * 
	 * @param email
	 * @return true se tem no sistema e false caso contrario
	 */
	private boolean userExists(String email) {
		if (this.customerCollection.customerAlreadyExist(email))
			return true;
		if (this.functionariesCollection.functionarieAlreadyExists(email))
			return true;
		return false;
	}

	/**
	 * Lista todos os alugueis pendentes
	 * 
	 * @return toString de todos os alugueis pendentes
	 */
	public String listAllPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) < 1)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * Lista todos os alugueis nao pendentes
	 * 
	 * @param date
	 * @return toString de todos os alugueis nao pendentes
	 */
	public String listAllNonPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) >= 1)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * Quantidade de alugueis
	 * 
	 * @return Quantidade de alugueis
	 */
	public int getAllRents() {
		return rents.size();
	}

	/**
	 * Quantidade de alugueis por cliente
	 * 
	 * @param email
	 * @return quantidade de alugueis por cliente
	 */
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

	/**
	 * Quantidade de alugueis por veiculo
	 * 
	 * @param plate
	 * @return Quantidade de alugueis por veiculo
	 */
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

	/**
	 * Verifica se um veiculo esta alugado.
	 * 
	 * @param plate
	 * @return true se estiver alugado ou false caso contrario
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
	 * Retorna todas as requisicoes de alugueis pendentes
	 * 
	 * @return quantidade de requisicoes de alugueis pendentes.
	 */
	public int getAllPendentRentRequests() {
		return this.requestList.size();
	}

	/**
	 * Faz um pedido de aluguel
	 * 
	 * @param clientEmail
	 * @param plate
	 * @throws InvalidParameterException
	 */
	public void requestRent(String clientEmail, String plate)
			throws InvalidParameterException {
		if (!verification.emailIsAMandatoryField(clientEmail)
				|| !verification.plateIsAMandatoryField(plate))
			throw new InvalidParameterException(
					"error: all fields are mandatory!");
		requestList.add(clientEmail, plate, calendar.getTime());
	}

	/**
	 * Lista todos os pedidos de aluguel
	 * 
	 * @return toSting de todos os pedidos de aluguel.
	 */
	public String listAllRequests() {
		return requestList.toString();
	}

	/**
	 * Visualiza todos os carros
	 */
	public void seeCars() {
		List<Vehicle> list = new ArrayList<Vehicle>();
		Iterator<Vehicle> it = vehicleCollection.getRegisteredVehicles()
				.iterator();
		while (it.hasNext()) {
			Vehicle vehicle = it.next();
			if (!vehicleIsRent(vehicle.getPlate()))
				list.add(vehicle);
		}
		List<Integer> listOfYears = vehicleCollection.getListOfYears();
		for (int i = 0; i < list.size(); i++)
			vehicleCollection.printCarsByYear(list, listOfYears.get(i));
	}

	/**
	 *Escreve os alugueis e os pedidos de aluguel em um arquivo .xml
	 */
	public void writeXML() {
		writeRents();
		writeRequestRents();
	}

	/**
	 * Escreve todos os alugueis em um arquivo .xml
	 */
	private void writeRents() {
		if (rents != null) {
			try {
				FileOutputStream rentsWriter = new FileOutputStream(RENTS_FILE);
				XStream xmlEncoder = new XStream();
				String rentsEmXML = xmlEncoder.toXML(rents);
				byte[] rentsByteArray = rentsEmXML.getBytes();
				rentsWriter.write(rentsByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Faz a leitura no arquivo .xml de todos os alugueis
	 * 
	 * @throws Exception
	 */
	public void readRents() throws Exception {
		FileInputStream file = new FileInputStream(RENTS_FILE);

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {

			XStream xmlDecoder = new XStream(new DomDriver());
			Collection<Rent> rentsArchive = (Collection<Rent>) xmlDecoder
					.fromXML(new BufferedInputStream(file));

			rents = rentsArchive;
		}
	}

	/**
	 * Apaga o conteudo dos arquivos .xml
	 * 
	 * @throws FileNotFoundException
	 */
	public void emptyXML() throws FileNotFoundException {
		FileOutputStream rentsWriter = new FileOutputStream(RENTS_FILE);
		FileOutputStream requesListWriter = new FileOutputStream(
				REQUEST_RENTS_FILE);
		this.rents = new ArrayList<Rent>();
		this.requestList.emptyList();
	}

	/**
	 * Escreve todos os pedidos de aluguel em um arquivo .xml
	 */
	private void writeRequestRents() {
		if (requestList != null) {
			try {

				FileOutputStream requesListWriter = new FileOutputStream(
						REQUEST_RENTS_FILE);
				XStream xmlEncoder = new XStream();
				String requestListEmXML = xmlEncoder.toXML(requestList);
				byte[] requestListByteArray = requestListEmXML.getBytes();
				requesListWriter.write(requestListByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Faz a leitura de todos os pedidos de um arquivo .xml
	 * 
	 * @throws Exception
	 */
	public void readRequestRents() throws Exception {
		FileInputStream file = new FileInputStream(REQUEST_RENTS_FILE);

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {

			XStream xmlDecoder = new XStream(new DomDriver());
			RequestRentCollection requestListArchive = (RequestRentCollection) xmlDecoder
					.fromXML(new BufferedInputStream(file));

			requestList = requestListArchive;
		}
	}

	/**
	 * Retorna a situacao vigente de todos os veiculos cadastrados.
	 * 
	 * @return a situacao dos veiculos
	 */
	public String getAllVehiclesSituation() {
		String output = "";
		Iterator<Vehicle> it = vehicleCollection.getRegisteredVehicles()
				.iterator();
		while (it.hasNext()) {
			Vehicle vehicle = it.next();
			output += vehicle.toString() + "\n"
					+ this.getVehicleSituation(vehicle.getPlate());
			output += "================================================\n";
		}
		return output;
	}

	/**
	 * Registra o aluguel de varios veiculos a um cliente.
	 * 
	 * @param customer
	 *            o alugador
	 * @param plates
	 *            as placas dos carros que o cliente deseja alugar
	 * @throws InvalidFieldException
	 * @throws CustomerAlreadyExistException
	 * @throws PhoneException
	 * @throws InvalidNameException
	 * @throws EmailException
	 * @throws InvalidDateException
	 * @throws InvalidParameterException
	 */
	public void addManyRents(Alugadores customer, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws InvalidParameterException, InvalidDateException,
			EmailException, InvalidNameException, PhoneException,
			CustomerAlreadyExistException, InvalidFieldException {
		for (int i = 0; i < plates.length; i++) {
			this.registerRent(plates[i], customer.getEmail(), initialDates[i],
					devolutionDates[i]);
		}
	}
}
