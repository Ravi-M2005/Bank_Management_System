import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements Serializable {
    String name;
    String pass;
    public Admin(String name, String pass) {
            this.name = name;
            this.pass = pass;
    }
    public boolean validateAdmin (String password){
        return this.pass.equals(password);
    }

    public void viewAll(ArrayList<BankAccount> list){
        for(BankAccount b : list){
            System.out.println("Name : " + b.getName());
            System.out.println("Account Number : " + b.getAcc());
            System.out.println("Balance : " + b.getBalance());
            //System.out.println("Transcation History : ");
            b.showHistory();
            System.out.println("-".repeat(30));
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean validate(String pass) {
        return this.pass.equals(pass);
    }
}
