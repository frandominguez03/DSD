import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Donaciones extends UnicastRemoteObject implements IDonaciones {
    private ArrayList<Entidad> entidades = new ArrayList<>();
    private double subtotal;

    /*
    * Servidor que hostea el objeto
    * 1: Servidor 1
    * 2: Servidor 2
    */
    private int servidor;
    private String nombreReplica;

    public Donaciones(int servidor) throws RemoteException {
        this.servidor = servidor;

        if(this.servidor == 1)
            this.nombreReplica = "ddonaciones2";
        else
            this.nombreReplica = "ddonaciones1";
    }

    @Override
    public boolean registroEntidad(String nombre, String codigoAcceso) throws RemoteException {
        /* Comprobamos que el cliente no exista en la replica */
        IDonaciones replica = this.getReplica(this.nombreReplica);
        
        if(replica != null) {
            if(replica.entidadRegistrada(nombre) != null)
                return false;
        }

        if(this.entidadRegistrada(nombre) != null)
            return false;
        
        this.entidades.add(new Entidad(nombre, codigoAcceso));
        System.out.println("Entidad " + nombre + " registrada con éxito");
        return true;
    }

    @Override
    public boolean donar(String nombre, int cantidad) throws RemoteException {
        Entidad entLocal = this.entidadRegistrada(nombre);

        if(entLocal != null) {
            this.subtotal += cantidad;
            entLocal.incrementarTotal(cantidad);
            System.out.println("Entidad " + nombre + " ha donado " + cantidad + "€");
            return true;
        }

        return false;
    }

    @Override
    public Entidad entidadRegistrada(String nombre) throws RemoteException {
        for(int i = 0; i < this.entidades.size(); i++) {
            if(this.entidades.get(i).getNombre().equals(nombre)) {
                return this.entidades.get(i);
            }
        }

        return null;
    }

    public IDonaciones getReplica(String nombre) throws RemoteException {
        IDonaciones replica = null;

        try {
            int puerto;
            if(this.nombreReplica == "ddonaciones1")
                puerto = 1099;
            else
                puerto = 1100;

            Registry mireg = LocateRegistry.getRegistry("localhost", 1100);
            replica = (IDonaciones)mireg.lookup("ddonaciones2");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return replica;
    }
}