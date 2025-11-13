package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zhtml.Messagebox;

public class DbConnection {
	
	private static Connection connection=null;
	
    private DbConnection()
    {
    	
    }
    
    public static Connection getConnection()
    {
    	try {
			if(connection==null || connection.isClosed())
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fintrustbank","root","Vikas123");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messagebox.show(e.getMessage());
		}
    	return connection;
    }
 
     
    
}











