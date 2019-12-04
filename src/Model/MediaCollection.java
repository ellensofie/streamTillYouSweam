package Model;

import java.util.ArrayList;

public class MediaCollection {
    protected ArrayList<Media> content;
    protected ArrayList<Movie> movies;
    protected ArrayList<Series> series;

    public MediaCollection() {
        content = new ArrayList<>();
        movies = new ArrayList<>();
        series = new ArrayList<>();
    }

    public void addMedia(Media media) {
        content.add(media);
    }

    public void seperateMedia() {
        for (Media m : content) {
            if (m instanceof Movie) {
                movies.add((Movie) m);
            } else if (m instanceof Series) {
                series.add((Series) m);
            }
        }
    }

    public void searchCategory(String category) {
        for (Media m : content) {
            System.out.println("1");
            for (int i = 0; i < m.getCategories().length; i++) {
                System.out.println("2");
                if (m.getCategory(i).equals(category)) {
                    System.out.println(m.getTitle());
                }
            }
        }
    }
}
