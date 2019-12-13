package Model;

import Exceptions.MediaNotFoundException;

import javax.swing.text.AbstractDocument;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MediaConstructor {
    protected ArrayList<String> pathNames;
    protected ArrayList<Media> content;

    public MediaConstructor() {
        this.pathNames = new ArrayList<>(Arrays.asList("./Data/film.txt", "./Data/serier.txt"));
        this.content = new ArrayList<>();
    }

    public void readMediaCollection() throws Exception {
        if (content.size() != 0) {
            throw new Exception("content has already been filled");
        }
        String lines;
        ArrayList<ArrayList<Episode>> series = new ArrayList<>();
        ArrayList<Episode> episodes = new ArrayList<>();
        for (String path : this.pathNames) {
            File file = new File(path);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                while ((lines = reader.readLine()) != null) {
                    String[] lineData = lines.trim().split(";");
                    String title = lineData[0];
                    //year
                    String[] yearList = lineData[1].trim().split("-");
                    String startYear = yearList[0];
                    String endYear = "-present";
                    if (yearList.length == 2) {
                        endYear = yearList[1];
                    }
                    //genre
                    String[] categories = lineData[2].trim().split(",");
                    for (int i = 0; i < categories.length; i++) {
                        categories[i] = categories[i].trim();
                    }
                    //rating
                    String ratingDot = lineData[3].trim().replaceAll(",", ".");
                    double rating = Double.parseDouble(ratingDot);
                    //Seasons
                    if (path.equals("./Data/serier.txt")) {
                        String[] allSeasonsString = lineData[4].trim().split(",");
                        for (String season : allSeasonsString) {
                            String[] currSeason = season.trim().split("-");
                            for (int j = 1; j < Integer.parseInt(currSeason[1]) + 1; j++) {
                                episodes.add(new Episode("ep" + (j), j));
                            }
                        }
                        series.add(episodes);
                        content.add(new Series(title, startYear, endYear, rating, categories, series));
                    } else if (path.equals("./Data/film.txt")) {
                        content.add(new Movie(title, startYear, rating, categories));
                    } else System.out.println("something went wrong :(");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addMedia(Media media) {

        content.add(media);
    }

    public ArrayList<Media> getContent(){
        return content;
    }

    public ArrayList<Media> getMovies() {
        ArrayList<Media> movies = new ArrayList<>();
        for(Media m : content){
            if(m instanceof Movie){
                movies.add(m);
            }
        }
        return movies;
    }

    public ArrayList<Media> getSeries(){
        ArrayList<Media> series = new ArrayList<>();
        for(Media m : content){
            if(m instanceof Series){
                series.add(m);
            }
        }
        return series;
    }


    public ArrayList<Media> searchTitle(String searchText) {
        ArrayList<Media> movieWithTitle = new ArrayList<>(); // Lav en tom arrayListe med navn movieWithTitle
        for(Media m : content){ // forloop gennem content som er en liste af alle film og serier
            if(m.getTitle().toLowerCase().contains(searchText.toLowerCase())){//Vi bruger .contains så man kun skal indtaste en lille del af titlen for at finde filmen
                movieWithTitle.add(m); // tilføj til resultat liste.
            }
        }
        return movieWithTitle;
    }

    //Equals fremfor contains i if-statement?
    public ArrayList<Media> searchCategory(String category) {
        ArrayList<Media> movieInCategory = new ArrayList<>();
        for(Media m : content){
            if(Arrays.toString(m.getCategories()).contains(category)){
                movieInCategory.add(m);
            }
        }
        return movieInCategory;
    }

    /* Henter mediet som har den titel metoden bliver kaldt med, kaster hjemmelavet MediaNotFoundException hvis der ikke findes en film med input titel */
    public Media getMedia(String title) throws MediaNotFoundException {
        for (Media m : content) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        throw new MediaNotFoundException("Could not find media with title " + title);
    }

    /* Returner en tilfældig film eller serie fra listen content */
    public Media getRandomMedia(){
        Random rand = new Random();
        int n = rand.nextInt(content.size());
        return content.get(n);
    }

    /* Metode der returnerer en liste af media hvis rating er >= det givne input. Bruges til søgning på rating. */
    public ArrayList<Media> searchRating(int rating){
        ArrayList<Media> ratedMovies = new ArrayList<>();
        for(Media m : content){
            if(m.getRating() >= rating){
                ratedMovies.add(m);
            }
        }
        return ratedMovies;
    }

    /* Metode der returnerer en liste af media hvis media har samme releaseyear som input. */
    public ArrayList<Media> searchReleaseYear(String releaseYear){
        ArrayList<Media> ReleaseYearMovies = new ArrayList<>();
        for(Media m : content){
            if(m.getReleaseYear().equals(releaseYear)){
                ReleaseYearMovies.add(m);
            }
        }
        return ReleaseYearMovies;
    }

}
