import java.io.Serializable;

public class Cancion implements Serializable{

	// STUDY Serializable es una interface de marcado que permite pasar un objeto a un flujo de bytes para que pueda ser sacado de la JVM a otro entorno, como ej. otra JVM, fichero, etc. Un obejto no puede salir de la JVM a menos que se le implemente la interface Serializable.
	private static final long serialVersionUID = 7704932698362948241L;

	private int id;
	private String titulo;
	private String banda;
	private String album;
	private int producido;

	public Cancion(int id,String titulo, String banda, String album, int producido) {
		this.titulo = titulo;
		this.banda = banda;
		this.album = album;
		this.producido = producido;
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	public String getBanda() {
		return banda;
	}
	public String getAlbum() {
		return album;
	}
	public int getProducido() {
		return producido;
	}

	@Override
	public String toString() {
		return titulo + " (" + banda + ") ï¿½lbum=" + album + " - " + producido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
