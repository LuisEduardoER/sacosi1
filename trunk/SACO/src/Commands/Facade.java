package Commands;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;

import Controller.RentController;
import Controller.UserController;
import Controller.VehiclesController;
import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Users.Alugadores;

/**
 * Esta classe � uma fachada para as classes UserController,
 * VehiclesController e RentController
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
	 * @throws NotExistException
	 */
	public void removeVehicle(String plate) throws NotExistException {
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 */
	public void addUser(String login, String name, String email, String phone)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
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
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeCustomer(String email) throws NotExistException,
			InvalidFieldException {
		userController.removeCustomer(email);
		this.userController.writeXML();
	}

	/**
	 * Este metodo remove um usuario do sistema
	 * 
	 * @param emailOrLogin
	 *            o email ou login do funcionario
	 * @throws LoginException
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeUser(String emailOrLogin) throws LoginException,
			NotExistException, InvalidFieldException {
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 */
	public void addCustomer(String name, String email, String phone)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {

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
	 * @throws EmptyFieldException
	 * @throws AlreadyExistException
	 */
	public void addVehicle(String type, String model, String color,
			String plate, String year, String price)
			throws InvalidFieldException, EmptyFieldException,
			AlreadyExistException {
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws EmptyFieldException
	 */
	public void registerRent(String plate, String email, String initialDate,
			String finalDate) throws AlreadyExistException,
			InvalidFieldException, EmptyFieldException {
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
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException 
	 */
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException {
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
	 * @throws MessagingException 
	 */
	public boolean releaseVehicle(String plate) throws MessagingException {
		boolean result = this.reController.releaseVehicle(plate);
		this.reController.writeXML();
		if (result == true) {
			this.reController.notifyCostumerAboutRelease(plate);
		}
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
	 * @throws EmptyFieldException
	 */
	public void requestRent(String clientEmail, String plate)
			throws EmptyFieldException {
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
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException
	 */
	public void addManyRents(Alugadores customer, String[] plates,
			String[] initialDates, String[] devolutionDates)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
		this.reController.addManyRents(customer, plates, initialDates,
				devolutionDates);
	}

	/**
	 * Notifica o cliente sobre o cancelamento da requisicao
	 * @throws MessagingException
	 */
	public void notifyAboutRequestRelease() throws MessagingException {
		this.reController.notifyAboutRequestRelease();
	}

}