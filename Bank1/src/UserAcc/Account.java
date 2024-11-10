package UserAcc;
public class Account {
    private String username;
    private long acc_no;
    private String acc_type;
    private double balance;
    private String password;
    private long phone_no;
    private String address;
    private String branch;
    
    public Account(String username, long acc_no, String acc_type, double balance, String password, long phone_no, String address, String branch) {
        this.username = username;
        this.acc_no = acc_no;
        this.acc_type = acc_type;
        this.balance = balance;
        this.password = password;
        this.phone_no = phone_no;
        this.address = address;
        this.branch = branch;
    }
   
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public long getAcc_no() {
        return acc_no;
    }
    public void setAcc_no(long acc_no) {
        this.acc_no = acc_no;
    }
    public String getAcc_type() {
        return acc_type;
    }
    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        System.out.println("Password updated successfully.");
    }
    public long getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
   
    public void update(String newUsername, long newPhone_no, String newAddress, String newBranch) {
        setUsername(newUsername);
        setPhone_no(newPhone_no);
        setAddress(newAddress);
        setBranch(newBranch);
        System.out.println("Account details updated successfully.");
    }
    
    public double checkBalance() {
        return getBalance();
    }
    
    public void displayAccountDetails() {
        System.out.println("Account Details:");
        System.out.println("Username: " + getUsername());
        System.out.println("Account Number: " + getAcc_no());
        System.out.println("Account Type: " + getAcc_type());
        System.out.println("Balance: $" + getBalance());
        System.out.println("Phone Number: " + getPhone_no());
        System.out.println("Address: " + getAddress());
        System.out.println("Branch: " + getBranch());
    }
}