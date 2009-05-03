package Tests;

import java.util.ArrayList;
import java.util.List;

import Commands.Facade;

import easyaccept.EasyAcceptFacade;


public class User07Test {
	
	

	
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us07.txt");

		//Instantiate the Monopoly Game fa�ade

		Facade test = new Facade();

		//Instantiate EasyAccept fa�ade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());

	}
}