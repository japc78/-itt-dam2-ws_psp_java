package itt;

import java.io.Serializable;
import java.util.ArrayList;

public class Agenda implements Serializable {
	private static final long serialVersionUID = 1324774912528254307L;
	private ArrayList<Contacto> contactos = new ArrayList<Contacto>();

	public Agenda() {
		this.contactos = new ArrayList <Contacto> ();
	}

	public ArrayList <Contacto> getContactos() {
		return contactos;
	}
}