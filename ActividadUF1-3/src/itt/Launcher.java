package itt;

/**
 * Lanzador
 */
import java.io.File;
import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		// Instanciamos las los procesos con ProcessBuilder, esta clase es la que se encarga de crear procesos,
		// más especificamente se usa para pasar parámetros a las aplicaciones.
		ProcessBuilder process1, process2;

		// El valor se le pasa directamente por parametro en la declaración del proceso.
		process1 = new ProcessBuilder("java", "itt/Principal", "Pepe", "Juan", "Luis");
		process2 = new ProcessBuilder("java", "itt/Principal", "Rosa", "Miguel", "Pedro");

		// Utilo función que imprime los resultados.
		resultado(process1, 1);
		resultado(process2, 2);

		// Se ejecunta con try catch para captar el errror y mostrarlo en caso de fallo de la aplicación.
		try {
			// Arrancamos los procesos
			process1.start();
			process2.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Función que muestra los resultados, final y error, en ficheros .txt para cada proceso con un valor de triangulo.
	 *
	 * @param process Proceso de la clase ProcessBuilder
	 * @param valor Valor del triángulo.
	 *
	 */
	private static void resultado(ProcessBuilder process, int valor) {
		process.redirectError(new File("Examen" + valor + "_error.txt"));
		process.redirectOutput(new File("Examen" + valor + ".txt"));
	}
}