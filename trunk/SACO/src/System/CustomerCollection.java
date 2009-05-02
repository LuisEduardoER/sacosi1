package System;

import java.util.ArrayList;
import java.util.List;

import Users.Customer;

import Exceptions.ClientNotRegisteredException;
import Exceptions.CustomerAlreadyExistException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidParameterException;
import Exceptions.NoCustomerOnDatabaseException;
import Exceptions.PhoneException;

/**
 * Classe que armazena todos os clientes cadastrados do sistema.
 * 
 * @author Ramon Lopes Brasileiro
 *
 */
public class CustomerCollection {
	
	private List<Customer> customerList;
	
	/**
	 * Construtor principal da classe.
	 */
	public CustomerCollection() {
		this.customerList = new ArrayList<Customer>();
	}
	
	public CustomerCollection(ArrayList<Customer> customers) {
		this.customerList = customers;
	}
	
	/**
	 * 
	 */
	public List<Customer> getCustomerList() {
		return this.customerList;
	}
	
	/**
	 * Retorna a quantidade de clientes adicionados ao sistema.
	 */
	public int size() {
		return customerList.size();
	}

	/**
	 * Adiciona um cliente ao sistema.
	 * 
	 * @throws PhoneException excecao lancada ao tentar adicionar um usuario sem o numero do seu telefone 
	 * @throws InvalidNameException excecao lancada ao tentar adicionar um usuario sem o seu nome
	 * @throws EmailException excecao lancada ao tentar adicionar um usuario sem o email
	 * @throws InvalidFieldException 
	 */
	public void add(String name, String email, String phone) throws CustomerAlreadyExistException {
		if (customerAlreadyExist(email)) 
			throw new CustomerAlreadyExistException("error: customer already exists!");
		customerList.add(new Customer(name, email, phone));
	}
	
	/*
	 * Verifica se no sistema h� um cliente cadastrado com o email especificado.
	 */
	public boolean customerAlreadyExist(String email) {
		for (Customer customer : customerList) {
			if (customer.getEmail().equalsIgnoreCase(email)) return true;
		}
		return false;
	}
	
	/*
	 * Retorna o cliente registrado com o email especificado.
	 */
	private Customer customerNotRegistered(String email) {
		for (Customer customer : customerList) {
			if (customer.getEmail().equalsIgnoreCase(email)) return customer;
		}
		return null;
	}

	/**
	 * Remove um cliente do sistema.
	 * @param email o email do cliente a ser removido.
	 * @throws ClientNotRegisteredException  cliente nao registrado no sistema.
	 * @throws NoCustomerOnDatabaseException 
	 * @throws InvalidParameterException 
	 */
	public void remove(String email) throws ClientNotRegisteredException, NoCustomerOnDatabaseException, InvalidParameterException {
		if (customerList.size() == 0)
			throw new NoCustomerOnDatabaseException("error: there is no customers on database");
		if (email == null || email.equals(""))
			throw new InvalidParameterException("error: invalid parameter!");
		if (!customerList.remove(customerNotRegistered(email))) 
			throw new ClientNotRegisteredException("error: no such customer!");
	}

}
