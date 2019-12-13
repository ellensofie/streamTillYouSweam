package View;

import Model.Media;
import Model.MediaConstructor;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeriesController implements Initializable{

    @FXML FlowPane fpSeries;

    @FXML ScrollPane spSeries;

    @FXML Button btBackToMMP;

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
            insertSerie(media);
        }
    }

    public void insertSerie(Media media) {
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
            fpSeries.getChildren().addAll(imageView);

            imageView.setOnMouseClicked(mouseEvent -> {
                System.out.println("You clicked " + media.getTitle());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Metode der styrer button btBackToMMP. Metoden navigerer brugeren til MediaMainPage. */
    public void goBackToMediaMainPage(ActionEvent e) throws IOException {
            Stage stage = (Stage)btBackToMMP.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader Login.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med Login.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }

//TODO Få indlæst serier istedet for movies
    }