package Model;
import java.util.ArrayList;

public class MediaCollection {
    protected ArrayList<Media> content;

    public MediaCollection() {
        content = new ArrayList<>();
    }

    public void addMedia(Media media) {
        content.add(media);
    }

    public Media getContent(){
        for(Media m : content){
            return m;
        }
        return null;
    }

    public Media getMovies() {
        for (Media m : content) {
            if (m instanceof Movie) {
                return m;
            }
        }
        return null;
    }

    public Media getSeries(){
        for (Media m : content){
            if (m instanceof Series) {
                return m;
            }
        }
        return null;
    }

    public void searchCategory(String category) {
        for (Media m : content) {
            for (int i = 0; i < m.getCategories().length; i++) {
                if (m.getCategory(i).equals(category)) {
                    System.out.println(m.getTitle());
                }
            }
        }
    }
/*
    public void addMovieCollection(){
        FileReaderMovie r = new FileReaderMovie();
        addMedia(r.getMovies());
        System.out.println(content.size());
    }

 */

}

