import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String servidor1 = "donaciones1";
        Scanner in = new Scanner(System.in);

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry mireg = LocateRegistry.getRegistry(args[0]);
            IDonaciones donaciones = (IDonaciones)mireg.lookup(servidor1);

            if(donaciones.registroEntidad("Pepe", "123123"))
                System.out.println("Hola");
        } catch(NotBoundException | RemoteException e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }
    }
}