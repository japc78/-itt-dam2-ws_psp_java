package itt_comun;

public class Producto {
	private String nombre;
	private int stock;
	private float precio;

	/**
	 * Constructor de producto pasandole nombre, stock y precio.
	 * @param nombre -> String
	 * @param stock -> Integer
	 * @param precio -> Float.
	 */
	public Producto(String nombre, int stock, float precio) {
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}

	//  Se sobreescribe el metodo toString para utilizarlo en la salida del mensaje.
	@Override
	public String toString() {
		return "Producto: nombre = " + nombre + ", precio = " + precio + ", stock = " + stock;
	}
}
