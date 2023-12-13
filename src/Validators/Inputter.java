package Validators;

import java.util.Scanner;

public class Inputter {
//Make sure that input field is not blank. If it's blank, input again
    public static String inputString() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            s = sc.nextLine().trim();
            if(s.isEmpty()) System.out.println("Field cannot be blank!");
        } while (s.isEmpty() == true);
        return s;
    }

//Make sure that input data is in correct format. If not, input again    
    public static String inputPattern(String pattern) {
        String input;
        do {
            input = inputString();
            if(input.matches(pattern) == false) System.out.println("Wrong format!");
        } while (input.matches(pattern) == false);
        return input;
    }

//Make sure the number inputted is not below 0. If it is, input again    
    public static double inputPosDouble() {
        double amount = 0;
        do {
            try{
                Scanner sc = new Scanner(System.in);
                amount = sc.nextDouble();
            }
            catch (Exception e) {
                System.err.println("(!)Invalid input! Amount must be a real number greater than 0!");
            }    
        } while (amount <= 0);
        return amount;
    }
}
