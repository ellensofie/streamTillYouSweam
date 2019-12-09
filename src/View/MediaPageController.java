package View;

import Model.Media;
import Model.Movie;
import Model.Series;
import Model.MediaConstructor;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MediaPageController implements Initializable {

    private ImageView imageview;

    @FXML HBox myList;
    @FXML HBox film;
    @FXML HBox series;
    @FXML AnchorPane mediaPageAnchor;
    @FXML Button btnSearch;
    @FXML Button btnMyList;
    @FXML Button btnCategories;
    @FXML TextField txtSearch;
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

    public void btnSearchFunction(){
        searchFunction();
    } //Søge knap kalder vores søgefunktion

    public void searchFunction(){

    series.getChildren().clear(); //fjern alle gamle serier når man søger
    film.getChildren().clear();  //fjern alle gamle film når man søger

        for(Media m : mc.searchTitle(txtSearch.getText())) {
            {
                if (m instanceof Movie) { //type tjek på Movie
                    insertMovie(m); //hvis mediet er en Movie så tilføj
                }
                if (m instanceof Series) { //type tjek på Series
                    insertSerie(m); //hvis mediet er en Series så tilføj
                }
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
            film.getChildren().addAll(imageView);

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
            series.getChildren().addAll(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

        //TODO myList i stedet for null


        /* Følgende kode stump har med btnSearch at gøre */
        /* Først tilføjes effekten dropShadow, som gør, at man kan se en skygge ved knappen, når cursoren kører over.*/
        /*DropShadow shadow = new DropShadow();
         //Tilføj skygge når mus er på knap
        btnSearch.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btnSearch.setEffect(shadow);
                    }
                });
        //Fjern skygge når mus er væk
        btnSearch.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btnSearch.setEffect(null);
                    }
                });
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }*/

