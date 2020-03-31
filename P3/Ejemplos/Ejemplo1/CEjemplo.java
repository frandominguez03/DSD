import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CEjemplo {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String obj_remoto = "Ejemplo";
            System.out.println("Buscando el objeto remoto");
            Registry registry = LocateRegistry.getRegistry(args[0]);
            IEjemplo instancia_local = (IEjemplo) registry.lookup(obj_remoto);
            System.out.println("Invocando el objeto remoto");
            instancia_local.escribir_mensaje(Integer.parseInt(args[1]));
        } catch (Exception e) {
            System.err.println("IEjemplo exception:");
            e.printStackTrace();
        }
    }
}