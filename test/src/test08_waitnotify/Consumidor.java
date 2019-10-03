package test08_waitnotify;

import java.time.LocalDateTime;

/**
 * Cosumidor
 */
public class Consumidor implements Runnable {
	private String nombre;
	private Cola cola;

	public Consumidor(String nombre, Cola cola) {
		super();
		this.nombre = nombre;
		this.cola = cola;
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			String mensaje = cola.obtenerMensaje();
			System.out.println(nombre + " ha consumido: " + mensaje + " - " + LocalDateTime.now());
		}
	}
}