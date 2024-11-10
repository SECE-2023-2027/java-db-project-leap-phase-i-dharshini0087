package UserAcc;


import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        HashMap<Long, Account> accounts = new HashMap<>();
       
        HashMap<Long, Transactions> transactions = new HashMap<>();
        
        long transactionCounter = 1;
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBank Application Menu:");
            System.out.println("1.Admin Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Display Account Details");
            System.out.println("4. Check Balance");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Update Account");
            System.out.println("9. Set Password");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                
                System.out.print("Enter admin username: ");
                String adminUsername = scanner.next();
                System.out.print("Enter admin password: ");
                String adminPassword = scanner.next();
                Admin admin = new Admin();
                int loggedInAdmin = admin.checkLogin(adminUsername, adminPassword);
                System.out.println(loggedInAdmin);
                if (loggedInAdmin != 0) {
                    System.out.println("Admin logged in successfully!");

                 
                    boolean adminExit = false;
                    while (!adminExit) {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. View All Accounts");
                        System.out.println("2. View All Transactions");
                        System.out.println("3. Log Out");
                        System.out.print("Enter your choice: ");
                        int adminChoice = scanner.nextInt();

                        switch (adminChoice) {
                            case 1:
                                System.out.println("Displaying all accounts:");
                                for (Account acc : accounts.values()) {
                                    acc.displayAccountDetails();
                                }
                                break;

                            case 2:
                                System.out.println("Displaying all transactions:");
                                for (Transactions trans : transactions.values()) {
                                    trans.report();
                                }
                                break;

                            case 3:
                                adminExit = true;
                                System.out.println("Logging out of admin account...");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid admin credentials. Access denied.");
                }
                break;
                case 2:
                   
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter account number: ");
                    long accNo = scanner.nextLong();
                    System.out.print("Enter account type (e.g., Savings, Checking): ");
                    String accType = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    System.out.print("Enter phone number: ");
                    long phoneNo = scanner.nextLong();
                    System.out.print("Enter address: ");
                    scanner.nextLine(); // consume newline
                    String address = scanner.nextLine();
                    System.out.print("Enter branch: ");
                    String branch = scanner.next();
                    Account newAccount = new Account(username, accNo, accType, balance, password, phoneNo, address, branch);
                    accounts.put(accNo, newAccount);
                    System.out.println("Account created successfully!");
                    break;
                case 3:
                  
                    System.out.print("Enter account number: ");
                    accNo = scanner.nextLong();
                    Account account = accounts.get(accNo);
                    if (account != null) {
                        account.displayAccountDetails();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                  
                    System.out.print("Enter account number: ");
                    accNo = scanner.nextLong();
                    account = accounts.get(accNo);
                    if (account != null) {
                        System.out.println("Balance: $" + account.checkBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
               
                case 5:  // Deposit
                    System.out.print("Enter account number: ");
                    long accNo1 = scanner.nextLong();
                    
                    // Debugging: Print the current state of accounts
                    System.out.println("Accounts in the system: " + accounts);

                    // Look up the account by account number
                    Account account1 = accounts.get(accNo1);
                    if (account1 != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        
                        // Create a Transaction object to deposit
                        Transactions depositTransaction = new Transactions(account1.getAcc_no(), account1.getAcc_no(), "Deposit", transactionCounter++, new Date(), depositAmount);
                        depositTransaction.deposit(account1, depositAmount);  // This will also insert the transaction into the DB
                        transactions.put(depositTransaction.getTransactionId(), depositTransaction);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 6:
                    // Withdraw
                    System.out.print("Enter account number: ");
                    accNo1 = scanner.nextLong();
                    account1 = accounts.get(accNo1);
                    if (account1 != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        
                        // Create a Transaction object to withdraw
                        Transactions withdrawTransaction = new Transactions(account1.getAcc_no(), account1.getAcc_no(), "Withdraw", transactionCounter++, new Date(), withdrawAmount);
                        withdrawTransaction.withdraw(account1, withdrawAmount);  // This will also insert the transaction into the DB
                        transactions.put(withdrawTransaction.getTransactionId(), withdrawTransaction);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    // Transfer
                    System.out.print("Enter source account number: ");
                    long fromAccNo = scanner.nextLong();
                    System.out.print("Enter destination account number: ");
                    long toAccNo = scanner.nextLong();
                    account1 = accounts.get(fromAccNo);
                    Account toAccount = accounts.get(toAccNo);
                    if (account1 != null && toAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        
                        // Create a Transaction object to transfer
                        Transactions transferTransaction = new Transactions(account1.getAcc_no(), toAccount.getAcc_no(), "Transfer", transactionCounter++, new Date(), transferAmount);
                        transferTransaction.transfer(account1, toAccount, transferAmount);  // This will also insert the transaction into the DB
                        transactions.put(transferTransaction.getTransactionId(), transferTransaction);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                case 8:
                   
                    System.out.print("Enter account number to update: ");
                    accNo1 = scanner.nextLong();
                    account1 = accounts.get(accNo1);
                    if (account1 != null) {
                        System.out.print("Enter new username: ");
                        String newUsername = scanner.next();
                        System.out.print("Enter new phone number: ");
                        long newPhoneNo = scanner.nextLong();
                        System.out.print("Enter new address: ");
                        scanner.nextLine(); // consume newline
                        String newAddress = scanner.nextLine();
                        System.out.print("Enter new branch: ");
                        String newBranch = scanner.next();
                        account1.update(newUsername, newPhoneNo, newAddress, newBranch);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 9:
                   
                    System.out.print("Enter account number to set password: ");
                    accNo1 = scanner.nextLong();
                    account1 = accounts.get(accNo1);
                    if (account1 != null) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.next();
                        account1.setPassword(newPassword);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 10:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}