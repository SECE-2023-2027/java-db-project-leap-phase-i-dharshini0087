package UserAcc;
import java.util.*;


public class Account {
    private int accountnum;
    private String accounttype;
    private long phone;
    private long balance;
    private String address;
    private String branch;
    private String password;
    private String username;
    private boolean loggedIn;

   
    public Account(int accountnum, String accounttype, long phone, long balance, String address, String branch, String password, String username) {
        this.accountnum = accountnum;
        this.accounttype = accounttype;
        this.phone = phone;
        this.balance = balance;
        this.address = address;    
        this.branch = branch;
        this.password = password;
        this.username = username;
        this.loggedIn = false;
    }

  
    public int getAccountnum() {
        return accountnum;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    protected String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

  
    public static class Transaction {
        private int transactionId;
        private int accountNumber;
        private String transactionType;
        private long amount;
        private long balanceAfterTransaction;
        private static int transactionCounter = 0;
        private static List<Transaction> transactionHistory = new ArrayList<>();

       
        public Transaction(int accountNumber, String transactionType, long amount, long balanceAfterTransaction) {
            this.transactionId = ++transactionCounter;
            this.accountNumber = accountNumber;
            this.transactionType = transactionType;
            this.amount = amount;
            this.balanceAfterTransaction = balanceAfterTransaction;
            transactionHistory.add(this);
        }

      
        public static void deposit(Account account, long amount) {
            if (amount > 0) {
                long newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                new Transaction(account.getAccountnum(), "Deposit", amount, newBalance);
                System.out.println("Deposited " + amount + ". New balance: " + newBalance);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        // Perform withdrawal
        public static void withdraw(Account account, long amount) {
            if (amount > 0 && amount <= account.getBalance()) {
                long newBalance = account.getBalance() - amount;
                account.setBalance(newBalance);
                new Transaction(account.getAccountnum(), "Withdrawal", amount, newBalance);
                System.out.println("Withdrew " + amount + ". New balance: " + newBalance);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        }

        // View transaction history
        public static void viewTransactionHistory() {
            if (transactionHistory.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("\nTransaction History:");
                for (Transaction t : transactionHistory) {
                    System.out.println("Transaction ID: " + t.transactionId);
                    System.out.println("Account Number: " + t.accountNumber);
                    System.out.println("Transaction Type: " + t.transactionType);
                    System.out.println("Amount: " + t.amount);
                    System.out.println("Balance After Transaction: " + t.balanceAfterTransaction);
                    System.out.println("----------------------------");
                }
            }
        }
    }

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an account
        System.out.println("Create a New Account");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        int accountnum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Account Type (e.g., Savings, Checking): ");
        String accounttype = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        long phone = scanner.nextLong();

        System.out.print("Enter Initial Balance: ");
        long balance = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();

        System.out.print("Set Password: ");
        String password = scanner.nextLine();

        Account account = new Account(accountnum, accounttype, phone, balance, address, branch, password, username);

        // Login functionality
        if (login(account, scanner)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Transaction History");
                System.out.println("5. Set New Password");
                System.out.println("6. Logout");
                System.out.println("7. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        long depositAmount = scanner.nextLong();
                        Transaction.deposit(account, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        long withdrawAmount = scanner.nextLong();
                        Transaction.withdraw(account, withdrawAmount);
                        break;
                    case 4:
                        Transaction.viewTransactionHistory();
                        break;
                    case 5:
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        account.setPassword(newPassword);
                        System.out.println("Password updated successfully.");
                        break;
                    case 6:
                        logout(account);
                        if (login(account, scanner)) {
                            continue;
                        } else {
                            exit = true;
                        }
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

        scanner.close();
    }

  
    public static boolean login(Account account, Scanner scanner) {
        System.out.print("\nTo access your account, enter your password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredPassword.equals(account.getPassword())) {
            account.setLoggedIn(true);
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect password. Access denied.");
            return false;
        }
    }

   
    public static void logout(Account account) {
        account.setLoggedIn(false);
        System.out.println("You have been logged out.");
    }
}