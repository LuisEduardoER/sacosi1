package Interface;

import LogicalSystem.LogicOfTheAdmin;
import LogicalSystem.LogicOfTheCustomer;
import LogicalSystem.LogicOfTheUser;

public class MainSACO {

	public static void main(String[] args) throws Exception {
		LoginSACO login = new LoginSACO();
		Object o = login.login();
		while (o != null) {
			if (o instanceof LogicOfTheAdmin) { 
				((LogicOfTheAdmin) o).inicia();
			}
			else if (o instanceof LogicOfTheUser) {
				((LogicOfTheUser) o).inicia();
			}
			else {
				((LogicOfTheCustomer) o).inicia();
			}
			o = login.login();
		}
	}

}
