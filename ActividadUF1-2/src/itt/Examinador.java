package itt;

public class Examinador implements Runnable {
	private BufferExamenes buffer;

	public Examinador(String alumno, BufferExamenes generador) {
		// Se Establece el valor de la propiedad buffer que se pasa como argumento en el constuctor.
		this.buffer = generador;

		// Se construye el hilo dentro del constructor
		new Thread(this, alumno).start();
	}

	@Override
	public synchronized void run() {
		String codigoExamen = buffer.consumirExamen();
		if (codigoExamen != null) {
			// Creo un array de String con las respuestas para mostrarlas depues con Random aleatoriamente.
			String[] respuesta = { "A", "B", "C", "D", "-" };

			// Con el for creo las 10 preguntas del examen del alumno y su respueta con Math.Randon que da un valor aleatorio del array de respuesta anteriormente instanciando.
			for (int i = 0; i < 10; i++) {
				System.out.println(codigoExamen + ";" + Thread.currentThread().getName() + ";" + "Pregunta " + (i+1) + ";" + respuesta[(int) (Math.random() * 5)]);
			}
		} else {
			System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");
		}
	}
}