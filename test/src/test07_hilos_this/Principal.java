package test07_hilos_this;

/**
 * PruebaHiloThis
 */
public class Principal {
	public static void main(String[] args) {
		new HiloThis("Hola Mundo 1", "hilo1");
		new HiloThis("Hola Mundo 2", "hilo2");
	}
}