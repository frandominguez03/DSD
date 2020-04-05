import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            String nombreServidor = "donaciones2";
            Registry reg = LocalteRegistry.createRegistry(1099);
            Donaciones donaciones2 = new Donaciones();
            Naming.rebind(nombreServidor, donaciones1);
            System.out.println("Servidor " + nombreServidor + " lanzado.");
        } catch (Exception e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }
    }
}