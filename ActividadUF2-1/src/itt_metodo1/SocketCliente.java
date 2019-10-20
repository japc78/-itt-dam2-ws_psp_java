package itt_metodo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {

	public static void main(String[] args) {
		System.out.println("------- APLICACION CLIENTE --------");
		System.out.println("-----------------------------------");

		try {
			// STUDY InetSocketAddress. Metodo de conexion Cliente/Servidor.
			// Se crea la conexion con el servidor.
			Socket conexionAServidor = new Socket();
			// Se crea un objeto de InetSocketsAddress donde se le pasa la Ip y el puerto por donde se realiza la conexion.
			InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2202);
			System.out.println("Esperando a que el servidor acepte la conexion");

			// Se establece la conexion con el servidor especificado por el objeto InetSocketAdreess.
			conexionAServidor.connect(direccionServidor);
			System.out.println("Comunicacion establecida con el servidor");

			// Se crea la entrada y salida de datos del cliente.
			// InputStream, Representa un strem de bytes de entrada, se le asgina el flujo de entrada de la conexion (stream) que se ha creado con Sockets -> conexionAServidor con el metodo getInputStream(). Tengo la entrada de datos del cliente procedente del Servidor.
			InputStream entrada = conexionAServidor.getInputStream();

			// OutputStreamm -> Representa un strem de bytes de salida. Se le asigna el flujo de salida de la conexion (stream) que se ha creado con Sockets -> conexionAServidor con el metodo  getOutputStream(). Tengo la salida de datos del cliente hacia el servidor.
			OutputStream salida = conexionAServidor.getOutputStream();

			// Se crear una instacia de Scanner para recoger los datos introducidos por el usuario (cliente).
			Scanner lector = new Scanner(System.in);
			String texto = "";
			while (!texto.equals("FIN")) {
				System.out.println("Escribe un codigo de articulo (FIN para terminar): ");

				// Recojo codigo introducido por el cliente en la variable texto.
				texto = lector.nextLine();

				// Se envia el texto al servidor mediante salida y write en formato de bytes, ya que esta se realiza mediante OutputStreamm.
				salida.write(texto.getBytes());

				// Se crea un obteto de la clase byte con una longitud de 200 para recoger el mensaje que se recibe del servidor.
				byte[] mensaje = new byte[200];
				System.out.println("Esperando respuesta ...... ");

				// Se recoge la respuesta del servidor y se muestra por consola.
				entrada.read(mensaje);
				System.out.println("Servidor responde: " + new String(mensaje).trim());
			}

			// Una vez finalizado se cierra las conexiones realizadas.
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