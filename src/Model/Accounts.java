package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Accounts {
    protected ArrayList<Account> accounts;
    File[] files;;

    public Accounts(){
        this.accounts = new ArrayList<>();
        this.files = new File("./Data/Accounts/").listFiles();
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account a){
        accounts.add(a);
    }

    public void loadAccounts(){
        String lines;
        try{
        for (File file : this.files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                while ((lines = reader.readLine()) != null) {
                    String[] lineData = lines.trim().split(";");
                    // TODO find username, email etc and call addAccount method with these.
                showFiles(); // Calls same method again.
                }
            } else {
                System.out.println("File: " + file.getName());
            }
        }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFiles() {

    }
}
