package View;

import Model.Media;
import Model.MediaConstructor;
import Model.Movie;
import Model.Series;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaMainPageController implements Initializable{

   @FXML BorderPane borderPane;

    @FXML ScrollPane bpScrollPane;

    @FXML VBox VBoxFilm;

    @FXML Label lbMyList;

    @FXML ScrollPane spMyList;

    @FXML HBox hbMyList;

    @FXML Label lbFilm;

    @FXML ScrollPane spMovie;

    @FXML HBox hbFilm;

    @FXML Label lbSeries;

    @FXML ScrollPane spSeries;

    @FXML HBox hbSeries;

        private ImageView imageview;


        MediaConstructor mc = new MediaConstructor(); //Global variabel og ikke kun i Initialize (skal bruges andre steder)
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            try {
                mc.readMediaCollection();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Løber gennem alle Media objekter
            for (Media media : mc.getContent()) {
                //Tjekker om media er en film
                if (media instanceof Movie) {
                    insertMovie(media);
                }
                if (media instanceof Series) {
                    insertSerie(media);
                }
            }
        }

        public void insertMovie(Media media){
            try {
                //Opretter billede
                //Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());
                //System.out.println("filmplakater/Billeder/" + media.getImage());

                BufferedImage bufferedImage = (BufferedImage) media.getImage();
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);

                //Opretter plads til billede i HBox
                ImageView imageView = new ImageView();
                imageView.setImage(img);

                //Indsætter billede i HBox
                hbFilm.getChildren().addAll(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void insertSerie(Media media){
            //Opretter billede
            try {
                //Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());
                //System.out.println("filmplakater/Billeder/" + media.getImage());

                BufferedImage bufferedImage = (BufferedImage) media.getImage();
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);

                //Opretter plads til billede i HBox
                ImageView imageView = new ImageView();
                imageView.setImage(img);

                //Indsætter billede i HBox
                hbSeries.getChildren().addAll(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


