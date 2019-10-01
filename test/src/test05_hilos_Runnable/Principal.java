package test05_hilos_Runnable;

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		Corredor c1 = new Corredor("Corredor1",Thread.MIN_PRIORITY);
		c1.getHilo().join();
		new Corredor("Corredor2",Thread.MIN_PRIORITY);
		new Corredor("Corredor3",Thread.MAX_PRIORITY);
	}
}