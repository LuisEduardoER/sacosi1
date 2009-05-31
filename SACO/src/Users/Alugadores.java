package Users;

import Exceptions.InvalidFieldException;

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
	public void setName(String nome) throws InvalidFieldException;

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
	public void setEmail(String email) throws InvalidFieldException;

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
	public void setPhone(String phone) throws InvalidFieldException;

	/**
	 * toString
	 * 
	 * @return
	 */
	public String toString();

}
