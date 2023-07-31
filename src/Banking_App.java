import java.util.*;
import java.lang.*;

public class Banking_App {
    public static void main(String[] args) {
        Account acc = new Account("00123", "John", "h2qbz3");
        Account acc1 = new Account("00124", "Jason", "h2qbz3");
        acc.addMoney();
        acc.transferMoney(acc1);
        acc.output();
        acc1.output();
    }
}
