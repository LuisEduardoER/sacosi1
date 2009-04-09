
public interface Alugadores {
	
		public String getName();
		public void setName(String nome) throws NameException;
		
		public String getEmail();
		public void setEmail(String email) throws EmailException;
		
		public String getPhone();
		public void setPhone(String phone) throws PhoneException;
		
		
		
}
