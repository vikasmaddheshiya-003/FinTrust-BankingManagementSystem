package controller;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.google.javascript.jscomp.serialization.NonLazyTypedAst.List;

import Service.CardServices;
import dao.DbConnection;

//SELECT * FROM fintrustbank.card_request;
//use fintrustbank;
//
//CREATE TABLE IssuedATMCard (
//    atm_card_number BIGINT PRIMARY KEY AUTO_INCREMENT,
//    customer_id varchar(50) NOT NULL,
//    account_number varchar(50)  NOT NULL,
//    cvv CHAR(3) NOT NULL,
//    pin CHAR(4) NOT NULL,
//    issued_date DATE ,
//    expiry_date DATE GENERATED ALWAYS AS (DATE_ADD(issued_date, INTERVAL 3 YEAR)) STORED,
//    
//    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
//    FOREIGN KEY (account_number) REFERENCES account(account_no)
//) AUTO_INCREMENT = 5467346754768709;


public class manageCardController extends SelectorComposer<Window> {

	
	
	
	@Wire
	Listbox cardList;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		Connection con=DbConnection.getConnection();
		CardServices ob=new CardServices();
		ResultSet rs=ob.getCardList();
	
		ArrayList<ArrayList<String>> cardData = new ArrayList<>();
		while(rs.next())
		{  ArrayList<String> row;
			System.out.println(rs.getString(4));
		}
		
		
	}
	
	public static void main(String[] args) {
		manageCardController ob=new manageCardController();
		Window w=new Window();
		try {
			ob.doAfterCompose(w);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
