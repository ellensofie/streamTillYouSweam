package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Media {
    protected String title;
    protected double rating;
    protected String[] categories;
    protected String year;
    protected BufferedImage img;
    //TODO protected HashMap<String, ArrayList> categorySearch;

    public Media(String title, String year, double rating, String[] categories, BufferedImage img) {
        this.title = title;
        this.rating = rating;
        this.categories = categories;
        this.year = year;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return year;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getCategory(int i) {
        return categories[i];
    }

    public Image getImage() throws Exception {
        return img;
    }

    public String getName() {
        return title;
    }




    /*public void searchCategory() {
        for (Media m : content) {
            if (Arrays.toString(m.getCategories()).contains("War")) {
                System.out.println(m.getTitle());
            }
        }

     */

    public void searchCategory2(String category) {
        //for (Media m : content) {
        //  System.out.println("1");
        for (int i = 0; i < getCategories().length; i++) {
            System.out.println("2");
            if (getCategory(i).equals(category)) {
                System.out.println(getTitle());
            }
            //}
        }
    }

}


