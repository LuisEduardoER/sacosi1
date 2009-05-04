package System;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe representa uma lista de alugueis
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class RentList {
	
	private List<Rent> rentList;
	private static RentList instance;
	
	/**
	 * Construtor
	 */
	private RentList() {
		rentList = new ArrayList<Rent>();
	}
	
	/**
	 * 
	 * @return instancia da classe
	 */
	public synchronized static RentList getInstance() {
		if (instance == null) instance = new RentList();
		return instance;
	}
	
	/**
	 * Adiciona um aluguel na lista
	 * @param rent
	 */
	public void addRent(Rent rent) {
		for (int i = 0; i < rentList.size(); i++) {
			if (rentList.get(i).getDevolutionDate().compareTo(rent.getDevolutionDate()) > 1) {
				rentList.add(i, rent);
			} else break;
		}
		rentList.add(rent);
	}

}
