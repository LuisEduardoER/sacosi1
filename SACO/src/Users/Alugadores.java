package Users;

import Exceptions.EmailException;
import Exceptions.InvalidNameException;
import Exceptions.PhoneException;

/**
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 * 
 */
public interface Alugadores {

	/**
	 * get Name
	 * 
	 * @return nome
	 */
	public String getName();

	/**
	 * set Name
	 * 
	 * @param nome
	 * @throws InvalidNameException
	 */
	public void setName(String nome) throws InvalidNameException;

	/**
	 * get email
	 * 
	 * @return email
	 */
	public String getEmail();

	/**
	 * set email
	 * 
	 * @param email
	 * @throws EmailException
	 */
	public void setEmail(String email) throws EmailException;

	/**
	 * get phone
	 * 
	 * @return telefone
	 */
	public String getPhone();

	/**
	 * set phone
	 * 
	 * @param phone
	 * @throws PhoneException
	 */
	public void setPhone(String phone) throws PhoneException;

	/**
	 * toString
	 * 
	 * @return
	 */
	public String toString();

}
