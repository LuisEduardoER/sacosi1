package Commands;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.security.auth.login.LoginException;

import Users.Alugadores;

import Controller.RentController;
import Controller.UserController;
import Controller.VehiclesController;
import Exceptions.ClientNotRegisteredException;
import Exceptions.ColorException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidLoginException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.ModelException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.NoFieldException;
import Exceptions.NoSuchVehicleException;
import Exceptions.NoUserOnDatabaseException;
import Exceptions.NoVehicleOnDatabaseException;
import Exceptions.PhoneException;
import Exceptions.PlateAlreadyExistsException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Exceptions.YearException;

/**
 * Esta classe � uma fachada para as classes UserController, VehiclesController
 * e RentController
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class Facade {

	private UserController userController;
	private VehiclesController vehController;
	private RentController reController;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public Facade() throws Exception {
		this.userController = UserController.getInstance();
		this.vehController = VehiclesController.getInstance();
		try {
			this.reController = RentController.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo remove um ve�culo da lista de ve�culos
	 * 
	 * @param plate
	 * @throws NoSuchVehicleException
	 * @throws NoVehicleOnDatabaseException
	 */
	public void removeVehicle(String plate) throws NoSuchVehicleException,
			NoVehicleOnDatabaseException {
		vehController.removeVehicle(plate);
		this.vehController.writeVehicles();
	}

	/**
	 * Este metodo retorna a quantidade de usuarios cadastrados
	 * 
	 * @return quantidade de usuarios
	 */
	public int getAllUsers() {
		return userController.getAllUsers();
	}

	/**
	 * Este metodo cadastra um usuario no sistema.
	 * 
	 * @param login
	 *            login do funcionario
	 * @param name
	 *            o nome do funcionario
	 * @param email
	 *            o email do funcionario
	 * @param phone
	 *            o telefone do funcionario
	 * @throws InvalidLoginException
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws UserAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void addUser(String login, String name, String email, String phone)
			throws InvalidLoginException, EmailException, InvalidNameException,
			PhoneException, UserAlreadyExistException, InvalidFieldException {
		userController.addUser(login, name, email, phone);
		this.userController.writeXML();
	}

	/**
	 * Este metodo retorna a quantidade de clientes cadastrados.
	 * 
	 * @return quantidade de clientes.
	 */
	public int getAllCustomers() {
		return userController.getAllCustomers();
	}

	/**
	 * Este metodo remove um cliente do sistema.
	 * 
	 * @param email
	 *            o email do cliente
	 * @throws ClientNotRegisteredException
	 * @throws NoCustomerOnDatabaseException
	 * @throws InvalidParameterException
	 */
	public void removeCustomer(String email)
			throws ClientNotRegisteredException, NoCustomerOnDatabaseException,
			InvalidParameterException {
		userController.removeCustomer(email);
		this.userController.writeXML();
	}

	/**
	 * Este metodo remove um usuario do sistema
	 * 
	 * @param emailOrLogin
	 *            o email ou login do funcionario
	 * @throws LoginException
	 * @throws UserNotFoundException
	 * @throws EmailException
	 * @throws NoUserOnDatabaseException
	 * @throws InvalidParameterException
	 */
	public void removeUser(String emailOrLogin) throws LoginException,
			UserNotFoundException, EmailException, NoUserOnDatabaseException,
			InvalidParameterException {
		userController.removeUser(emailOrLogin);
		this.userController.writeXML();
	}

	/**
	 * Este metodo cadastra um novo cliente no sistema.
	 * 
	 * @param name
	 *            nome do cliente
	 * @param email
	 *            o email do cliente
	 * @param phone
	 *            o telefone do cliente
	 * @throws EmailException
	 * @throws InvalidNameException
	 * @throws PhoneException
	 * @throws CustomerAlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void addCustomer(String name, String email, String phone)
			throws EmailException, InvalidNameException, PhoneException,
			CustomerAlreadyExistException, InvalidFieldException {

		this.userController.addCustomer(name, email, phone);
		this.userController.writeXML();
	}

	/**
	 * Este metodo adiciona um novo ve�culo no sistema.
	 * 
	 * @param type
	 *            o tipo do veiculo
	 * @param model
	 *            o modelo do veiculo
	 * @param color
	 *            a cor do veiculo
	 * @param plate
	 *            a placa do veiculo
	 * @param year
	 *            o ano de fabricacao do veiculo
	 * @param price
	 *            o preco de aluguel do veiculo
	 * @throws InvalidFieldException
	 * @throws NoFieldException
	 * @throws TypeException
	 * @throws ModelException
	 * @throws ColorException
	 * @throws PlateException
	 * @throws PriceException
	 * @throws YearException
	 * @throws PlateAlreadyExistsException
	 */
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price)
			throws InvalidFieldException, NoFieldException, TypeException,
			ModelException, ColorException, PlateException, PriceException,
			YearException, PlateAlreadyExistsException {
		this.vehController.addVehicle(type, model, color, plate, year, price);
		this.vehController.writeVehicles();
	}

	/**
	 * Este metodo registra um novo aluguel no sistema.
	 * 
	 * @param plate
	 *            a placa do veiculo
	 * @param email
	 *            o email do cliente
	 * @param initialDate
	 *            a data do aluguel
	 * @param finalDate
	 *            a data de entrega
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
		this.reController.registerRent(plate, email, initialDate, finalDate);
		this.reController.writeXML();
	}

	/**
	 * Este metodo registra um aluguel atrasado no sistema.
	 * 
	 * @param plate
	 *            a placa do veiculo
	 * @param email
	 *            o email do cliente
	 * @param initialDate
	 *            a data do aluguel
	 * @param finalDate
	 *            a data de entrega
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
		this.reController
				.registerLateRent(plate, email, initialDate, finalDate);
		this.reController.writeXML();
	}

	/**
	 * Este metodo retorna a situacao de um aluguel que pode ser late ou active
	 * 
	 * @param email
	 *            o email do cliente
	 * @param plate
	 *            a placa do veiculo
	 * @param inicialDate
	 *            data do aluguel
	 * @param finalDate
	 *            data da entrega
	 * @return
	 */
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) {
		return this.reController.getRentSituation(email, plate, inicialDate,
				finalDate);
	}

	/**
	 * Este metodo retorna a quantidade de algueis cadastrados no sistema.
	 * 
	 * @return quantidade de alugueis
	 */
	public int getAllRents() {
		return this.reController.getAllRents();
	}

	/**
	 * Este metodo retorna a quantidade de alugueis de um cliente.
	 * 
	 * @param email
	 * @return quantidade de algueis de um cliente
	 */
	public int getRentsByCustomer(String email) {
		return this.reController.getRentsByCustomer(email);
	}

	/**
	 * Este metodo retorna a quantidade de algueis por veiculo.
	 * 
	 * @param plate
	 *            a placa do veiculo
	 * @return quantidade de algueis por veiculo
	 */
	public int getRentsByVehicle(String plate) {
		return this.reController.getRentsByVehicle(plate);
	}

	/**
	 * Este metodo retorna a quantidade de reservas pendentes de Aluguel
	 * 
	 * @return quantidade de reservas pendentes de Aluguel
	 */
	public int getAllPendentRentRequests() {
		return this.reController.getAllPendentRentRequests();
	}

	/**
	 * Este metodo retorna a quantidade de alugueis ativos
	 * 
	 * @return quantidade de alugueis ativos
	 */
	public int getAllActiveRents() {
		return this.reController.getAllActiveRents();
	}

	/**
	 * Este metodo faz a liberacao de um veiculo alugado
	 * 
	 * @param plate
	 *            a placa do veiculo
	 * @return uma confirmacao da liberacao
	 */
	public boolean releaseVehicle(String plate) {
		boolean result = this.reController.releaseVehicle(plate);
		this.reController.writeXML();
		return result;
	}

	/**
	 * Este metodo retorna a situcao do veiculo.
	 * 
	 * @param plate
	 *            a placa do veiculo
	 * @return situcao do veiculo.
	 */
	public String getVehicleSituation(String plate) {
		return this.reController.getVehicleSituation(plate);
	}

	/**
	 * Este metodo faz uma requisicao de aluguel de um cliente por um veiculo.
	 * 
	 * @param clientEmail
	 * @param plate
	 *            a placa do veiculo
	 * @throws InvalidParameterException
	 */
	public void requestRent(String clientEmail, String plate)
			throws InvalidParameterException {
		this.reController.requestRent(clientEmail, plate);
		this.reController.writeXML();
	}

	/**
	 * Retorna a quantidade de veiculos cadastrados no sistema.
	 * 
	 * @return quantidade de veiculos
	 */
	public int getAllVehicles() {
		return this.vehController.getAllVehicles();
	}

	/**
	 * Este metodo apaga o conteudo de todos os arquivos xmls.
	 * 
	 * @throws FileNotFoundException
	 */
	public void cleanDB() throws FileNotFoundException {
		this.reController.emptyXML();
		this.vehController.emptyXML();
		this.userController.emptyXML();
	}

	/**
	 * Este metodo permite a visualizacao no console de todos os carros.
	 */
	public void seeCars() {
		this.reController.seeCars();

	}

	/**
	 * Este metodo permite a visualizacao de todos os pedidos de aluguel.
	 */
	public void listAllRequests() {
		this.reController.listAllRequests();

	}

	/**
	 * Este metodo permite a visualizacao de todos os alugueis nao pendentes.
	 * 
	 * @param date
	 *            a data de atual
	 */
	public void listAllNonPendingRents(Calendar date) {
		this.reController.listAllNonPendingRents(date);

	}

	/**
	 * Este metodo permite a visualizacao de todos os alugueis pendentes.
	 * 
	 * @param date
	 *            a data atual
	 */
	public void listAllPendingRents(Calendar date) {
		this.reController.listAllPendingRents(date);

	}

	/**
	 * Este metodo permite a visualizacao da situacao de todos os veiculos.
	 * 
	 * @return a situacao de todos os veiculos
	 */
	public String getAllVehiclesSituation() {
		return this.reController.getAllVehiclesSituation();
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
		this.reController.addManyRents(customer, plates, initialDates,
				devolutionDates);
	}

}