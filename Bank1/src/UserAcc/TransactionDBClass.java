package UserAcc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class TransactionDBClass {
    
    public boolean insertTransaction(long fromAccount, long toAccount, String type, Date date, double amount) {
        String query = "INSERT INTO transactions (from_account, to_account, type, date, amount) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = new dbconnection().getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setLong(1, fromAccount);
            pst.setLong(2, toAccount);
            pst.setString(3, type);
            pst.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
            pst.setDouble(5, amount);
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error occurred while inserting transaction.");
            e.printStackTrace();
            return false;
        }
    }

    public void viewAllTransactions() {
        String query = "SELECT * FROM transactions";
        
        try (Connection con = new dbconnection().getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet res = pst.executeQuery()) {
            
            while (res.next()) {
                System.out.println("Transaction ID: " + res.getInt("transaction_id"));
                System.out.println("From Account: " + res.getLong("from_account"));
                System.out.println("To Account: " + res.getLong("to_account"));
                System.out.println("Type: " + res.getString("type"));
                System.out.println("Date: " + res.getTimestamp("date"));
                System.out.println("Amount: $" + res.getDouble("amount"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving transactions.");
            e.printStackTrace();
        }
    }
}
