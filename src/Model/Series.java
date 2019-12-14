package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Series extends Media {
    // TODO seasons og episodes kan måske være en list of lists?
    protected ArrayList<ArrayList<Episode>> seasons;
    protected String endYear;

    public Series(String title,String year,String endYear, double rating, String[] categories,ArrayList<ArrayList<Episode>> seasons) throws Exception{
        super(title,year+endYear ,rating, categories);
        this.seasons = seasons;
        this.endYear = endYear;
        setImage();
    }
    public ArrayList<ArrayList<Episode>> getSeasons() {
        return this.seasons;
    }

    public String getEndYear() {
        return this.endYear;
    }

    public void setImage() throws Exception {
        String imagePath = ("./Billeder/serier/"+this.title+".jpg");
        this.img = ImageIO.read(new File(imagePath));
    }

    /*public ArrayList<Episode> getEpisodes(){
        ArrayList<Episode> episodes = new ArrayList<>();
        for(int i = 0; i < getSeasons().size(); i++) {
            episodes.add(getSeasons().get(i).size());
        }
        return episodes;
    }

     */

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    public void play() {}
}
