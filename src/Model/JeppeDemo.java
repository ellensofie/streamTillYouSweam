package Model;

import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;

public class JeppeDemo {
    public static void main(String[] args) throws FileAlreadyExistsException {
        Account a = new Account("demo","demo","1");
        Media m = new Media("Casablanca", "1942",8.5 ,new String[]{"Drama", "Romance", "War"});
        a.createAccountFile();
            try {
                File currFile = new File("./Data/Accounts/demo.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./Data/Accounts/" + LoginController.getUser().getEmail() + ".txt"), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                reader.readLine();
                String lines = reader.readLine();

            } catch (IOException ie) {
                ie.getStackTrace();
            }
    }
}
