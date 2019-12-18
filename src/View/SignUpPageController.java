package View;

import Exceptions.InvalidEmailException;
import Model.*;
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
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageController implements Initializable{

    @FXML private ImageView btBack;

    @FXML private Label lblStatus;

    @FXML private TextField txtUsername;

    @FXML private TextField txtPassword;

    @FXML private TextField txtEmail;

    @FXML private Button btSignup;

    @FXML private AnchorPane loginPane;

    @FXML private Label emailErrorLabel;

    public void initialize(URL location, ResourceBundle resources) {
    }

    /* Metode der styrer btBack. Formålet er at navigere brugeren til Login */
    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage)btBack.getScene().getWindow(); //Henter button-logins scene/vindue
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //loader Login.fxml ind

        Scene scene = new Scene(root); //opretter ny scene med Login.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }

    public void login(ActionEvent event) throws Exception {
        if(txtUsername.getText().equals("user") && txtPassword.getText().equals("pass") && txtEmail.getText().equals("user@email.com")) { //tjek om username er "user" og password er "pass"
            Stage stage = (Stage)btSignup.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader MediaMainPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaMediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
        else {
            lblStatus.setText("Login Failed");
        }
    }

    public void createAccount(ActionEvent e) throws IOException, InvalidEmailException {
        Pattern validPattern =  Pattern.compile("([aA-zZ]|[0-9]|\\.)*@([aA-zZ]|\\.)*\\.[aA-zZ]{1,3}");
        Matcher patternMatch = validPattern.matcher(txtEmail.getText());
        if (!patternMatch.matches()) {
            lblStatus.setVisible(false);
            emailErrorLabel.setVisible(true);
            throw new InvalidEmailException(txtEmail.getText()+" is an invalid email");
        }
        try {
            Account a = new Account(txtUsername.getText(), txtEmail.getText(), txtPassword.getText());
            a.createAccountFile();
        } catch (Exception f) {

            f.getMessage();
        }
        goBack(e);
    }
}
