package test10_sockets_datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// Este programa se dedica exclusivamente a recibir mensajes, algo así como un buzón de correo.. Recibirá mensajes desde la dirección IP = 192.168.56.1 y al puerto 5000. El programa terminará cuando se reciba el mensaje FIN

public class ReceptorMensajes {
	public static void main(String[] args) {
		try {
			// Creamos un objeto DatagramSocket capaz de recibir mensajes de la dirección IP = 127.0.0.1 y el puerto 5000
			InetSocketAddress direccion = new InetSocketAddress("127.0.0.1", 5000);
			DatagramSocket ds = new DatagramSocket(direccion);
			System.out.println("Preparado para recibir");
			String texto = "";
			while (!texto.equals("FIN")) {
				//  Creamos un paquete vacío con una longitud de 100 bytes y lo llenamos en la ejecución del método receive() con un paquete recibido a partir del primer programa que hemos creado.
				byte[] mensaje = new byte[100];
				DatagramPacket carta = new DatagramPacket(mensaje, 100);
				// El método receive() hará una pausa que terminará con la recepción de algún paquete o mensaje
				ds.receive(carta);
				texto = new String(mensaje).trim();
				System.out.println("RECIBIDO: " + texto);
			}
			ds.close();
			System.out.println("Socket Datagram cerrado");
		} catch (SocketException | UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}