package itt;

import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
	private Queue<String> colaExamenes;

	public BufferExamenes() {
		colaExamenes = new LinkedList<String>();
	}

	public synchronized void fabricarNuevoExamen(String codigo) {
		// Añade a colaExamenes el codigo dado por argumento.
		// El proceso notifica cuando libere el proceso.
		colaExamenes.add(codigo);
		notify();
	}

	public synchronized String consumirExamen() {
		// Mientras la cola este vacia, no contenga examenes el hilo se espera
		while (colaExamenes.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Continua el hilo cuando hay examen, lo extrae de la cola de examenes, notifica que  y lo retorna la funcion.
		String examen = colaExamenes.poll();
		notify();
		return examen;
	}
}