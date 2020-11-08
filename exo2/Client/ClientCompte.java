import java.rmi.*;
import java.util.Scanner;


public class ClientCompte {

    public static Compte getCompte(Banque b)
    {
        Scanner scanner = new Scanner( System.in );
        Compte cp = null;
        do
        {
            System.out.println("Opération : \n numéro du compte: ");
            int nb = scanner.nextInt();
            try
            {   
                cp =b.SelectionCompte(nb);
            }catch (Exception e) {
                System.err.println("Erreur : " + e);
            }
        }while(cp ==null);
        return cp;
    }

    public static void main(String args[]) {

            Scanner scanner = new Scanner( System.in );
            try {
                Banque b = (Banque) Naming.lookup("banque");
                Compte c = null;
                int choix = 0;
                do
                {
                    
                    String menu = "Menu : \n"+
                                   "0 : Consulter liste des comptes \n"+
                                   "1 : Créer un compte \n"+
                                   "2 : Dépôt \n"+
                                   "3 : Retrait \n"+
                                   "4 : Consultation solde \n"+
                                   "5 : Consultation opération\n"+
                                   "6 : Quitter\n\n";
                    System.out.println(menu);
                    choix = scanner.nextInt();
                    float montant=0;
                    String message = "";
                    switch(choix) {
                    case 0:
                    message = b.getListCompte().toString();
                    break;
                    case 1:
                    System.out.println("Numéro du compte:");
                    montant = scanner.nextInt();
                    b.CreationCompte((int)montant);
                    break;
                    case 2:
                    c=getCompte(b);
                    System.out.println("Montant de la transaction:");
                    montant = scanner.nextFloat();
                    message = c.Depot(montant);
                    break;
                    case 3:
                    c=getCompte(b);
                    System.out.println("Montant de la transaction:");
                    montant = scanner.nextFloat();
                    message = c.Retrait(montant);
                    break;
                    case 4:
                    c=getCompte(b);
                    message = c.Consultation();
                    break;
                    case 5:
                    c=getCompte(b);
                    message = c.ConsultationOp();
                    break;
                    case 6:
                    message = "fermeture ...";
                    break;
                    }
                    System.out.println(message+"\n\n");
                }while(choix != 6);
    
            } catch (Exception e) {
                System.err.println("Erreur : " + e);
            }
        
    }
}
