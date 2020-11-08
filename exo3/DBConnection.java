import java.sql.*;

public class DBConnection{
	private Connection con;
	private Statement stmt;

	public Connection getConnection(){
		return this.con;
	}

	public Statement getStatement(){
		return this.stmt;
	}

	public DBConnection()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
        	this.con=DriverManager.getConnection("jdbc:oracle:thin:@eluard:1521:ense2020","eb143296","eb143296");
        	this.stmt=con.createStatement();
    	}catch(SQLException e){e.printStackTrace()}
	}

	public boolean isConnected(){
		return this.connection !=null;
	}

	public void disconnect(){
		if(isConnected()){
			try {
				connection.close();
				System.out.println("deconnection de la bdd");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 

	public void UpdateBDCompte(Compte cp) throws RemoteException
    {
        try{
		    this.stmt.executeQuery("UPDATE compte SET SOLDE = "+cp.getSolde()+" WHERE NCP = "+cp.getNCp());
            
			    }catch(Exception e){ System.out.println(e);}
           
    }

    public void UpdateBDOp(Compte cp, String typeOp) throws RemoteException
            {
                try{ 
		  
				    this.stmt.executeQuery("INSERT INTO Operation VALUES ( NULL, '"+typeOp+"', "+cp.getNCp()+")"); 
  				    }catch(Exception e){ System.out.println(e);}
                   
            }

     public void CreationCompte(Compte cp)
     {
     	try{ 	
     	    this.stmt.executeQuery("INSERT INTO compte (ncp, solde) VALUES ( '"+cp.ncp+"','"+cp.getSolde()+"')");
			    }catch(Exception e){ System.out.println(e);}

     }

     public ResultSet getCompte()
     {
     	ResultSet rs=this.stmt.executeQuery("select * from compte");
     	return rs;
     }

     public ResultSet getOperation(int nCp)
     {
     	ResultSet rs =this.stmt.executeQuery("select * from operation where compte="+nCp);
     	return rs;
     }
}