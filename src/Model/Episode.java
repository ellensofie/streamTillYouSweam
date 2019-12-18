package Model;

import java.awt.image.BufferedImage;

public class Episode{
    private String title;
    private int epNumber;

    Episode(String title, int epNumber) {
        this.epNumber = epNumber;
        this.title = title;
    }

    public int getEpNumber() {
        return epNumber;
    }

    public String getTitle() {
        return title;
    }
}
