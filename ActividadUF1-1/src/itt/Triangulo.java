package itt;

import java.time.LocalDateTime;

// Clase Triangulo.
public class Triangulo {
	public static void main(String[] args) {
		if (args.length == 0) {
			 System.out.println("Se requiere un argumento");
			 return;
		}

		int filas = Integer.parseInt(args[0]);

		// Se añade las lineas que mostraran el resultado
		// Se utiliza LocalDateTime.now() para mostrar la fecha en al inicia y finaliza el proceso.

		System.out.println("Triangulo con parametro: " + filas);
		System.out.println("--------------------------------------------");
		System.out.println("La fecha de inicio: " + LocalDateTime.now());
		System.out.println("--------------------------------------------");

		for (int i=filas; i>=1; i--) {
			for (int n=1; n<=i; n++) {
            	System.out.print(n);
            }
			System.out.println();
        }

		System.out.println("--------------------------------------------");
		System.out.println("La fecha de fin: " + LocalDateTime.now());
		System.out.println("--------------------------------------------");
	}
}