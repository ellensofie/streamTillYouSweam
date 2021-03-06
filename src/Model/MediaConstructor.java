package Model;

import Exceptions.MediaNotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class MediaConstructor {

    private static MediaConstructor mc = null;
    protected ArrayList<String> pathNames;
    protected ArrayList<Media> content;

    private MediaConstructor() {
        //Opretter ny arrayliste med vej til txt-filerne for mere overskuelig søgning
        this.pathNames = new ArrayList<>(Arrays.asList("./Data/film.txt", "./Data/serier.txt"));
        this.content = new ArrayList<>();
    }

    public static MediaConstructor getInstance() {
        if (mc == null) {
            mc = new MediaConstructor();
            try {
            mc.readMediaCollection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mc;
    }

    private void readMediaCollection() throws Exception {
        if (content.size() != 0) {
            throw new Exception("content has already been filled");
        }
        String lines;
        for (String path : this.pathNames) {
            File file = new File(path);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                while ((lines = reader.readLine()) != null) {
                    ArrayList<ArrayList<Episode>> series = new ArrayList<>();
                    String[] lineData = lines.trim().split(";");
                    String title = lineData[0];
                    //year
                    String[] yearList = lineData[1].trim().split("-");
                    String startYear = yearList[0];
                    String endYear = "present";
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
                            ArrayList<Episode> episodes = new ArrayList<>();
                            String[] currSeason = season.trim().split("-");
                            for (int j = 1; j < Integer.parseInt(currSeason[1]) + 1; j++) {
                                episodes.add(new Episode("ep" + (j), j));
                            }
                            series.add(episodes);
                        }
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

    //Returnerer Arraylisten "content", som indeholder alle medier.
    public ArrayList<Media> getContent(){
        return content;
    }

    /*Opretter en ny arrayliste, hvor medier der opfylder søgekriteriet at parametren indeholdes i titlen
    og derefter returnerer en arrayliste med netop de film, der indeholder parametren i mediets titel
    */
    public ArrayList<Media> searchTitle(String searchText) {
        ArrayList<Media> movieWithTitle = new ArrayList<>(); // Lav en tom arrayListe med navn movieWithTitle
        for(Media m : content){ // forloop gennem content som er en liste af alle film og serier
            if(m.getTitle().toLowerCase().contains(searchText.toLowerCase())){//Vi bruger .contains så man kun skal indtaste en lille del af titlen for at finde filmen
                movieWithTitle.add(m); // tilføj til resultat liste.
            }
        }
        return movieWithTitle;
    }

    /* Opretter en ny Arrayliste og gennemløber "content". De medier som indeholder genren(category)
    man søger efter, lægges ind i arraylisten, som derefter bliver returneret.
     */
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
    public ArrayList<Media> searchRating(double rating){
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
