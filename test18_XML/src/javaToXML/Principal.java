package javaToXML;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import common.Persona;

public class Principal {
	public static void main(String[] args) {
		JAXBContext contexto;

		try {
			// Obtiene el contexto asociado a la clase Persona. Provoca una excepción de tipo JAXBException si la clase Persona no cumple los requisitos para la conversión a XML, es decir, contener las anotaciones necesarias y contar con un constructor sin argumentos
			contexto = JAXBContext.newInstance(Persona.class);
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto");
			System.out.println(e.getMessage());
			return;
		}

		Marshaller m;

		try {
			// Obtiene el objetoMarshaller asociado al contexto.
			m = contexto.createMarshaller();

			// Establecer la propiedad JAXB_FORMATTED_OUTPUT con el valor true permite que en la conversión a formato XML se incluyan  retornos de carro e indentación (sangrado del texto). Prueba a ejecutar el programa con los valores true y false para ver la diferencia.
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Crea una objeto persona, lo convierte a XML y devuelve el resultado a la consola (System.out)
			Persona p = new Persona(1, "Homer", "Simpson", 48);
			m.marshal(p, System.out);

			// Si lo deseas, también puedes enviar el resultado a un fichero externo de la siguiente manera:
			m.marshal(p, new File("Homer.xml"));

		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato PXML ");
			System.out.println(e.getMessage());
		}
	}
}