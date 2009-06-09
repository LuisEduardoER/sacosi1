package System;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que armazena todos os pedidos de alugueis cadastrados no sistema.
 * 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 * 
 */
public class RequestRentCollection {
	
	/**
	 * instancia da classe
	 */
	private static RequestRentCollection instance;
	/**
	 * lista de requisicoes de alugueis
	 */
	private List<RequestObject> requestList;

	/**
	 * Construtor
	 */
	private RequestRentCollection() {
		requestList = new ArrayList<RequestObject>();
	}

	/**
	 * Metodo que retorna a instancia da classe
	 * @return a instancia da classe
	 */
	public static synchronized RequestRentCollection getInstance() {
		if (instance == null)
			return instance = new RequestRentCollection();
		return instance;
	}
	
	/**
	 * Zera a lista
	 */
	public void emptyList() {
		this.requestList = new ArrayList<RequestObject>();
	}

	/**
	 * get iterador da requestList
	 * @return iterador
	 */
	public Iterator<RequestObject> iterator() {
		return this.requestList.iterator();
	}

	/**
	 * Retorna a quantidade de requisicoes pendentes do sistema.
	 * 
	 * @return a quantidade de requisicoes.
	 */
	public int size() {
		return this.requestList.size();
	}

	/**
	 * Adiciona ao sistema uma requisicao de um cliente por um determinado
	 * veiculo.
	 * 
	 * @param clientEmail
	 *            o email do cliente
	 * @param plate
	 *            a placa do carro
	 * @param date
	 *            a data atual
	 */
	public void add(String clientEmail, String plate) {
		requestList.add(new RequestObject(clientEmail, plate));
	}

	/**
	 * Remove uma requisicao do sistema. Requisicao pode ser removida por ter
	 * passado muito tempo e o cliente nao ter aparecido ou por jah ter sido
	 * atendida.
	 * 
	 * @param request
	 *            a requisicao a ser removida.
	 */
	public void remove(RequestObject request) {
		RequestObject that = getRequest(request.getEmail(), request.getPlate());
		requestList.remove(that);
	}

	/**
	 * Imprime todas as requisicoes pendentes do sistema.
	 */
	public String toString() {
		String output = "";
		for (RequestObject request : requestList) {
			output += request.toString();
		}
		return output;
	}
	
	/**
	 * requisicao de aluguel
	 * @param email
	 * @param plate
	 * @return
	 */
	private RequestObject getRequest(String email, String plate){
		Iterator <RequestObject> it = requestList.iterator();
		while(it.hasNext()){
			RequestObject request = it.next();
			if (request.getEmail().equalsIgnoreCase(email) && request.getPlate().equalsIgnoreCase(plate))
				return request;
		}
		return null;
	}
}
