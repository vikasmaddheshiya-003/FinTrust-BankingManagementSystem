package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	private static Connection connection=null;
	
    private DbConnection()
    {
    	
    }
    
    public static Connection getConnection()
    {
    	try {
			if(connection==null||connection.isClosed())
			{
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fintrustbank","root","Vikas123");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return connection;
    }
 
 
    

	
  
    
}
