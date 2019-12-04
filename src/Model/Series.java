package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Series extends Media {
    // TODO seasons og episodes kan måske være en list of lists?
    protected ArrayList<ArrayList<Episode>> seasons;
    protected int endYear;

    public Series(String title,int year,int endYear, double rating, String[] categories,ArrayList<ArrayList<Episode>> seasons) {
        super(title,year ,rating, categories,null);
        this.seasons = seasons;
        this.endYear = Objects.requireNonNullElse(endYear, Calendar.getInstance().get(Calendar.YEAR));
    }

    public void addSeason(int episodes) {
        //this.seasons +=1;
    }

    public ArrayList<ArrayList<Episode>> getSeasons() {
        return this.seasons;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void play() {}
}
