package Tests;

import java.util.ArrayList;
import java.util.List;

import Commands.Facade;
import easyaccept.EasyAcceptFacade;

public class AllTests {
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us01.txt");

		//Instantiate the Monopoly Game façade

		Facade test = new Facade();

		//Instantiate EasyAccept façade

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us02-17.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us03.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us04.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us05.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us06.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us07.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us08.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us10-11.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us12-13.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
		files = new ArrayList<String>();

		//Put the us1.txt file into the "test scripts" list

		files.add("us14.txt");

		//Instantiate the Monopoly Game façade

		test = new Facade();

		//Instantiate EasyAccept façade

		eaFacade = new EasyAcceptFacade(test, files);

		//Execute the tests

		eaFacade.executeTests();

		//Print the tests execution results

		System.out.println(eaFacade.getCompleteResults());
		test.emptyXML();
	}
}
