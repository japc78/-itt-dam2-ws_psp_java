package test08_waitnotify;

import java.time.LocalDateTime;

/**
 * Productor
 */
public class Productor implements Runnable{
	private String nombre = new String();
	private Cola cola = new Cola();

	// Constructor
	public Productor(String nombre, Cola cola) {
		this.nombre = nombre;
		this.cola = cola;
		new Thread(this).start();
	}

	@Override
	public void run() {
		// Se crear un bucle en el que el constructor irá añadiendo mensajes a la cola
		for (int i = 0; i < 5; i++) {


			String mensaje = String.format("P: %s - %d", nombre, i);
			//String mensaje = nombre + " - " + i;
			cola.addMensaje(mensaje);
			System.out.println((nombre + " - " + "añade el producto " + i + " - " + LocalDateTime.now()));
		}
	}
}