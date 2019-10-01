package itt;

import java.time.LocalDateTime;

public class Examinador implements Runnable {
	private Thread hilo;
	BufferExamenes buffer;

	public Thread getHilo() {
		return hilo;
	}

	public Examinador(String alumno, BufferExamenes generador) {
		// Construye el hilo. El nombre será el nombre del alumno.
		hilo = new Thread(this, alumno);

		// Establece el valor de la propiedad buffer
		this.buffer = generador;

		// Inicia el hilo.
		hilo.start();
	}

	@Override
	public synchronized void run() {
		String codigoExamen = this.buffer.consumirExamen();
		if (codigoExamen != null) {
			// Simula aquí un examen de 10 preguntas
			// cuyas respuestas se seleccionarán al azar
			// entre A, B, C, D o – (sin contestar).

			// Creo un array de String con las respuestas para mostrarlas depues con Random aleatoriamente.
			String[] respuesta = { "A", "B", "C", "D", "-" };

			//System.out.println("Producido el examen " + hilo);
			for (int i = 0; i < 10; i++) {
				System.out.println(codigoExamen + ";" + hilo.getName() + ";" + "Pregunta " + (i+1) + ";" + respuesta[(int) (Math.random() * 5)] + " - " + LocalDateTime.now());
			}
		} else {
			System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");
		}
	}
}