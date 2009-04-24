package Users;

import javax.security.auth.login.LoginException;

import Exceptions.ClientNotRegisteredException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.NameException;
import Exceptions.PhoneException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;

/**
 * 
 * @author Ramon Lopes Brasileiro
 *
 */
public class UserController {
	
	/** */
	private UserController instance;
	/** */
	private CustomerCollection registeredCustomers;
	/** */
	private FunctionariesCollection registeredFunctionaries;
	
	/*
	 * Construtor privado. Serve para o padrao Singleton.
	 */
	private UserController() {
		registeredCustomers = new CustomerCollection();
		registeredFunctionaries = new FunctionariesCollection();
	}
	
	/**
	 * Retorna uma unica instancia da classe. Padrao singleton.
	 * @return UserController a instancia da classe.
	 */
	public UserController getInstance() {
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
	 * @throws NameException  caso o nome do cliente esteja vazio;
	 * @throws EmailException caso o email do cliente esteja vazio.
	 * @throws CustomerAlreadyExistException 
	 * @throws InvalidFieldException 
	 */
	public void addCustomer(String name, String email, String phone) throws EmailException, 
																			NameException, 
																			PhoneException, 
																			CustomerAlreadyExistException, 
																			InvalidFieldException {
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
	 */
	public void removeCustomer(String email) throws ClientNotRegisteredException {
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
	 * @throws NameException nome invalido;
	 * @throws EmailException email invalido;
	 * @throws LoginException login invalido.
	 * @throws InvalidFieldException 
	 * @throws UserAlreadyExistException 
	 */
	public void addUser(String login, String name, String email, String phone) throws LoginException, 
																					  EmailException, 
																					  NameException, 
																					  PhoneException, 
																					  InvalidFieldException, 
																					  UserAlreadyExistException {
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
	 */
	public void removeUser(String emailOrLogin) throws UserNotFoundException {
		registeredFunctionaries.remove(emailOrLogin);
	}

}
