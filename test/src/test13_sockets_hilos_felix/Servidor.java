package test13_sockets_hilos_felix;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		//Este objeto es el que abrirá un puerto
		ServerSocket servidor = null;
		Socket socketConexion = null;

		System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
		System.out.println("----------------------------------");
		try {
			// Se crea el servidor y se asigna el puerto de escucha 2202
			servidor = new ServerSocket(2202);

			// Se muestra por pantalla los mensajes de servidor listo y se muestra la ip, nombre del host y puerto de la maquina.
			// InetAddress.getLocalHost() -> Retorna la ip y host name de la maquina .
			System.out.println("SERVIDOR: listo para aceptar solicitudes");
			System.out.println("Dirección IP: " + InetAddress.getLocalHost());
			System.out.println("Puerto: " + servidor.getLocalPort());

			// Se queda el servidor en modo 'Demonio' esperando una conexcion.
			while (true) {
				System.out.println("SERVIDOR: Esperando peticion...");
				// El servidor se queda esperando a la conexion.
				socketConexion = servidor.accept();
				System.out.println("Comunicación entrante");
				// Cuando tenemos una conexion se le pasa a la clase hilosEscuchasdor la conexion del cliente y el TreeMap con los productos.
				// Se creara un hilo para esta conexion (cliente)
				new HiloConexcionCliente(socketConexion);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}