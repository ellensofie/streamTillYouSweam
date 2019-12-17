package Model;

import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JeppeDemo {
    private static String username;
    private static String email;
    private static String password;
    //private static ArrayList<Media> myList;

    public static void main(String[] args) throws FileAlreadyExistsException {
        Account a = loadSingleAccount("1");
        System.out.println("name "+a.getUsername()+" email "+a.getEmail()+" password "+a.getPassword());
        Media m = new Media("The Godfather", "1972", 9.2, new String[]{"Crime", "Drama"});
        try {
            a.addToList(m);
            updateAccountFile(a);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        /*for (Media med:a.getMyList()) {
            System.out.println(med.getTitle());
        }*/
        //a.loadMyList(email);
        //System.out.println(a.getMyList());
    }

    public static void updateAccountFile(Account acc) throws FileNotFoundException {
        copyTempFile(acc);
    }

    public static void copyTempFile(Account acc){
        try {
            File userFile = new File("./Data/Accounts/"+acc.getEmail()+".txt");
            ArrayList<Media> userList = acc.getMyList();
            BufferedWriter userFileWriter = new BufferedWriter(new FileWriter(userFile, StandardCharsets.ISO_8859_1));
            userFileWriter.write(acc.getUsername() + ";" + acc.getEmail() + ";" + acc.getPassword()+";");//skriver brugerens info ind.
            if (userList.size() != 0) {
                userFileWriter.newLine();// laver en ny linje i temp filen
                for (Media m : userList) {
                    userFileWriter.write(m.getTitle()+";");
                }
            }
            userFileWriter.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static File createTempFile(Account acc){
    try {
        File tempFile = new File("./Data/Accounts/temp.txt");
        ArrayList<Media> userList = acc.getMyList();
        BufferedWriter tempFileWriter = new BufferedWriter(new FileWriter(tempFile, StandardCharsets.ISO_8859_1));
        tempFileWriter.write(acc.getUsername() + ";" + acc.getEmail() + ";" + acc.getPassword()+";");//skriver brugerens info ind.
        if (userList.size() != 0) {
            tempFileWriter.newLine();// laver en ny linje i temp filen
            for (Media m : userList) {
                tempFileWriter.write(m.getTitle()+";");
            }
        }
        tempFileWriter.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    return new File("./Data/Accounts/temp.txt");
    }

    public static Account loadSingleAccount(String email) throws FileAlreadyExistsException {
        File dirPath = new File("./Data/Accounts/"+email+".txt");
        String lines;
        Account acc = null;

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
                return acc;
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
/*

    }

    //Load single account virker. Det samme gør account.addToList
}*/