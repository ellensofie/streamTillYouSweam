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

    /*    public Media(){
        }

        public Media(String title, int year, double rating, String[] categories, BufferedImage img) {
    */
    public Media(String title, String year, double rating, String[] categories) {
        this.title = title;
        this.rating = rating;
        this.categories = categories;
        this.year = year;
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

    public BufferedImage getImage() throws Exception {
        return img;
    }




    /*public void searchCategory() {
        for (Media m : content) {
            if (Arrays.toString(m.getCategories()).contains("War")) {
                System.out.println(m.getTitle());
            }
        }

     */

}



