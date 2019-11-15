package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * AppSever
 */
public class AppSever {
	// STUDY RMI -> Servidor
	public static void main(String[] args) {
		String host;
		int port = 2200;

		try {
			// Se averigual la ip de conexion. Genera un tipo de excepcion UnknownHostException que se captura con el try/catch.
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("No se ha podido obtener la dirección IP");
			System.out.println(e.getMessage());
			return;
		}

		try {
			// Se crea el resgistro de objeto remotos
			Registry reg = LocateRegistry.createRegistry(port);

			// Se crea el objeto a compartir, AppLogic contiene la logica del programa.
			AppLogic app = new AppLogic();

			// rebind() inscribe del nuevo objeto App dentro del registro de objetos, asi los clientes pueden optener el Stub, bajo el nombre "appConstelaciones".
			reg.rebind("appConstelaciones", app);

			System.out.println("Servicio registrado en host " + host + " y puerto " + port);
		} catch (RemoteException e) {
			System.out.println("No se ha podido registrar el servicio");
			System.out.println(e.getMessage());
		}

	}
}