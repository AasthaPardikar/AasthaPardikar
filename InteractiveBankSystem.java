import java.util.*;

// Abstract Base class
abstract class BankAccount {
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;
    protected List<String> transactionHistory = new ArrayList<>();

    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        transactionHistory.add("Account opened with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount);

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + accountHolder + ":");
        for (String record : transactionHistory) {
            System.out.println(" - " + record);
        }
    }

    public void displayAccountInfo() {
        System.out.println("---- Account Info ----");
        System.out.println("Holder: " + accountHolder);
        System.out.println("Account #: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

// Savings Account
class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 500;
    private static final double INTEREST_RATE = 0.03; // 3%

    public SavingsAccount(String holder, String accNo, double balance) {
        super(holder, accNo, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Withdrawal failed. Minimum balance must be maintained.");
        }
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        transactionHistory.add("Interest applied: " + interest);
        System.out.println("Interest of " + interest + " applied. New Balance: " + balance);
    }
}

// Current Account
class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 1000;

    public CurrentAccount(String holder, String accNo, double balance) {
        super(holder, accNo, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Withdrawal denied. Overdraft limit exceeded.");
        }
    }
}

// Main Class with User Interaction
public class InteractiveBankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("Welcome to Java Bank!");
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Choose account type (1. Savings, 2. Current): ");
        int type = scanner.nextInt();

        System.out.print("Enter initial deposit: ");
        double initial = scanner.nextDouble();

        if (type == 1) {
            account = new SavingsAccount(name, "SA" + new Random().nextInt(9999), initial);
        } else if (type == 2) {
            account = new CurrentAccount(name, "CA" + new Random().nextInt(9999), initial);
        } else {
            System.out.println("Invalid account type selected.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Info");
            System.out.println("4. View Transaction History");
            if (account instanceof SavingsAccount) {
                System.out.println("5. Apply Interest");
            }
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Amount to deposit: ");
                    account.deposit(scanner.nextDouble());
                    break;
                case 2:
                    System.out.print("Amount to withdraw: ");
                    account.withdraw(scanner.nextDouble());
                    break;
                case 3:
                    account.displayAccountInfo();
                    break;
                case 4:
                    account.printTransactionHistory();
                    break;
                case 5:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).applyInterest();
                    } else {
                        System.out.println("Interest can only be applied to savings accounts.");
                    }
                    break;
                case 0:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
