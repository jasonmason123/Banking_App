import java.util.*;
import java.lang.*;
import java.io.*;

public class Banking_App {
    private static Account CustomerAccount;
    
    public static void main(String[] args) {
        AccountList AccLst = new AccountList();
        Account JohnAccount = new Account("00123", "John", "h2qbz3");
        AccLst.add(JohnAccount);
        
        boolean valid;
        boolean check;
        
        String[] CustomerMenu = {"Set/Reset password", "Add money to balance", "Withdraw money", "Transfer money", "Display account information", "Exit"};
        System.out.println("---==============Banking app==============---");
        System.out.println("Customer log in:");
        
//        do {
//            
//        } while (valid != true || check != true);
        
        System.out.println("Account number:");
        String AccNum = Inputter.inputNotBlank();
        if (AccLst.found(AccNum) != null) {
            valid = true;
            CustomerAccount = AccLst.found(AccNum);
            check = SecurityMethods.inputPassword(CustomerAccount.getEncryptPassword());
        } else {
            valid = false;
            check = false;
        }
        
        
        if (valid && check) {
            System.out.println("\n(*)Welcome, " + CustomerAccount.getAccountOwner());
            Menu mn = new Menu();
            int choice;
            do {
                System.out.println("---==============Menu==============---");
                choice = mn.int_getChoice(CustomerMenu);
                switch (choice) {
                    case 1:
                        CustomerAccount.setPassword();
                        break;
                    case 2:
                        CustomerAccount.addMoney();
                        break;
                    case 3:
                        CustomerAccount.withdrawMoney();
                        break;
                    case 4:
                        Account Destination = AccLst.searchAccount();
                        CustomerAccount.transferMoney(Destination);
                        break;
                    case 5:
                        CustomerAccount.output();
                        break;
                    default:
                        System.out.println("Have a great day!");
                        AccLst.save();
                        break;
                }
            }while(choice > 0 && choice < 5);
        }
    }
}
