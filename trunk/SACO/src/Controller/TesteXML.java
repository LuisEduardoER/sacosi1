package Controller;

import Commands.Facade;


public class TesteXML {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		UserController teste = UserController.getInstance();

		try {
			teste.addCustomer("Luiz", "luizinho16@gmail.com", "1111111111");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			teste.addCustomer("Luiz3", "luizinho216@gmail.com", "1111111111");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			teste.addCustomer("Luiz4", "luizin1ho16@gmail.com", "1111111111");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		System.out.println(teste.getAllCustomers());

		teste.writeXML();

		try {
			teste.removeCustomer("luizinho16@gmail.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(teste.getAllCustomers());
		teste.writeXML();
		try {
			teste.readCostumers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(teste.getAllCustomers());
		//Facade facade = new Facade();
		//facade.emptyXML();
	}

}
