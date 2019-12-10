package View;

import Model.Media;
import Model.Movie;
import Model.Series;
import Model.MediaConstructor;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable{

    @FXML
    private ImageView btBack;

    @FXML
    private Button testButton;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txPassword;

    @FXML
    private PasswordField txEmail;

    @FXML
    private Button btSignup;

    @FXML
    private AnchorPane loginPane;



    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage)btBack.getScene().getWindow(); //Henter button-logins scene/vindue
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //loader MediaPage.fxml ind

        Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }

    public void login(ActionEvent event) throws Exception {
        if(txtUsername.getText().equals("user") && txPassword.getText().equals("pass") && txEmail.getText().equals("user@email.com")) { //tjek om username er "user" og password er "pass"
            Stage stage = (Stage)btSignup.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
        else {
            lblStatus.setText("Login Failed");
        }

    }
}
