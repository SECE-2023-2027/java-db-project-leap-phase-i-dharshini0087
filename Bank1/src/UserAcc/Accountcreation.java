package UserAcc;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Accountcreation {
    
    public int checkLogin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection con = new dbconnection().getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getInt("adminid");
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error occurred in getting the admin");
        }
        return 0;
    }
    
    // New method to create a user account in the database
    public boolean createUserAccount(Account account) {
        String insertQuery = "INSERT INTO user_accounts (username, acc_no, acc_type, balance, password, phone_no, address, branch) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = new dbconnection().getConnection();
             PreparedStatement pst = con.prepareStatement(insertQuery)) {
            pst.setString(1, account.getUsername());
            pst.setLong(2, account.getAcc_no());
            pst.setString(3, account.getAcc_type());
            pst.setDouble(4, account.getBalance());
            pst.setString(5, account.getPassword());
            pst.setLong(6, account.getPhone_no());
            pst.setString(7, account.getAddress());
            pst.setString(8, account.getBranch());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error occurred while creating the user account: " + e.getMessage());
            return false;
        }
    }
}
