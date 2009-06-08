package Tests;

import java.util.ArrayList;
import java.util.List;

import Commands.Facade;
import easyaccept.EasyAcceptFacade;

public class AllTests {
	public static void main(String[] args) throws Exception {

		List<String> files = new ArrayList<String>();

		files.add("us01.txt");
		files.add("us02-17.txt");
		files.add("us03.txt");
		files.add("us04.txt");
		files.add("us05.txt");
		files.add("us06.txt");
		files.add("us07.txt");
		files.add("us08.txt");
		files.add("us10-11.txt");
		files.add("us12-13.txt");
		files.add("us14.txt");

		Facade test = new Facade();
		
		test.cleanDB();
		
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(test, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
		
		test.cleanDB();

		
	}
}
