import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.security.*;

public class SecurityMethods {
    public static boolean inputPassword(String CurrentPassword) {
        String InputCurrentPassword;

        System.out.println("Password:");
        InputCurrentPassword = Inputter.inputNotBlank();
        String res = encryptThisString(InputCurrentPassword);
        
        if (res.equals(CurrentPassword)) return true;
        else {
            System.out.println("(!)Wrong password! Access denied!");
            return false;
        }
    }
    
    public static String encryptThisString(String input) //SHA-512 encryption
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
  
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
  
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
  
            // return the HashText
            return hashtext;
        }
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
//    public void save() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"))) {
//            for (Account acc : AccountList) {
//                String line = acc.getAccountNumber() + ":" + acc.getPassword();
//                writer.write(line);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error occurred while saving cars: " + e.getMessage());
//        }
//    }
}
