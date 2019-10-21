package test13_sockets_hilos_felix;

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
		Socket conexionCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
		Scanner lector = null;

		// Ip y puerto de conexion al servidor.
		String servidorIp	= "127.0.0.1";
		int servidorPuerto	= 2202;

		System.out.println(" APLICACIÓN CLIENTE");
		System.out.println("-----------------------------------");
		try {
			// Se realiza la conexion y el tubo de comunicacion.
			conexionCliente = new Socket(servidorIp, servidorPuerto);
			entrada = new InputStreamReader(conexionCliente.getInputStream());
			salida = new PrintStream(conexionCliente.getOutputStream());

			// Se pide al usuario que introduzca los datos.
			lector = new Scanner(System.in);
			System.out.println("Esperando a que el servidor acepte la conexión");
			System.out.println("Comunicación establecida con el servidor");
			String texto = "";
			while (!texto.equals("FIN")) {
				System.out.println("Escribe mensaje (FIN para terminar): ");
				texto = lector.nextLine();
				salida.println(texto);
				System.out.println("Esperando respuesta ...... ");

				// STUDY BufferedReader -> nos ayuda a leer datos del servidor linea a linea.
				BufferedReader bf = new BufferedReader(entrada);

			// STUDY bf.readLine() -> El hilo de ejecucion se queda parado hasta que el servidor el servidor responda.
				String respuestaServidor = bf.readLine();
				System.out.println("Servidor responde: " + new String(respuestaServidor));
			}
		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicación con el servidor");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		} finally {
			try {
				entrada.close();
				salida.close();
				lector.close();
				System.out.println("Comunicación cerrada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}