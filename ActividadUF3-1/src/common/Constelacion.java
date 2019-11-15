package common;

import java.io.Serializable;

/**
 * Clase Constelacion
 *
 */
public class Constelacion implements Serializable {
	// Se serializa para poder pasar el objeto cuadno lo solicite el cliente. En este caso, como el cliente y el servidor estan en el mismo proyecto se utiliza la misma clase. Pero en los otros casos, la ambas clases creadas en sus proyectos correspondientes deben de tener el mismo identificador.
	private static final long serialVersionUID = 5334247614476509887L;

	private String name, description;

	/**
	 * Constructor general de la clase.
	 * @param name -> De tipo String. Nombre de la constelacion.
	 * @param description -> De tipo String. Descripcion de la constelacion.
	 */
	public Constelacion(String name, String description) {
		this.name = name;
		this.description = description;
	}


	// Getters & Setters
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

	// To String
		@Override
		public String toString() {
			return  name + ": " + description;
		}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}