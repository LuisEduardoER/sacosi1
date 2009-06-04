package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Mail.MailManager;
import System.CustomerCollection;
import System.FieldSystemVerification;
import System.FunctionariesCollection;
import System.Rent;
import System.RentCollection;
import System.RequestObject;
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
	private static final long QUARENTA_E_OITO_HORAS = 172800000;

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
		this.rents = RentCollection.getInstance();
		this.userController = UserController.getInstance();
		this.functionariesCollection = this.userController
				.getFunctionariesCollection();
		this.customerCollection = this.userController.getCustomerCollection();
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException
	 */
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException {

		if (!this.verification.validateRegisterLateRent(finalDate)) {
			throw new InvalidFieldException(
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 */
	public void registerRent(String plate, String email, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException {
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 */
	public void register(String plate, String email, String initialDate,
			String finalDate, String rentSituation)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {

		if (!this.verification.plateIsAMandatoryField(plate)
				|| !this.verification.emailIsAMandatoryField(email)
				|| !this.verification.dateIsMandatoryField(initialDate)
				|| !this.verification.dateIsMandatoryField(finalDate))
			throw new InvalidFieldException(
					"error: all parameters are mandatory!");

		if (!this.verification.validateEmail(email)
				|| !this.verification.isValidPlate(plate)
				|| this.vehicleIsRent(plate))
			throw new InvalidFieldException("error: invalid parameter(s)");

		if (!this.vehicleIsRent(plate)) {
			if (!this.userExists(email))
				this.userController.addCustomer("name", email, "8388888888");
			Iterator<Vehicle> vehicleList = this.vehicleCollection.iterator();
			Vehicle rentVehicle = null;
			while (vehicleList.hasNext()) {
				Vehicle vehicle = vehicleList.next();
				if (vehicle.getPlate().equalsIgnoreCase(plate)) {
					rentVehicle = vehicle;
					break;
				}
			}

			Iterator<Customer> customers = userController.iterator();
			Customer rentCustomer = null;
			while (customers.hasNext()) {
				Customer customer = customers.next();
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
		return this.rents.releaseVehicle(plate);
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
	 * @throws InvalidFieldException
	 */
	public void requestRent(String clientEmail, String plate)
			throws EmptyFieldException {
		if (!verification.emailIsAMandatoryField(clientEmail)
				|| !verification.plateIsAMandatoryField(plate))
			throw new EmptyFieldException("error: all fields are mandatory!");
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
		Iterator<Vehicle> it = vehicleCollection.iterator();
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
		Iterator<Vehicle> it = vehicleCollection.iterator();
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
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException
	 */
	public void addManyRents(Alugadores customer, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
		for (int i = 0; i < plates.length; i++) {
			this.registerRent(plates[i], customer.getEmail(), initialDates[i],
					devolutionDates[i]);
		}
	}

	/**
	 * Metodo que, apos constatacao de que o veiculo foi liberado para aluguel,
	 * envia email para o (os) cliente(s) que estao interessados em aluga-lo.
	 * 
	 * @param plate
	 * @throws MessagingException
	 */
	public void notifyCostumerAboutRelease(String plate)
			throws MessagingException {
		Iterator<RequestObject> it = this.requestList.iterator();
		ArrayList<String> adressForEmail = new ArrayList<String>();
		while (it.hasNext()) {
			RequestObject requestObject = (RequestObject) it.next();
			if (requestObject.getPlate().equals(plate)) {
				adressForEmail.add(requestObject.getEmail());
			}
		}
		if (adressForEmail.size() > 0) {
			String [] sendTo = new String[adressForEmail.size()];
			for (int i = 0; i < adressForEmail.size(); i++){
				sendTo[i] = adressForEmail.get(i);
			}
			String message = "The vehicle you requested is available for rent.";
			MailManager.getInstanceOf().sendEmail(sendTo, message);
		}
	}

	/**
	 * Metodo que apos constatacao de que o veiculo esta com o aluguel atrasado,
	 * envia para o email do cliente uma mensagem de atraso.
	 * 
	 * @param email
	 * @throws MessagingException
	 */
	private void notifyCostumerAboutLateRent(String email)
			throws MessagingException {
		String [] sendTo = {email};
		String message = "The rental of your vehicle is late. The fine will be charged uppon their return.";
		MailManager.getInstanceOf().sendEmail(sendTo, message);
	}
	
	/**
	 * Metodo que apos constatacao de que a requisicao do aluguel esta 48 horas atrasada,
	 * notificando via email aos clientes que a requisicao foi cancelada.
	 * @throws MessagingException
	 */
	public void notifyAboutRequestRelease() throws MessagingException{
		Date data = new Date();
		Iterator<RequestObject> it = requestList.iterator();
		ArrayList<String> sendTo = new ArrayList<String>();
		while (it.hasNext()){
			RequestObject requestObject = (RequestObject)it.next();
			if (requestObject.getDate().getTime() - data.getTime() == QUARENTA_E_OITO_HORAS){
				sendTo.add(requestObject.getEmail());
			}
		}
		if (sendTo.size() > 0){
			String [] sendTo2 = new String[sendTo.size()];
			for (int i = 0; i < sendTo.size(); i++){
				sendTo2[i] = sendTo.get(i);
			}
			String message = "Your request was release, because 48h passed.";
			MailManager.getInstanceOf().sendEmail(sendTo2, message);
		}
	}
	
	
}
