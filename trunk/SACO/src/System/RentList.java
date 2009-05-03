package System;

import java.util.ArrayList;
import java.util.List;

public class RentList {
	
	private List<Rent> rentList;
	private static RentList instance;
	
	private RentList() {
		rentList = new ArrayList<Rent>();
	}
	
	public synchronized static RentList getInstance() {
		if (instance == null) instance = new RentList();
		return instance;
	}
	
	public void addRent(Rent rent) {
		for (int i = 0; i < rentList.size(); i++) {
			if (rentList.get(i).getDevolutionDate().compareTo(rent.getDevolutionDate()) > 1) {
				rentList.add(i, rent);
			} else break;
		}
		rentList.add(rent);
	}

}
