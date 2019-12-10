package View;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txPassword;

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignup;

    @FXML
    private AnchorPane loginPane;

public void initialize(URL location, ResourceBundle resources) {

}

    public void login(ActionEvent event) throws Exception {
        if(txtUsername.getText().equals("user") && txPassword.getText().equals("pass")) { //tjek om username er "user" og password er "pass"
            Stage stage = (Stage)btLogin.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("MediaPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        }
        else {
            lblStatus.setText("Login Failed");
        }

    }

    public void signUp(ActionEvent event) throws Exception {
            Stage stage = (Stage)btSignup.getScene().getWindow(); //Henter button-logins scene/vindue
            Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml")); //loader MediaPage.fxml ind

            Scene scene = new Scene(root); //opretter ny scene med MediaPage.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren

    }
}
    /* Denne metode gør, at Login label ændrer sig til Login failed */
/*    public void Login(ActionEvent event){
        if(txtUsername.getText().equals("user") && txPassword.getText().equals("pass")) {
            lblStatus.setText("Login Success");
        }
        else {
                lblStatus.setText("Login Failed");
             }
    }
}*/