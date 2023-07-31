import java.util.*;
import java.lang.*;

public class AccountList extends ArrayList<Account> {
    
    public void listAll() {
        for (Account acc: this) acc.output();
    }
    
    public Account found(String AccNumber) {
        for (Account acc : this) {
            if (acc.getAccountNumber().equals(AccNumber)) {
                return acc;
            }
        }
        System.out.println("(!)Account not found!");
        return null;
    }
    
    public boolean numberNotDuplicated(String Number) {
        Number = Number.trim().toUpperCase();
        return found(Number) != null;
    }
    
    public Account searchAccount() {
        System.out.println("Please input destination account number:");
        String AccNum = Inputter.inputNotBlank();
        Account result = found(AccNum);
        return result;
    }
    
    public void addAccount() {
        String AccNum;
        System.out.println("New account number:");
        do {
            AccNum = Inputter.inputNotBlank();
        } while (numberNotDuplicated(AccNum) == false);
        System.out.println("Account owner:");
        String AccOwner = Inputter.inputNotBlank();
        System.out.println("Initiate password:");
        String Psswrd = Inputter.inputNotBlank();
        Account newAcc = new Account(AccNum, AccOwner, Psswrd);
        this.add(newAcc);
        System.out.println("Account" + AccNum + "was successfully added!");
    }
    
    public void removeAccount() {
        System.out.println("Delete account:");
        System.out.println("(*)Please input account number:");
        String AccNum = Inputter.inputNotBlank();
        Account result = found(AccNum);
        if (result != null){
            this.remove(result);
            System.out.println("Account " + result.getAccountNumber() + " has been removed from list!");
        }
    }
}
