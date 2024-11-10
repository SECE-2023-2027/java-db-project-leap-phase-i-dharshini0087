package UserAcc;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDBClass {
	
		    public int checkLogin(String username,String password) {
			String query ="select * from admin where username= ? and password =?";
			try(Connection con=new dbconnection().getConnection();
					PreparedStatement pst=con.prepareStatement(query)){
				pst.setString(1,username);
				pst.setString(2,password);
				ResultSet res=pst.executeQuery();
				if(res.next()) {
					return res.getInt("adminid") ;
					
				} else {
					return 0 ;
				}
			}catch(Exception e ) {
				System.out.println("Error occurred in getting the admin");
			}
			return 0;
		}
}
