package System;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import Exceptions.AlreadyExistException;
import Exceptions.InvalidFieldException;
import Mail.MailManager;

/**
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */

public class RentCollection {

	private static RentCollection instance;
	private List<Rent> rents;

	/**
	 * Construtor privado da classe
	 */
	private RentCollection() {
		rents = new ArrayList<Rent>();
	}

	/**
	 * Metodo que retorna a unica instancia da classe
	 * @return a unica instancia da classe
	 */
	public static synchronized RentCollection getInstance() {
		if (instance == null)
			return instance = new RentCollection();
		return instance;
	}

	/**
	 * Faz um pedido de aluguel
	 * 
	 * @param clientEmail
	 * @param plate
	 * @throws InvalidFieldException
	 */
	public boolean removeRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rents.remove(rent);
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a quantidade de algueis ativos
	 * 
	 * @return quantidade de algueis ativos
	 */
	public int getAllActiveRents() {
		int cont = 0;
		for (Rent rent : rents) {
			if (rent.getRentSituation().equals("active")) {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * Registra um aluguel atrasado
	 * 
	 * @param plate
	 * @param email
	 * @param initialDate
	 * @param finalDate
	 * @throws AlreadyExistException
	 * @throws InvalidFieldException
	 * @throws MessagingException 
	 * @throws MessagingException
	 */
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws AlreadyExistException,
			InvalidFieldException, MessagingException {

		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rent.setRentSituation("late");
				notifyCostumerAboutLateRent(email);
				break;
			}
		}
	}

	/**
	 * Retorna a situacao do veiculo.
	 * 
	 * @param plate
	 * @return situacao do veiculo.
	 */
	public String getVehicleSituation(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate))
				return "unavailable";
		}
		return "available";
	}

	/**
	 * Retorna a situcao do aluguel
	 * 
	 * @param email
	 * @param plate
	 * @param inicialDate
	 * @param finalDate
	 * @return situcao do aluguel
	 */
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) {
		for (Rent rent : rents) {
			if (rent.getCostumer().getEmail().equals(email)
					&& rent.getVehiclePlate().equals(plate))
				return rent.getRentSituation();
		}
		return null;

	}

	/**
	 * Lista todos os alugueis pendentes
	 * 
	 * @return toString de todos os alugueis pendentes
	 */
	public String listAllPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.getInitial().compareTo(date) <= 0)
			if (rent.compareTo(date) > 0)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * Lista todos os alugueis nao pendentes
	 * 
	 * @param date
	 * @return toString de todos os alugueis nao pendentes
	 */
	public String listAllNonPendingRents(Calendar date) {
		String output = "";
		for (Rent rent : rents) {
			if (rent.getInitial().compareTo(date) <= 0)
			if (rent.compareTo(date) <= 0)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * Quantidade de alugueis por cliente
	 * 
	 * @param email
	 * @return quantidade de alugueis por cliente
	 */
	public int getRentsByCustomer(String email) {
		int cont = 0;
		if (rents != null)
			for (Rent rent : rents) {
				if (rent.getCostumer().getEmail().equals(email)) {
					cont++;
				}
			}
		return cont;
	}

	/**
	 * Quantidade de alugueis por veiculo
	 * 
	 * @param plate
	 * @return Quantidade de alugueis por veiculo
	 */
	public int getRentsByVehicle(String plate) {
		int cont = 0;
		if (rents != null)
			for (Rent rent : rents) {
				if (rent.getVehiclePlate().equals(plate)) {
					cont++;
				}
			}
		return cont;
	}
	
	/**
	 * LIbera um veiculo
	 * 
	 * @param plate
	 * @return uma confirmacao
	 */
	public boolean releaseVehicle(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equalsIgnoreCase(plate)) {
				rents.remove(rent);
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se um veiculo esta alugado.
	 * 
	 * @param plate
	 * @return true se estiver alugado ou false caso contrario
	 */
	public boolean vehicleIsRent(String plate) {
		for (Rent rent : rents) {
			if (rent.getVehiclePlate().equals(plate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Cria uma nova Lista para limpar os registros do programa
	 */
	public void emptyList() {
		this.rents = new ArrayList<Rent>();
	}

	/**
	 * Retorna a quantidade de alugueis na lista
	 * @return a quantidade de alugueis na lista
	 */
	public int size() {
		return rents.size();
	}

	/**
	 * Adiciona um novo aluguel a lista
	 * @param rent novo aluguel
	 */
	public void add(Rent rent) {
		this.rents.add(rent);
	}
	
	/**
	 * Metodo que apos constatacao de que o veiculo esta com o aluguel atrasado,
	 * envia para o email do cliente uma mensagem de atraso.
	 * 
	 * @param email
	 * @throws MessagingException
	 */
	private void notifyCostumerAboutLateRent(String email)
			throws MessagingException {
		String [] sendTo = {email};
		String message = "The rental of your vehicle is late. The fine will be charged uppon their return.";
		MailManager.getInstanceOf().sendEmail(sendTo, message);
	}
}
