package itt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Principal {
	static Scanner lector;
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

		try {
			// Obtenemos el objeto KeyGenerator invocando al método estático getInstance(),
			// que deberá recibir como argumento el tipo de algoritmo que vamos a emplear.
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

			// Nuestro objeto KeyGenerator (generador) nos proporciona la clave que
			// utilizará el algoritmo DES para cifrar el mensaje.
			cryptoTxt = Cipher.getInstance("AES");

			if (keyfile.exists()) {
				key = readkey();
				System.out.println("pasa");
			} else {
				key = keyGenerator.generateKey();
				makeSecrectKey(key);
			}

			// Obtenemos un objeto Cipher (descifrador) que será el encargado de llevar a
			// cabo los procesos de cifrado y descifrado.

		} catch (GeneralSecurityException gse) {
			System.out.println("Algo ha fallado.." + gse.getMessage());
			gse.printStackTrace();
		}

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
		String nombre = encode(lector.nextLine());
		System.out.println("Teléfono: ");
		String tlf = encode(lector.nextLine());
		agenda.getContactos().add(new Contacto(nombre, tlf));
		System.out.println("El contacto ha sido añadido con éxito");
	}

	public static void borrarContacto(Agenda agenda) {
		int i = 0;
		System.out.println("Nombre buscado: ");
		String nombre = lector.nextLine();

		while (i < agenda.getContactos().size() && !agenda.getContactos().get(i).getNombre().equals(nombre)) {
			i++;
		}

		if (i == agenda.getContactos().size()) {
			System.out.println("No encontrado");
		} else {
			System.out.println(agenda.getContactos().get(i) + " SER�? ELIMINADO ");
			agenda.getContactos().remove(i);
		}
	}

	public static void consultarContacto(Agenda agenda) {
		int i = 0;
		System.out.println("Nombre buscado: ");
		String nombre = encode(lector.nextLine());

		while (i < agenda.getContactos().size() && !agenda.getContactos().get(i).getNombre().equals(nombre)) {
			i++;
		}

		if (i == agenda.getContactos().size()) {
			System.out.println("No encontrado");
		} else {
			System.out.println("Teléfono de " + decode(nombre) + ": "
					+ decode(agenda.getContactos().get(i).getTelefono()));
		}
	}

	public static void listadoContactos(Agenda agenda) {
		for (Contacto c : agenda.getContactos()) {
			System.out.println(decode(c.getNombre()) + ": " + decode(c.getTelefono()));
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

	public static void makeSecrectKey(SecretKey key) throws IOException {
		FileOutputStream file = new FileOutputStream("keyfile.key");
		ObjectOutputStream buffer = new ObjectOutputStream(file);
		buffer.writeObject(key);
		buffer.close();
		file.close();
	}

	public static SecretKey readkey() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("keyfile.key");
		ObjectInputStream buffer = new ObjectInputStream(file);
		SecretKey key = (SecretKey) buffer.readObject();
		buffer.close();
		file.close();
		return key;
	}

	public static String encode(String s) {
		try {
			cryptoTxt.init(Cipher.ENCRYPT_MODE, key);

			byte[] sCrypto = cryptoTxt.doFinal(s.getBytes());
			// System.out.println("Cifrando...");
			s = Base64.getEncoder().encodeToString(sCrypto);
			// System.out.println("Cifrado: " + s);

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String decode(String s) {
		try {
			cryptoTxt.init(Cipher.DECRYPT_MODE, key);
			// System.out.println("Descrifrando...");
			// System.out.println("Cifrado: " + s);
			byte[] sCrypto = cryptoTxt.doFinal(Base64.getDecoder().decode(s.getBytes()));
			s = new String(sCrypto);
			// System.out.println("Normal: " + s);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String filterCrypto(String s, int v) {
		try {
			cryptoTxt.init(v, key);
			// System.out.println("Primero: " + sCrypto);
			// byte[] sCrypto = cryptoTxt.doFinal(s.getBytes(StandardCharsets.UTF_8));


			// s = (v == 1)?Base64.getEncoder().encodeToString(sCrypto):new String(Base64.getDecoder().decode(sCrypto),StandardCharsets.UTF_8);

/* 			if (v ==1 ){
				byte[] sCrypto = cryptoTxt.doFinal(s.getBytes(StandardCharsets.UTF_8));
				System.out.println("Cifrando...");
				s = Base64.getEncoder().encodeToString(sCrypto);
				System.out.println("Cifrado: " + s);
			} else {
				System.out.println("Descrifrando...");
				System.out.println("Cifrado: " + s);
				s =  new String(Base64.getDecoder().decode(sCrypto),StandardCharsets.UTF_8);
				byte[] sCrypto = cryptoTxt.doFinal(s.getBytes(StandardCharsets.UTF_8));
			}
 */
			// System.out.println("Segundo: " + s);

		} catch (GeneralSecurityException e) {
			System.out.println("Error..." + e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
}