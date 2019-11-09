package app;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * AppClient
 */
public class AppClient {
	private static String ipServer = "192.168.254.1";
	private static int port = 2200;
	public static void main(String[] args) {
		Registry registry;
		Scanner lector = new Scanner(System.in);
			try {
				registry = LocateRegistry.getRegistry(ipServer, port);
				System.out.println("Hemos obtenido el registro");
				AppInterface constelaciones = (AppInterface) registry.lookup("appConstelaciones");
				System.out.println("Hemos obtenido el objeto remoto");

				String find;
				String option;

				do {
					menu();
					option = lector.nextLine().toUpperCase();
					switch (option) {
						case "L":
							System.out.println(constelaciones.listarConstelaciones());
							break;
						case "N":
							System.out.println("Escribe el nombre de la constelación");
							find = lector.nextLine();
							System.out.println(constelaciones.buscarPorNombre(find));
							break;
						case "I":
							System.out.println("Escribe número del constelación");
							find = lector.nextLine();
							System.out.println(constelaciones.buscarPorId(Integer.parseInt(find)));
							break;
						case "F":
							System.out.println("Programa finalizado");
							break;
						default:
						System.out.println("Opción incorrecta");
					}

				} while (!option.equals("F") );
			} catch (RemoteException | NotBoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			lector.close();
	}

	public static void menu() {
		System.out.println();
		System.out.println("--- Menu constelaciones ---");
		System.out.println("L = Listar constelaciones");
		System.out.println("N = Por nombre");
		System.out.println("I = Por id");
		System.out.println("F = Terminar programa");
		System.out.println("--------------------------");
		System.out.println("¿Qué opción eliges?");
	}
}