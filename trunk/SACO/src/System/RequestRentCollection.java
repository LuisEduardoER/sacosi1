package System;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que armazena todos os pedidos de alugueis cadastrados no sistema. 
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 *
 */
public class RequestRentCollection {
	
	private List<RequestObject> requestList;
	
	/**
	 * Construtor
	 */
	public  RequestRentCollection(){
		requestList = new ArrayList<RequestObject>();
	}
	
	/**
	 * Retorna a lista de requisicoes do sistema.
	 * 
	 * @return
	 * 			
	 */
	public List<RequestObject> getRequestRentCollection(){
		return this.requestList;
	}
	
	/**
	 * Retorna a quantidade de requisicoes pendentes do sistema.
	 * @return
	 * 			a quantidade de requisicoes.
	 */
	public int size(){
		return this.requestList.size();
	}
	
	/**
	 * Adiciona ao sistema uma requisicao de um cliente por um determinado veiculo.
	 * 
	 * @param clientEmail 
	 * 					o email do cliente
	 * @param plate
	 * 					a placa do carro 
	 * @param date
	 * 					a data atual
	 */
	public void add (String clientEmail, String plate, Date date){
		requestList.add(new RequestObject(clientEmail, plate, date));
	}
	
	/**
	 * Remove uma requisicao do sistema. Requisicao pode ser removida por ter passado
	 * muito tempo e o cliente nao ter aparecido ou por jah ter sido atendida.
	 * 
	 * @param request
	 * 				a requisicao a ser removida.
	 */
	public void remove (RequestObject request) {
		requestList.remove(request);
	}
	
	/**
	 * Imprime todas as requisicoes pendentes do sistema. 
	 */
	public String toString() {
		String output = "";
		for (RequestObject request: requestList) {
			output += request.toString();
		}
		return output;
	}
}
