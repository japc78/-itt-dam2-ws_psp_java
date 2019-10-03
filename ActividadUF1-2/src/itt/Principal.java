package itt;
/**
 * DAM2 - UTF1<br/>
 * Actividad 2. Tarea individual.
 * Lanzamiento de procesos. Triángulo numérico.
 *
 * Especificaciones:
 * 	1.- Para cada alumno que va a examinarse se debe imprimir un examen, que tendrá un código diferenciado. La clase BufferExamenes mantiene una cola (objeto LinkedList) de códigos de examen. Para cada alumno se extrae un examen de la cola.
 * 	2.- La clase ProductorExamenes se encargará de generar exámenes, asignándole a cada uno un código que estará formado por la letra E seguida del número de examen, un guión y el año, por ejemplo: “E2-2018 (segundo examen del año 2018).�? El número de examen se establece a partir de la variable estática numeroExamen.
 *	3.- La clase Examinador se lanza cada vez que un alumno va a examinarse, simulando la realización del examen por parte del alumno. El examen consta de 10 preguntas, cuyas respuestas se han seleccionado al azar en los valores A, B, C, D o guión (sin responder). Puesto que varios alumnos se examinan simultáneamente, podrán entremezclarse líneas de la respuesta del examen de un alumno con las del examen de otro. Pero las respuestas del examen de un mismo alumno siempre tendrán el mismo código.
 *
 * Fuentes consultadas:
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