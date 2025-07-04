import java.io.*;
import java.util.*;

public class Main {
    static final String ACCOUNT_FILE = "accounts.dat";
    static final String ADMIN_FILE = "admins.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = loadAccounts();
        ArrayList<Admin> admins = loadAdmins();

        // If no admins exist, create default
        if (admins.isEmpty()) {
            admins.add(new Admin("admin", "admin123"));
            saveAdmins(admins);
        }

        System.out.println("Welcome to Indian Bank");

        while (true) {
            System.out.println("1. User Login");
            System.out.println("2. Create Bank Account");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            int n = sc.nextInt();

             if (n == 1) {
                System.out.print("Enter Account Number: ");
                int acc = sc.nextInt();
                boolean found = false;

                for (BankAccount b : accounts) {
                    if (b.getAcc() == acc) {
                        System.out.print("Enter PIN: ");
                        int pin = sc.nextInt();
                        if (b.getPIN() == pin) {
                            System.out.println("Login successful!");
                            while (true) {
                                System.out.println("\n1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Balance");
                                System.out.println("4. History");
                                System.out.println("5. Calculate Interest");
                                System.out.println("6. Logout");

                                int ch = sc.nextInt();
                                if (ch == 1) {
                                    System.out.print("Enter amount: ");
                                    double amt = sc.nextDouble();
                                    b.Deposit(amt);
                                    saveAccounts(accounts);
                                } else if (ch == 2) {
                                    System.out.print("Enter amount: ");
                                    double amt = sc.nextDouble();
                                    b.withdraw(amt);
                                    saveAccounts(accounts);
                                } else if (ch == 3) {
                                    System.out.printf("Balance: ₹%.2f\n", b.getBalance());
                                } else if (ch == 4) {
                                    b.showHistory();
                                } else if (ch == 5) {
                                    System.out.print("Enter rate (%): ");
                                    double r = sc.nextDouble();
                                    System.out.print("Enter time (years): ");
                                    double t = sc.nextDouble();
                                    double p = b.getBalance();
                                    double A = p * Math.pow((1 + r / 100), t);
                                    double interest = A - p;
                                    System.out.printf("Interest: ₹%.2f\n", interest);
                                    System.out.printf("Total after %.1f years: ₹%.2f\n", t, A);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Incorrect PIN!");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Account Doesn't Exist!");

            } else if (n == 2) {
                 sc.nextLine();
                 System.out.print("Enter Name: ");
                 String name = sc.nextLine();

                 System.out.print("Enter Account Number: ");
                 int acc = sc.nextInt();

                 boolean exists = false;
                 for (BankAccount b : accounts) {
                     if (b.getAcc() == acc) {
                         System.out.println("Account already exists!");
                         exists = true;
                         break;
                     }
                 }
                 if (exists) continue;

                 System.out.print("Set your PIN: ");
                 int pin = sc.nextInt();

                 System.out.print("Enter Initial Deposit: ");
                 double amount = sc.nextDouble();

                 accounts.add(new BankAccount(name, acc, pin, amount));
                 saveAccounts(accounts);
                 System.out.println("Account created successfully!");

             }else if (n == 3) {
                sc.nextLine();
                System.out.print("Enter Admin Username: ");
                String user = sc.nextLine();
                System.out.print("Enter Password: ");
                String pass = sc.nextLine();
                Admin loggedInAdmin = null;

                for (Admin a : admins) {
                    if (a.getName().equals(user) && a.validate(pass)) {
                        loggedInAdmin = a;
                        break;
                    }
                }

                if (loggedInAdmin != null) {
                    System.out.println("Admin Login Successful!");
                    while (true) {
                        System.out.println("\n1. View All Accounts");
                        System.out.println("2. Create New Admin");
                        System.out.println("3. Delete Bank Account");
                        System.out.println("4. Logout");

                        int ch = sc.nextInt();
                        sc.nextLine();

                        if (ch == 1) {
                            loggedInAdmin.viewAll(accounts);
                        } else if (ch == 2) {
                            if (admins.size() < 3) {
                                System.out.print("Enter new admin name: ");
                                String newName = sc.nextLine();
                                System.out.print("Enter password: ");
                                String newPass = sc.nextLine();
                                admins.add(new Admin(newName, newPass));
                                saveAdmins(admins);
                                System.out.println("New admin added!");
                            } else {
                                System.out.println("Max 3 admins allowed!");
                            }
                        }
                        else if (ch == 3) {
                            System.out.print("Enter Account Number To Delete: ");
                            int acc = sc.nextInt();
                            Iterator<BankAccount> it = accounts.iterator();
                            boolean deleted = false;
                            while (it.hasNext()) {
                                BankAccount b = it.next();
                                if (b.getAcc() == acc) {
                                    System.out.println("Are You Sure to delete your Account?");
                                    System.out.println("1. Continue\n2. Cancel");
                                    int confirm = sc.nextInt();
                                    if (confirm == 1) {
                                        it.remove();
                                        saveAccounts(accounts);
                                        System.out.println("Account deleted.");
                                    }
                                    deleted = true;
                                    break;
                                }
                            }
                            if (!deleted) System.out.println("Account not found!");

                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid admin credentials!");
                }

            } else if (n == 4) {
                System.out.println("Thanks for using Indian Bank!");
                break;
            }
        }

        sc.close();
    }

    public static void saveAccounts(ArrayList<BankAccount> list) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE));
            out.writeObject(list);
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving accounts.");
        }
    }

    public static ArrayList<BankAccount> loadAccounts() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE));
            ArrayList<BankAccount> list = (ArrayList<BankAccount>) in.readObject();
            in.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveAdmins(ArrayList<Admin> list) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADMIN_FILE));
            out.writeObject(list);
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving admins.");
        }
    }

    public static ArrayList<Admin> loadAdmins() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(ADMIN_FILE));
            ArrayList<Admin> list = (ArrayList<Admin>) in.readObject();
            in.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
