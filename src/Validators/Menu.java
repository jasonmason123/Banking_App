package Validators;

import java.util.*;

public class Menu {
//    public static int int_getChoice (ArrayList options) {
//        int response;
//        boolean check;
//        for (int i=0; i<options.size(); i++) {
//            System.out.println((i+1) + "-" + options.get(i));
//        }
//        do {
//            try {
//                System.out.println("Please choose an option 1..." + options.size() + ":");
//                Scanner sc = new Scanner(System.in);
//                response = sc.nextInt();
//                check = true;
//            }
//            catch (Exception e) {
//                System.out.println("(!)Invalid input, please try again!");
//                response = -1;
//                check = false;
//            }
//        } while (check != true);
//        
//        return response;
//    }

    public static int int_getChoice (Object[] options) {
        int response;
        boolean check;
        for (int i=0; i<options.length; i++) {
            System.out.println((i+1) + "-" + options[i]);
        }
        do {
            try {
                System.out.println("Please choose an option 1..." + options.length + ":");
                Scanner sc = new Scanner(System.in);
                response = sc.nextInt();
                check = true;
            }
            catch (Exception e) {
                System.out.println("(!)Invalid input, please try again!");
                response = -1;
                check = false;
            }
        } while (check != true);
        
        return response;
    }
    
//    public static Object ref_getChoice (ArrayList options) {
//        int response;
//        do {
//            response = int_getChoice(options);
//        } while (response <= 0 || response > options.size());
//        return options.get(response - 1);
//    }
//    
//    public static Object ref_getChoice (Object[] options) {
//        int response;
//        do {
//            response = int_getChoice(options);
//        } while (response < 0 || response > options.length);
//        return options[response - 1];
//    }
    
}
