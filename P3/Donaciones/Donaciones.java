import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Donaciones implements IDonaciones {
    private ArrayList<Entidad> entidades = new ArrayList<Entidad>();
    private double subTotal;
    private String nombre;
}