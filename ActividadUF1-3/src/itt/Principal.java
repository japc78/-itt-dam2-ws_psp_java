package itt;
/**
 * DAM2 - UTF1<br/>
 * Actividad 2. Tarea individual.
 * Lanzamiento de procesos. Triángulo numérico.
 *
 * Enunciado: Esta actividad constituye una ampliación de la segunda actividad. Se lanzarán dos procesos simultáneos del programa desarrollado en la actividad anterior “AI-2: Multitarea. Centro de exámenes”, con el objeto de poder realizar dos exámenes diferentes, cada uno a un grupo de alumnos distinto.
 *
 * Especificaciones:
 * 	1.- Realiza una copia del proyecto ActividadUF1-2 como ActividadUF1-3.
 * 	2.- Realiza todos los cambios que te indicamos a continuación en el proyecto ActividadUF1-3.
 *	3.- Modifica la clase Principal para examinar a tantos alumnos como argumentos se pasen en la línea de comandos. Los argumentos llevarán los nombres de las personas que se examinarán.
 *	4.- Crea un programa multiproceso, llamado lanzador, que lanzará dos exámenes distintos:
 *		- El primer examen será para los alumnos Pepe, Juan y Luis.
 *		- El segundo examen será para los alumnos Rosa, Miguel y Pedro.
 *
 *
 * @author Juan Antonio Pavón Carmona
 *
 */

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		BufferExamenes generador = new BufferExamenes();
		new ProductorExamenes(generador);
		new Examinador("Rosa", generador);
		new ProductorExamenes(generador);
		new Examinador("Miguel", generador);
		new ProductorExamenes(generador);
		new Examinador("Carlos", generador);
	}
}