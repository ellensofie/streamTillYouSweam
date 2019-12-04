package Model;

import java.awt.image.BufferedImage;

public class Episode{
    protected String title;
    protected int epNumber;

    public Episode(String title, int epNumber) {
        this.epNumber = epNumber;
        this.title = title;
    }
    public void play() {
    }


    public int getEpNumber() {
        return epNumber;
    }

    public String getTitle() {
        return title;
    }
}
