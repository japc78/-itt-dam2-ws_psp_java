package itt_metodo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
	// Para identificar a cada cliente
	private static int numCliente = 0;

	// Para la conexion con el cliente
	private Socket conexionACliente;

	// Constructor del hilo, que se le pasa por parametros la conexcion y un Mapa del tipo TreeMap con los datos de los procutos.
	// Asignara a cada hilo un nombre Cliente+NumeroCliente.
	public HiloEscuchador(Socket cliente) {
		numCliente++;
		new Thread(this, "Cliente" + numCliente).start();
		this.conexionACliente = cliente;
	}

	@Override
	public void run() {
		// Se muestra informacion de la conexion y el cliente.
		System.out.println("Estableciendo comunicacion con " + Thread.currentThread().getName());
		try {
			// InputStreamReader, Lector de flujo de entrada, se le asgina el flujo de entrada de la conexion (stream) que se ha creado con Sockets -> conexionACliente con el metodo getInputStream(). Tengo la entrada de datos del servidor procedente del cliente.
			InputStream entrada = conexionACliente.getInputStream();

			// OutputStreamm -> Representa un strem de bytes de salida. Se le asigna el flujo de salida de la conexion (stream) que se ha creado con Sockets -> conexionACliente con el metodo  getOutputStream(). Tengo la salida de datos del servidor hacia el cliente.
			OutputStream salida = conexionACliente.getOutputStream();
			String texto = "";

			// Logica del programa.
			// Si el cliente introduce FIN, termina la conexion con el servidor.
			while (!texto.equals("FIN")) {
				byte[] mensaje = new byte[500];
				entrada.read(mensaje);
				texto = new String(mensaje).trim();
				if (texto.equals("FIN")) {
					salida.write("Hasta pronto, gracias por establecer conexion".getBytes());
					System.out.println(Thread.currentThread().getName() + " ha cerrado la comunicacion");

				// Si el cliente introduce un codigo que se encuentre en el TreeMap de produtos.
				// TreeMap.containstKey(key) -> te devuleve true si la key se encuentra en el treemap.
				} else if (Servidor.getProductos().containsKey(texto)) {
					System.out.println(Thread.currentThread().getName() + " dice: " + texto);
					System.out.println("Se le envia la informacion " + Servidor.getProductos().get(texto).toString());

					// Se envia al cliente el valor de la key que contiene la informacion del producto.
					salida.write((Servidor.getProductos().get(texto).toString()).getBytes());
				// Si el cliente introduce un codigo que no se encuentra en productos. Se le informa y se le solicita que lo introuzca de nuevo.
				} else {
					System.out.println("El codigo: " + texto + " introducido por el " + Thread.currentThread().getName() + " no exite.");
					salida.write(("Lo sentimos, el codigo introducido: " + texto + " no exite, recuerda que el programa es Case sensitivity. Por favor intentelo de nuevo.").trim().getBytes());
				}
			}

			// Al finalizar cerramos la salida, entrada y la conexion.
			entrada.close();
			salida.close();
			conexionACliente.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}