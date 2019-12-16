package View;

import Exceptions.MediaAlreadyInMyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.*;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class MediaSpecificController implements Initializable {

    @FXML private ImageView btBack;

    @FXML private ImageView mediaImage;

    @FXML private Label titleLabel;

    @FXML private Label categoriesLabel;

    @FXML private Label ratingLabel;

    @FXML private Label addErrorLabel;

    @FXML private ComboBox<String> seasonComboBox = new ComboBox<>();

    private ComboBox<String> episodeComboBox = new ComboBox<>();

    private Media selectedMedia = MediaMainPageController.getSelectedMedia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            seasonComboBox.setDisable(true);
            seasonComboBox.setVisible(false);
            episodeComboBox.setDisable(true);
            episodeComboBox.setVisible(false);
        setTitleLabel();
        setCategoriesLabel();
        setRatingLabel();
        try {
            setMediaImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(selectedMedia instanceof Series){
            setSeasonComboBox();
            //setEpisodeComboBox();
        }
    }

    //TODO kan ikke tilføje episoder
    public void setSeasonComboBox(){
        seasonComboBox.setVisible(true);
        seasonComboBox.setDisable(false);
        for(int i = 0; i < ((Series) selectedMedia).getSeasons().size(); i++) {
            seasonComboBox.getItems().add("Season " + (i + 1));
        }
    }

        //TODO FUCKING HJÆLP
    /*
    public void setEpisodeComboBox(){
        for(int i = 0; i < ((Series) selectedMedia).getSeasons().size(); i ++){
            String s = seasonComboBox.getPromptText();
            int j = Integer.parseInt(s);
            episodeComboBox.getItems().add("Episode " + ((Series) selectedMedia).getSeasons().get(j));
        }
    }

     */



    //TODO Add functionality for "Add to my list"
    public void addCurrentMediaToUsersList(ActionEvent actionEvent) {
        Media media = MediaMainPageController.getSelectedMedia();
        Account account = LoginController.getUser();
        try {
            account.addToList(media);
        } catch (MediaAlreadyInMyList e) {
            String s = "The serie"; // fejlbesked tjekker om det er en film eller en serie
            if(media instanceof Movie) {
                s = "The movie";
            }
            addErrorLabel.setText(s + " is already in your list");
        } catch ( FileNotFoundException e) {
            addErrorLabel.setText("Hard error: Unable to find users file");
        }
    }

    public void setTitleLabel(){
        if(selectedMedia instanceof Movie) {
            titleLabel.setText(selectedMedia.getTitle() + " " + "(" + selectedMedia.getReleaseYear() + ")");
        }
        if(selectedMedia instanceof Series){
            String s = (selectedMedia.getTitle() + " (" + selectedMedia.getReleaseYear() + "-" + ((Series) selectedMedia).getEndYear() + ")");
            titleLabel.setText(s);
        }
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
        Parent root = FXMLLoader.load(getClass().getResource("playMovie.fxml")); //loader playMovie.fxml ind

        Scene scene = new Scene(root); //opretter ny scene med playMovie.fxml som indhold
        stage.setScene(scene); //Sætter scenen
        stage.show(); //viser scenen for brugeren
    }

    //TODO Indsæt sæsoner og epsioder ved serier
}
