import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

public class Servidor1 {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            String nombreServidor = "donaciones1", nombreReplica = "donaciones2";
            int puerto = 1099;
            Registry reg = LocateRegistry.createRegistry(puerto);
            Donaciones donaciones1 = new Donaciones(nombreServidor, nombreReplica, puerto);
            Naming.rebind(nombreServidor, donaciones1);
            System.out.println("Servidor " + nombreServidor + " lanzado.");
        } catch (Exception e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }
    }
}