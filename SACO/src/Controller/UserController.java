package Controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.security.auth.login.LoginException;

import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Exceptions.EmptyFieldException;
import System.CustomerCollection;
import System.FieldSystemVerification;
import System.FunctionariesCollection;
import Users.Customer;

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
public class UserController {

	private static UserController instance;
	private CustomerCollection registeredCustomers;
	private FunctionariesCollection registeredFunctionaries;
	private FieldSystemVerification verification;
	private static final String FUNCTIONARIES_FILE = "Functionaries.xml";
	private static final String CUSTOMERS_FILE = "Customers.xml";

	/*
	 * Construtor privado. Serve para o padrao Singleton.
	 */
	private UserController() throws Exception {
		registeredCustomers = CustomerCollection.getInstance();
		registeredFunctionaries = FunctionariesCollection.getInstance();
		verification = new FieldSystemVerification();
		this.readCostumers();
		this.readFunctionaries();
	}

	/**
	 * Retorna uma unica instancia da classe. Padrao singleton.
	 * 
	 * @return UserController a instancia da classe.
	 * @throws Exception
	 */
	public static synchronized UserController getInstance() throws Exception {
		return instance == null ? instance = new UserController() : instance;
	}

	/**
	 * Adiciona um cliente ao sistema, requisitando, obrigatoriamente, seu nome,
	 * email e login.
	 * 
	 * @param name
	 *            o nome do cliente;
	 * @param email
	 *            o email do cliente;
	 * @param phone
	 *            o telefone do cliente;
	 * @throws InvalidFieldException
	 *             caso o campo seja invalido ou vazio;
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException
	 */
	public void addCustomer(String name, String email, String phone)
			throws AlreadyExistException, InvalidFieldException, EmptyFieldException {
		if (verification.allCustomerFieldsInvalids(name, email, phone))
			throw new EmptyFieldException("error: all fields are mandatory!");
		if (!verification.nameIsAMandatoryField(name))
			throw new EmptyFieldException("error: name is a mandatory field!");
		if (!verification.emailIsAMandatoryField(email))
			throw new EmptyFieldException("error: e-mail is a mandatory field!");
		if (!verification.phoneNumberIsAMandatoryField(phone))
			throw new EmptyFieldException(
					"error: phone number is a mandatory field!");
		if (!verification.validateName(name)
				|| !verification.validateEmail(email)
				|| !verification.validatePhoneNumber(phone))
			throw new InvalidFieldException("error: invalid field!");
		registeredCustomers.add(name, email, phone);
	}

	/**
	 * Retorna a quantidade de clientes cadastrados no sistema.
	 * 
	 * @return a quantidade de clientes do sistema.
	 */
	public int getAllCustomers() {
		return registeredCustomers.size();
	}

	/**
	 * Remove um cliente do sistema, de acordo com o email fornecido, caso o
	 * cliente esteja cadastrado.
	 * 
	 * @param email
	 *            o email do cliente a ser removido do sistema.
	 * @throws NotExistException
	 *             cliente nao registrado no sistema.
	 * @throws InvalidFieldException
	 */
	public void removeCustomer(String email)
			throws InvalidFieldException, NotExistException {
		registeredCustomers.remove(email);
	}

	/**
	 * Adiciona um funcionario ao sistema.
	 * 
	 * @param login
	 *            o login do usuario;
	 * @param name
	 *            o nome do usuario;
	 * @param email
	 *            o email do usuario;
	 * @param phone
	 *            o telefone do usuario;
	 * @throws InvalidFieldException
	 *             campo invalido ou vazio;
	 * @throws AlreadyExistException
	 * @throws EmptyFieldException
	 */
	public void addUser(String login, String name, String email, String phone)
			throws InvalidFieldException, AlreadyExistException, EmptyFieldException {
		if (verification.allUserFieldsInvalids(login, name, email, phone))
			throw new EmptyFieldException("error: all fields are mandatory!");
		if (!verification.loginIsAMandatoryField(login))
			throw new EmptyFieldException(
					"error: login is a mandatory field!");
		if (!verification.nameIsAMandatoryField(name))
			throw new EmptyFieldException("error: name is a mandatory field!");
		if (!verification.emailIsAMandatoryField(email))
			throw new EmptyFieldException("error: e-mail is a mandatory field!");
		if (!verification.phoneNumberIsAMandatoryField(phone))
			throw new EmptyFieldException(
					"error: phone number is a mandatory field!");
		if (!verification.validateLogin(login)
				|| !verification.validateName(name)
				|| !verification.validateEmail(email)
				|| !verification.validatePhoneNumber(phone))
			throw new InvalidFieldException("error: invalid field!");
		registeredFunctionaries.add(login, name, email, phone);
	}

	/**
	 * Retorna a quantidade de funcionarios cadastrados no sistema.
	 * 
	 * @return
	 */
	public int getAllUsers() {
		return registeredFunctionaries.size();
	}

	/**
	 * Remove um funcionario do sistema. Um funcionario eh removido pelo email
	 * ou pelo seu login.
	 * 
	 * @param emailOrLogin
	 *            o email ou login do funcionario.
	 * @throws NotExistException
	 *             funcionario nao encontrado.
	 * @throws LoginException
	 * @throws InvalidFieldException
	 * @throws NotExistException 
	 */
	public void removeUser(String emailOrLogin) throws LoginException, 
			InvalidFieldException, NotExistException {
		registeredFunctionaries.remove(emailOrLogin);
	}

	/**
	 * Retorna uma colecao de todos os funcionarios.
	 * 
	 * @return colecao de todos os funcionarios.
	 */
	public FunctionariesCollection getFunctionariesCollection() {
		return this.registeredFunctionaries;
	}

	/**
	 * Retorna uma lista de todos os clientes cadastrados
	 * 
	 * @return lista de todos os clientes cadastrados
	 * 
	 */
	public CustomerCollection getCustomerCollection() {
		return this.registeredCustomers;
	}

	/**
	 * Retorna uma colecao de todos os clientes.
	 * 
	 * @return colecao de todos os clientes.
	 * 
	 */
	public Iterator<Customer> iterator() {
		return this.registeredCustomers.iterator();
	}

	/**
	 *Escreve os alugueis e os pedidos de aluguel em um arquivo .xml
	 */
	public void writeXML() {
		writeCustomers();
		writeFunctionaries();
	}

	/**
	 * Escreve todos os clientes em um arquivo .xml
	 */
	private void writeCustomers() {
		if (registeredCustomers != null) {
			try {

				FileOutputStream customersWriter = new FileOutputStream(
						CUSTOMERS_FILE);
				XStream xmlEncoder = new XStream();
				String registeredCustomersEmXML = xmlEncoder
						.toXML(registeredCustomers);
				byte[] registeredCustomersByteArray = registeredCustomersEmXML
						.getBytes();
				customersWriter.write(registeredCustomersByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Faz a leitura no arquivo .xml de todos os clientes
	 * 
	 * @throws Exception
	 */
	public void readCostumers() throws Exception {
		FileInputStream file = new FileInputStream(CUSTOMERS_FILE);

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (!(file.available() == 0)) {
			XStream xmlDecoder = new XStream(new DomDriver());
			CustomerCollection costumersArchive = (CustomerCollection) xmlDecoder
					.fromXML(new BufferedInputStream(file));

			registeredCustomers = costumersArchive;
		}
	}

	/**
	 * Apaga o conteudo dos arquivos .xml
	 * 
	 * @throws FileNotFoundException
	 */
	public void emptyXML() throws FileNotFoundException {
		FileOutputStream customersWriter = new FileOutputStream(CUSTOMERS_FILE);
		FileOutputStream functionariesWriter = new FileOutputStream(
				FUNCTIONARIES_FILE);
		this.registeredCustomers.emptyList();
		this.registeredFunctionaries.emptyList();
	}

	/**
	 * Escreve todos os funcionarios em um arquivo .xml
	 */
	private void writeFunctionaries() {
		if (registeredFunctionaries != null) {
			try {

				FileOutputStream functionariesWriter = new FileOutputStream(
						FUNCTIONARIES_FILE);
				XStream xmlEncoder = new XStream();
				String registeredFunctionariesEmXML = xmlEncoder
						.toXML(registeredFunctionaries);
				byte[] registeredFunctionariesByteArray = registeredFunctionariesEmXML
						.getBytes();
				functionariesWriter.write(registeredFunctionariesByteArray);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Faz a leitura de todos os funcionarios de um arquivo .xml
	 * 
	 * @throws Exception
	 */
	public void readFunctionaries() throws Exception {
		FileInputStream file = new FileInputStream(FUNCTIONARIES_FILE);

		if (file == null) {
			throw new Exception("File does not exist.");
		} else if (file.available() != 0) {

			XStream xmlDecoder = new XStream(new DomDriver());
			FunctionariesCollection functionariesArchive = (FunctionariesCollection) xmlDecoder
					.fromXML(new BufferedInputStream(file));

			registeredFunctionaries = functionariesArchive;
		}
	}

}
