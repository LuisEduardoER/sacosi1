package Tests;

import java.util.ArrayList;
import java.util.List;

import Commands.Facade;
import easyaccept.EasyAcceptFacade;

public class User14Test {
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us14.txt");

		//Instantiate the Monopoly Game façade

		Facade test = new Facade();

		//Instantiate EasyAccept façade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
	}
}
