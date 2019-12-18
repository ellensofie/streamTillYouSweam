package Model;

import Exceptions.MediaAlreadyInMyList;
import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;

public class Account {
    private String username;
    private String password;
    private String email;
    private ArrayList<Media> myList;
    protected MediaConstructor mc = MediaConstructor.getInstance();

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.myList = new ArrayList<>();
    }

    public void createAccountFile() throws FileAlreadyExistsException {
        File currFile = new File("./Data/Accounts/" + email + ".txt");
        if (!currFile.exists()) {
            try {
                PrintWriter writer = new PrintWriter("./Data/Accounts/" + email + ".txt", StandardCharsets.ISO_8859_1);
                writer.println(username + ";" + email + ";" + password);
                for (Media m : myList) {
                    writer.print(m.title + ";");
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new FileAlreadyExistsException("./Data/Accounts/" + email + ".txt");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Media> getMyList() {
        return myList;
    }

    public void addToList(Media m) throws MediaAlreadyInMyList {
        for (Media med : myList){
            if (m.getTitle().equals(med.getTitle())){
                throw new MediaAlreadyInMyList(m.getTitle() + " is already in your list");
            }
        }
        myList.add(m);
        updateAccount();
    }

    public void removeFromList(Media m) throws Exception{
        int i = 0;
        try {
            while (!(myList.get(i).getTitle().equals(m.getTitle()))) {
                i++;
            }
            myList.remove(i);
            updateAccount();
        } catch (Exception e) {
            throw new Exception(m.getTitle() + " was not in your list");
        }
    }

    public void updateAccount() {
        try {
            File userFile = new File("./Data/Accounts/"+this.email+".txt");
            ArrayList<Media> userList = getMyList();
            BufferedWriter userFileWriter = new BufferedWriter(new FileWriter(userFile, StandardCharsets.ISO_8859_1));
            userFileWriter.write(this.username + ";" + this.email + ";" + this.password+";");//skriver brugerens info ind.
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

}
