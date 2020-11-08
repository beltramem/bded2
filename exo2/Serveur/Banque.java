import java.rmi.*;
import java.util.*;

public interface Banque extends Remote {
    public void CreationCompte(int ncp) throws RemoteException;
    public Compte SelectionCompte(int ncp) throws RemoteException;
    public List<ImpCompte> getListCompte() throws RemoteException;

}
