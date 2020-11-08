import java.util.*;
import java.rmi.*;
import java.net.*;
import java.rmi.server.*;
import java.sql.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


public class ImpBanque extends UnicastRemoteObject implements Banque {
    private List<ImpCompte> listCompte;
    private DBConnection con;


    public List<ImpCompte> getListCompte() throws RemoteException{
        return listCompte;    
    }

   
    public ImpBanque() throws RemoteException {
        super();
        this.listCompte = new ArrayList<ImpCompte>(); 
        this.con = new DBConnection();
          try{ 

                ResultSet rs=con.getCompte();
                while(rs.next()) 
                {
                    System.out.println(rs.getInt(1)+" "+rs.getInt(2)+"\n");
                    ImpCompte c= new ImpCompte(rs.getInt(1),rs.getInt(2),new ArrayList<String>(), this.con);
                    this.listCompte.add(c);

                }
                for (ImpCompte cp : this.listCompte) {
                    rs=this.con.getOperation(cp.getNCp());
                    ArrayList<String> listOp = new ArrayList<String>();
                    while(rs.next()) 
                    {
                        listOp.add(rs.getString(2));
                    }
                    cp.setListeOp(listOp);
                }
                    
                //con.close();
               }catch(Exception e){ System.out.println(e);}
      }

    public synchronized void CreationCompte(int ncp) throws RemoteException
    {
        if(SelectionCompte(ncp)==null)
        {
        ImpCompte c = new ImpCompte(ncp,this.con);
        this.listCompte.add(c);
        con.CreationCompte(c);
                }
        else System.out.println("Ce numero de compte existe deja");
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
        Registry registry = LocateRegistry.createRegistry(1099);
        String nom = "banque";
        Naming.rebind(nom, s); // enregistrement
        //System.out.println("Serveur enregistré.");
        } catch (Exception e) {
        System.err.println("Erreur : " + e);
        }
    }
}
