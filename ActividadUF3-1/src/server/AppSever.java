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

	public static void main(String[] args) {
		String host;
		int port = 2200;

		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("No se ha podido obtener la dirección IP");
			System.out.println(e.getMessage());
			return;
		}

		try {
			Registry reg = LocateRegistry.createRegistry(port);
			AppLogic app = new AppLogic();
			reg.rebind("appConstelaciones", app);

			System.out.println("Servicio registrado en host " + host + " y puerto " + port);
		} catch (RemoteException e) {
			System.out.println("No se ha podido registrar el servicio");
			System.out.println(e.getMessage());
		}

	}
}