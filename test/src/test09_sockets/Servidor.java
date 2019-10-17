package test09_sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		System.out.println(" APLICACIÓN DE SERVIDOR ");
		System.out.println("----------------------------------");
		try {
			//STUDY Sockects. Creacion del objeto ServerSocket y establecimeinto de la IP y puerto del servidor
			ServerSocket servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress("localhost", 2000); // Se indica localhost porque el servidor tb se correra en la misma maquina.
			servidor.bind(direccion);

			System.out.println("Servidor creado y escuchando .... ");
			System.out.println("Dirección IP: " + direccion.getAddress());

			// Metodo accept() de la Clase ServerSocket() detiene la ejecucion del proceso hasta que un cliente solicite una conexion.  En el momento en que un cliente solicita comunicación con el servidor, el servidor recogerá el socket cliente en el objeto que hemos llamado enchufeAlCliente, aceptará la conexión y continuará el proceso mostrando de ese modo en pantalla el mensaje "Comunicación entrante".
			Socket enchufeAlCliente = servidor.accept();
			System.out.println("Comunicación entrante");

			// El socket cliente entrante, cuya referencia hemos llamado enchufeAlCliente, es el encargado de devolver los flujos de datos de entrada y salida mediante la llamada a los método getInputStream() y getOutputStream().
			InputStream entrada = enchufeAlCliente.getInputStream();
			OutputStream salida = enchufeAlCliente.getOutputStream();

			// El servidor irá leyendo los mensajes enviados por el cliente mientras el mensaje leído no contenga el texto "FIN", condición que establecemos en la estructura while.

			String texto = "";
			while (!texto.trim().equals("FIN")) {
				byte[] mensaje = new byte[100];
				// El método read() lee los bytes almacenados en el buffer de entrada y los guarda en el array de bytes que se pasa como argumento. El array de bytes mensaje finalmente contendrá los códigos numéricos de los caracteres qué componen en mensaje enviado por el cliente. Finalmente el array de bytes es convertido a texto en la expresión new String(mensaje).
				entrada.read(mensaje);

				// La variable texto proviene de la conversión de un array de 100 bytes a String. Aunque el mensaje recibido por el cliente ocupe sólo 10 bytes, texto tendrá una longitud de 100 caracteres, los que no se utilicen serán espacios en blanco. Por este motivo, se aplica el método trim() para recortar los espacios en blanco de la cadena.
				texto = new String(mensaje);

				if (texto.trim().equals("FIN")) {
					salida.write("Hasta pronto, gracias por establecer conexión ".getBytes());
					}
					else {
						System.out.println("Cliente dice: " + texto);
						salida.write(("Tu mensaje tiene " +
							texto.trim().length() + " caracteres").getBytes());
					}
				}

				// Cierre de los flujos de datos y los sockets. Una vez que el cliente ha cerrado la comunicación, el proceso servidor también se cierra los flujos de datos y los sockets
				entrada.close();
				salida.close();
				enchufeAlCliente.close();
				servidor.close();
				System.out.println("Comunicación cerrada");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}