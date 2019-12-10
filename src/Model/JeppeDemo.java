package Model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class JeppeDemo {
    public static void main(String[] args) {
        try {
            Account a = new Account("Weakboi", "weakBOI@gmail.com", "1234");
            Accounts as = new Accounts();
            as.showFiles();
            MediaConstructor mc = new MediaConstructor();
            mc.readMediaCollection();
            ArrayList<Media> ct = mc.getContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
