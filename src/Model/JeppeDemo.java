package Model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class JeppeDemo {
    public static void main(String[] args) {
        try {
            Account a = new Account("user", "user", "pass");
            a.createAccountFile();
            /*Accounts as = new Accounts();
            as.loadAllAccounts();
            System.out.println(as.getAccounts());
            MediaConstructor mc = new MediaConstructor();
            mc.readMediaCollection();
            ArrayList<Media> ct = mc.getContent();
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
