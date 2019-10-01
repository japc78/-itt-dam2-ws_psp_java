package test06_hilos_sincronizacion;

public class Principal {
	public static void main(String[] args) {
		BufferRadios buffer = new BufferRadios();
		new Productor(buffer);
		new Consumidor(buffer);
		new Productor(buffer);
		new Consumidor(buffer);
		new Productor(buffer);
		new Consumidor(buffer);
	}
}