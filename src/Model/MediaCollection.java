package Model;
import java.util.ArrayList;
import java.util.Arrays;

public class MediaCollection {
    protected ArrayList<Media> content;

    public MediaCollection() {

        content = new ArrayList<>();
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

    public ArrayList<Media> searchTitle(String title){
        ArrayList<Media> movieWithTitle = new ArrayList<>();
        for(Media m : content){
            if(m.getTitle().contains(title)){
                movieWithTitle.add(m);
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

    public ArrayList<Media> searchRating(int i){
        ArrayList<Media> ratedMovies = new ArrayList<>();
        for(Media m : content){
            if(m.getRating() >= i){
                ratedMovies.add(m);
            }
        }
        return ratedMovies;
    }
/*
    public void addMovieCollection(){
        FileReaderMovie r = new FileReaderMovie();
        addMedia(r.getMovies());
        System.out.println(content.size());
    }

 */

}

