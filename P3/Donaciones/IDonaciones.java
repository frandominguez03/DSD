import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDonaciones extends Remote {
    public boolean registroEntidad() throws RemoteException;
    public boolean deposito() throws RemoteException;
    public boolean entidadRegistrada() throws RemoteException;
    public double getSubtotal() throws RemoteException;
    public double getTotal() throws RemoteException;
}