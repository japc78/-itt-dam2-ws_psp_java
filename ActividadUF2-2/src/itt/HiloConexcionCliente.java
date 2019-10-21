package itt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloConexcionCliente implements Runnable {
	private static int numCliente = 0;
	private Socket conexionACliente;
	PrintStream salida = null;
	InputStreamReader entrada = null;

	// Metodo constructor que creara hilos por conexiones de clientes estasblecidas, asignandoles un nombre y numero de manera incremental.
	public HiloConexcionCliente(Socket cliente) {
		numCliente++;
		new Thread(this, "Cliente" + numCliente).start();
		this.conexionACliente = cliente;
	}


	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + Thread.currentThread().getName());
		try {
			// InputStreamReader, Lector de flujo de entrada, se le asgina el flujo de entrada de la conexion (stream) que se ha creado con Sockets -> conexionACliente con el metodo getInputStream(). Tengo la entrada de datos del servidor procedente del cliente.
			entrada = new InputStreamReader(conexionACliente.getInputStream());

			// El hilo princial se queda parado hasta que se recibe los datos del cliente. En este caso la primera lectura, se recoge el Nombre del cliente y la IP.
			BufferedReader bf = new BufferedReader(entrada);
			// Se le realiza un split a los datos recibidos para separar la Ip y el nombre.
			String[] infoCliente = bf.readLine().split(";");

			// Se muestra un mensaje por pantalla con el nombre del cliente y su direccion IP de conexion.
			System.out.println("Cliente con Nombre:" + infoCliente[1] + " IP: "+ infoCliente[0] + ", contectado.");

			// Cambiamos el nombre del hilo principal con .setName(String name).
			Thread.currentThread().setName(infoCliente[0] + "_" + infoCliente[1]);

			// Logica del programa. El cliente envia mensajes y el servidor le responde con el numero total de caracteres que tiene el mensaje.
			String texto = "";
			while (!texto.equals("FIN")) {
				texto = bf.readLine();
				salida = new PrintStream(conexionACliente.getOutputStream());
				if (texto.equals("FIN")) {
					salida.println("Hasta pronto, gracias por establecer conexión");
					System.out.println(Thread.currentThread().getName() + " ha cerrado la comunicación");
				} else {
					System.out.println(Thread.currentThread().getName() + " dice: " + texto);
					salida.println(("Tu mensaje tiene " + texto.length() + " caracteres"));
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(salida != null && entrada != null){
					salida.close();
					entrada.close();
					conexionACliente.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}