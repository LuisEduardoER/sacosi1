package Controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import Exceptions.ClientNotRegisteredException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidLoginException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.NoUserOnDatabaseException;
import Exceptions.PhoneException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import System.CustomerCollection;
import System.FieldSystemVerification;
import System.FunctionariesCollection;
import Users.Customer;


/**
 * 
 * @author Ramon Lopes Brasileiro
 *
 */
public class UserController {
	
	/** */
	private static UserController instance;
	/** */
	private CustomerCollection registeredCustomers;
	/** */
	private FunctionariesCollection registeredFunctionaries;
	/** */
	private FieldSystemVerification verification;
	
	/*
	 * Construtor privado. Serve para o padrao Singleton.
	 */
	private UserController() {
		registeredCustomers = new CustomerCollection();
		registeredFunctionaries = new FunctionariesCollection();
		verification = new FieldSystemVerification();
	}
	
	/**
	 * Retorna uma unica instancia da classe. Padrao singleton.
	 * @return UserController a instancia da classe.
	 */
	public static UserController getInstance() {
		return instance == null ? 
			   instance = new UserController() : 
			   instance;
	}
	
	/**
	 * Adiciona um cliente ao sistema, requisitando, obrigatoriamente, seu nome, email e login.
	 * @param name o nome do cliente;
	 * @param email o email do cliente;
	 * @param phone o telefone do cliente;
	 * @throws PhoneException caso o telefone do cliente esteja vazio;
	 * @throws InvalidNameException  caso o nome do cliente esteja vazio;
	 * @throws EmailException caso o email do cliente esteja vazio.
	 * @throws CustomerAlreadyExistException 
	 * @throws InvalidFieldException 
	 */
	public void addCustomer(String name, String email, String phone) throws EmailException, 
																			InvalidNameException, 
																			PhoneException, 
																			CustomerAlreadyExistException, 
																			InvalidFieldException {
		if (verification.allCustomerFieldsInvalids(name, email, phone))
			throw new InvalidFieldException("error: all fields are mandatory!");
		if (!verification.nameIsAMandatoryField(name)) 
			throw new InvalidFieldException("error: name is a mandatory field!");
		if (!verification.validateName(name)) 
			throw new InvalidNameException("error: invalid field!");
		if (!verification.emailIsAMandatoryField(email))
			throw new EmailException("error: e-mail is a mandatory field!");
		if (!verification.validateEmail(email))
			throw new EmailException("error: invalid field!");
		if (!verification.phoneNumberIsAMandatoryField(phone)) 
			throw new PhoneException("error: phone number is a mandatory field!");
		if (!verification.validatePhoneNumber(phone))
			throw new PhoneException("error: invalid field!");
		registeredCustomers.add(name, email, phone);
	}
	
	/**
	 * Retorna a quantidade de clientes cadastrados no sistema.
	 * @return a quantidade de clientes do sistema.
	 */
	public int getAllCustomers() {
		return registeredCustomers.size();
	}
	
	/**
	 * Remove um cliente do sistema, de acordo com o email fornecido, caso o cliente
	 * esteja cadastrado.
	 * @param email o email do cliente a ser removido do sistema.
	 * @throws ClientNotRegisteredException cliente nao registrado no sistema.
	 * @throws NoCustomerOnDatabaseException 
	 * @throws InvalidParameterException 
	 */
	public void removeCustomer(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException {
		registeredCustomers.remove(email);
	}
	
	/**
	 * Adiciona um funcionario ao sistema.
	 * 
	 * @param login o login do usuario;
	 * @param name o nome do usuario;
	 * @param email o email do usuario;
	 * @param phone o telefone do usuario;
	 * @throws PhoneException  telefone invalido;
	 * @throws InvalidNameException nome invalido;
	 * @throws EmailException email invalido;
	 * @throws LoginException login invalido.
	 * @throws InvalidFieldException 
	 * @throws UserAlreadyExistException 
	 */
	public void addUser(String login, String name, String email, String phone) throws InvalidLoginException, 
																					  EmailException, 
																					  InvalidNameException, 
																					  PhoneException, 
																					  InvalidFieldException, 
																					  UserAlreadyExistException {
		if (verification.allUserFieldsInvalids(login, name, email, phone))
			throw new InvalidFieldException("error: all fields are mandatory!");
		if (!verification.loginIsAMandatoryField(login)) 
			throw new InvalidLoginException("error: login is a mandatory field!");
		if (!verification.validateLogin(login)) 
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.nameIsAMandatoryField(name)) 
			throw new InvalidFieldException("error: name is a mandatory field!");
		if (!verification.validateName(name))
			throw new InvalidNameException("error: invalid field!");
		if (!verification.emailIsAMandatoryField(email))
			throw new EmailException("error: e-mail is a mandatory field!");
		if (!verification.validateEmail(email))
			throw new InvalidFieldException("error: invalid field!");
		if (!verification.phoneNumberIsAMandatoryField(phone)) 
			throw new PhoneException("error: phone number is a mandatory field!");
		if (!verification.validatePhoneNumber(phone))
			throw new InvalidFieldException("error: invalid field!");
		registeredFunctionaries.add(login, name, email, phone);
	}
	
	/**
	 * Retorna a quantidade de funcionarios cadastrados no sistema.
	 * @return
	 */
	public int getAllUsers() {
		return registeredFunctionaries.size();
	}
	
	/**
	 * Remove um funcionario do sistema.
	 * @param emailOrLogin o email ou login do funcionario.
	 * @throws UserNotFoundException funcionario nao encontrado.
	 * @throws EmailException 
	 * @throws LoginException 
	 * @throws InvalidParameterException 
	 * @throws NoUserOnDatabaseException 
	 */
	public void removeUser(String emailOrLogin) throws UserNotFoundException, LoginException, EmailException, NoUserOnDatabaseException, InvalidParameterException {
		registeredFunctionaries.remove(emailOrLogin);
	}
	
	public FunctionariesCollection getFunctionariesCollection() {
		return this.registeredFunctionaries;
	}
	
	public CustomerCollection getCustomerCollection() {
		return this.registeredCustomers;
	}
	
	public List<Customer> getCustomerList() {
		return this.registeredCustomers.getCustomerList();
	}

}
