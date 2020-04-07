import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDonaciones extends Remote {
    boolean registroEntidad(String nombre, String codigoAcceso) throws RemoteException;
    boolean donar(String nombre, int cantidad) throws RemoteException;
    Entidad entidadRegistrada(String nombre) throws RemoteException;
    IDonaciones getReplica(String nombre) throws RemoteException;
}