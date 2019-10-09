package itt;
import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {
	private BufferExamenes buffer;
	private static int numeroExamen = 0;
	// private Thread hilo;  //-> Creo hilo directamente en el constructor .

	public ProductorExamenes(BufferExamenes buffer) {
		// Incrementa el contador de exámenes (variable numeroExamen).
		numeroExamen ++;

		// Se Establece el valor de la propiedad buffer que se pasa como argumento en el constuctor.
		this.buffer = buffer;
		// Se construye el hilo dentro del constructor
		new Thread(this, "E"+numeroExamen).start();
	}

	@Override
	public void run() {
		// Se pasa el año actual a una variable para generar el código del examen.
		int aa = LocalDateTime.now().getYear();

		// Generación del código de examen.
		String codigo = Thread.currentThread().getName() + "-" + aa;

		// Añade el nuevo código al buffer de exámenes.
		buffer.fabricarNuevoExamen(codigo);

		// Muestra un mensaje en consola informando sobre el
		// código del examen que se acaba de producir.
		System.out.println("Producido el examen " + codigo + " - ");
	}
}