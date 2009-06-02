package Interface;

import java.util.Scanner;

public class InterfaceText {
	
	private Scanner sc;

	public InterfaceText() {
		sc = new Scanner(System.in);
	}

	public static final int ADMINISTRADOR = 1;
	public static final int FUNCIONARIO = 2;
	public static final int CLIENTE = 3;
	public static final int ADICIONAR_CLIENTE = 1;
	public static final int ADICIONAR_USUARIO = 2;
	public static final int REMOVER_USUARIO = 3;
	public static final int ADICIONAR_VEICULO = 4;
	public static final int REMOVER_VEICULO = 5;
	public static final int REGISTRA_ALUGUEL = 1;
	public static final int ALUGUEIS_REGISTRADOS = 2;
	public static final int CONSULTA_SITUACAO = 3;
	public static final int ALUGUEL_VIGENTE = 5;
	public static final int ALUGUEL_ATRASADO = 6;
	public static final int REMOVER_CLIENTE = 2;
	public static final int RESERVA_ALUGUEL = 4;
	public static final int INTERESSE_CARRO = 5;
	public static final int SAIR = 0;	
	
	public void imprimeMensagemInicial() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Bem vindo ao Sistema de Alugar Carros e Outros!");
	}

	public void imprimeMensagemFinal() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Sistema desligado.");
	}
	
	public int exibeMenuDeIdentificacao() {
		
		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADMINISTRADOR + " - Administrador ");
		System.out.println(FUNCIONARIO + " - Funcionario");
		System.out.println(CLIENTE + " - Cliente");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");


		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}
	}

	public String[] exibeLogar() {
		System.out.println("----------------------------------------------------------");
		System.out.print("Digite seu login: ");
		String login = sc.nextLine();

		System.out.print("Digite sua senha: ");
		String senha = sc.nextLine();

		return new String[] { login, senha };
	}
	
	public int exibeMenuDoAdministrador() {
	
		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADICIONAR_CLIENTE + " - Adicionar Cliente");
		System.out.println(ADICIONAR_USUARIO + " - Adicionar Usuario");
		System.out.println(REMOVER_USUARIO + " - Remover Usuario");
		System.out.println(ADICIONAR_VEICULO + " - Adicionar Veiculo");
		System.out.println(REMOVER_VEICULO + " - Remover Veiculo");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}

	}
	
	
	public int exibeMenuDoFuncionario() {
		
		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(REGISTRA_ALUGUEL + " - Registrar Aluguel");
		System.out.println(ALUGUEIS_REGISTRADOS + " - Ver Alugueis Registrados ");
		System.out.println(CONSULTA_SITUACAO + " - Consultar Situacao de um veiculo");
		System.out.println(ADICIONAR_VEICULO + " - Adicionar Veiculo");
		System.out.println(ALUGUEL_VIGENTE + " - Ver Alugueis Vigentes");
		System.out.println(ALUGUEL_ATRASADO + " - Ver Alugueis Atrasados");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}
		
	}
	
	public int exibeMenuDoCliente() {
		
		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADICIONAR_CLIENTE + " - Adicionar Cliente");
		System.out.println(REMOVER_CLIENTE + " - Remover Cliente");
		System.out.println(CONSULTA_SITUACAO + " - Consultar Situacao de um veiculo");
		System.out.println(RESERVA_ALUGUEL + " - Fazer Reserva de Aluguel");
		System.out.println(INTERESSE_CARRO + " - Mostrar interesse em um Carro Alugado");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}
		
	}
	

	public String[] DadosDoVeiculo() {
		System.out.println("----------------------------------------------------------");
		System.out.println();

		System.out.print("Digite o tipo do veiculo (car/motocycle): ");
		String type = sc.nextLine();
		
		System.out.print("Digite modelo do veiculo: ");
		String model = sc.nextLine();

		System.out.print("Digite a cor do veiculo: ");
		String color = sc.nextLine();
		
		System.out.println("Digite o numero da placa do veiculo(aaa0000): ");
		String plate = sc.nextLine();
	
		System.out.print("Digite o ano do veiculo: ");
		String year = sc.nextLine();
		
		System.out.print("Digite o preco do atraso do alguel por dia: ");
		String price = sc.nextLine();
	
		return new String[] {type, model, color, plate, year, price};
	}
	
	public String[] DadosDoUsuario() {
		System.out.println("----------------------------------------------------------");
		System.out.println();

		System.out.println("Digite o nome do interessado: ");
		String name = sc.nextLine();
		
		System.out.println("Digite o seu email: ");
		String email = sc.nextLine();

		System.out.println("Digite o seu telefone: ");
		String phone = sc.nextLine();	

		return new String[] {name, email, phone};
	}
	
	public String lePlaca(){
		System.out.println("Digite o numero da placa do veiculo: ");
		String placa = sc.nextLine();
		return placa;
	}
	
	public String leLogin(){
		System.out.println("Digite o login do usuario: ");
		String login = sc.nextLine();
		return login;
	}
	
	public String leEmail(){
		System.out.println("Digite o seu telefone: ");
		String email = sc.nextLine();	
		return email;
	}
	
	public String loginOrEmail(){
		System.out.println("Digite o seu Login ou e-mail: ");
		String emailOrLogin = sc.nextLine();
		return emailOrLogin;
	}
	
	public String leData(){
		String data = sc.nextLine();
		return data;
	}


}
