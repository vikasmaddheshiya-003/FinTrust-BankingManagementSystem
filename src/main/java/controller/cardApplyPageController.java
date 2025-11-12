package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;

import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import Service.CardServices;
import dao.DbConnection;

public class cardApplyPageController extends SelectorComposer<Window>{


    @Wire
    private Combobox accountList;

    @Wire
    private Radiogroup cardType;

    @Wire
    private Combobox cardCategory;

    @Wire
    private Textbox address;

    @Wire
    private Textbox remarks;

    @Wire
    private Checkbox terms;
    
    
    @Wire
    Button submitApplyCard;
    
    CardServices cardService =new CardServices();
    
     
    @Override
	public  void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fintrustbank","root","Vikas123");

	//	Connection con=DbConnection.getConnection();
		String sql="select account_no from account where customer_id=?;";
		String custId="CUST001";                                        // take it from session
		PreparedStatement pStatement=con.prepareStatement(sql);
		pStatement.setString(1, custId);    
		
		ResultSet resultSet=pStatement.executeQuery();
		while(resultSet.next())
		{ 
		     System.out.println(resultSet.getString("account_no"));
			accountList.appendItem(resultSet.getString("account_no"));
		}
		
		
		
	}

	  @Listen("onClick=#submitApplyCard")
     public  void submitCardRequest()
    {
		  
	   String accNumber=accountList.getValue();
       String cardTypes=cardType.getSelectedItem().getValue();
       String cardCat=cardCategory.getValue();
       String addresss=address.getValue();
       String remark=remarks.getValue();
       boolean isMarkCheck= terms.isChecked();
       
//       if(accNumber.length()<10||accNumber.isEmpty())
//       {
//    	   alert("Account Number must be in 10 Digits");
//       }else {
//    	   
//    	   if(isMarkCheck==false)
//    	   {
//    		 alert("Please, Accept Term and Condition!!");
//    	   }
//    	   else {
//    		  }
//    	   
//       }
       
       
       if(isMarkCheck)
       {
    	  try {
			cardService.submitCardRequest(accNumber, cardTypes, cardCat, addresss, remark); 
			alert("card requested succesfully!!!!!!!!!!!!!!!");
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
		     Clients.showNotification(e.getMessage());
		  }  
       }
       else {
    	   alert("please , Accept term and condition !!!!");
       }
      
       
    }
    
 
	
//	public static void main(String[] args) {
//	
//		cardApplyPageController ob=new cardApplyPageController();
//		Window w=new Window();
//		try {
//			ob.doAfterCompose(w);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//   }
	
}
