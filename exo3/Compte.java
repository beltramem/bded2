import java.rmi.*;

public interface Compte extends Remote{
    public String Depot(float montant) throws RemoteException ;
    public String Retrait(float val) throws RemoteException ;
    public String Consultation() throws RemoteException;
    public String ConsultationOp() throws RemoteException;
    public int getNCp() throws RemoteException;

}
