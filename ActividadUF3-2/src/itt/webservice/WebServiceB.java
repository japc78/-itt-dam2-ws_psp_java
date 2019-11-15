package itt.webservice;
import javax.ws.rs.*;

import itt.beans.People;
import itt.beans.Person;

@Path("datosb")
public class WebServiceB {
	private People bd = new People();
	
	public WebServiceB() {
		bd.getGroup().add(new Person(1, "RODOLFO", "LANGOSTINO", 47));
		bd.getGroup().add(new Person(2, "PEDRO", "TORRES", 49));
		bd.getGroup().add(new Person(3, "ALICIA", "DURAN", 52));
		bd.getGroup().add(new Person(4, "LUCAS", "DELGADO", 15));
		bd.getGroup().add(new Person(5, "SOLEDAD", "TORRES", 21));
		bd.getGroup().add(new Person(6, "ISMAEL", "DELGADO", 12));		
	}
	
	// Url de tipo: ActividadUF3-2/datosb/--String a buscar--/xml
	@GET
	@Path("{surname}/xml")
	@Produces({"application/xml"})
	public People datosXML(@PathParam("surname") String s) {
		return finder(s);
	}

	// Url de tipo: ActividadUF3-2/datosb/--String a buscar--/json
	@GET
	@Path("{surname}/json")
	@Produces({"application/json"})
	public People datosJSON(@PathParam("surname") String s) {
		return finder(s);
	}

	// Muestra todos los elementos del Array en XML: ActividadUF3-2/datosb/xml
	@GET
	@Path("xml")
	@Produces({"application/xml"})
	public People datosXML() {
		return bd;
	}

	// Muestra todos los elementos del Array en JSON: ActividadUF3-2/datosb/xml
	@GET
	@Path("json")
	@Produces({"application/json"})
	public People datosJSON() {
		return bd;
	}

	/**
	 * Metodo que busca el valor pasado por parametro dentro del Array de datos.
	 * @param s De tipo estring, correponde al apellido.
	 * @return retorna un objeto de People, donde se definde la raiz del documento xml personalizada.
	 */
	private People finder (String s) {
		People g = new People();
		for (Person p : bd.getGroup()) {
			if (p.getSurname().contains(s.toUpperCase())) {
				g.getGroup().add(p);
			}
		}
		return g;
	}
}