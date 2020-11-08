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
    	}catch(Exception e){e.printStackTrace();}
	}

	public boolean isConnected(){
		return this.con !=null;
	}

	public void disconnect(){
		if(isConnected()){
			try {
				this.con.close();
				System.out.println("deconnection de la bdd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 

	public void UpdateBDCompte(ImpCompte cp) 
    {
        try{
		    this.stmt.executeQuery("UPDATE compte SET (solde) = "+cp.getSolde()+" WHERE NCP = "+cp.getNCp());
            
			    }catch(Exception e){ System.out.println(e);}
           
    }

    public void UpdateBDOp(ImpCompte cp, String typeOp)
            {
                try{ 
		  
				    this.stmt.executeQuery("INSERT INTO Operation VALUES ( NULL, '"+typeOp+"', "+cp.getNCp()+")"); 
  				    }catch(Exception e){ System.out.println(e);}
                   
            }

     public void CreationCompte(ImpCompte cp)
     {

     	try{ 	
     	    this.stmt.executeQuery("INSERT INTO compte (ncp, solde) VALUES ( "+cp.getNCp()+","+cp.getSolde()+")");
			    }catch(Exception e){ System.out.println(e);}

     }

     public ResultSet getCompte()
     {
     	try{ 
     	ResultSet rs=this.stmt.executeQuery("select * from compte");
     	return rs;
     	}catch(Exception e){ System.out.println(e);}

     	return null;
     }

     public ResultSet getOperation(int nCp)
     {
     	try{ 
     	ResultSet rs =this.stmt.executeQuery("select * from operation where compte="+nCp);
     	return rs;
     	}catch(Exception e){ System.out.println(e);}

		return null;     	
     }
}