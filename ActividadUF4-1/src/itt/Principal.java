package itt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Principal {
	static Scanner lector;

	// Atributos necesarios para el cifrado/descifrado.
	private static SecretKey key;
	private static Cipher cryptoTxt;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		lector = new Scanner(System.in);
		Agenda agenda = null;
		int opc = 0;
		File file = new File("agenda.dat");
		File keyfile = new File("keyfile.key");

		if (file.exists())
			agenda = leerFichero();
		else
			agenda = new Agenda();

		// -----------------------------------------------------------------------
		// Lógica básica necesaria para cifradO/descifrado de la Agenda.
		// -----------------------------------------------------------------------

		try {
			// STUDY KeyGenerator.getInstance() -> Se obtiene el KeyGenerator a traves del
			// método stático getInstance() y pasándole como argumento el tipo de Algoritmo
			// de encryptación a utilizar.
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

			// STUDY keyGenerator.generateKey() -> Crea la clave de encryptación.
			// Sino existe la genera y la guarda en un fichero, si exite lee la clave.
			if (keyfile.exists()) {
				key = readkey();
				System.out.println("pasa");
			} else {
				key = keyGenerator.generateKey();
				makeSecrectKey(key);
			}

			// STUDY Cipher.getInstance() -> Se Obtiene el cifrador/descifrador. Objeto de
			// la clase Cipher. A través del método estático getInstance() se pasa por
			// argumento el mismo tipo de Algoritmo de encryptación que la declarción
			// anterior del KeyGenerator.
			cryptoTxt = Cipher.getInstance("AES");

		} catch (GeneralSecurityException gse) {
			System.out.println("Algo ha fallado.." + gse.getMessage());
			gse.printStackTrace();
		}

		// -----------------------------------------------------------------------

		while (opc != 5) {
			mostrarMenu();
			opc = lector.nextInt();
			lector.nextLine(); // Para recoger el retorno de carro.
			switch (opc) {
			case 1:
				nuevoContacto(agenda);
				break;
			case 2:
				borrarContacto(agenda);
				break;
			case 3:
				consultarContacto(agenda);
				break;
			case 4:
				listadoContactos(agenda);
				break;
			}
		}
		crearFichero(agenda);
		lector.close();
	}

	public static void mostrarMenu() {
		System.out.println(" AGENDA TELEFÓNICA");
		System.out.println("---------------------------------------");
		System.out.println("1. Añadir nuevo contacto");
		System.out.println("2. Borrar contacto");
		System.out.println("3. Consultar contacto");
		System.out.println("4. Listado de contactos");
		System.out.println("5. Terminar programa");
		System.out.println("---------------------------------------");
		System.out.println("¿Qué opción eliges?");
	}

	public static void nuevoContacto(Agenda agenda) {
		System.out.println("Nombre: ");

		// Se implementa el método crypto en modo cifrado.
		String nombre = crypto(lector.nextLine().toUpperCase(), 1);
		System.out.println("Teléfono: ");

		// Se implementa el método crypto en modo cifrado.
		String tlf = crypto(lector.nextLine(), 1);
		agenda.getContactos().add(new Contacto(nombre, tlf));
		System.out.println("El contacto ha sido añadido con éxito");
	}

	public static void borrarContacto(Agenda agenda) {
		int i = 0;
		System.out.println("Nombre buscado: ");

		// Se implementa el método crypto en modo cifrado.
		String nombre = crypto(lector.nextLine().toUpperCase(), 1);

		while (i < agenda.getContactos().size() && !agenda.getContactos().get(i).getNombre().equals(nombre)) {
			i++;
		}

		if (i == agenda.getContactos().size()) {
			System.out.println("No encontrado");
		} else {
			System.out.println(crypto(agenda.getContactos().get(i).getNombre(), 2) + " : "
					+ crypto(agenda.getContactos().get(i).getTelefono(), 2) + " HA SIDO ELIMINADO ");
			agenda.getContactos().remove(i);
		}
	}

	public static void consultarContacto(Agenda agenda) {
		int i = 0;
		System.out.println("Nombre buscado: ");

		// Se implementa el método crypto en modo cifrado.
		String nombre = crypto(lector.nextLine().toUpperCase(), 1);

		while (i < agenda.getContactos().size() && !agenda.getContactos().get(i).getNombre().equals(nombre)) {
			i++;
		}

		if (i == agenda.getContactos().size()) {
			System.out.println("No encontrado");
		} else {
			// Se implementa el método crypto en modo descifrado.
			System.out.println(
					"Teléfono de " + crypto(nombre, 2) + ": " + crypto(agenda.getContactos().get(i).getTelefono(), 2));
		}
	}

	public static void listadoContactos(Agenda agenda) {
		for (Contacto c : agenda.getContactos()) {
			// Se implementa el método crypto en modo descifrado.
			System.out.println(crypto(c.getNombre(), 2) + ": " + crypto(c.getTelefono(), 2));
		}
	}

	public static void crearFichero(Agenda agenda) throws IOException {
		FileOutputStream file = new FileOutputStream("agenda.dat");
		ObjectOutputStream buffer = new ObjectOutputStream(file);
		buffer.writeObject(agenda);
		buffer.close();
		file.close();
	}

	public static Agenda leerFichero() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("agenda.dat");
		ObjectInputStream buffer = new ObjectInputStream(file);
		Agenda agenda = (Agenda) buffer.readObject();
		buffer.close();
		file.close();
		return agenda;
	}

	/**
	 * Método que guarda la Objeto de tipo SecretKey en un fichero del tipo .key
	 *
	 * @param key Objeto del tipo SecretKey.
	 * @throws IOException
	 */
	public static void makeSecrectKey(SecretKey key) throws IOException {
		FileOutputStream file = new FileOutputStream("keyfile.key");
		ObjectOutputStream buffer = new ObjectOutputStream(file);
		buffer.writeObject(key);
		buffer.close();
		file.close();
	}

	// -----------------------------------------------------------------------
	// Métodos implementados para la lógica de cifrad/descifrado de la Agenda.
	// -----------------------------------------------------------------------

	/**
	 * Método que Lee la clave guardada en un archivo keyfile.key
	 *
	 * @return Objeto del tipo Secretkey.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static SecretKey readkey() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("keyfile.key");
		ObjectInputStream buffer = new ObjectInputStream(file);
		SecretKey key = (SecretKey) buffer.readObject();
		buffer.close();
		file.close();
		return key;
	}

	/**
	 * Método para cifrar y descifrar cadenas de texto.
	 *
	 * @param s Del tipo String. El string a codificar.
	 * @param v Del tipo integer. El valor del Cypher: 1 -> Cipher.ENCRYPT_MODE, 2 -> Cipher.DECRYPT_MODE
	 *          2.
	 * @return Retorna un String.
	 */
	public static String crypto(String s, int v) {
		try {
			cryptoTxt.init(v, key);
			s = (v == 1) ? Base64.getEncoder().encodeToString(cryptoTxt.doFinal(s.getBytes()))
					: new String(cryptoTxt.doFinal(Base64.getDecoder().decode(s.getBytes())));
		} catch (GeneralSecurityException e) {
			System.out.println("Error..." + e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
}