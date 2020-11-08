import java.rmi.*;

public interface Compte extends Remote{
    public synchronized String Depot(float montant) throws RemoteException ;
    public synchronized Retrait(float val) throws RemoteException ;
    public String Consultation() throws RemoteException;
    public String ConsultationOp() throws RemoteException;
    public int getNCp() throws RemoteException;
}
