package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Series extends Media {
    protected ArrayList<ArrayList<Episode>> seasons;
    protected String endYear;

    public Series(String title,String year,String endYear, double rating, String[] categories,ArrayList<ArrayList<Episode>> seasons) throws Exception{
        super(title,year ,rating, categories);
        this.seasons = seasons;
        this.endYear = endYear;
        setImage();
    }

    public ArrayList<ArrayList<Episode>> getSeasons() {
        return this.seasons;
    }

    public ArrayList<Episode> getEpisodes(int i){
        return this.seasons.get(i);
    }

    public String getEndYear() {
        return this.endYear;
    }

    public void setImage() throws Exception {
        String imagePath = ("./Billeder/serier/"+this.title+".jpg");
        this.img = ImageIO.read(new File(imagePath));
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

}
