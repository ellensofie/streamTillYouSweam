package Model;

import java.util.ArrayList;

public class JeppeDemo {
    public static void main(String[] args) {
        try{
        Account a = new Account("Jeppe","jeppemmoeller@gmail.com","1234");
        MediaConstructor mc = new MediaConstructor();
        mc.readMediaCollection();
        ArrayList<Media> ct = mc.getContent();
        a.addMyList(ct.get(0));
        a.addMyList(ct.get(1));
        a.addMyList(ct.get(2));
        a.createAccountFile();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
