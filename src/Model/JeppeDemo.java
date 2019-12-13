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

    public static void main(String[] args) throws FileAlreadyExistsException {
        try {
            Account ac = new Account("1","1","1");
            ac.createAccountFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Account a = loadSingleAccount("1");
        System.out.println(a.getEmail());
        System.out.println(a.getUsername());
        System.out.println(a.getPassword());
        System.out.println(a.getMyList());
        Media m = new Media("ET", "1942", 8.5, new String[]{"Drama", "Romance", "War"});
        try {
            a.addToList(m);
            updateAccountFile(a);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        for (Media med:a.getMyList()) {
            System.out.println(med.getTitle());
        }
        //a.loadMyList(email);
        //System.out.println(a.getMyList());

    }

    public static void updateAccountFile(Account acc) throws FileNotFoundException {//updaterer al oplysning om en bruger herunder navn, email, kode, og liste   TODO fjern static når du smider den ind i den rigtige klasse.
        /* Denne metode skal updatere brugernes lister i deres txt filer. Den starter ud med at kopiere(gøres ved at læse og derefter skrive det samme ord for ord) brugerens data fra deres txt fil ind i en temp fil.
        Vi gør så det samme den anden vej rundt, men denne gang kigges der på kontoens myList objekt for at se, hvilken film der skal tilføjes/fjernes. */
        File tempFile = new File("./Data/Accounts/temp.txt");
        String tempFileLines = null;
        String[] tempFileLinesSplit = null;
        ArrayList<Media> userList = acc.getMyList();

        try {
            BufferedWriter tempFileWriter = new BufferedWriter(new FileWriter(tempFile, StandardCharsets.ISO_8859_1));
            tempFileWriter.write(acc.getUsername() + ";" + acc.getEmail() + ";" + acc.getPassword());//skriver brugerens info ind.
            if (userList.size() != 0) {
                tempFileWriter.newLine();// laver en ny linje i temp filen
                for (Media m : userList) {
                    tempFileWriter.write(m.getTitle());
                }
                File userFile = new File("./Data/Accounts/"+acc.getEmail()+".txt");
                BufferedWriter userFileWriter = new BufferedWriter(new FileWriter(userFile));
                BufferedReader tempFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile),StandardCharsets.ISO_8859_1));

                while ( (tempFileLines = tempFileReader.readLine()) !=null ) {
                    tempFileLinesSplit = tempFileLines.split(";");
                    for (String tmpString : tempFileLinesSplit) {
                        userFileWriter.write(tmpString);
                    }
                }
            }

        /*if (tempFile.delete()) {
            System.out.println("temp file was deleted successfully");
        }
        else {
            System.out.println("Could not delete temp file...");
        }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load single account virker. Det samme gør account.addToList
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
                        System.out.println(lines);
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