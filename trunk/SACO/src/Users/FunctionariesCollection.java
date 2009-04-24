package Users;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.NameException;
import Exceptions.PhoneException;
import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;

public class FunctionariesCollection {
	
	private List<User> usersList;
	
	/**
	 * Construtor da classe.
	 */
	public FunctionariesCollection() {
		usersList = new ArrayList<User>();
	}

	/**
	 * Adiciona um funcionario ao sistema.
	 * @param login o login do usuario;
	 * @param name o nome do usuario;
	 * @param email o email do usuario;
	 * @param phone o telefone do usuario;
	 * @throws PhoneException telefone invalido;
	 * @throws NameException nome invalido;
	 * @throws EmailException email invalido;
	 * @throws LoginException login invalido;
	 * @throws InvalidFieldException 
	 * @throws UserAlreadyExistException 
	 */
	public void add(String login, String name, String email, String phone) throws LoginException, 
																				  EmailException, 
																				  NameException, 
																				  PhoneException, 
																				  InvalidFieldException, 
																				  UserAlreadyExistException {
		if (findUser(login) != null || findUser(email) != null) 
			throw new UserAlreadyExistException("error: customer already exists!");
		usersList.add(new User(login, name, email, phone));
		
	}

	/**
	 * Retorna a quantidade de usuarios do sistema.
	 * @return
	 */
	public int size() {
		return usersList.size();
	}

	/**
	 * Remove um funcionario do sistema.
	 * @param emailOrLogin o email ou o login do funcionario.
	 * @throws UserNotFoundException funcionario nao encontrado. 
	 */
	public void remove(String emailOrLogin) throws UserNotFoundException {
		if (!usersList.remove(findUser(emailOrLogin))) 
			throw new UserNotFoundException("error: no such user!"); 
	}

	/*
	 * Encontra um funcionario no sistema utilizando como base de pesquisa
	 * seu email ou login. Retorna null caso nao o encontre. 
	 */
	private User findUser(String emailOrLogin) {
		for (User user : usersList) {
			if (user.getEmail().equalsIgnoreCase(emailOrLogin) 
					|| user.getLogin().equalsIgnoreCase(emailOrLogin))
				return user;
		}
		return null;
	}
	
}
