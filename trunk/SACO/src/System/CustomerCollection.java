package System;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Users.Customer;

/**
 * Classe que armazena todos os clientes cadastrados no sistema.
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class CustomerCollection {

	private static CustomerCollection instance;
	private List<Customer> customerList;

	/**
	 * Construtor principal da classe.
	 */
	private CustomerCollection() {
		this.customerList = new ArrayList<Customer>();
	}

	/**
	 * Metodo que retorna a instancia da classe.
	 * @return a instancia da classe.
	 */
	public static synchronized CustomerCollection getInstance() {
		if (instance == null)
			return instance = new CustomerCollection();
		return instance;
	}


	/**
	 * Retorna a quantidade de clientes adicionados ao sistema.
	 * 
	 * @return quantidade de clientes adicionados ao sistema.
	 */
	public int size() {
		return customerList.size();
	}
	
	/**
	 * get Iterador da lista de clientes
	 * @return
	 */
	public Iterator <Customer> iterator() {
		return this.customerList.iterator();
	}
	
	/**
	 * Adiciona um cliente ao sistema.
	 * 
	 * @throws AlreadyExistException
	 *             excecao lancada ao tentar adicionar um usuario que ja existe
	 * @throws InvalidFieldException
	 */
	public void add(String name, String email, String phone)
			throws AlreadyExistException {
		if (customerAlreadyExist(email))
			throw new AlreadyExistException(
					"error: customer already exists!");
		customerList.add(new Customer(name, email, phone));
	}

	/**
	 * Verifica se no sistema h� um cliente cadastrado com o email
	 * especificado.
	 * 
	 * @return true se o cliente existe ou false caso contrario
	 */
	public boolean customerAlreadyExist(String email) {
		for (Customer customer : customerList) {
			if (customer.getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

	/**
	 * Retorna o cliente registrado com o email especificado.
	 * 
	 * @return o cliente
	 */
	private Customer customerNotRegistered(String email) {
		for (Customer customer : customerList) {
			if (customer.getEmail().equalsIgnoreCase(email))
				return customer;
		}
		return null;
	}

	/**
	 * Remove um cliente do sistema.
	 * 
	 * @param email
	 *            o email do cliente a ser removido.
	 * @throws NotExistException
	 *             cliente nao registrado no sistema.
	 * @throws InvalidFieldException
	 */
	public void remove(String email) throws NotExistException, InvalidFieldException {
		if (customerList.size() == 0)
			throw new NotExistException(
					"error: there is no customers on database");
		if (email == null || email.equals(""))
			throw new InvalidFieldException("error: invalid parameter!");
		if (!customerList.remove(customerNotRegistered(email)))
			throw new NotExistException("error: no such customer!");
	}

	/**
	 * Zera Lista
	 */
	public void emptyList() {
		this.customerList = new ArrayList<Customer>();
	}
	
	/**
	 * Metodo para busca do cliente com determinado email
	 * @param email email do cliente
	 * @return cliente com o email ou null caso cliente nao exista
	 */
	public Customer getCustomer(String email) {
		for (Customer customer : customerList) {
			if (customer.getEmail().equals(email)) {
				return customer;
			}
		}
		return null;
	}

}
