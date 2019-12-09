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
}
