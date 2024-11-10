package UserAcc;

import java.util.Date;
import java.sql.*;

public class Transactions {
    private long fromAccount;
    private long toAccount;
    private String transactionType;
    private long transactionId;
    private Date date;
    private double amount;
    
    // Constructor
    public Transactions(long fromAccount, long toAccount, String transactionType, long transactionId, Date date, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
    }

    public long getFromAccount() {
        return fromAccount;
    }
    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }
    public long getToAccount() {
        return toAccount;
    }
    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
   
    // Deposit method
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited $" + amount + " successfully.");
        
        // Insert deposit transaction into the database
        TransactionDBClass transactionsDB = new TransactionDBClass();
        transactionsDB.insertTransaction(account.getAcc_no(), account.getAcc_no(), "Deposit", new Date(), amount);
    }
   
    // Withdraw method
    public void withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrew $" + amount + " successfully.");
            
            // Insert withdrawal transaction into the database
            TransactionDBClass transactionsDB = new TransactionDBClass();
            transactionsDB.insertTransaction(account.getAcc_no(), account.getAcc_no(), "Withdraw", new Date(), amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
    
    // Transfer method
    public void transfer(Account from, Account to, double amount) {
        if (from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            System.out.println("Transferred $" + amount + " successfully from account " + from.getAcc_no() + " to account " + to.getAcc_no() + ".");
            
            // Insert transfer transaction into the database
            TransactionDBClass transactionsDB = new TransactionDBClass();
            transactionsDB.insertTransaction(from.getAcc_no(), to.getAcc_no(), "Transfer", new Date(), amount);
        } else {
            System.out.println("Insufficient balance in the source account.");
        }
    }
   
    // Report method to display transaction details
    public void report() {
        System.out.println("Transaction Report:");
        System.out.println("Transaction ID: " + getTransactionId());
        System.out.println("From Account: " + getFromAccount());
        System.out.println("To Account: " + getToAccount());
        System.out.println("Type: " + getTransactionType());
        System.out.println("Date: " + getDate());
        System.out.println("Amount: $" + getAmount());
    }
}
