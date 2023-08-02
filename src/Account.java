import java.util.*;
import java.lang.*;
import java.io.*;

public class Account implements Serializable {
    private static final long serialVersionUID = 3086936957232207550L;
    private String AccountNumber,
                   AccountOwner;
    private String Etpd;
    private double Balance = 0;
    
    public Account() {
    }

    public Account(String AccountNumber, String AccountOwner, String InitialPassword) {
        this.AccountNumber = AccountNumber;
        this.AccountOwner = AccountOwner;
        this.Etpd = SecurityMethods.encryptThisString(InitialPassword);
    }

//Account number and owner
    public String getAccountNumber() {
        return AccountNumber;
    }
    
    //This method can be removed if we can make a generateAccountNumber() method.
    //Recommend doing researches on bank account number
    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public String getAccountOwner() {
        return AccountOwner;
    }

    public void setAccountOwner(String AccountOwner) {
        this.AccountOwner = AccountOwner;
    }

//Account security
    public String getEtpd() {
        return Etpd;
    }

    public void setPassword() {
        boolean check = SecurityMethods.inputPassword(getEtpd());
        if (check == true){
            System.out.println("What's your new password?");
            String newPassword = Inputter.inputNotBlank();
            this.Etpd = SecurityMethods.encryptThisString(newPassword);
            System.out.println("New password has been updated!");
        }
    }

//Balance
    
    private void updateBalance (double Amount) {
        this.Balance += Amount;
    }
    
    private void updateBalance(double Amount, String mode) {
        if (mode.equals("ADD")) this.Balance += Amount;
        else if (mode.equals("SUB") && this.Balance >= Amount) this.Balance -= Amount;
        else throw new IllegalArgumentException("Invalid mode or balance smaller than amount! \t Accepted mode: ADD, SUB");
    }
    
    public void addMoney() {
        System.out.println("How much do you want to add to your balance?");
        double Amount = Inputter.inputAmount();
        this.Balance += Amount;
        System.out.println("Transaction success!");
    }
    
    public boolean withdrawMoney() {
        System.out.println("How much do you want to withdraw?");
        double Amount = Inputter.inputAmount();
        if (this.Balance >= Amount) {
            System.out.println("Please verify your transaction:");
            boolean Verify = SecurityMethods.inputPassword(getEtpd());
            if (Verify) {
                this.Balance -= Amount;
                System.out.println("Transaction success!");
            }
            return Verify;
        }
        else {
            System.out.println("Your balance is less than " + Amount + "\n(!)Transaction failed");
            return false;
        }
    }
    
    public boolean transferMoney(Account Destination) {
        if (Destination == this) {
            System.out.println("(!)Invalid account number! Cannot transfer money to own account!");
            return false;
        }
        System.out.println("How much do you want to transfer?");
        double Amount = Inputter.inputAmount();
        if (this.Balance >= Amount) {
            System.out.println("Please verify your transaction:");
            boolean Verify = SecurityMethods.inputPassword(getEtpd());
            if (Verify) {
                this.Balance -= Amount;
                Destination.updateBalance(Amount, "ADD");
                System.out.println("Transaction success!");
            }
            return Verify;
        }
        else {
            System.out.println("Your balance is less than " + Amount + "\n(!)Transaction failed");
            return false;
        }
    }

//output
    @Override
    public String toString() {
        return "Account: " + AccountNumber + ", Owner: " + AccountOwner;
    }
    
    void output() {
        System.out.println("Account: " + AccountNumber +"\nOwner: " + AccountOwner + "\nBalance: " + Balance + '$');
    }
}
