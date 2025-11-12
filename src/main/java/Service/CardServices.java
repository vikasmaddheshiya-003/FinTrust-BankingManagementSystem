package Service;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zk.ui.util.Clients;

import dao.DbConnection;

public class CardServices {

  public void submitCardRequest(String accNumber,String cardTypes,String cardCat,String addresss,String remark) throws SQLException
  {
	  try {
		  System.out.println("9");
		 
			 String sql="insert into card_request (account_no,card_type,card_category,address,remarks) values(?,?,?,?,?)";
			 System.out.println("2");
			 PreparedStatement pstm = DbConnection.getConnection().prepareStatement(sql);
			 pstm.setString(1, accNumber);
			 System.out.println("3");
			 pstm.setString(2, cardTypes);
			 pstm.setString(3, cardCat);
			 System.out.println("5");
			 pstm.setString(4, addresss);
			 System.out.println("5");
			 pstm.setString(5, remark);
			 System.out.println("sd5");
			 int n=pstm.executeUpdate();
			 System.out.println("5");
	} catch (Exception e) {
		Clients.showNotification(e.getMessage());
	}
	 
	
  }
  
 
 
}
