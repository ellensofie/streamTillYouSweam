package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Movie extends Media{

    public Movie(String title, String year,double rating, String[] categories) throws Exception {
        super(title, year,rating, categories);
        setImage();
    }


    public String[] getCategories(){
        return categories;
    }


    public void setImage() throws Exception {
        String imagePath = ("./Billeder/film/"+this.title+".jpg");
        this.img = ImageIO.read(new File(imagePath));


    }
    public BufferedImage getImage() throws Exception {
        if (img != null) {
            return this.img;
        }
        throw new Exception("img is null");
    }
}

