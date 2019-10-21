import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {
	public static void main(String[] args) throws IOException {
		//Socket es la clase que nos va a permitir comunicarnos con el servidor
		Socket conexionCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
		Scanner lector = null;
		String texto;

		// Se averigua la Ip publica del cliente.
		URL ipPublicaCliente = new URL("http://checkip.amazonaws.com");

		// BufferedReader -> Nos ayuda a leer los datos de la url. openStream() Abre una conexión a esta URL y devuelve un InputStream para leer desde esa conexión.
		BufferedReader urlBf = new BufferedReader(new InputStreamReader(ipPublicaCliente.openStream()));
		String ip = urlBf.readLine(); // Se pasa la IP como un String

		// Ip y puerto de conexion al servidor.
		String servidorIp	= "ittdam.sytes.net";
		int servidorPuerto	= 2202;

		System.out.println("------ APLICACIÓN CLIENTE ---------");
		System.out.println("-----------------------------------");

		try {
			// Identificacion del cliente
			lector = new Scanner(System.in);
			System.out.println("Por favor, identifiquese antes de realizar la comunicacion");
			texto = lector.nextLine();

			// Se realiza la conexion y el tubo de comunicacion.
			conexionCliente = new Socket(servidorIp, servidorPuerto);
			entrada = new InputStreamReader(conexionCliente.getInputStream());
			salida = new PrintStream(conexionCliente.getOutputStream());

			// Se envia la identificacin.
			salida.println(ip + ";" + texto);

			// Se pide al usuario que introduzca los datos.
			System.out.println("Esperando a que el servidor acepte la conexión");
			System.out.println("Comunicación establecida con el servidor");

			while (!texto.equals("FIN")) {
				System.out.println("Escribe mensaje (FIN para terminar): ");
				texto = lector.nextLine();
				salida.println(texto);
				System.out.println("Esperando respuesta ...... ");

				// BufferedReader -> nos ayuda a leer datos del servidor linea a linea. bf.readLine() -> El hilo de ejecucion se queda parado hasta que el servidor el servidor responda.
				BufferedReader bf = new BufferedReader(entrada);
				String respuestaServidor = bf.readLine();
				System.out.println("Servidor responde: " + new String(respuestaServidor));
			}
		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicación con el servidor");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		} finally {
			try {
				entrada.close();
				salida.close();
				lector.close();
				System.out.println("Comunicación cerrada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}