import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEjemplo extends Remote {
    public void escribir_mensaje (int id_proceso) throws RemoteException;
}