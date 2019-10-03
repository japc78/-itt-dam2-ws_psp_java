package test07_hilos_this;

/**
 * HiloThis
 */
public class HiloThis implements Runnable{
	private String valorImprimir;
	// private String nombreHilo;

	// Constructor que le pasamos dos parametros y crea un nuevo hilo del objeto.

	// STUDY Como crear un hilo con this desde el constructor.
	public HiloThis(String valorImprimir, String nombreHilo) {
		this.valorImprimir = valorImprimir;

		// Como HiloThis implementa Runnable, podemos crear en el constructo el hilo haciendo referencia al objeto con this y pasandole el nombre que tendrá el hilo.
		new Thread(this, nombreHilo).start();

		// Otra forma menos optimizada.
		/* 		Thread t1 = new Thread(this); // Se instancia el hilo
				t1.setName(nombreHilo); // Le pasamos el nombre del hilo
				t1.start(); // lo iniciamos
		 */
	}

	// Seria como un "main"
	@Override
	public void run() {
		System.out.println("Arrancando hilo: " + Thread.currentThread().getName());
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);// 0.5 segundos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out
					.println("Hilo: " + Thread.currentThread().getName() + " , dice: " + valorImprimir + " ciclo: " + i);
		}
		System.out.println("Hilo: " + Thread.currentThread().getName() + " acabado");
	}
}