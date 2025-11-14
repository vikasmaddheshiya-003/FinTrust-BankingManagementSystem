//package controller;
//
//import java.security.Provider.Service;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.zkoss.zk.ui.select.SelectorComposer;
//import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Window;
//
//
//import Service.CardServices;
//import dao.DbConnection;
//
////SELECT * FROM fintrustbank.card_request;
////use fintrustbank;
////
////CREATE TABLE IssuedATMCard (
////    atm_card_number BIGINT PRIMARY KEY AUTO_INCREMENT,
////    customer_id varchar(50) NOT NULL,
////    account_number varchar(50)  NOT NULL,
////    cvv CHAR(3) NOT NULL,
////    pin CHAR(4) NOT NULL,
////    issued_date DATE ,
////    expiry_date DATE GENERATED ALWAYS AS (DATE_ADD(issued_date, INTERVAL 3 YEAR)) STORED,
////    
////    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
////    FOREIGN KEY (account_number) REFERENCES account(account_no)
////) AUTO_INCREMENT = 5467346754768709;
//
//
//
//
//public class manageCardController extends SelectorComposer<Window> {
//
//	@Wire
//	Listbox 
//	
//	
//	@Wire
//	Listbox cardList;
//
//	@Override
//	public void doAfterCompose(Window comp) throws Exception {
//		// TODO Auto-generated method stub
//		super.doAfterCompose(comp);
//		
//		ArrayList<ArrayList<String>> cardData = new ArrayList<>();
//
//	        try (Connection con = DbConnection.getConnection()) {
//	            String sql = "SELECT * FROM IssuedATMCard_Dummy";
//	            PreparedStatement ps = con.prepareStatement(sql);
//	            ResultSet rs = ps.executeQuery();
//
//	            while (rs.next()) {
//	            	ArrayList<String> row = new ArrayList<>();
//	                row.add(String.valueOf(rs.getLong("atm_card_number")));
//	                row.add(rs.getString("customer_id"));
//	                row.add(rs.getString("account_number"));
//	                row.add(rs.getString("cvv"));
//	                row.add(rs.getString("pin"));
//	                row.add(String.valueOf(rs.getDate("issued_date")));
//	                row.add(String.valueOf(rs.getDate("expiry_date")));
//	                row.add(rs.getString("card_status"));
//	                cardData.add(row);
//	            }
//
//	            ListModelList<List<String>> model = new ListModelList<>(cardData);
//	            cardListbox.setModel(model);
//
//	        } catch (SQLException e) {
//	            Messagebox.show("Error loading card data: " + e.getMessage());
//	            e.printStackTrace();
//	        }
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
////		ArrayList<ArrayList<String>> cardData = new ArrayList<>();
////		while(rs.next())
////		{  ArrayList<String> row;
////			System.out.println(rs.getString(4));
////		}
//		
//		
//	}
//	
//	public static void main(String[] args) {
//		manageCardController ob=new manageCardController();
//		Window w=new Window();
//		try {
//			ob.doAfterCompose(w);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}











package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import dao.DbConnection;

public class manageCardController extends SelectorComposer<Window> {

    @Wire
    private Listbox cardListbox;  
    
     
    @Wire
    private Button manageBtn;

    
    
    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);

        List<List<String>> cardData = new ArrayList<>();

        try (Connection con = DbConnection.getConnection()) {
            String sql = "SELECT * FROM issuedatmcard_dummy where customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            String custId="CUST101";                                     //take it from session**(((((((((((

            ps.setString(1,custId );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(rs.getLong("atm_card_number")));
                row.add(rs.getString("customer_id"));
                row.add(rs.getString("account_number"));
                row.add(rs.getString("cvv"));
                row.add(rs.getString("pin"));
                row.add(String.valueOf(rs.getDate("issued_date")));
                row.add(String.valueOf(rs.getDate("expiry_date")));
                row.add(rs.getString("card_status"));
                cardData.add(row);
            }

            ListModelList<List<String>> model = new ListModelList<>(cardData);
            cardListbox.setModel(model);

        } catch (SQLException e) {
            Messagebox.show("Error loading card data: " + e.getMessage(), "Database Error", Messagebox.OK, Messagebox.ERROR);
            e.printStackTrace();
        }
    }
    
   

       

    
    @Listen("onClick=button")
    public void showDetails(Event e)
    {
       
         Component targetButton= e.getTarget();
       
          Listitem item =  (Listitem) targetButton.getParent().getParent();
         //  List<Listcell> lc= items.getChildren();
         //    Listcell ll  =((Listcell) lc.get(0));
         //    String s=ll.getLabel();
         // or
          
          String atmNumber = ((Listcell) item.getChildren().get(0)).getLabel();        
          Sessions.getCurrent().setAttribute("atmNumber", atmNumber);
          Executions.sendRedirect("/Card/cardDetails.zul");
             
    }
  
}












