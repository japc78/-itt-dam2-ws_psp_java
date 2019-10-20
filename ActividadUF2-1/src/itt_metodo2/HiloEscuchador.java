package itt_metodo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.TreeMap;

import itt_comun.Producto;

public class HiloEscuchador implements Runnable {
	// Para identificar a cada cliente
	private static int numCliente = 0;

	// Para la entrada y salida de datos de la conexion.
	PrintStream salida = null;
	InputStreamReader entrada = null;

	// Para la conexion con el cliente
	private Socket conexionACliente;

	// Para acceder a la lista de productos.
	TreeMap <String, Producto>  productos;

	// Constructor del hilo, que se le pasa por parametros la conexcion y un Mapa del tipo TreeMap con los datos de los procutos.
	// Asignara a cada hilo un nombre Cliente+NumeroCliente.
	public HiloEscuchador(Socket cliente, TreeMap<String, Producto> productos) {
		numCliente++;
		new Thread(this, "Cliente" + numCliente).start();
		this.conexionACliente = cliente;
		this.productos = productos;
	}

	@Override
	public void run() {
		// Se muestra informacion de la conexion y el cliente.
		System.out.println("Estableciendo comunicacion con " + Thread.currentThread().getName());
		try {
			// InputStreamReader, Lector de flujo de entrada, se le asgina el flujo de entrada de la conexion (stream) que se ha creado con Sockets -> conexionACliente con el metodo getInputStream(). Tengo la entrada de datos del servidor procedente del cliente.
			entrada = new InputStreamReader(conexionACliente.getInputStream());
			String texto = "";
			while (!texto.equals("FIN")) {
				// BufferedReader -> Nos ayuda a leer datos del cliente. Se Instancia bf de la clase BufferedReader pasandole como argumentos el flujo de datros que procede del cliente, bf se apoya en el objeto de entrada. readline() -> se queda parado el hilo principal de ejecucion hasta que reciba los datos del cliente.
				BufferedReader bf = new BufferedReader(entrada);
				texto = bf.readLine();

				// PrintStream -> Imprime el flujo de salida, se le asigna el flujo de salida de la conexion (stream) que se ha creado con Sockets -> conexionACliente con el metodo  getOutputStream(). Tengo la salida de datos del servidor hacia el cliente.
				salida = new PrintStream(conexionACliente.getOutputStream());

				// Logica del programa.
				// Si el cliente introduce FIN, termina la conexion con el servidor.
				if (texto.equals("FIN")) {
					salida.println("Hasta pronto, gracias por establecer conexion");
					System.out.println(Thread.currentThread().getName() + " ha cerrado la comunicacion");

				// STUDY Treemap. Comprobacion y lectura de datos.
				// Si el cliente introduce un codigo que se encuentre en el TreeMap de produtos.
				// TreeMap.containstKey(key) -> te devuleve true si la key se encuentra en el treemap.
				} else if (productos.containsKey(texto)) {
					System.out.println(Thread.currentThread().getName() + " dice: " + texto);
					// TreeMap.get(key) te devuelve valor asignado a dicha clave, key.
					System.out.println("Se le envia la informacion " + productos.get(texto).toString());

					// Se envia al cliente el valor de la key que contiene la informacion del producto.
					salida.println((productos.get(texto).toString()));

				// Si el cliente introduce un codigo que no se encuentra en productos. Se le informa y se le solicita que lo introuzca de nuevo.
				} else {
					System.out.println("El codigo : " + texto + " introducido por el " + Thread.currentThread().getName() + " no exite.");
					salida.println("Lo sentimos, el codigo introducido: " + texto + " no exite, recuerda que el programa es Case sensitivity. Por favor intentelo de nuevo");
				}
			}

			// Al finalizar cerramos la salida, entrada y la conexion.
			salida.close();
			entrada.close();
			conexionACliente.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}