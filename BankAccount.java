import java.io.Serializable;
import java.util.ArrayList;

public class BankAccount implements Serializable {
    private String name;
    private int accountNumber;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String name, int accountNumber, int pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with ₹" + balance);
    }

    public int getAcc() {
        return accountNumber;
    }

    public int getPIN() {
        return pin;
    }

    public void Deposit(double amount) {
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
        transactionHistory.add("Deposited: ₹" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            transactionHistory.add("Withdrew: ₹" + amount);
        } else {
            System.out.println("Insufficient balance.");
            transactionHistory.add("Failed Withdraw: ₹" + amount);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void showHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String s : transactionHistory) {
                System.out.println("- " + s);
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
