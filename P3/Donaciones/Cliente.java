import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.*;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        // Crea e instala el gestor de seguridad
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
        // Crea el stub para el cliente especificando el nombre del servidor

            IDonaciones donaciones = (IDonaciones)Naming.lookup("ddonaciones1");
            donaciones.registroEntidad("Pepe", "Bien");
            donaciones.donar("Pepe", 12000);
        } catch(NotBoundException | RemoteException | MalformedURLException e) {
            System.err.println("Exception del sistema: " + e);
        }
        
        System.exit(0);
    }
}