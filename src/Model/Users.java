package Model;

import java.util.ArrayList;

public class Users {
    protected ArrayList<User> users;

    public Users(){
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
