package test09_sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {
	public static void main(String[] args) {
		System.out.println(" APLICACIÓN CLIENTE");
		System.out.println("-----------------------------------");
		// Creamos un objeto lector para permitir al usuario introducir por teclado los mensajes que serán enviados al servidor.
		Scanner lector = new Scanner(System.in);

		try {
			// Crear el socket cliente y establecer comunicación con el servidor.
			// La clase Socket situada en el paquete java.net representa un socket o enchufe cliente cuya misión será solicitar conexión con el servidor con el fin de consumir los servicios que este provee.
			Socket cliente = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2000); // Se indica localhost porque el servidor tb se correra en la misma maquina.

			System.out.println("Esperando a que el servidor acepte la conexión ");
			// El método connect() intentará conectar con el servidor especificado por el objeto InetSocketAddress del argumento.
			cliente.connect(direccionServidor); System.out.println("Comunicación establecida con el	servidor ");

			// Igual que en caso del servidor, el cliente necesita sus correspondientes flujos de entrada y salida de datos para establecer la comunicación.
			InputStream entrada = cliente.getInputStream();
			OutputStream salida = cliente.getOutputStream(); String texto = "";
			// Comunicándose con el servidor
			// El usuario tendrá que introducir por teclado los mensajes que serán enviados al servidor. La entrada por teclado se efectúa por medio del método lector.nextLine().
			while (!texto.equals("FIN")) {
				System.out.println("Escribe mensaje (FIN para terminar):");
					texto = lector.nextLine();
					// El método write() envía un array de bytes, no un String, por ese motivo ejecutamos el método getBytes() sobre el texto para obtener un array con los códigos numéricos de los caracteres del objeto String texto.
					salida.write(texto.getBytes());
					byte[] mensaje = new byte[100];
					entrada.read(mensaje);
					System.out.println("Servidor responde: " + new String(mensaje));
				}
				// Cierre de los flujos de datos y el socket cliente
				entrada.close();
				salida.close();
				cliente.close();
				lector.close();
				System.out.println("Comunicación cerrada");

		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicación con el servidor ");
				System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		}
	}
}