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
		  Long accoutNum=Long.parseLong(accNumber);
			 String sql="insert into cart_request values(?,?,?,?,?)";
			 PreparedStatement pstm = DbConnection.getConnection().prepareStatement(sql);
			 pstm.setLong(1, accoutNum);
			 pstm.setString(2, cardTypes);
			 pstm.setString(3, cardCat);
			 pstm.setString(4, addresss);
			 pstm.setString(5, remark);
			 
			 int n=pstm.executeUpdate();
	} catch (Exception e) {
		Clients.showNotification(e.getMessage());
	}
	 
	
  }
  
 
 
}
