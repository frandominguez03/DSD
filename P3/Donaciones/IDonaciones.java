import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDonaciones extends Remote {
    public boolean registroEntidad(String nombre, String codigoAcceso) throws RemoteException;
    public boolean deposito(Entidad ent, double cantidad) throws RemoteException;
    public boolean entidadRegistrada(String nombre) throws RemoteException;
    public double getSubtotal() throws RemoteException;
    public double getTotal() throws RemoteException;
    public IDonaciones getReplica(String nombre) throws RemoteException;
    public int getNumeroEntidades() throws RemoteException;
    public void addEntidad(Entidad ent) throws RemoteException;
}