package common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Con la anotación @XmlRootElement(name = "persona"), estamos especificando el nombre del elementos raíz.
@XmlRootElement(name = "persona")

public class Persona {
	private int idPersona;
	private String nombre;
	private String apellido;
	private int edad;
	public Persona() {}
	public Persona(int id, String nombre, String apellido, int edad) {
		this.idPersona = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	// Anotamos con @XmlAttribute el método getIdPersona() porque deseamos utilizar la propiedad idPersona como atributo de la etiqueta raíz (elemento persona).
	@XmlAttribute
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	// Anotamos con @XmlElement los métodos get correspondientes a las propiedades del objeto que deseamos importar
	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@XmlElement
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + ", Edad=" + edad;
	}
}