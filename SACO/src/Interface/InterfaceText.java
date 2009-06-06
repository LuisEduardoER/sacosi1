package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class InterfaceText {

	private static Scanner sc = new Scanner(System.in);

	public static final int ADMINISTRADOR = 1;
	public static final int FUNCIONARIO = 2;
	public static final int CLIENTE = 3;
	public static final int ADICIONAR_USUARIO = 1;
	public static final int ADICIONAR_CLIENTE = 2;
	public static final int REMOVER_USUARIO = 3;
	public static final int REMOVER_CLIENTE = 4;
	public static final int ADICIONAR_VEICULO = 5;
	public static final int REMOVER_VEICULO = 6;
	public static final int REGISTRA_ALUGUEL = 1;
	public static final int ALUGUEIS_REGISTRADOS = 2;
	public static final int CONSULTA_SITUACAO = 3;
	public static final int RESERVA_ALUGUEL = 4;
	public static final int ALUGUEL_VIGENTE = 5;
	public static final int ALUGUEL_ATRASADO = 6;
	public static final int INTERESSE_CARRO = 7;
	public static final int SAIR = 0;	


	private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	private static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	private static String nextString() throws IOException {
		in.nextToken();
		return (String) in.sval;
	}

	private void run() throws IOException {
		out.flush();
	}


	public void imprimeMensagemInicial() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Bem vindo ao Sistema de Alugar Carros e Outros!");
	}

	public void imprimeMensagemFinal() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Sistema desligado.");
	}

	public static void mensagemDeLoginComSucesso() {
		System.out.println("Login efetuado com sucesso.");
		System.out.println("----------------------------------------------------------");
	}

	public static int exibeMenuDeIdentificacao() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADMINISTRADOR + " - Administrador ");
		System.out.println(FUNCIONARIO + " - Funcionario");
		System.out.println(CLIENTE + " - Cliente");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");
		
		int opcao = nextInt();
		
		if (opcao < SAIR || opcao > CLIENTE) {
			exibeMenuDeIdentificacao();
		}

		return opcao;
	}

	public static String[] exibeLogar() throws IOException {
		System.out.println("----------------------------------------------------------");
		System.out.print("Digite seu login: ");
		String login = sc.nextLine();

		System.out.print("Digite sua senha: ");
		String senha = sc.nextLine();
		
		System.out.println(login + " " + senha);

		return new String[] { login, senha };
	}

	public static int exibeMenuDoAdministrador() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADICIONAR_USUARIO + " - Adicionar Usuario");
		System.out.println(ADICIONAR_CLIENTE + " - Adicionar Cliente");
		System.out.println(REMOVER_USUARIO + " - Remover Usuario");
		System.out.println(ADICIONAR_VEICULO + " - Adicionar Veiculo");
		System.out.println(REMOVER_VEICULO + " - Remover Veiculo");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		return nextInt();

	}


	public int exibeMenuDoFuncionario() throws IOException {

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

	public int exibeMenuDoCliente() throws IOException {

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

	public static String[] DadosDoVeiculo() {
		System.out.println("----------------------------------------------------------");
		System.out.println();

		System.out.print("Digite o tipo do veiculo (car/motocycle): ");
		String type = leDados();

		System.out.print("Digite modelo do veiculo: ");
		String model = leDados();

		System.out.print("Digite a cor do veiculo: ");
		String color = leDados();

		System.out.println("Digite o numero da placa do veiculo(aaa0000): ");
		String plate = leDados();

		System.out.print("Digite o ano do veiculo: ");
		String year = leDados();

		System.out.print("Digite o preco do atraso do alguel por dia: ");
		String price = leDados();

		return new String[] {type, model, color, plate, year, price};
	}

	public static String[] DadosDoUsuario() {
		System.out.println("----------------------------------------------------------");
		System.out.println();

		System.out.println("Digite o login:");
		String login = leDados();

		System.out.println("Digite o nome: ");
		String name = leDados();

		System.out.println("Digite o email: ");
		String email = leDados();

		System.out.println("Digite o telefone: ");
		String phone = leDados();	

		return new String[] {login, name, email, phone};
	}


	public static String menuRemocaoCliente() {
		System.out.println("Email do usuario a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}


	public static String menuRemocaoDoFuncionario() {
		System.out.println("Email ou login do funcionario a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}


	public static String menuRemocaoDeVeiculo() {
		System.out.println("Placa do veiculo a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}
	
	
	public static void menuInsucesso() {
		System.out.println("Login sem sucesso. Usuario nao existe ou algum campo esta invalido.");
		System.out.println("Tente novamente.");
		System.out.println("------------------------------------------------------");
		
	}


	public static String leDados(){
		String data = sc.nextLine();
		return data;
	}


}
