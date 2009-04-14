package Users;

import Exceptions.EmailException;
import Exceptions.NameException;
import Exceptions.PhoneException;

public class Costumer extends Person {

	public Costumer(String name, String email, String phone)
			throws EmailException, NameException, PhoneException {

		super(name, email, phone);

	}
}

	