package LogicalSystem;

import java.io.IOException;

import Commands.Facade;
import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Exceptions.NotExistException;
import Exceptions.EmptyFieldException;
import Interface.InterfaceText;

/**
 * Logica do cliente
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 */
public class LogicOfTheCustomer {
	Facade facade;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public LogicOfTheCustomer() throws Exception {
		facade = new Facade();
	}

	// FIXME colocar todo o procedimento igual ao que foi feito em
	// LogicOfTheAdmin
	// Em cada metodo utiliza a interface para ler os dados, e aqui nesse metodo
	// fica um case para as opçoes do menu
	public void inicia() throws IOException {
		
	}

	/**
	 * Adiciona cliente
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 */
	public void addCustomer(String name, String email, String phone)
			throws AlreadyExistException, InvalidFieldException,
			EmptyFieldException {
		facade.addCustomer(name, email, phone);
	}

	/**
	 * Remove cliente
	 * 
	 * @param email
	 * @throws NotExistException
	 * @throws InvalidFieldException
	 */
	public void removeCustomer(String email) throws NotExistException,
			InvalidFieldException {
		facade.removeCustomer(email);
	}

	/**
	 * Visualiza carros
	 */
	public void seeCars() {
		facade.seeCars();
	}
}
