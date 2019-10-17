package test10_sockets_datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

//Este programa se dedica exclusivamente a enviar mensajes, aunque un sólo programa podría enviar y recibir. Enviará los mensajes que escriba el usuario por teclado a la dirección IP, en este caso localhost o 127.0.0.1 y al puerto 5000. El programa terminará cuando el usuario introduzca el texto FIN.


public class EmisorMensajes {
	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		try {
			// Creamos el socket datagram como un objeto de la clase DatagramSocket.
			DatagramSocket ds = new DatagramSocket();
			// La clase InetAddress representa una dirección IP.
			InetAddress destino = InetAddress.getByName("127.0.0.1");
			String mensaje = "";
			while (!mensaje.equals("FIN")) {
				System.out.println("Escribe un mensaje: ");
				mensaje = lector.nextLine();
				int lon = mensaje.length();
				// Podríamos decir que esto equivale a enviar una carta. La clase DatagramPacket representa un paquete a enviar. Construimos el objeto carta con los siguientes argumentos: el mensaje a enviar, la longitud del mensaje, el destino (objeto InetAddress), el puerto).
				DatagramPacket carta = new
				DatagramPacket(mensaje.getBytes(), lon, destino, 5000);
				// Con el método send() de la clase DatagramSocket enviamos el paquete (objeto DatagramPacket) especificado en el argumento.
				ds.send(carta);
				System.out.println("Enviado");
			}
			ds.close();
			lector.close();
			System.out.println("Socket Datagram cerrado");
		} catch (SocketException | UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}