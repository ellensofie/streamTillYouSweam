package Model;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageReader {

    public static void main(String[] args) throws IOException {
        String imagePath = "./Billeder/film/12 Angry Men.jpg";
        BufferedImage myImage = ImageIO.read(new File(imagePath));

        JLabel picLabel = new JLabel();
        JPanel picturePanel = new JPanel();
        picturePanel.add(picLabel);

        JFrame f = new JFrame();
        f.setSize(new Dimension(myImage.getWidth(), myImage.getHeight()));
        f.add(picturePanel);
        f.setVisible(true);

    }
}

