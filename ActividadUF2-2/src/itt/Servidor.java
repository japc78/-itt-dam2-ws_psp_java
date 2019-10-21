package itt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * DAM2 - UTF2<br/>
 * Actividad 2. Tarea de refuerzo.
 * Comunicación cliente-servidor con identificación cliente y usando IP pública.
 *
 * Enunciado: Partiendo de la última versión del sistema de comunicación cliente-servidor desarrollado durante el estudio de esta unidad realizarás las modificaciones oportunas para que cada cliente aparezca identificado con su nombre.
 *
 * Especificaciones:
 * - Para comenzar, partirás de una copia de la última versión de la aplicación cliente-servidor que desarrollaste durante el estudio de la unidad. Concretamente la que quedó desarrollada en la lección “2.3. Utilización de hilos en la programación de aplicaciones en red” apartado “Servidor multihilo”.
 * - Debes modificar el programa que hace de cliente para que éste tenga que identificarse antes de iniciar la comunicación.
 * - La identificación del cliente se efectuará como una operación de escritura más del cliente con destino al servidor.
 * - También modificaras el programa que hace de servidor para que lea el nombre del cliente y lo muestre en pantalla.
 * - Como pista, te diremos que puedes modificar el nombre inicial del hilo de ejecución para cada cliente por el nombre de dicho cliente una vez que el servidor lo haya leído.
 * - Averigua cuál es tu IP pública. Recuerda que basta con que abras tu navegador y realices una búsqueda con el texto “mi IP pública” para que encuentres rápidamente un sitio web que te la dirá. El objetivo final es que tú servidor sirva para que se pueda establecer comunicación con él fuera de la red local de tu casa.
 * - Cambia en el programa servidor la IP privada por la IP pública. De entrada el programa no funcionará por culpa del puerto que utilizas para establecer la comunicación. Para que tu servidor sea accesible desde el exterior, tendrás que abrir el puerto que vayas a utilizar dentro de tu router y esto se hace de manera diferente según tu proveedor de telefonía.
 *
 *
 * @author Juan Antonio Pavón Carmona
 * @version Metodo 2. Con Socket y BufferedReader.
 *
 */

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