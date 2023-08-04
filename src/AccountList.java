import java.util.*;
import java.lang.*;
import java.io.*;

public class AccountList extends TreeMap<String, Account> {
    
    public void listAll() {
        for (Account acc: this.values()) System.out.println(acc);
    }
    
    public Account found(String AccNumber) {
        return this.get(AccNumber);
    }
    
    public boolean numberNotDuplicated(String Number) {
        return found(Number) != null;
    }
    
    public Account searchAccount() {
        System.out.println("Destination account number:");
        String AccNum = Inputter.inputNotBlank();
        Account result = found(AccNum);
        if (result == null) System.out.println("(!)Account not found!");
        return result;
    }
    
    public void addAccount() {
        String AccNum;
        System.out.println("New account number");
        do {
            AccNum = Inputter.inputNotBlank();
        } while (numberNotDuplicated(AccNum) == true);
        System.out.println("Account owner:");
        String AccOwner = Inputter.inputNotBlank();
        System.out.println("Initiate password:");
        String Psswrd = Inputter.inputNotBlank();
        Account newAcc = new Account(AccNum, AccOwner, Psswrd);
        this.put(AccNum, newAcc);
        System.out.println("Account " + AccNum + ", owned by Mr./Ms./Mrs." + AccOwner + " was successfully added!");
    }
    
    public void deleteAccount() {
        System.out.println("Delete account");
        System.out.println("Account number:");
        String AccNum = Inputter.inputNotBlank();
        Account result = this.remove(AccNum);
        if (result != null) System.out.println("Account " + result.getAccountNumber() + " has been deleted!");
        else System.out.println("(!)Account not found!");
    }
    
    public void save() {
        try {
            // Serialize all Accounts to a file
            FileOutputStream fileOut = new FileOutputStream("AccountList.ser");
            ObjectOutputStream accOut = new ObjectOutputStream(fileOut);
            for (Account acc : this.values()) accOut.writeObject(acc);
            accOut.close();
            fileOut.close();
            //System.out.println("All accounts are serialized and saved to 'AccountList.ser'");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
         try {
            // Deserialize all Accounts from the file
            FileInputStream fileIn = new FileInputStream("AccountList.ser");
            ObjectInputStream accIn = new ObjectInputStream(fileIn);
            while(true) {
                try {
                    Account acc = (Account) accIn.readObject();
                    this.put(acc.getAccountNumber(), acc);
                }
                catch(EOFException e) {
                    break;
                }
            }
            accIn.close();
            fileIn.close();
            //System.out.println("All accounts are deserialized from 'AccountList.ser' and added to AccountList");

        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
