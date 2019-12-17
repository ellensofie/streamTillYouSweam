package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Accounts {
    protected ArrayList<Account> accounts;
    File[] files;

    public Accounts(){
        this.accounts = new ArrayList<>();
        this.files = new File("./Data/Accounts/").listFiles();
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void loadAllAccounts(){
        String lines;
        int lineCounter;
        try{
        for (File file : this.files) {
            if (!file.isDirectory()) {
                System.out.println("File: " + file.getName());
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                lines = reader.readLine();
                String[] lineData = lines.trim().split(";");
                String userName = lineData[0];
                String userEmail = lineData[1];
                String userPassword = lineData[2];
                // TODO find username, email etc and call addAccount method with these.
                accounts.add(new Account(userName,userEmail,userPassword));
                }
            else {
                System.out.println("Directory: " + file.getName());
            }
        }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loadSingleAccount(String email,String password) {
        String lines;
        String userPassword = "";
        String userEmail = "";
        String userName = "";
        boolean userFound = false;
        try{
            for (File file : this.files) {
                if (!file.isDirectory()) {
                    if (file.getName().equals(email+".txt")){
                        userFound = true;
                        System.out.println("File: " + file.getName());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                        lines = reader.readLine();
                        String[] lineData = lines.trim().split(";");
                        userName = lineData[0];
                        userEmail = lineData[1];
                        userPassword = lineData[2];
                        }
                    }
                }
            if (!userFound) {
                System.out.println(email+" does not have a user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userPassword.equals(password);
    }

    public Account getSingleAccount(String email) throws FileAlreadyExistsException {
        File dirPath = new File("./Data/Accounts/"+email+".txt");
        String lines;
        Account acc = null;
        String username;
        String password;
        try {
            MediaConstructor mc = new MediaConstructor();
            mc.readMediaCollection();
            if (dirPath.isFile()) {
                if (dirPath.getName().equals(email + ".txt")) {
                    //indlæs bruger oplysninger
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dirPath), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                    lines = reader.readLine();
                    String[] lineData = lines.trim().split(";");
                    username = lineData[0];
                    password = lineData[2];
                    acc = new Account(username,email,password);

                    // indlæs brugerens liste af media
                    if ((lines = reader.readLine()) != null) {
                        lineData = lines.split(";");
                        for (String lineDatum : lineData) {
                            acc.getMyList().add(mc.getMedia(lineDatum));
                        }
                    }
                }
            }
            else {
                System.out.println(email + " does not have a user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }
}

