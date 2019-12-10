package Model;

import java.util.ArrayList;

public class Accounts {
    protected ArrayList<Account> accounts;

    public Accounts(){
        accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account a){
        accounts.add(a);
    }

    public void loadAccounts(){}

    public void newUser(String username, String email,String password){
        accounts.add(new Account(username, email, password));
    }
}
