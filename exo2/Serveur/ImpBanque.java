import java.util.*;
import java.rmi.*;
import java.net.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ImpBanque extends UnicastRemoteObject implements Banque {
    private List<ImpCompte> listCompte;

    public List<ImpCompte> getListCompte() throws RemoteException{
        return listCompte;    
    }
    
    public ImpBanque() throws RemoteException {
        super();
        this.listCompte = new ArrayList<ImpCompte>();
    }    

    public synchronized void CreationCompte(int ncp) throws RemoteException
    {
        ImpCompte c = new ImpCompte(ncp);
        this.listCompte.add(c);
    }

    public Compte SelectionCompte(int ncp) throws RemoteException
    {
        for (ImpCompte cp : this.listCompte) {
            if (cp.getNCp() == ncp) {
                System.out.println(cp);
                return cp;
            }
        }
        return null;
    }    

    public static void main(String args[])throws Exception
    {   
        try {
        ImpBanque s = new ImpBanque();
        String nom = "banque";
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind(nom, s); // enregistrement
        System.out.println("Serveur enregistré.");
        } catch (Exception e) {
        System.err.println("Erreur : " + e);
        }
    }
}
