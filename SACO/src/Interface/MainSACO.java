package Interface;

import java.util.Calendar;

import Commands.Facade;

public class MainSACO {
	
	public static void main(String[] args) throws Exception {
		MainSACO programa = new MainSACO();
		programa.executaPrograma();
	}
	// campo que representa a fachada do SACO
	public Facade facade;
	// campo que representa a interface texto do executavel
	private InterfaceText it;

	// executa o programa no modo texto
	/**
	 * Executa o programa.
	 */
	private void executaPrograma() throws Exception {
		it = new InterfaceText();
		facade = new Facade();

		it.imprimeMensagemInicial();

		int opcao = it.exibeMenuDeIdentificacao();
		while (opcao != InterfaceText.SAIR) {
			Boolean estaLogado = true;
			
			switch (opcao) {
			case InterfaceText.ADMINISTRADOR:
				if (estaLogado == true) {
					Administrador();
				}
				break;
			case InterfaceText.FUNCIONARIO:
				if (estaLogado == true) {
					Funcionario();
				}
				break;
			case InterfaceText.CLIENTE:
				ehCliente();
				break;
			default:
				System.out.println("Opcao invalida.");
			}
			opcao = it.exibeMenuDeIdentificacao();
		}
		
		it.imprimeMensagemFinal();

	}
	
	private void ehCliente() throws Exception {
		int opcao;
		do {
			opcao = it.exibeMenuDoCliente();
		switch (opcao) {
		case InterfaceText.ADICIONAR_CLIENTE:
			String[] string = it.DadosDoUsuario();
			facade.addCustomer(string[0], string[1], string[2]);
			break;
		case InterfaceText.REMOVER_CLIENTE:
			String mail = it.leEmail();
			facade.removeCustomer(mail);
			break;
		case InterfaceText.CONSULTA_SITUACAO:
			String plate = it.lePlaca();
			facade.getVehicleSituation(plate);
			break;
		case InterfaceText.RESERVA_ALUGUEL:
			String clientEmail = it.leEmail();
			String placa = it.lePlaca();
			facade.requestRent(clientEmail, placa);
			break;
		case InterfaceText.INTERESSE_CARRO:
			String clientMail = it.leEmail();
			String placaa = it.lePlaca();
			facade.requestRent(clientMail, placaa);
			break;
		default:
			System.out.println("Opção invalida.");
		}
			//escolhaDoUsuario(opcao);
		} while (opcao != InterfaceText.SAIR);
		if (opcao == InterfaceText.SAIR)
			executaPrograma();
	}
	
	private void Administrador() throws Exception {
		int opcao;
		do {
			opcao = it.exibeMenuDoAdministrador();
		switch (opcao) {
		case InterfaceText.ADICIONAR_CLIENTE:
			String[] string = it.DadosDoUsuario();
			facade.addCustomer(string[0], string[1], string[2]);
			break;
		case InterfaceText.ADICIONAR_USUARIO:
			String login = it.leLogin();
			String[] dados = it.DadosDoUsuario();
			facade.addUser(login, dados[0],dados[1],dados[2]);
			break;
		case InterfaceText.REMOVER_USUARIO:
			String emailOrLogin = it.loginOrEmail();
			facade.removeUser(emailOrLogin);
			break;
		case InterfaceText.ADICIONAR_VEICULO:
			String[] string2 = it.DadosDoVeiculo();
			facade.addVehicle(string2[0],string2[1],string2[2],string2[3],string2[4],string2[5]);
			break;
		case InterfaceText.REMOVER_VEICULO:
			String placa = it.lePlaca();
			facade.removeVehicle(placa);
			break;
		default:
			System.out.println("Opção invalida.");
		}
			//escolhaDoUsuario(opcao);
		} while (opcao != InterfaceText.SAIR);
		if (opcao == InterfaceText.SAIR)
			executaPrograma();
	}
	
	private void Funcionario() throws Exception {
		int opcao;
		do {
			opcao = it.exibeMenuDoFuncionario();
		switch (opcao) {
		case InterfaceText.REGISTRA_ALUGUEL:
			String plate = it.lePlaca();
			String email = it.leEmail();
			System.out.println("Informe a data de realizacao do aluguel:");
			String initialDate = it.leData();
			System.out.println("Informe a data de devolucao do veiculo:");
			String finalDate = it.leData();
			facade.registerRent(plate, email, initialDate, finalDate);
			break;
		case InterfaceText.ALUGUEIS_REGISTRADOS:
			facade.getAllActiveRents();
			break;
		case InterfaceText.CONSULTA_SITUACAO:
			String placa = it.lePlaca();
			facade.getVehicleSituation(placa);
			break;
		case InterfaceText.ADICIONAR_VEICULO:
			String[] string = it.DadosDoVeiculo();
			facade.addVehicle(string[0],string[1],string[2],string[3],string[4],string[5]);
			break;
		case InterfaceText.ALUGUEL_VIGENTE:
			Calendar date = Calendar.getInstance();
			facade.listAllNonPendingRents(date);
			break;
		case InterfaceText.ALUGUEL_ATRASADO:
			Calendar data = Calendar.getInstance();
			facade.listAllPendingRents(data);
			break;
		default:
			System.out.println("Opção invalida.");
		}
			//escolhaDoUsuario(opcao);
		} while (opcao != InterfaceText.SAIR);
		if (opcao == InterfaceText.SAIR)
			executaPrograma();
	}
	

}
