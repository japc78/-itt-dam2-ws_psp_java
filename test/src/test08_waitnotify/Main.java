package test08_waitnotify;

/**
 * Main
 */
public class Main {
	public static void main(String[] args) {
		Cola cola = new Cola();

		new Productor("Productor 1", cola);
		new Productor("Productor 2", cola);
		new Productor("Productor 3", cola);

		new Consumidor("Consumidor 1", cola);
		new Consumidor("Consumidor 2", cola);
		new Consumidor("Consumidor 3", cola);
	}
}