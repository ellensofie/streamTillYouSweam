package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Movie extends Media{

    public Movie(String title, String year,double rating, String[] categories) throws Exception {
        super(title, year,rating, categories);
        setImage();
    }

    public void play() {
    }

    public String[] getCategories(){
        return categories;
    }

    public String getCategory(int i){
        return categories[i];
    }

    public void setImage() throws Exception {
        String imagePath = ("./Billeder/film/"+this.title+".jpg");
        this.img = ImageIO.read(new File(imagePath));

    /*public void show(){
        String s = " ";
        System.out.println(title + " (" + year + ")");
        System.out.print("Genre: ");
        for (int i = 0; i < categories.length; i++) {
            System.out.print(categories[i]);
            if (i != categories.length-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("Rating: " + rating + "\n");

     */


    }
    public BufferedImage getImage() throws Exception {
        if (img != null) {
            return this.img;
        }
        throw new Exception("img is null");
    }
}

