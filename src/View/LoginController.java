package View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    public void Login(ActionEvent event) {
        //MainView.changeScreen(event);
    }

    public void loadMediaPage(ActionEvent event) {

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