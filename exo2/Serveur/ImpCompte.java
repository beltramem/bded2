import java.util.*;
import java.rmi.* ;
import java.rmi.server.* ;

public class ImpCompte extends UnicastRemoteObject
                implements Compte{

            private int nCp;
            private float solde;
            private List<String> listeOp;

    
            public int getNCp() throws RemoteException
            {
                return this.nCp;
            }
            public float getSolde() throws RemoteException
            {
                return this.solde;
            }
            public void setSolde(float i) throws RemoteException
            {
                this.solde=i;
            }

            public List<String> getListeOp() throws RemoteException
            {
                return this.listeOp;
            }

            public ImpCompte(int cp) throws RemoteException
            {
                this.nCp= cp;
                this.solde= 0;
                this.listeOp= new ArrayList<String>();;
            }

            public synchronized String Depot(float somme) throws RemoteException
   		    {
       		
        	this.setSolde(this.getSolde()+somme);
        	this.listeOp.add("Dépot de "+somme+"€");
            String message = "Vous avez effectuée un dépot de: " + somme + "€";
            return message;
    		}
    
    	    public synchronized String Retrait(float somme) throws RemoteException
    		{
    			String message = "Vous avez effectuée un débit de: " + somme + "€";
        		this.setSolde(this.getSolde()-somme);
        		this.listeOp.add("Retrait de "+somme+"€");
                return message;
    		}
    
    	    public String Consultation() throws RemoteException
    		{
        		String message = "Compte n°"+this.getNCp()+"\nVotre solde est de: "+this.getSolde() + "€";
                this.listeOp.add("Consultation du solde");
                return message;
    		}
    
            public String ConsultationOp() throws RemoteException
    		{
        		String message = "Voici vos dernieres operations: "+this.getListeOp().toString();
                this.listeOp.add("Consultation des operations");
                return message;
    		}


}
