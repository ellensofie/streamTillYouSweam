package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.Arrays;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MediaSpecificController implements Initializable {

    @FXML
    private ImageView btBack;

    @FXML
    private ImageView mediaImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label categoriesLabel;

    @FXML
    private Label ratingLabel;

    private Media selectedMedia = MediaMainPageController.getSelectedMedia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitleLabel();
        setCategoriesLabel();
        setRatingLabel();
        try {
            setMediaImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Add functionality for "Add to my list"

    public String returnTitleLabelString(){
        return titleLabel.getText();
    }

    public Media getSelectedMedia(){
        return selectedMedia;
    }

    public void setTitleLabel(){
        titleLabel.setText(selectedMedia.getTitle() + " " + "(" + selectedMedia.getReleaseYear() + ")");
    }

    public void setCategoriesLabel(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < selectedMedia.getCategories().length; i++){
            if(i < selectedMedia.getCategories().length-1){
                builder.append(selectedMedia.getCategories()[i] + ", ");
            } else {
                builder.append(selectedMedia.getCategories()[i]);
            }
        }
        categoriesLabel.setText(builder.toString());
    }

    public void setRatingLabel(){
        ratingLabel.setText(Double.toString(selectedMedia.getRating()));
    }

    public void setMediaImage() throws Exception {
        BufferedImage bufferedImage = (BufferedImage) selectedMedia.getImage();
        Image img = SwingFXUtils.toFXImage(bufferedImage, null);
        mediaImage.setImage(img);
    }

    /* Metode der styrer btBack. Metoden navigerer brugeren MediaMainPage */
    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage)btBack.getScene().getWindow(); //Henter button-logins scene/vindue
        Parent root = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml")); //loader MediaMainpage.fxml ind
        Scene scene = new Scene(root); //opretter ny scene med MediaMainPage.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }

    public void playMovie(ActionEvent e) throws IOException {
        Stage stage = (Stage)btBack.getScene().getWindow(); //Henter button-logins scene/vindue
        Parent root = FXMLLoader.load(getClass().getResource("playMovie.fxml")); //loader Login.fxml ind

        Scene scene = new Scene(root); //opretter ny scene med Login.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }


    //TODO Indsæt sæsoner og epsioder ved serier
}
