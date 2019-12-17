package Model;

import View.LoginController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JeppeDemo {
    private static String username;
    private static String email;
    private static String password;
    //private static ArrayList<Media> myList;

    public static void main(String[] args) throws FileAlreadyExistsException {
        Account acc = new Accounts().getSingleAccount("1");
        MediaConstructor mc = new MediaConstructor();
        try {
            mc.readMediaCollection();
            } catch (Exception e) {
                e.printStackTrace();
        }
        Media m = mc.getContent().get(75);
        try {
            acc.removeFromList(m);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
        }
        /*for (Media med:a.getMyList()) {
            System.out.println(med.getTitle());
        }*/
        //a.loadMyList(email);
        //System.out.println(a.getMyList());
    }



}
/*

    }

    //Load single account virker. Det samme gør account.addToList
}*/