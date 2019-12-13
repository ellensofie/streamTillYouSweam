package Model;

import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;

public class Account {
    protected String username;
    protected String password;
    protected String email;
    protected ArrayList<Media> myList;
    protected MediaConstructor mc = new MediaConstructor();


    public Account(String username, String email,String password) throws FileAlreadyExistsException{
        this.username = username;
        this.email = email;
        this.password = password;
        this.myList = new ArrayList<Media>();
    }

    public void loadMyList(String email) {
        this.myList = new ArrayList<>();
    }

    public void createAccountFile() throws FileAlreadyExistsException{
        File currFile = new File("./Data/Accounts/"+email+".txt");
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
        }
        else {
            throw new FileAlreadyExistsException("./Data/Accounts/"+email+".txt");
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

    public ArrayList<Media> getMyList(){
        return myList;
    }

    public void addToList(Media m) throws MediaAlreadyInListException{
        if (!myList.contains(m)) {
            myList.add(m);
            //updateAccountFile();
        }
        else throw new MediaAlreadyInListException(m.getTitle() + " is already in your list");
    }

    public void loadList() throws FileAlreadyExistsException{
        try {
            mc.readMediaCollection();
        File currFile = new File("./Data/Accounts/"+email+".txt");
        if (currFile.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(currFile), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                reader.readLine();
                String lines = reader.readLine();
                String[] splitLines = lines.split(";");
                for (int i = 0;  i < mc.getContent().size(); i++) {
                    Media currMedia = mc.getContent().get(i);
                    for (String string: splitLines) {
                        if (string.equals(currMedia.getTitle())){
                            myList.add(currMedia);
                        }
                    }
                }
        }
        else {
            throw new FileAlreadyExistsException("./Data/Accounts/"+email+".txt");
        } }catch (Exception e) {
            e.printStackTrace();
    }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
