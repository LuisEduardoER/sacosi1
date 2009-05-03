package Tests;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class User06Tests {
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us06.txt");

		//Instantiate the Monopoly Game fa�ade

		VehiclesControllerTest test = new VehiclesControllerTest();

		//Instantiate EasyAccept fa�ade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());

	}
}