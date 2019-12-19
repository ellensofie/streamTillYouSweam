package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface playable {
    void play();
    BufferedImage getImage() throws Exception;
}
