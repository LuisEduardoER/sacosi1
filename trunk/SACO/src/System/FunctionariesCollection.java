package System;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.LoginException;

import Exceptions.EmailException;
import Exceptions.InvalidParameterException;
import Exceptions.NoUserOnDatabaseException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Users.User;

/**
 * Classe que armazena todos os funcionarios cadastrados no sistema.
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class FunctionariesCollection {

	private static FunctionariesCollection instance;
	private List<User> usersList;

	/**
	 * Construtor da classe.
	 */
	private FunctionariesCollection() {
		usersList = new ArrayList<User>();
	}

	/**
	 * Metodo que retorna a instancia da classe
	 * @return a instancia da classe
	 */
	public static synchronized FunctionariesCollection getInstance() {
		if (instance == null)
			return instance = new FunctionariesCollection();
		return instance;
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
	 * @throws UserAlreadyExistException
	 */
	public void add(String login, String name, String email, String phone)
			throws UserAlreadyExistException {
		if (findUserByLogin(login) != null || findUserByEmail(email) != null)
			throw new UserAlreadyExistException("error: user already exists!");
		usersList.add(new User(login, name, email, phone));

	}

	/**
	 * Verifica se o funcionario j� existe no sistema
	 * 
	 * @param email
	 * @return true se existir ou false caso contrario
	 */
	public boolean functionarieAlreadyExists(String email) {
		for (User user : usersList) {
			if (user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Encontra um usuario por um email
	 * 
	 * @param email
	 * @return usuario
	 * @throws EmailException
	 */
	private User findUserByEmail(String email) {
		for (User user : usersList) {
			if (user.getEmail().equalsIgnoreCase(email)
					|| user.getLogin().equalsIgnoreCase(email))
				return user;
		}
		return null;
	}

	/**
	 * Retorna a quantidade de usuarios do sistema.
	 * 
	 * @return
	 */
	public int size() {
		return usersList.size();
	}

	/**
	 * Remove um funcionario do sistema.
	 * 
	 * @param email
	 *            o email do funcionario.
	 * @throws UserNotFoundException
	 *             funcionario nao encontrado.
	 * @throws LoginException
	 * @throws EmailException
	 * @throws NoUserOnDatabaseException
	 * @throws InvalidParameterException
	 */
	public void remove(String loginOrEmail) throws UserNotFoundException,
			LoginException, EmailException, NoUserOnDatabaseException,
			InvalidParameterException {
		if (usersList.size() == 0)
			throw new NoUserOnDatabaseException(
					"error: there is no users on database");
		if (loginOrEmail == null || loginOrEmail.equals(""))
			throw new InvalidParameterException("error: invalid parameter!");
		if (!usersList.remove(findUserByLogin(loginOrEmail))
				&& !usersList.remove(findUserByEmail(loginOrEmail)))
			throw new UserNotFoundException("error: no such user!");
	}

	/**
	 * Encontra um funcionario no sistema utilizando como base de pesquisa seu
	 * email ou login. Retorna null caso nao o encontre.
	 */
	private User findUserByLogin(String login) {
		for (User user : usersList) {
			if (user.getEmail().equalsIgnoreCase(login)
					|| user.getLogin().equalsIgnoreCase(login))
				return user;
		}
		return null;
	}
	
	/**
	 * get iterator da lista de usuarios
	 * @return
	 */
	public Iterator <User>iterator() {
		return this.usersList.iterator();
	}
	
	/**
	 * Zera Lista
	 */
	public void emptyList() {
		this.usersList = new ArrayList<User>();
	}
}
