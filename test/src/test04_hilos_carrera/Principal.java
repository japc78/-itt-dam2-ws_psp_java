package test04_hilos_carrera;

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		new Corredor("Corredor1",Thread.MAX_PRIORITY);
		new Corredor("Corredor2",Thread.MIN_PRIORITY);
		new Corredor("Corredor3",Thread.MAX_PRIORITY);
	}
}