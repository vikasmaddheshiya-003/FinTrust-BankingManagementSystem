package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class card_status_controller extends SelectorComposer<Window>{

	@Wire 
	Listbox atmRequestList;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fintrustbank","root","Vikas123");
		//String atmCardNumber=(String)Sessions.getCurrent().getAttribute("atmNumber");
		//long atmNumber = (Long) Long.parseLong(atmCardNumber);
		
		String sql="select * from card_request where customer_id =?" ;         
		PreparedStatement ptsm=connection.prepareStatement(sql);
		ptsm.setString(1, "CUST001");                              // ****TAKE IT FROM SESSIONOS
		
		ResultSet rs=ptsm.executeQuery();
		while(rs.next())
		{
			
		   Listitem li=new Listitem();
		   
		   
		   Listcell lc=new Listcell((String)rs.getString("account_no"));
		   li.appendChild(lc);
		   
		    lc=new Listcell((String)rs.getString("card_type"));
		   li.appendChild(lc);
		   
		   lc=new Listcell((String)rs.getString("card_category"));
		   li.appendChild(lc);
		   
		   lc=new Listcell((String)rs.getString("address"));
		   li.appendChild(lc);
		   
		   lc=new Listcell((String)rs.getString("remarks"));
		   li.appendChild(lc);
		 
		   lc=new Listcell((String)rs.getString("card_request_status"));
		   li.appendChild(lc);
		 
		   lc=new Listcell((String)rs.getString("remarks"));
		   li.appendChild(lc);
		 
	       
		   atmRequestList.appendChild(li);
		}
		
	}
	
	
	
	
	
	
	
	
}
