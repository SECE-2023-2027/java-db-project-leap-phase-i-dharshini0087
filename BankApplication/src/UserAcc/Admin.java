package UserAcc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void Main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create Admin account
        System.out.println("Bank Application");
        System.out.println("Create Admin Account");
        System.out.print("Enter Admin ID: ");
        int adminId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Admin Username: ");
        String adminUsername = scanner.nextLine();

        System.out.print("Set Admin Password: ");
        String adminPassword = scanner.nextLine();

        Admin admin = new Admin(adminId, adminUsername, adminPassword);

        // Admin login
        if (!Admin.adminLogin(admin, scanner)) {
            System.out.println("Exiting program...");
            scanner.close();
            return;
        }

        List<Account> accounts = new ArrayList<>(); // List to store user accounts

        boolean exit = false;
        while (!exit) {
            System.out.println("\nAdmin Actions:");
            System.out.println("1. Create User Account");
            System.out.println("2. View All User Accounts");
            System.out.println("3. Delete User Account");
            System.out.println("4. Logout and Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createUserAccount(accounts, scanner);
                    break;
                case 2:
                    viewAllAccounts(accounts);
                    break;
                case 3:
                    deleteUserAccount(accounts, scanner);
                    break;
                case 4:
                    System.out.println("Logging out and exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Method to create a user account
    private static void createUserAccount(List<Account> accounts, Scanner scanner) {
        System.out.println("\nCreate a New User Account");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        int accountnum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Account Type (e.g., Savings, Checking): ");
        String accounttype = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        long phone = scanner.nextLong();

        System.out.print("Enter Initial Balance: ");
        long balance = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine();

        System.out.print("Set Password: ");
        String password = scanner.nextLine();

        Account account = new Account(accountnum, accounttype, phone, balance, address, branch, password, username);
        accounts.add(account);
        System.out.println("User account created successfully!");
    }

    // Method to view all user accounts
    private static void viewAllAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("\nNo user accounts found.");
            return;
        }

        System.out.println("\nList of User Accounts:");
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountnum());
            System.out.println("Username: " + account.getUsername());
            System.out.println("Account Type: " + account.getAccounttype());
            System.out.println("Phone: " + account.getPhone());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Address: " + account.getAddress());
            System.out.println("Branch: " + account.getBranch());
            System.out.println("---------------------------");
        }
    }

    // Method to delete a user account
    private static void deleteUserAccount(List<Account> accounts, Scanner scanner) {
        System.out.print("\nEnter the Account Number of the user to delete: ");
        int accountnum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account accountToDelete = null;
        for (Account account : accounts) {
            if (account.getAccountnum() == accountnum) {
                accountToDelete = account;
                break;
            }
        }

        if (accountToDelete != null) {
            accounts.remove(accountToDelete);
            System.out.println("User account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }
}
