package Model;

import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;

public class JeppeDemo {
    private static String username;
    private static String email;
    private static String password;
    //private static ArrayList<Media> myList;

    public static void main(String[] args) throws FileAlreadyExistsException, MediaAlreadyInListException {
        //try {
            //Account ac = new Account("1","1","1");
            //ac.createAccountFile();
        //} catch (Exception e) {
           // e.printStackTrace();
        //}
        Account a = loadSingleAccount();
        Media m = new Media("ET", "1942", 8.5, new String[]{"Drama", "Romance", "War"});
        a.addToList(m);
        try {
            updateAccountFile(a);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        //a.loadMyList(email);
        //System.out.println(a.getMyList());

    }

    public static void updateAccountFile(Account acc) throws FileNotFoundException {//fjern static når du smider den ind i den rigtige klasse.
        /*
        Denne metode skal updatere brugernes lister i deres txt filer. Den starter ud med at kopiere(gøres ved at læse og derefter skrive det samme ord for ord) brugerens data fra deres txt fil ind i en temp fil.
        Vi gør så det samme den anden vej rundt, men denne gang kigges der på kontoens myList objekt for at se, hvilken film der skal tilføjes/fjernes.
         */
        File tempFile = new File("./Data/Accounts/temp.txt");
        File dirPath = new File("./Data/Accounts/"+acc.getEmail()+".txt");
        String lines = null;
        ArrayList<Media> userList = acc.getMyList();
        try {
            BufferedWriter tempFileWriter = new BufferedWriter(new FileWriter(tempFile, StandardCharsets.ISO_8859_1));
            BufferedReader userFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(dirPath), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
            tempFileWriter.write(acc.getUsername() + ";" + acc.getEmail() + ";" + acc.getPassword());
            tempFileWriter.newLine();
            userFileReader.readLine();
            String userLines = userFileReader.readLine();
            String[] userLinesSplit = userLines.split(";");
            if (userList.size() != userLinesSplit.length) {
                for (Media m : userList) {
                    tempFileWriter.write(m.title + ";");
                }
            }
            else {
                throw new Exception("List is unchanged");
                }
            System.out.println("hvad");
            tempFileWriter.close();
            userFileReader.close();
            BufferedReader tempFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
            BufferedWriter userFileWriter = new BufferedWriter(new FileWriter(dirPath, StandardCharsets.ISO_8859_1));
            lines = tempFileReader.readLine();
            userFileWriter.write(lines);
            userFileWriter.newLine();
            lines = tempFileReader.readLine();
            String[] linesArray = lines.split(";");
            for (Media m: acc.getMyList()){
                userFileWriter.write(m.getTitle()+";");
            }
            tempFileReader.close();
            userFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Account loadSingleAccount() throws FileAlreadyExistsException {
        String email = "1";
        File dirPath = new File("./Data/Accounts/1.txt");
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
                        System.out.println(lines);
                        lineData = lines.split(";");
                        for (int i = 0; i < lineData.length; i++) {
                        acc.getMyList().add(mc.getMedia(lineData[i]));
                    }
                    }
                }
            }
            else {
                System.out.println(email + " does not have a user");
            }
            return acc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }
}