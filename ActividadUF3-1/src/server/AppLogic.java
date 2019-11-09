package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.Constelacion;
import common.AppInterface;

/**
 * AppLogic
 */
public class AppLogic extends UnicastRemoteObject implements AppInterface {
	private static final long serialVersionUID = 5725959696066663305L;
	private ArrayList<Constelacion> constelaciones;

	protected AppLogic() throws RemoteException {
		super();
		constelaciones = new ArrayList<Constelacion>();
		constelaciones.add(new Constelacion("Osa Mayor", "Se desplaza en círculos alrededor del polo norte."));
		constelaciones.add(new Constelacion("Osa Menor", "Su estrella más conocida es la polar que se encuentra en la prolongación del eje de la tierra."));
		constelaciones.add(new Constelacion("Tauro", "Una de las constelaciones más conocidas desde tiempos remotos."));
		constelaciones.add(new Constelacion("Leo", "De las más brillantes del Zodíaco."));
		constelaciones.add(new Constelacion("Escorpio", "Sus estrellas forman un escorpión."));
		constelaciones.add(new Constelacion("Can Mayor", "Contiene la estrella Sirio, la más brillante en el cielo nocturno."));
		constelaciones.add(new Constelacion("Casiopea", "Tiene forma de M o W. Es conocida desde mucha antigüedad."));
		constelaciones.add(new Constelacion("El Boyero", "Contiene la estrella Arturo, uno de las más luminosas del cielo."));
		constelaciones.add(new Constelacion("Cruz del sur", "Señala al polo sur. Constelación muy pequeña."));
		constelaciones.add(new Constelacion("Acuario", "Una de las más antiguas. Incluye 56 estrellas."));
		constelaciones.add(new Constelacion("Géminis", "Destaca por sus dos gemelos, las estrellas Cástor y Pólux."));
	}

	@Override
	public String listarConstelaciones() throws RemoteException {
		String r = "";
		r += "-- Listado de constelaciones -- \n" ;
		for (int i = 0; i < constelaciones.size(); i++) {
			r+= "(" + (i) + ") " + constelaciones.get(i).getName()  + "\n";
		}
		r+= "------------------------------- \n";
		System.out.println();
		return r;
	}

	@Override
	public String buscarPorNombre(String name) throws RemoteException {
		String r = "";
		for (Constelacion c : constelaciones) {
			if (c.getName().equalsIgnoreCase(name)) {
				r = c.toString();
			}
		}
		return (!r.isEmpty()?r:"No exite");
	}

	@Override
	public Constelacion buscarPorId(int i) throws RemoteException {
		return ((i > 0) && (i < constelaciones.size()))?constelaciones.get(i-1):null;
	}
}