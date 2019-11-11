package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import beans.Persona;

public class XMLToObject {
	public static void main(String[] args) {
		try {
			// Se crea el contexto de la clase a la que pertecene el objeto a desserialziar.
			//Esta vez creamos un objeto que nos permite pasar de XML a Object, es decir deserializar
			JAXBContext contexto = JAXBContext.newInstance(Persona.class);

			// Para deserializar se utiliza Unmarshaller.
			Unmarshaller u = contexto.createUnmarshaller();

			// Se indica el fichero xml.
			File fichero = new File("Homer.xml");

			// Si el fichero existe, mostramos los datos del objeto persona deserializado.
			if (fichero.exists()) {
				Persona p = (Persona) u.unmarshal(fichero);
				System.out.println(p.getApellido());
				System.out.println(p.getNombre());
				System.out.println(p);
			} else {
				System.out.println("Fichero XML Homer.xml no encontrado");
			}

		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}
	}
}
