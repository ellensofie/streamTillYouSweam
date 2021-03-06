package View;

import Model.Media;
import Model.MediaConstructor;
import Model.Movie;
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

public class MoviesController implements Initializable {

    @FXML FlowPane fpMovies;

    @FXML ScrollPane spMovies;

    @FXML Button btBackToMMP;


    MediaConstructor mc = MediaConstructor.getInstance(); //Global variabel og ikke kun i Initialize (skal bruges andre steder)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Løber gennem alle Media objekter
        for (Media media : mc.getContent()) {
            if (media instanceof Movie) {
                insertMovie(media);
            }
        }
    }

    public void insertMovie (Media media){
        try {
                //Opretter billede
                //Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());

                BufferedImage bufferedImage = media.getImage();
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);

                //Opretter plads til billede
                ImageView imageView = new ImageView();
                imageView.setImage(img);

                imageView.setOnMouseClicked(mouseEvent -> {
                    Stage stage = (Stage) imageView.getScene().getWindow();
                    Parent root = null; //loader MediaPageSpecific.fxml ind
                    try {
                        MediaMainPageController.selectedMedia = media;
                        root = FXMLLoader.load(getClass().getResource("MediaSpecific.fxml"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Scene scene = new Scene(root); //opretter ny scene med MediaSpecific.fxml som indhold
                    stage.setScene(scene); //Sætter scenen
                    stage.show(); //viser scenen for brugeren
                });

                fpMovies.getChildren().addAll(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* Metode der styrer Button btBackToMMP. Formålet med metoden er at navigerer MediaMainPage */
        public void goBackToMediaMainPage(ActionEvent e) throws IOException {
            Stage stage = (Stage)btBackToMMP.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader Login.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med Login.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
}
