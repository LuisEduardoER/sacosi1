package Commands;

import Exceptions.ColorException;
import Exceptions.EmailException;
import Exceptions.InvalidFieldException;
import Exceptions.LoginException;
import Exceptions.ModelException;
import Exceptions.NameException;
import Exceptions.NoFieldException;
import Exceptions.PhoneException;
import Exceptions.PlateException;
import Exceptions.PriceException;
import Exceptions.TypeException;
import Exceptions.YearException;
import Users.Administrator;
import Vehicles.Car;
import Vehicles.Motorcycle;
import Vehicles.Vehicle;



public class Facade {

	public static void main(String[] args) {
		//exemplo de como executar:
		//EasyAccept.main(new String[] {"Facade", "test1.txt"});
		try {
			Administrator admin = new Administrator("foo bar", "foo@bar.com", "6666666666", "acabou"
									);
			System.out.println(admin.getLogin());
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (NameException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (PhoneException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		
	}

}