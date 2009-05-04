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
	
		public String getName();
		public void setName(String nome) throws InvalidNameException;
		public String getEmail();
		public void setEmail(String email) throws EmailException;
		public String getPhone();
		public void setPhone(String phone) throws PhoneException;
		public String toString();
		
}
