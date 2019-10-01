/**
 * DAM2 - UTF1<br/>
 * Actividad 1. Tarea individual.
 * Lanzamiento de procesos. Triángulo numérico.
 *
 * Especificaciones:
 * 	1.- Realiza un programa que lanza tres procesos simultáneos del programa Triangulo con los parámetros 5, 7 y 9.
 * 	2.- La salida de los tres procesos debe ser enviada a los archivos triangulo5.txt, triangulo7.txt y triangulo9.txt.
 *	3.- Al principio de cada fichero de salida y antes del triángulo, debe escribirse la fecha de inicio del proceso. Para ello, tendrás que modificar la clase Triangulo.
 *	4.- Al final de cada fichero de salida, después del triángulo debes escribir la fecha de finalización del proceso. Para ello, tendrás que modificar la clase Triangulo.
 *
 *
 * Fuentes consultadas:
 *
 *  - https://es.stackoverflow.com/questions/41189/como-usar-el-m%C3%A9todo-processbuilder-de-java
 * 	- https://www.campusmvp.es/recursos/post/Como-ejecutar-otras-aplicaciones-desde-Java.aspx
 *  - https://es.stackoverflow.com/questions/96278/para-que-sirve-el-try-y-catch-en-java
 *
 *
 * @author Juan Antonio Pavón Carmona
 *
 */

package itt;

import java.io.File;
import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		// Instanciamos las los procesos con ProcessBuilder, esta clase es la que se encarga de crear procesos,
		// más especificamente se usa para pasar parámetros a las aplicaciones.
		ProcessBuilder process1, process2, process3;

		// El valor se le pasa directamente por parametro en la declaración del proceso.
		process1 = new ProcessBuilder("java", "itt/Triangulo", "5");
		process2 = new ProcessBuilder("java", "itt/Triangulo", "7");
		process3 = new ProcessBuilder("java", "itt/Triangulo", "9");

		// Se redirige los resultados, error y resultado, a los ficheros de salida con una funcion creada. Dejo las lineas comentadas, y saco una función que realize la impresión del resultado, final y error, a la cual se le pasa por argumentos un proceso y el valor para el triangulo.

		// process1.redirectError(new File("errorT2.txt"));
		// process1.redirectOutput(new File("triangulo7.txt"));

		// process2.redirectError(new File("errorT2.txt"));
		// process2.redirectOutput(new File("triangulo7.txt"));

		// process3.redirectError(new File("errorT9.txt"));
		// process3.redirectOutput(new File("triangulo9.txt"));

		// Aunque el programa es sencillo, por practicar y recordar, he creado una función que imprime los resultados.
		resultado(process1, 5);
		resultado(process2, 7);
		resultado(process3, 9);

		// Se ejecunta con try catch para captar el errror y mostrarlo en caso de fallo de la aplicación.
		try {
			// Arrancamos los procesos
			process1.start();
			process2.start();
			process3.start();

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
		process.redirectError(new File("triangulo" + valor + "_error.txt"));
		process.redirectOutput(new File("triangulo" + valor + ".txt"));
	}
}