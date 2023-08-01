import java.util.*;
import java.lang.*;
import java.io.*;

public class Account implements Serializable {
    private String AccountNumber,
                   AccountOwner;
    private transient String EncryptPassword;
    private double Balance = 0;
    
    public Account() {
    }

    public Account(String AccountNumber, String AccountOwner, String InitialPassword) {
        this.AccountNumber = AccountNumber;
        this.AccountOwner = AccountOwner;
        this.EncryptPassword = SecurityMethods.encryptThisString(InitialPassword);
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
    
//Can save passwords into a txt file
//Use encryption methods, like hashing
    public String getEncryptPassword() {
        return EncryptPassword;
    }

    public void setPassword() {
        boolean check = SecurityMethods.inputPassword(getEncryptPassword());
        if (check == true){
            System.out.println("What's your new password?");
            String newPassword = Inputter.inputNotBlank();
            this.EncryptPassword = SecurityMethods.encryptThisString(newPassword);
            System.out.println("New password has been updated!");
        }
    }

//Balance
    private double getBalance() {
        return Balance;
    }
    
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
        System.out.println("Please verify your transaction:");
        boolean Verify = SecurityMethods.inputPassword(getEncryptPassword());
        if (this.Balance >= Amount && Verify==true) {
            this.Balance -= Amount;
            System.out.println("Transaction success!");
            return true;
        }
        else {
            System.out.println("Your balance is less than " + Amount + "\n(!)Transaction failed");
            return false;
        }
    }
    
    public boolean transferMoney(Account Destination) {
        System.out.println("How much do you want to transfer?");
        double Amount = Inputter.inputAmount();
        System.out.println("Please verify your transaction:");
        boolean Verify = SecurityMethods.inputPassword(getEncryptPassword());
        if (this.Balance >= Amount) {
            this.Balance -= Amount;
            Destination.updateBalance(Amount, "ADD");
            System.out.println("Transaction success!");
            return true;
        }
        else {
            System.out.println("Your balance is less than " + Amount + "\n(!)Transaction failed");
            return false;
        }
    }

//output
    @Override
    public String toString() {
        return "Account{" + "AccountNumber=" + AccountNumber + ", AccountOwner=" + AccountOwner + '}';
    }
    
    public void output() {
        System.out.println(AccountOwner + "'s account:");
        boolean check = SecurityMethods.inputPassword(getEncryptPassword());
        if (check == true) System.out.println("Account: " + AccountNumber +"\nOwner: " + AccountOwner + "\nBalance: " + Balance + '$');
    }
}
