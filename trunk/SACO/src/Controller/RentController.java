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
import System.RentCollection;
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

	private RentCollection rents;
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
	public static synchronized RentController getInstance() throws Exception {
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
		this.rents = RentCollection.getInstance();
		this.userController = UserController.getInstance();
		this.functionariesCollection = FunctionariesCollection.getInstance();
		this.customerCollection = CustomerCollection.getInstance();
		this.vehicleCollection = VehiclesController.getInstance();
		this.verification = new FieldSystemVerification();
		this.requestList = RequestRentCollection.getInstance();
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

		if (!this.validateRegisterLateRent(finalDate)) {
			throw new InvalidDateException(
					"error: end date is greater than today date!");
		}
		this.rents.registerLateRent(plate, email, initialDate, finalDate);
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
	private void register(String plate, String email, String initialDate,
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
			
			Customer rentCustomer = customerCollection.getCustomer(email);
			
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
		return rents.removeRent(plate);
	}

	/**
	 * Retorna a quantidade de algueis ativos
	 * 
	 * @return quantidade de algueis ativos
	 */
	public int getAllActiveRents() {
		return this.rents.getAllActiveRents();
	}

	/**
	 * Retorna a situacao do veiculo.
	 * 
	 * @param plate
	 * @return situacao do veiculo.
	 */
	public String getVehicleSituation(String plate) {
		return this.rents.getVehicleSituation(plate);
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
		return this.rents.getRentSituation(email, plate, inicialDate, finalDate);

	}

	/**
	 * Verifica se uma data final de entrega de veiculo eh maior que a data
	 * atual, para que seja registrado um aluguel atrasado.
	 * 
	 * @param end
	 *            a data final de entrega
	 * @return true se a data for valida (nao excedeu o dia de entrega), false
	 *         caso contrario
	 */
	private boolean validateRegisterLateRent(String end) {

		int day2 = Integer.valueOf(end.substring(0, 2));
		int month2 = Integer.valueOf(end.substring(3, 5));
		int year2 = Integer.valueOf(end.substring(6, 8));
		year2 += 2000;
		month2 = month2 - 1;
		Calendar hoje = Calendar.getInstance();
		Calendar entrega = Calendar.getInstance();
		entrega.set(year2, month2, day2);
		if (entrega.after(hoje)) {
			return false;
		}
		return true;
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
		return this.rents.listAllPendingRents(date);
	}

	/**
	 * Lista todos os alugueis nao pendentes
	 * 
	 * @param date
	 * @return toString de todos os alugueis nao pendentes
	 */
	public String listAllNonPendingRents(Calendar date) {
		return this.rents.listAllNonPendingRents(date);
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
		return this.rents.getRentsByCustomer(email);
	}

	/**
	 * Quantidade de alugueis por veiculo
	 * 
	 * @param plate
	 * @return Quantidade de alugueis por veiculo
	 */
	public int getRentsByVehicle(String plate) {
		return this.rents.getRentsByVehicle(plate);
	}

	/**
	 * Verifica se um veiculo esta alugado.
	 * 
	 * @param plate
	 * @return true se estiver alugado ou false caso contrario
	 */
	public boolean vehicleIsRent(String plate) {
		return this.rents.vehicleIsRent(plate);
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
			RentCollection rentsArchive = (RentCollection) xmlDecoder
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
		this.rents.emptyList();
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
