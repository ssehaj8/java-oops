import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class BankAccount
abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public abstract double calculateInterest();

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

// Interface Loanable
interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

// Subclass SavingsAccount
class SavingsAccount extends BankAccount {
    private double interestRate = 0.04;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }
}

// Subclass CurrentAccount
class CurrentAccount extends BankAccount implements Loanable {
    private double overdraftLimit = 5000;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return 0; // No interest for Current Account
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan application submitted for Rs." + amount);
    }

    @Override
    public boolean calculateLoanEligibility() {
        return getBalance() > overdraftLimit;
    }
}

// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>();

        // Adding a Savings Account
        System.out.print("Enter Savings Account Number: ");
        String savingsAccNum = sc.nextLine();
        System.out.print("Enter Holder Name: ");
        String savingsHolder = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double savingsBalance = sc.nextDouble();
        sc.nextLine(); // Consume newline
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccNum, savingsHolder, savingsBalance);
        accounts.add(savingsAccount);

        // Adding a Current Account
        System.out.print("\nEnter Current Account Number: ");
        String currentAccNum = sc.nextLine();
        System.out.print("Enter Holder Name: ");
        String currentHolder = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double currentBalance = sc.nextDouble();
        sc.nextLine(); // Consume newline
        CurrentAccount currentAccount = new CurrentAccount(currentAccNum, currentHolder, currentBalance);
        accounts.add(currentAccount);

        // Display Account Details and Interest
        System.out.println("\nBank Account Details:");
        for (BankAccount account : accounts) {
            account.displayDetails();
            System.out.println("Interest Earned: Rs." + account.calculateInterest());
            if (account instanceof Loanable) {
                System.out.println("Eligible for Loan: " + ((Loanable) account).calculateLoanEligibility());
            }
            System.out.println("----------------");
        }
    }
}


/*
I/P->
Enter Savings Account Number: 345665432
Enter Holder Name: Sanya Khanna
Enter Initial Balance: 32000

Enter Current Account Number: 5678911
Enter Holder Name: Sehajpreet
Enter Initial Balance: 43000

O/P ->
Bank Account Details:
Account Number: 345665432
Holder Name: Sanya Khanna
Balance: 32000.0
Interest Earned: Rs.1280.0
----------------
Account Number: 5678911
Holder Name: Sehajpreet
Balance: 43000.0
Interest Earned: Rs.0.0
Eligible for Loan: true
----------------
 */