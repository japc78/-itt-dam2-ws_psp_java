package test08_waitnotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Cola
 */
public class Cola {

	// Declaracion del maximo de elementos que tendra la cola.
	// STUDY Cuando usamos “static final” se dice que creamos una constante de
	// clase, un atributo común a todos los objetos de esa clase.
	public final static int MAX_ELEMENTOS = 3;

	// Se crea una coleccion del tipo Queue tipo String. Lista circular.
	// Primero en entrar, primero en salir.

	// STUDY Queue<E>: Colección ordenada con extracción por el principio e
	// inserción por el principio (LIFO – Last Input, First Output) o por el final
	// (FIFO – First Input, First Output). Se permiten elementos duplicados. No da
	// excepciones cuando la cola está vacía/llena, hay métodos para interrogar, que
	// devuelven null. Los métodos put()/take() se bloquean hasta que hay espacio en
	// la cola/haya elementos.
	// STUDY En un Queue, se utiliza offer para añadir un elemento a la lista y poll
	// para sacarlo.
	private Queue<String> cola = new LinkedList<String>();

	// Metodo para add un mensaje a la cola.
	public synchronized void addMensaje(String mensaje) {

		while (cola.size() == MAX_ELEMENTOS) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Add un elemento a la cola.
		cola.offer(mensaje);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
	}

	// Metodo para leer y coger un mensaje
	public synchronized String obtenerMensaje() {
		String mensaje;
		// Sacamos un elemento de la cola.
		while(cola.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mensaje = cola.poll();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
		return mensaje;
	}
}