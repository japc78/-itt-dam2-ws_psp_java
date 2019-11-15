package itt.webservice;
import java.util.ArrayList;

import javax.ws.rs.*;

import itt.beans.Person;

@Path("datos")
public class WebServiceA {
	private ArrayList<Person> bd = new ArrayList<>();
	
	public WebServiceA() {
		bd.add(new Person(1, "RODOLFO", "LANGOSTINO", 47));
		bd.add(new Person(2, "PEDRO", "TORRES", 49));
		bd.add(new Person(3, "ALICIA", "DURAN", 52));
		bd.add(new Person(4, "LUCAS", "DELGADO", 15));
		bd.add(new Person(5, "SOLEDAD", "TORRES", 21));
		bd.add(new Person(6, "ISMAEL", "DELGADO", 12));		
	}	

	// Url de tipo: ActividadUF3-2/datos/--String a buscar--/xml
	@GET
	@Path("{surname}/xml")
	@Produces({"application/xml"})
	public ArrayList<Person> datosXML2(@PathParam("surname") String s) {
		return finder(s);
	}

	// Url de tipo: ActividadUF3-2/datos/--String a buscar--/json
	@GET
	@Path("{surname}/json")
	@Produces({"application/json"})
	public ArrayList<Person> datosJSON(@PathParam("surname") String s) {
		return finder(s);
	}


	// Muestra todos los elementos del Array en XML: ActividadUF3-2/datos/xml
	@GET
	@Path("xml")
	@Produces({"application/xml"})
	public ArrayList<Person> datosXML() {
		return bd;
	}

	// Muestra todos los elementos del Array en JSON: ActividadUF3-2/datos/xml
	@GET
	@Path("json")
	@Produces({"application/json"})
	public ArrayList<Person> datosJSON() {
		return bd;
	}	
	
	
	/**
	 * Metodo que busca el valor pasado por parametro dentror del Array de datos.
	 * @param s De tipo estring, correponde al apellido.
	 * @return retorna un ArrayList. Para XML tomara el plural de clase tipo <Person>, en este caso mostrara People
	 */	
	private ArrayList<Person> finder (String s) {
		ArrayList<Person> g = new ArrayList<>();
		for (Person p : bd) {
			if (p.getSurname().contains(s.toUpperCase())) {
				g.add(p);
			}
		}
		return g;
	}
}