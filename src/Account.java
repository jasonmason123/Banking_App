import java.io.Serializable;

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

    public String getAccountOwner() {
        return AccountOwner;
    }

    public void setAccountOwner(String AccountOwner) {
        this.AccountOwner = AccountOwner;
    }

//Account security
    
    public boolean verifyAccessByPassword() {
        return SecurityMethods.inputPassword(Etpd);
    }

    public void setPassword() {
        System.out.println("Current password:");
        boolean check = verifyAccessByPassword();
        if (check == true){
            System.out.println("New password:");
            String newPassword = Inputter.inputString();
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
        updateBalance(Inputter.inputPosDouble());
        System.out.println("Transaction success!");
    }
    
    public boolean withdrawMoney() {
        System.out.println("How much do you want to withdraw?");
        double Amount = Inputter.inputPosDouble();
        if (this.Balance >= Amount) {
            System.out.println("Please verify your transaction by your password:");
            boolean Verify = verifyAccessByPassword();
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
        double Amount = Inputter.inputPosDouble();
        if (this.Balance >= Amount) {
            System.out.println("Please verify your transaction by your password:");
            boolean Verify = verifyAccessByPassword();
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
