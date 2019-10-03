package try_01_numeros_primos;

/**
 * Nprimos
 */
public class NumerosPrimos implements Runnable {
	private Thread hilo;

	public Nprimos (int n, int prioridad)  {
		hilo = new Thread(this, nombre);
		hilo.setPriority(prioridad);
		hilo.start();
	}



	@Override
	public void run() {
		// Auto-generated method stub

	}

}