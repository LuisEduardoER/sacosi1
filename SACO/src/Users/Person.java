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
	 * Construtor de uma Pessoa
	 * @param name nome da pessoa
	 * @param email email da pessoa
	 * @param phone telefone da pessoa
	 */
	public Person(String name, String email, String phone) {
		this.setName(name);
		this.setEmail(email);
		this.setPhone(phone);

	}
	
	/**
	 * Metodo que altera o nome da pessoa
	 * @param name nome da pessoa
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o nome da pessoa
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo que altera o nome da pessoa
	 * @param email email da pessoa
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que retorna o email da pessoa
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que altera o telefone da pessoa
	 * @param phone telefone da pessoa
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Metodo que retorna o telefone da pessoa
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Metodo que retorna uma string correspondendo a todos os dados de uma pessoa.
	 */
	public String toString() {
		String output = "";
		output += "Nome: " + this.getName() + "\n";
		output += "Email: " + this.getEmail() + "\n";
		output += "Telefone: " + this.getPhone() + "\n";
		return output;
	}

}
