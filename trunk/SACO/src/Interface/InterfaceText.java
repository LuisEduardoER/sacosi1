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

/**
 * Interface Texto
 * @author Filipe
 * @author Melina
 * @author Luiz
 * @author Raissa
 * @author Ramon
 */
public class InterfaceText {
	
	/**
	 * Scanner 
	 */
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * administrador
	 */
	public static final int ADMINISTRADOR = 1;
	/**
	 * funcionario
	 */
	public static final int FUNCIONARIO = 2;
	/**
	 * cliente
	 */
	public static final int CLIENTE = 3;
	//MENU DO ADMINISTRADOR
	
	/**
	 * constante para adicionar administrador
	 */
	public static final int ADICIONAR_USUARIO_ADMIN = 1;
	
	/**
	 * constante para adicionar cliente
	 */
	public static final int ADICIONAR_CLIENTE_ADMIN = 2;
	
	/**
	 * constante para adicionar veiculo
	 */
	public static final int ADICIONAR_VEICULO_ADMIN = 3;
	
	/**
	 * constante para remover usuario
	 */
	public static final int REMOVER_USUARIO_ADMIN = 4;
	
	/**
	 * constante para remover cliente
	 */
	public static final int REMOVER_CLIENTE_ADMIN = 5;
	
	/**
	 * constante para remover veiculo
	 */
	public static final int REMOVER_VEICULO_ADMIN = 6;
	//MENU DO FUNCIONARIO
	
	/**
	 * constante para registrar aluguel 
	 */
	public static final int REGISTRA_ALUGUEL_EMPLOYEE = 1;
	
	/**
	 * constante para adicionar varios alugueis
	 */
	public static final int ADICIONA_VARIOS_ALUGUEIS_EMPLOYEE = 2;
	
	/**
	 * constante para registrar aluguel atrasado
	 */
	public static final int ALUGUEL_ATRASADO_EMPLOYEE = 3;
	
	/**
	 * constante para remover aluguel
	 */
	public static final int REMOVER_ALUGUEL_EMPLOYEE = 4;
	
	/**
	 * constante para consultar situacao
	 */
	public static final int CONSULTA_SITUACAO_ALL_VEHICLES_EMPLOYEE = 5;
	
	/**
	 * constante para consulta de situacao de aluguel
	 */
	public static final int CONSULTA_SITUACAO_EMPLOYEE = 6;
	
	/**
	 * constante para aluguel registrado
	 */
	public static final int ALUGUEIS_REGISTRADOS_EMPLOYEE = 7;
	
	/**
	 * constante para aluguel vigente
	 */
	public static final int ALUGUEL_VIGENTE_EMPLOYEE = 8;
	
	/**
	 * constante para consulta de reservas
	 */
	public static final int CONSULTAR_RESERVAS_EMPLOYEE = 9;
	
	/**
	 * constante para consulta de alugueis nao atrasados
	 */
	public static final int CONSULTAR_ALUGUEIS_NAO_ATRASADOS_EMPLOYEE = 10;
	
	/**
	 * constante para consulta de alugueis nao atrasados
	 */
	public static final int CONSULTAR_ALUGUEIS_ATRASADOS_EMPLOYEE = 11;
	//MENU DO CLIENTE
	
	/**
	 * constante para reservas de alugueis
	 */
	public static final int RESERVA_ALUGUEL_CUSTOMER = 1;
	
	/**
	 * constante para consulta de clientes
	 */
	public static final int CONSULTAR_DISPONIVEIS_CUSTOMER = 2;
	
	/**
	 * constante para remover cliente
	 */
	public static final int REMOVER_CLIENTE_CUSTOMER = 3;
	
	/**
	 * constante para interesse de carro
	 */
	public static final int INTERESSE_CARRO_CUSTOMER = 4;
	
	/**
	 * constante para sair do sistema
	 */
	public static final int SAIR = 0;
	
	/**
	 * constante para registrar-se
	 */
	public static final int REGISTRAR_SE = 4;


	/**
	 * StreamTokenizer
	 */
	private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	/**
	 * PrintWriter
	 */
	private PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	
	/**
	 * ler inteiro
	 * @return
	 * @throws IOException
	 */
	private static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	/**
	 * imprime mensagem inicial
	 */
	public void imprimeMensagemInicial() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Bem vindo ao Sistema de Alugar Carros e Outros!");
	}
	
	/**
	 * imprime mensagem final
	 */
	public void imprimeMensagemFinal() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Sistema desligado.");
	}
	
	/**
	 * mensagem de login 
	 */
	public static void mensagemDeLoginComSucesso() {
		System.out.println("Login efetuado com sucesso.");
		System.out.println("----------------------------------------------------------");
	}
	
	/**
	 * exibe menu
	 * @return
	 * @throws IOException
	 */
	public static int exibeMenuDeIdentificacao() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADMINISTRADOR + " - Administrador ");
		System.out.println(FUNCIONARIO + " - Funcionario");
		System.out.println(CLIENTE + " - Cliente");
		System.out.println(REGISTRAR_SE + " - Registrar-se");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");
		
		int opcao = nextInt();
		
		if (opcao < SAIR || opcao > REGISTRAR_SE) {
			exibeMenuDeIdentificacao();
		}

		return opcao;
	}
	
	/**
	 * exibe logar
	 * @return
	 * @throws IOException
	 */
	public static String[] exibeLogar() throws IOException {
		System.out.println("----------------------------------------------------------");
		System.out.print("Digite seu login (nome se for cliente): ");
		String login = sc.nextLine();

		System.out.print("Digite seu email: ");
		String senha = sc.nextLine();
		
		System.out.println(login + " " + senha);

		return new String[] { login, senha };
	}
	
	/**
	 * exibe menu do adm
	 * @return
	 * @throws IOException
	 */
	public static int exibeMenuDoAdministrador() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(ADICIONAR_USUARIO_ADMIN + " - Adicionar Usuario");
		System.out.println(ADICIONAR_CLIENTE_ADMIN + " - Adicionar Cliente");
		System.out.println(ADICIONAR_VEICULO_ADMIN + " - Adicionar Veiculo");
		System.out.println(REMOVER_USUARIO_ADMIN + " - Remover Usuario");
		System.out.println(REMOVER_CLIENTE_ADMIN + " - Remover Cliente");
		System.out.println(REMOVER_VEICULO_ADMIN + " - Remover Veiculo");
		System.out.println(SAIR + " - Sair");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		return nextInt();

	}

	
	/**
	 * exibe menu do funcionario
	 * @return
	 * @throws IOException
	 */
	public static int exibeMenuDoFuncionario() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
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
		System.out.print("Escolha uma opção: ");

		try {
			int opcao = Integer.parseInt(sc.nextLine());
			return opcao;
		} catch (Exception e) {
			System.out.println("Voce deve digitar um numero.");
			return exibeMenuDeIdentificacao();
		}

	}
	
	/**
	 * exibe menu do cliente
	 * @return
	 * @throws IOException
	 */
	public static int exibeMenuDoCliente() throws IOException {

		System.out.println("----------------------------------------------------------");
		System.out.println("Opções: ");
		System.out.println();
		System.out.println(RESERVA_ALUGUEL_CUSTOMER + " - Fazer Reserva de Aluguel");
		System.out.println(CONSULTAR_DISPONIVEIS_CUSTOMER + " - Consultar veiculos disponiveis para aluguel");
		System.out.println(REMOVER_CLIENTE_CUSTOMER + " - Remover Cliente");
		System.out.println(INTERESSE_CARRO_CUSTOMER + " - Mostrar interesse em um Carro Alugado");
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
	
	/**
	 * EXIBE DADOS DO VEICULO
	 * @return
	 */
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
	
	/**
	 * exibe dados do usuario
	 * @return
	 */
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

	
	/**
	 * menu de remocao de cliente
	 * @return
	 */
	public static String menuRemocaoCliente() {
		System.out.println("Email do usuario a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}

	/**
	 * menu de remocao de funcionario
	 * @return
	 */public static String menuRemocaoDoFuncionario() {
		System.out.println("Email ou login do funcionario a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}

		/**
		 * menu de remocao de veiculo
		 * @return
		 */
	public static String menuRemocaoDeVeiculo() {
		System.out.println("Placa do veiculo a ser removido: ");
		System.out.println("------------------------------------------------------");
		return leDados();
	}
	
	/**
	 * menu de insucesso
	 * @return
	 */
	public static void menuInsucesso() {
		System.out.println("Login sem sucesso. Usuario nao existe ou algum campo esta invalido.");
		System.out.println("Tente novamente.");
		System.out.println("------------------------------------------------------");
		
	}

	/**
	 * le dados
	 * @return
	 */
	public static String leDados(){
		String data = sc.nextLine();
		return data;
	}
	/**
	 * dados do aluguel
	 * @return
	 */
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
	/**
	 * numero de alugueis
	 * @param valor
	 */
	public static void numeroDeAlugueis(int valor){
		System.out.println("Alugueis Registrados no momento:");
		System.out.println("---------------------------------");
		System.out.println(valor);
	}
	/**
	 * retorna a situacao do veiculo
	 * @return
	 */
	public static String getVehicleSituation(){
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		return placa;
	}
	
	/**
	 * alugueis vigentes
	 * @param valor
	 */
	public static void alugueisVigentes(int valor){
		System.out.println("Alugueis Vigentes:");
		System.out.println("---------------------------------");
		System.out.println(valor);
	}
	
	/**
	 * registra aluguel atrasado
	 * @return
	 */
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
	
	/**
	 * adiciona varios alugueis
	 * @return
	 */
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
	
	/**
	 * libera veiculo
	 * @return
	 */
	public static String releaseVehicle() {
		System.out.println("Digite a placa do veiculo que deseja remover(ex: aaa0000): ");
		String plate = sc.nextLine();
		return plate;
	}
	/**
	 * imprime lista de requisicoes
	 * @param printRequestList
	 */
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
	
	/**
	 * 
	 * @param listAllPendingRents
	 */
	public static void listAllPendingRents(String listAllPendingRents) {
		System.out.println(listAllPendingRents);
		
	}
	
	/**
	 * lista todas os alugueis nao pendentes
	 * @param listAllNonPendingRents
	 */
	public static void listAllNonPendingRents(String listAllNonPendingRents) {
		System.out.println(listAllNonPendingRents);
	}
	
	/**
	 * lista todas a situacoes dos veiculos
	 * @param impressao
	 */
	public static void listAllVehiclesSituation(String impressao){
		System.out.println("=========================================");
		System.out.println(impressao);
	}
	
	/**
	 * dados do cliente
	 * @return
	 */
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
	
	/**
	 * exibe situacao
	 * @param situacao
	 * @param placa
	 */
	public static void showSituation(String situacao, String placa) {
		System.out.println("--------------------------------------------------");
		if (situacao.equalsIgnoreCase("unavailable")){
			System.out.println("O veiculo de placa " + placa + " esta atualmente alugado.");
		}
		else if (situacao.equalsIgnoreCase("available")){
			System.out.println("O veiculo de placa " + placa + " esta atualmente disponivel.");
		}
		else {
			System.out.println(situacao);
		}
		
	}
	
	/**
	 * imprime erro
	 * @param message
	 */
	public static void printError(String message) {
		System.out.println(message);
	}
	
	/**
	 * menu de reserva de veiculo
	 * @return
	 */
	public static String[] menuReservarVeiculo() {
		System.out.println("Digite o seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite a placa do veiculo (ex: aaa0000): ");
		String placa = sc.nextLine();
		return new String[] {email, placa};
	}
	
}
