package Model;

import java.util.ArrayList;
public class Account {
    protected String username;
    protected String password;
    protected int age;
    protected ArrayList<Media> myList;


    public Account(String username, String password, int age){
        this.username = username;
        this.password = password;
        this.age = age;
        myList = new ArrayList<>();
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
