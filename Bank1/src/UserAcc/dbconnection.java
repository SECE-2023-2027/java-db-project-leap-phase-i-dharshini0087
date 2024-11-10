package UserAcc;
import java.sql.Connection;
import java.sql.DriverManager;
public class dbconnection {
	Connection con=null;
	String url="jdbc:mysql://localhost:3306/Bankapplication29";
	String userName="root";
	String password="Ani12@2006";
	public Connection getConnection()
	{
		if(con==null) {
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url,userName,password);
				System.out.println("Connection established successfully");
			}catch(Exception e) {
				System.out.println("Excpetion occured "+ e);
			}
			
		}
		return con;
	}
	public static void main(String[] args) {
		dbconnection connect=new dbconnection();
		Connection con=connect.getConnection();
	}
	
}