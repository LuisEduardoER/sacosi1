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
	 * @throws Exception 
	 * @throws InvalidFieldException
	 */
	public boolean removeRent(String plate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public int getAllActiveRents() throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws MessagingException
	 * @throws Exception 
	 */
	public void registerLateRent(String plate, String email,
			String initialDate, String finalDate) throws Exception {

		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public String getVehicleSituation(String plate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public String getRentSituation(String email, String plate,
			String inicialDate, String finalDate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public String listAllPendingRents(Calendar date) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) < 1)
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
	public String listAllNonPendingRents(Calendar date) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
		String output = "";
		for (Rent rent : rents) {
			if (rent.compareTo(date) >= 1)
				output += rent.toString();
		}
		return output;
	}

	/**
	 * Quantidade de alugueis por cliente
	 * 
	 * @param email
	 * @return quantidade de alugueis por cliente
	 * @throws Exception 
	 */
	public int getRentsByCustomer(String email) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis do cliente esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public int getRentsByVehicle(String plate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis de veiculos esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public boolean releaseVehicle(String plate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
	 * @throws Exception 
	 */
	public boolean vehicleIsRent(String plate) throws Exception {
		if (rents.size() == 0){
			throw new Exception("A lista de alugueis esta vazia.");
		}
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
