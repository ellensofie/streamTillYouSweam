package View;

import Model.Media;
import Model.Movie;
import Model.Series;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaPageController implements Initializable {

    private ImageView imageview;
    @FXML HBox minliste;
    @FXML HBox film;
    @FXML HBox serier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media m = new Media("lol","1999",9.6,new String[]{"jeg hader mac"},null);
        //MediaConstructor mc = new MediaConstructor();
        //mc.readMediaCollection();



        //Løber gennem alle Media objekter
        for (Media media : m.content) {
            //Tjekker om media er en film
            if (media instanceof Movie){
                try {
                    //Opretter billede
                    Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());
                    System.out.println("filmplakater/Billeder/"+media.getImage());

                    //Opretter plads til billede i HBox
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);

                    //Indsætter billede i HBox
                    film.getChildren().addAll(imageView);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //Løber gennem alle Media objekter
        for (Media media : m.content) {
            //Tjekker om media er en serie
            if (media instanceof Series){
                //Opretter billede
                try {
                    Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());
                    System.out.println("filmplakater/Billeder/"+ media.getImage());

                    //Opretter plads til billede i HBox
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);

                    //Indsætter billede i HBox
                    serier.getChildren().addAll(imageView);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
