package Users;

/**
 * 
 * @author Filipe
 * @author Luiz
 * @author Melina
 * @author Raissa
 * @author Ramon
 *
 */
public abstract class Person implements Alugadores {

	private String name;
	private String email;
	private String phone;

	/**
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 */
	public Person(String name, String email, String phone) {
		this.setName(name);
		this.setEmail(email);
		this.setPhone(phone);

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	
	public String toString() {
		String output = "";
		output += "Nome: " + this.getName() + "\n";
		output += "Email: " + this.getEmail() + "\n";
		output += "Telefone: " + this.getPhone() + "\n";
		return output;
	}

}
