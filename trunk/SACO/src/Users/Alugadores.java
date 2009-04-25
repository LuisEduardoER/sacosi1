package Users;

import Exceptions.EmailException;
import Exceptions.InvalidNameException;
import Exceptions.PhoneException;

public interface Alugadores {
	
		public String getName();
		public void setName(String nome) throws InvalidNameException;
		
		public String getEmail();
		public void setEmail(String email) throws EmailException;
		
		public String getPhone();
		public void setPhone(String phone) throws PhoneException;
		
		
		
}
