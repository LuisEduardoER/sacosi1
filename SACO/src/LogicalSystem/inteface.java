package LogicalSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class inteface {

	public static ArrayList<String> addUser() {
		Scanner sc1 = new Scanner(System.in);
		ArrayList <String>  array = new ArrayList<String>();
		System.out.print("Digite seu nome: ");
		String name = sc1.nextLine();
		System.out.print("Digite seu email: ");
		String email = sc1.nextLine();
		System.out.print("Digite seu telefone: ");
		String phone = sc1.nextLine();
		System.out.print("Digite seu login: ");
		String login = sc1.nextLine();

		array.add(login);
		array.add(name);
		array.add(email);
		array.add(phone);
		return array;
	}

}
