import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class Servidor {
    public static void main(String[] args) {

    // Crea e instala el gestor de seguridad
    if (System.getSecurityManager() == null) {
        System.setSecurityManager(new SecurityManager());
    }

    try {
        // Crea una instancia de contador
        Registry reg=LocateRegistry.createRegistry(1099);
        Contador micontador = new Contador();
        Naming.rebind("rmi://localhost:1099/mmicontador", micontador);
        System.out.println("Servidor RemoteException | MalformedURLExceptiondor preparado");

        } catch (RemoteException | MalformedURLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}