import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CEjemplo implements Runnable {

    public String nombre_objeto_remoto = "IEjemplo";
    public String server;

    public CEjemplo (String server) {
    //Almacenamos el nombre del host servidor
    this.server = server;
    }

    public void run() {
        System.out.println("Buscando el objeto remoto");
        try {
            Registry registry = LocateRegistry.getRegistry(server);
            IEjemplo instancia_local = (IEjemplo) registry.lookup(nombre_objeto_remoto);
            System.out.println("Invocando el objeto remoto");
            instancia_local.escribir_mensaje(Thread.currentThread().getName());
        } catch (Exception e) {
            System.err.println("IEjemplo exception:");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        int n_hebras = Integer.parseInt(args[1]);
        CEjemplo[] v_clientes = new
        CEjemplo[n_hebras];
        Thread[] v_hebras = new Thread[n_hebras];
        for (int i=0;i<n_hebras;i++) {
            //A cada hebra le pasamos el nombre el servidor
            v_clientes [i] = new CEjemplo(args[0]);
            v_hebras[i] = new Thread(v_clientes[i],"Cliente "+i);
            v_hebras[i].start();
        }
    }
}