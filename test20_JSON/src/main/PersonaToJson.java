package main;

import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;

import beans.Persona;

/**
 * PersonaToJson
 */
public class PersonaToJson {

	public static void main(String[] args) {
		Persona p1 = new Persona(1, "hommer", "Simpson", 48);

		// Objeto para serializar
		Gson gson = new Gson();

		// to Json, se convierte el objeto a Json
		String json = gson.toJson(p1);

		System.out.println(json);

		File file = new File("hommer.json");

		try (PrintWriter pw = new PrintWriter(file)) {
			pw.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}