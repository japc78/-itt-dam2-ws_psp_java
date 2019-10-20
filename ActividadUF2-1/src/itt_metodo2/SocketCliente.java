package itt_metodo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {
	public static void main(String[] args) {
		//Socket es la clase que nos va a permitir comunicarnos con el servidor
		Socket conexionAServidor = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
		// Ip y puerto de conexion al servidor.
		String servidorIp	= "localhost";
		int servidorPuerto	= 2202;

		System.out.println("------- APLICACIÓN CLIENTE --------");
		System.out.println("-----------------------------------");
		try {
			System.out.println("Esperando a que el servidor acepte la conexion...");
			// STUDY Sockets. Conexion Cliente/Servidor -> Se crea un flujo o stream de datos donde viaja la informacion en ambos sentidos. Sockets solo se encarga de realizar la conexion y de crear el flujo de datos.
			// Se instancia conexionAServidor de la clase Socket pasandole como argumentos la ip del servidor y el puerto.
			conexionAServidor = new Socket(servidorIp, servidorPuerto);

			// STUDY Sockets. InputStreamReader y PrintSream -> Se encargan de la entrada y salida de datos del stream. Deben tener un flujo de entrada y salida respectivamente.
			// InputStreamReader, Lector de flujo de entrada, se le asgina el flujo de entrada de la conexion (stream) que se ha creado con Sockets -> conexcionCliente con el metodo getInputStream(). Tengo la entrada de datos del cliente procedente del Servidor.
			entrada = new InputStreamReader(conexionAServidor.getInputStream());

			// PrintStream -> Imprime el flujo de salida, se le asigna el flujo de salida de la conexion (stream) que se ha creado con Sockets -> conexionAServidor con el metodo  getOutputStream(). Tengo la salida de datos del cliente hacia el servidor.
			salida = new PrintStream(conexionAServidor.getOutputStream());

			System.out.println("Comunicacion establecida con el servidor.");

			// Se crear una instacia de Scanner para recoger los datos introducidos por el usuario (cliente).
			Scanner lector = new Scanner(System.in);
			String texto = "";

			while (!texto.equals("FIN")) {
				System.out.println("Escribe un codigo de artículo (FIN para terminar): ");
				// Aqui podria ponerlo -> texto = lector.nextLine().toUpperCase() <- para pasarlo siempre a mayusculas, pero interpreto que los codigos son Case sensitivity, por eso lo dejo tal cual.

				// Recojo codigo introducido por el cliente en la variable texto.
				texto = lector.nextLine();

				// Realizo la salida de datos del servidor mediante salida y printnl.
				salida.println(texto);

				System.out.println("Esperando respuesta ...... ");

				// BufferedReader -> Nos ayuda a leer datos del servidor. Se Instancia bf de la clase BufferedReader pasandole como argumentos el flujo de datros que procede servidor, bf se apoya en el objeto de entrada. readline() -> se queda parado el hilo principal de ejecucion hasta que reciba los datos del servidor.
				BufferedReader bf = new BufferedReader(entrada);
				String respuestaServidor = bf.readLine();

				// Mostramos por consola la respuesta del servidor
				System.out.println("Servidor responde: " + new String(respuestaServidor));
			}
			entrada.close();
			salida.close();
			conexionAServidor.close();
			lector.close();
			System.out.println("Comunicacion cerrada");

		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicacion con el servidor");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		}
	}
}