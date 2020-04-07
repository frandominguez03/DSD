import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Donaciones extends UnicastRemoteObject implements IDonaciones {
    private ArrayList<Entidad> entidades = new ArrayList<Entidad>();
    private double subtotal;
    private String nombreServidor;
    private String nombreReplica;
    private int puerto;

    /* Constructor */
    public Donaciones(String nombreServidor, String nombreReplica, int puerto) throws RemoteException {
        this.nombreServidor = nombreServidor;
        this.nombreReplica = nombreReplica;
        this.puerto = puerto;
    }

    /* Registrar una entidad */
    @Override
    public boolean registroEntidad(String nombre, String codigoAcceso) {
        /* Comprobamos si la entidad está registrada en esta réplica */
        if(this.entidadRegistrada(nombre))
            return false;
        
        /* En caso contrario, debemos buscar en la otra réplica */
        IDonaciones replica = this.getReplica(this.nombreReplica);

        if(replica != null) {
            try {
                if(replica.entidadRegistrada(nombre))
                    return false;    
            
                /* Ya sabemos que no está registrado en ninguna réplica, ahora tenemos que elegir en cuál registrarla */
                if(this.getNumeroEntidades() <= replica.getNumeroEntidades())
                    this.addEntidad(new Entidad(nombre, codigoAcceso));
                
                else 
                    replica.addEntidad(new Entidad(nombre, codigoAcceso));
                
                System.out.println("Entidad " + nombre + " registrada con éxito");

            } catch(RemoteException e) {
                System.err.println("Exception:");
                e.printStackTrace();
            }
        }

        return true;
    }

    /* Hacer un depósito */
    @Override
    public boolean deposito(Entidad ent, double cantidad) {
        Entidad entLocal = null;
        boolean exito = false;

        for(int i = 0; i < this.entidades.size(); i++) {
            if(this.entidades.get(i).equals(ent)) {
                entLocal = this.entidades.get(i);
                exito = true;
            }
        }

        if(exito) {
            this.subtotal += cantidad;
            entLocal.incrementarTotal(cantidad);
            System.out.println("Entidad " + entLocal.getNombre() + " ha donado " + cantidad + "€");
        }
        
        return exito;
    }

    /* Comprobar si una entidad está registrada en la réplica */
    @Override
    public boolean entidadRegistrada(String nombre) {
        for(int i = 0; i < this.entidades.size(); i++) {
            if(this.entidades.get(i).getNombre() == nombre) {
                return true;
            }
        }

        return false;
    }

    /* Obtener subtotal de la réplica */
    @Override
    public double getSubtotal() {
        return this.subtotal;
    }

    /* Obtener total de todas las réplicas */
    @Override
    public double getTotal() {
        IDonaciones replica = this.getReplica(this.nombreReplica);

        if(replica != null) {
            try {
                return this.subtotal + replica.getSubtotal();
            } catch(RemoteException e) {
                System.err.println("Exception:");
                e.printStackTrace();
            }
        }

        return 0;
    }

    /* Obtener la réplica correspondiente */
    @Override
    public IDonaciones getReplica(String nombreReplica) {
        IDonaciones replica = null;
        String host = "localhost";

        try {
            Registry mireg = LocateRegistry.getRegistry(host, this.puerto);
            replica = (IDonaciones)mireg.lookup(nombreReplica);
        } catch(NotBoundException | RemoteException e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }

        return replica;
    }
    
    /* Obtener el número de entidades registrados en esta réplica */
    @Override
    public int getNumeroEntidades() {
        return this.entidades.size();
    }

    /* Método para añadir una entidad a la lista de entidades */
    @Override
    public void addEntidad(Entidad ent) {
        this.entidades.add(ent);
    }
}