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
    public void addToList(Media m){
        try {
        File currFile = new File("./Data/Accounts/"+email+".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./Data/Accounts/" + LoginController.getUser().getEmail() + ".txt"), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
        reader.readLine();
        String lines = reader.readLine();

        myList.add(m);
        } catch (IOException ie) {
            ie.getStackTrace();
        }
    }

    public void removeMyList(Media m){
        myList.remove(m);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
