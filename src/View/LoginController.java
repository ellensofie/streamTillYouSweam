package View;

import Model.Account;
import Model.Accounts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignup;

    @FXML
    private AnchorPane loginPane;

    protected static Account user;

    public void login(ActionEvent event) throws Exception {
        if(txtEmail.getText().equals("user") && txtPassword.getText().equals("pass")) { //tjek om Email er "user" og password er "pass"
            Stage stage = (Stage)btLogin.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
        else {
            lblStatus.setText("Login Failed");
        }

    }

    public void loginProper(ActionEvent event) throws Exception {
        if(new Accounts().loadSingleAccount(txtEmail.getText(),txtPassword.getText())) { //tjek om Email er "user" og password er "pass"
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./Data/Accounts/" + txtEmail.getText() + ".txt"), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
            String lines = reader.readLine();
            String[] firstLine = lines.split(";");
            user = new Account(firstLine[0],txtEmail.getText(),txtPassword.getText());
            Stage stage = (Stage)btLogin.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
        else {
            lblStatus.setText("Login Failed");
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public static Account getUser() {
        return user;
    }

    public void signUp(ActionEvent event) throws Exception {
            Stage stage = (Stage)btSignup.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren

    }
}
