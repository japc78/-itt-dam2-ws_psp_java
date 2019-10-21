package test13_sockets_hilos_felix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloConexcionCliente implements Runnable {
	private static int numCliente = 0;
	PrintStream salida = null;
	InputStreamReader entrada = null;

	private Socket conexion;

	public HiloConexcionCliente(Socket cliente) {
		numCliente++;
		new Thread(this, "Cliente" + numCliente).start();
		this.conexion = cliente;
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + Thread.currentThread().getName());
		try {
			entrada = new InputStreamReader(conexion.getInputStream());
			String texto = "";
			while (!texto.equals("FIN")) {
				BufferedReader bf = new BufferedReader(entrada);
				texto = bf.readLine();
				salida = new PrintStream(conexion.getOutputStream());
				if (texto.equals("FIN")) {
					salida.println("Hasta pronto, gracias por establecer conexión");
					System.out.println(Thread.currentThread().getName() + " ha cerrado la comunicación");
				} else {
					System.out.println(Thread.currentThread().getName() + " dice: " + texto);
					salida.println(("Tu mensaje tiene " + texto.length() + " caracteres"));
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(salida != null && entrada != null){
					salida.close();
					entrada.close();
					conexion.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}