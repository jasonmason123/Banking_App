
import Validators.*;

public class Banking_App {
    
    public static void main(String[] args) {
        
        AccountList AccLst = new AccountList();
//        AccLst.add(new Account("00123", "John", "h2qbz3"));
//        AccLst.add(new Account("00124", "Joe", "h2qbz3"));
//        AccLst.save();
        AccLst.load();
        
        String[] LogIn = {"Customer", "Administrator", "Exit"};
        int LogInAs;
        System.out.println("---==============Banking app==============---");
        System.out.println("Log in as:");
        LogInAs = Menu.int_getChoice(LogIn);
        
        if (LogInAs == 1) {
            Account CustomerAccount;
            boolean valid = false;
            String[] CustomerMenu = {"Set/Reset password", "Add money to balance", "Withdraw money", "Transfer money", "Exit"};
            System.out.println("---==============Customer login==============---");

            System.out.println("Account number:");
            do {
                CustomerAccount = AccLst.searchAccount();
                if(CustomerAccount == null) System.out.println("Please try again:");
            } while(CustomerAccount == null);
            
            System.out.println("Your password:");
            boolean check = CustomerAccount.verifyAccessByPassword();
            if (check) {
                valid = true;
            } else {
                System.err.println("Access denied!");
                return;
            }

            if (valid) {
                System.out.println("\nWelcome, " + CustomerAccount.getAccountOwner() + '!');
                int choice;
                do {
                    System.out.println("---=========" + CustomerAccount.getAccountOwner() + "'s account=========---");
                    CustomerAccount.output();
                    System.out.println("---==============Menu==============---");
                    choice = Menu.int_getChoice(CustomerMenu);
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
                            System.out.println("Destination account number:");
                            Account Destination = AccLst.searchAccount();
                            if (Destination != null) {
                                CustomerAccount.transferMoney(Destination);
                            }
                            break;
                        default:
                            System.out.println("Have a great day!");
                            break;
                    }
                } while (choice > 0 && choice < 5);
            }
        }
        
        else if (LogInAs == 2) {
            System.out.println("Admin login:");
            String pswd = SecurityMethods.encryptThisString("admin");
            boolean valid = SecurityMethods.checkCorrectPassword(pswd);
            if (valid) {
                String[] AdminMenu = {"List all accounts", "Search an account", "Add an account", "Delete an account", "Exit"};
                int choice;
                do {
                    System.out.println("---==============Administrator==============---");
                    choice = Menu.int_getChoice(AdminMenu);
                    switch (choice) {
                        case 1:
                            AccLst.listAll();
                            break;
                        case 2:
                            System.out.println("Destination account number:");
                            Account acc = AccLst.searchAccount();
                            if(acc != null) System.out.println(acc);
                            break;
                        case 3:
                            AccLst.addAccount();
                            break;
                        case 4:
                            AccLst.deleteAccount();
                            break;
                        default:
                            System.out.println("Have a nice day!");
                            break;
                    }
                }while (choice > 0 && choice < 5);
            }
        }
        AccLst.save();
    }
}
