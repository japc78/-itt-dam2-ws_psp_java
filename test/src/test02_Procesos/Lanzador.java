package test02_Procesos;
import java.io.File;
import java.io.IOException;

public class Lanzador {
	public static void main(String[] args) {
		ProcessBuilder proceso1, proceso2;
		proceso1 = new ProcessBuilder("java", "test02_Procesos/Principal");
		proceso2 = new ProcessBuilder("java", "test02_Procesos/Principal");

		proceso1.redirectError(new File("errores1.txt"));
		proceso1.redirectOutput(new File("salida1.txt"));
		proceso1.redirectInput(new File("entrada1.txt"));

		proceso2.redirectError(new File("errores2.txt"));
		proceso2.redirectOutput(new File("salida2.txt"));
		proceso2.redirectInput(new File("entrada2.txt"));

		try {
			proceso1.start().waitFor();
			proceso2.start().waitFor();
			System.out.println("Los procesos se han lanzado");
			System.out.println("Examina los archivos de salida");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}