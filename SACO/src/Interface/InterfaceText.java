package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InterfaceText {

	private static Scanner sc = new Scanner(System.in);

	public static final int ADMINISTRADOR = 1;
	public static final int FUNCIONARIO = 2;
	public static final int CLIENTE = 3;
	//MENU DO ADMINISTRADOR
	public static final int ADICIONAR_USUARIO_ADMIN = 1;
	public static final int ADICIONAR_CLIENTE_ADMIN = 2;
	public static final int ADICIONAR_VEICULO_ADMIN = 3;
	public static final int REMOVER_USUARIO_ADMIN = 4;
	public static final int REMOVER_CLIENTE_ADMIN = 5;
	public static final int REMOVER_VEICULO_ADMIN = 6;
	//MENU DO FUNCIONARIO
	public static final int REGISTRA_ALUGUEL_EMPLOYEE = 1;
	public static final int ADICIONA_VARIOS_ALUGUEIS_EMPLOYEE = 2;
	public static final int ALUGUEL_ATRASADO_EMPLOYEE = 3;
	public static final int REMOVER_ALUGUEL_EMPLOYEE = 4;
	public static final int CONSULTA_SITUACAO_ALL_VEHICLES_EMPLOYEE = 5;
	public static final int CONSULTA_SITUACAO_EMPLOYEE = 6;
	public static final int ALUGUEIS_REGISTRADOS_EMPLOYEE = 7;
	public static final int ALUGUEL_VIGENTE_EMPLOYEE = 8;
	public static final int CONSULTAR_RESERVAS_EMPLOYEE = 9;
	public static final int CONSULTAR_ALUGUEIS_NAO_ATRASADOS_EMPLOYEE = 10;
	public static final int CONSULTAR_ALUGUEIS_ATRASADOS_EMPLOYEE = 11;
	//MENU DO CLIENTE
	public static final int RESERVA_ALUGUEL_CUSTOMER = 1;
	public static final int CONSULTAR_DISPONIVEIS_CUSTOMER = 2;
	public static final int REMOVER_CLIENTE_CUSTOMER = 3;
	public static final int INTERESSE_CARRO_CUSTOMER = 4;
	public static final int SAIR = 0;

	public static final int REGISTRAR_SE = 4;



	private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	private static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
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
		System.out.println("Op��es: ");
		System.out.println();
		System.out.println(ADMINISTRADOR + " - Administrador ");
		System.out.println(FUNCIONARIO + " - Funcionario");
		System.out.println(CLIENTE + " - Cliente");
		System.out.println(REGISTRAR_SE + " - Registrar-se");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma op��o: ");
		
		int opcao = nextInt();
		
		if (opcao < SAIR || opcao > REGISTRAR_SE) {
			exibeMenuDeIdentificacao();
		}

		return opcao;
	}

	public static String[] exibeLogar() throws IOException {
		System.out.println("----------------------------------------------------------");
		System.out.print("Digite seu login (nome se for cliente): ");
		String login = sc.nextLine();

		System.out.print("Digite seu email: ");
		String senha = sc.nextLine();
		
		System.out.println(login + " " + senha);

		return new String[] { login, senha };
	}

	public static int exibeMenuDoAdministrador() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Op��es: ");
		System.out.println();
		System.out.println(ADICIONAR_USUARIO_ADMIN + " - Adicionar Usuario");
		System.out.println(ADICIONAR_CLIENTE_ADMIN + " - Adicionar Cliente");
		System.out.println(ADICIONAR_VEICULO_ADMIN + " - Adicionar Veiculo");
		System.out.println(REMOVER_USUARIO_ADMIN + " - Remover Usuario");
		System.out.println(REMOVER_CLIENTE_ADMIN + " - Remover Cliente");
		System.out.println(REMOVER_VEICULO_ADMIN + " - Remover Veiculo");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma op��o: ");

		return nextInt();

	}


	public static int exibeMenuDoFuncionario() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Op��es: ");
		System.out.println();
		System.out.println(REGISTRA_ALUGUEL_EMPLOYEE + " - Registrar Aluguel");
		System.out.println(ADICIONA_VARIOS_ALUGUEIS_EMPLOYEE + " - Adiciona Varios Alugueis");
		System.out.println(ALUGUEL_ATRASADO_EMPLOYEE + " - Setar Alugueis Atrasados");
		System.out.println(REMOVER_ALUGUEL_EMPLOYEE + " - Remover Aluguel");
		System.out.println(CONSULTA_SITUACAO_ALL_VEHICLES_EMPLOYEE + " - Consulta Situacao de Todos os Veiculos");
		System.out.println(CONSULTA_SITUACAO_EMPLOYEE + " - Consultar Situacao de um veiculo");
		System.out.println(ALUGUEIS_REGISTRADOS_EMPLOYEE + " - Ver Alugueis Registrados ");
		System.out.println(ALUGUEL_VIGENTE_EMPLOYEE + " - Ver Alugueis Vigentes");
		System.out.println(CONSULTAR_RESERVAS_EMPLOYEE + " - Consultar Reservas de Aluguel");
		System.out.println(CONSULTAR_ALUGUEIS_NAO_ATRASADOS_EMPLOYEE + " - Listar alugueis nao atrasados");
		System.out.println(CONSULTAR_ALUGUEIS_ATRASADOS_EMPLOYEE + " - Listar alugueis atrasados");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma op��o: ");

		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}

	}

	public static int exibeMenuDoCliente() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Op��es: ");
		System.out.println();
		System.out.println(RESERVA_ALUGUEL_CUSTOMER + " - Fazer Reserva de Aluguel");
		System.out.println(CONSULTAR_DISPONIVEIS_CUSTOMER + " - Consultar veiculos disponiveis para aluguel");
		System.out.println(REMOVER_CLIENTE_CUSTOMER + " - Remover Cliente");
		System.out.println(INTERESSE_CARRO_CUSTOMER + " - Mostrar interesse em um Carro Alugado");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma op��o: ");

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

		System.out.print("Digite o tipo do veiculo (car/motorcycle): ");
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

	public static String[] dadosDoAluguel(){
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		System.out.println("Digite o email do cliente: ");
		String email = sc.nextLine();
		System.out.println("Digite a data inicial de aluguel (ex:10/04/09): ");
		String dataInicial = sc.nextLine();
		System.out.println("Digite a data final de aluguel (ex:10/04/09): ");
		String dataFinal = sc.nextLine();
		return new String[] {placa, email, dataInicial, dataFinal};
	}
	
	public static void numeroDeAlugueis(int valor){
		System.out.println("Alugueis Registrados no momento:");
		System.out.println("---------------------------------");
		System.out.println(valor);
	}
	
	public static String getVehicleSituation(){
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		return placa;
	}

	public static void alugueisVigentes(int valor){
		System.out.println("Alugueis Vigentes:");
		System.out.println("---------------------------------");
		System.out.println(valor);
	}

	public static String[] registerLateRent() {
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		System.out.println("Digite o seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite a data inicial de aluguel (ex:10/04/09): ");
		String dataInicial = sc.nextLine();
		System.out.println("Digite a data final de aluguel (ex:10/04/09): ");
		String dataFinal = sc.nextLine();
		return new String[] {placa, email, dataInicial, dataFinal};
	}

	public static String[][] adicionaVariosAlugueis() {
		System.out.println("Quantos alugueis serao feitos: ");
		String tamanhoString = sc.nextLine();
		int tamanho = Integer.parseInt(tamanhoString);
		String[] placas = new String[tamanho];
		String[] datasIniciais = new String[tamanho];
		String[] datasFinais = new String[tamanho];
		int i = 0;
		while (i < tamanho) {
			System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
			String placa = sc.nextLine();
			placas[i] = placa;
			System.out.println("Digite a data inicial de aluguel (ex:10/04/09): ");
			String dataInicial = sc.nextLine();
			datasIniciais[i] = dataInicial;
			System.out.println("Digite a data final de aluguel (ex:10/04/09): ");
			String dataFinal = sc.nextLine();
			datasFinais[i] = dataFinal;
			i++;
		}
		System.out.println("Digite o seu email: ");
		String email = sc.nextLine();
		String[] emails = {email};
		return new String[][] {placas, emails, datasIniciais, datasFinais};
	}

	public static String releaseVehicle() {
		System.out.println("Digite a placa do veiculo que deseja remover(ex: aaa0000): ");
		String plate = sc.nextLine();
		return plate;
	}

	public static void printRequestList(String printRequestList) {
		System.out.println("------------------------------------------------------");
		System.out.println(printRequestList);
		
	}

	@SuppressWarnings("deprecation")
	public static Calendar menuEscolherData() {
		System.out.println("Digite o dia: ");
		String dia = sc.nextLine();
		System.out.println("Digite o mes: ");
		String mes = sc.nextLine();
		System.out.println("Digite o ano(ex: 09 para 2009): ");
		String ano = sc.nextLine();
		ano = "1" + ano;
		int mesCorreto = Integer.parseInt(mes);
		mesCorreto -= 1;
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date(Integer.parseInt(ano), mesCorreto, Integer.valueOf(dia)));
		return calendario;
	}

	public static void listAllPendingRents(String listAllPendingRents) {
		System.out.println(listAllPendingRents);
		
	}

	public static void listAllNonPendingRents(String listAllNonPendingRents) {
		System.out.println(listAllNonPendingRents);
	}

	public static void listAllVehiclesSituation(String impressao){
		System.out.println("=========================================");
		System.out.println(impressao);
	}
	public static String[] DadosDoCliente() {
		System.out.println("----------------------------------------------------------");
		System.out.println();

		System.out.println("Digite o nome: ");
		String name = leDados();

		System.out.println("Digite o email: ");
		String email = leDados();

		System.out.println("Digite o telefone: ");
		String phone = leDados();	
		
		return new String[] {name, email, phone};
	}

	public static void showSituation(String situacao, String placa) {
		System.out.println("--------------------------------------------------");
		if (situacao.equalsIgnoreCase("active")){
			System.out.println("O veiculo de placa " + placa + " esta atualmente alugado.");
		}
		else{
			System.out.println("O veiculo de placa " + placa + " esta atualmente disponivel.");
		}
		
	}

	public static void printError(String message) {
		System.out.println(message);
	}

	public static String[] menuReservarVeiculo() {
		System.out.println("Digite o seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		return new String[] {email, placa};
	}
	
}
