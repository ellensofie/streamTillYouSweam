package View;

import Model.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayMovieController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Label movieTitle;

    private Media selectMedia = MediaMainPageController.getSelectedMedia();


    public void initialize(URL location, ResourceBundle resources) {
        setMovieTitle();
    }

    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage)btnBack.getScene().getWindow(); //Henter button-logins scene/vindue
        Parent root = FXMLLoader.load(getClass().getResource("MediaSpecific.fxml")); //loader MediaMainpage.fxml ind
        Scene scene = new Scene(root); //opretter ny scene med MediaMainPage.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }

    public void setMovieTitle(){
        movieTitle.setText("Playing " + selectMedia.getTitle() + "...");
    }
}
