package dao;

import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;

import Exceptions.AlreadyExistException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;

/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public interface UserDAO {
	
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
	throws AlreadyExistException, InvalidFieldException, EmptyFieldException;
	
	/**
	 * Retorna a quantidade de clientes cadastrados no sistema.
	 * 
	 * @return a quantidade de clientes do sistema.
	 */
	public int getAllCustomers();
	
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
	throws InvalidFieldException, NotExistException;
	
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
	throws InvalidFieldException, AlreadyExistException, EmptyFieldException;
	
	/**
	 * Retorna a quantidade de funcionarios cadastrados no sistema.
	 * 
	 * @return
	 */
	public int getAllUsers();
	
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
	InvalidFieldException, NotExistException;
	
	/**
	 * Apaga o conteudo dos arquivos .xml
	 * 
	 * @throws FileNotFoundException
	 */
	public void cleanXML() throws FileNotFoundException;
	
	/**
	 * verifica
	 * @param dataOne
	 * @param dataTwo
	 * @return
	 */
	public boolean find(String dataOne, String dataTwo);
}
