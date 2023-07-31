import java.util.*;
import java.lang.*;

public class Inputter {
//Make sure that input field is not blank. If it's blank, input again
    public static String inputNotBlank() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.println("(*)Please input:");
            s = sc.nextLine().trim();
        } while (s.isEmpty() == true);
        return s;
    }

//Make sure that input data is in correct format. If not, input again    
    public static String inputPattern (String pattern) {
        String input;
        do {
            System.out.println("(*)Please input:");
            input = inputNotBlank();
        } while (input.matches(pattern) == false);
        return input;
    }

//Make sure the number inputted is not below 0. If it is, input again    
    public static double inputAmount() {
        double Amount;
        do {
            try{
                System.out.println("(*)Please input amount: ");
                Scanner sc = new Scanner(System.in);
                Amount = sc.nextDouble();
            }
            catch (Exception e) {
                System.out.println("(!)Invalid input! Amount must be a real number bigger than 0!");
                Amount = -1;
            }    
        } while (Amount < 0);
        return Amount;
    }
}
