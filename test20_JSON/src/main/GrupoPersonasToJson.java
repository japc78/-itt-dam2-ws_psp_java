package main;

import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;

import beans.GrupoPersonas;
import beans.Persona;

/**
 * GrupoPersonasToJson
 */
public class GrupoPersonasToJson {

	public static void main(String[] args) {
		GrupoPersonas grupo = new GrupoPersonas();
		grupo.getPersonas().add(new Persona(1, "Homer", "Simpson", 48));
		grupo.getPersonas().add(new Persona(2, "Lisa", "Simpson", 12));
		grupo.getPersonas().add(new Persona(3, "Bart", "Simpson", 13));
		grupo.getPersonas().add(new Persona(4, "Marge", "Simpson", 40));

		// Objeto para selializar y deserializar
		Gson gson = new Gson();

		// toJson, se convierte un objeto a Json
		String json = gson.toJson(grupo);

		File file = new File("simpson.json");

		try (PrintWriter pw = new PrintWriter(file)) {
			pw.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}