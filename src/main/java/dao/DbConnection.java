package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root123");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return connection;
    }
    
   
}
