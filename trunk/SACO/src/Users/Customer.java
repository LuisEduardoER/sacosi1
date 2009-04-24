package Users;

import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.NameException;
import Exceptions.PhoneException;

/**
 * 
 * @author Luiz Teixeira Neto
 *
 */
public class Customer extends Person {

	/**
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @throws EmailException
	 * @throws NameException
	 * @throws PhoneException
	 * @throws InvalidFieldException 
	 */
	public Customer(String name, String email, String phone)
			throws EmailException, NameException, PhoneException, InvalidFieldException {

		super(name, email, phone);

	}
}

	