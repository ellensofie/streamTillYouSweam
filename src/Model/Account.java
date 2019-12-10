package Model;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Account {
    protected String username;
    protected String password;
    protected String email;
    protected ArrayList<Media> myList;


    public Account(String username, String email,String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.myList = new ArrayList<Media>();
        createAccountFile();
    }

    public void loadMyList(String email) {
        this.myList = new ArrayList<>();
    }

    public void createAccountFile(){
        try {
            PrintWriter writer = new PrintWriter("./Data/Accounts/"+this.email+".txt", StandardCharsets.ISO_8859_1);
            writer.println(this.username+";"+this.email+";"+this.password);
            for (Media m:myList) {
                writer.print(m.title+";");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addMyList(Media m){
        myList.add(m);
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
