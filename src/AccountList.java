import java.util.*;
import java.lang.*;
import java.io.*;

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
    
    public void deleteAccount() {
        System.out.println("Delete account:");
        System.out.println("(*)Please input account number:");
        String AccNum = Inputter.inputNotBlank();
        Account result = found(AccNum);
        if (result != null){
            this.remove(result);
            System.out.println("Account " + result.getAccountNumber() + " has been deleted!");
        }
    }
    
    public void save() {
        try {
            // Serialize the ArrayList to a file
            FileOutputStream fileOut = new FileOutputStream("AccountList.ser");
            ObjectOutputStream accOut = new ObjectOutputStream(fileOut);
            for (Account acc : this) accOut.writeObject(acc);
            accOut.close();
            fileOut.close();
            System.out.println("All accounts are serialized and saved to 'AccountList.ser'");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
         try {
            // Deserialize the ArrayList from the file
            FileInputStream fileIn = new FileInputStream("AccountList.ser");
            ObjectInputStream accIn = new ObjectInputStream(fileIn);
            while(true) {
                try {
                    Account acc = (Account) accIn.readObject();
                    this.add(acc);
                }
                catch(EOFException e) {
                    break;
                }
            }
            accIn.close();
            fileIn.close();
            System.out.println("All accounts are deserialized from 'AccountList.ser' and added to AccountList");

        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
