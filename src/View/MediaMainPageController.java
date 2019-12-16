package View;

import Model.Media;
import Model.MediaConstructor;
import Model.Movie;
import Model.Series;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.jfr.Category;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class MediaMainPageController implements Initializable {

    @FXML BorderPane borderPane;

    @FXML ScrollPane bpScrollPane;

    @FXML VBox vBoxFilm;

    @FXML Label lbMyList;

    @FXML ScrollPane spMyList;

    @FXML HBox hbMyList;

    @FXML Label lbFilm;

    @FXML ScrollPane spMovie;

    @FXML HBox hbFilm;

    @FXML Label lbSeries;

    @FXML ScrollPane spSeries;

    @FXML HBox hbSeries;

    @FXML Button logOutButton;

    @FXML Button btMovies;

    @FXML Button btSeries;

    @FXML TextField txtSearch;

    @FXML ImageView ivMovieOfTheDay;

    @FXML ComboBox<String> categoryComboBox;

    @FXML TextField ratingTextField;

    @FXML Label usernameLabel;


    public static Media selectedMedia;


    MediaConstructor mc = new MediaConstructor(); //Global variabel og ikke kun i Initialize (skal bruges andre steder)

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAll();
        initializeCategory();
        usernameLabel.setText("Hello, " + LoginController.getUser().getUsername());
        try {
            randomMedia();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Metode der opretter og indsætter Movie i Imageview med billede*/
    public void insertMovie(Media media) {
        try {
            //Opretter billede
            //Image image = new Image(getClass().getResource("filmplakater/Billeder/" + media.getImage()).toExternalForm());
            //System.out.println("filmplakater/Billeder/" + media.getImage());

            BufferedImage bufferedImage = (BufferedImage) media.getImage();
            Image img = SwingFXUtils.toFXImage(bufferedImage, null);

            //Opretter plads til billede i HBox
            ImageView imageView = new ImageView();

            imageView.setOnMouseClicked(mouseEvent -> {
                Parent root = null;
                Stage stage = (Stage) imageView.getScene().getWindow();
                try {
                    selectedMedia = new Movie(media.getTitle(), media.getReleaseYear(), media.getRating(), media.getCategories());
                    //loader MediaPageSpecific.fxml ind
                    root = FXMLLoader.load(getClass().getResource("MediaSpecific.fxml"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root); //opretter ny scene med MediaSpecific.fxml som indhold
                stage.setScene(scene); //Sætter scenen
                stage.show(); //viser scenen for brugeren
            });

            imageView.setImage(img);
            //Opretter plads til billede i HBox
            //ImageView imageView = new ImageView();
            //imageView.setImage(img);

            //Indsætter billede i HBox
            hbFilm.getChildren().add(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TODO hvordan skal MediaSpecific reagere ved series?
    /* Metode der opretter og indsætter Serie i Imageview med billede */
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
            hbSeries.getChildren().addAll(imageView);

            imageView.setOnMouseClicked(mouseEvent -> {
                Parent root = null;
                Stage stage = (Stage) imageView.getScene().getWindow(); //Henter button-logins scene/vindue
                try {
                    selectedMedia = new Series(media.getTitle(), media.getReleaseYear(), ((Series) media).getEndYear(), media.getRating(), ((Series) media).getCategories(), ((Series) media).getSeasons());
                    root = FXMLLoader.load(getClass().getResource("MediaSpecific.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root); //opretter ny scene med MediaSpecific.fxml som indhold
                stage.setScene(scene); //Sætter scenen
                stage.show(); //viser scenen for brugeren
            });

            //Indsætter billede i HBox
            hbSeries.getChildren().add(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Media getSelectedMedia(){
        return selectedMedia;
    }

    /* Metode der opretter og indsætter Mylist i Imageview med billede i Hbox (hbMyList) */
    public void insertMyList(Media media) {
        File file = new File("./Data/Accounts/" + LoginController.getUser().getEmail() + ".txt");
        String lines;
        try {
            if (file.isFile()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./Data/Accounts/" + LoginController.getUser().getEmail() + ".txt"), StandardCharsets.ISO_8859_1));// charset kan læse svenske symboler.
                reader.readLine();// skipper første linje
                lines = reader.readLine();
                String[] mediaString = lines.split(";");
                for (String s : mediaString) {
                    if (s.equals(media.getTitle())) {
                        BufferedImage bufferedImage = (BufferedImage) media.getImage();
                        Image img = SwingFXUtils.toFXImage(bufferedImage, null);

                        //Opretter plads til billede i HBox
                        ImageView imageView = new ImageView();
                        imageView.setImage(img);

                        //Indsætter billede i HBox
                        hbMyList.getChildren().add(imageView);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Metode der kontrollerer logOut Button således at der skiftes til Login siden */
    public void logOut(ActionEvent e) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void randomMedia() throws Exception {
        Media randomMedia = mc.getRandomMedia();

        BufferedImage bufferedImage = (BufferedImage) randomMedia.getImage();
        Image img = SwingFXUtils.toFXImage(bufferedImage, null);

        ivMovieOfTheDay.setImage(img);
        ivMovieOfTheDay.setOnMouseClicked(mouseEvent -> {
            selectedMedia = randomMedia;
            Parent root = null;
            Stage stage = (Stage) ivMovieOfTheDay.getScene().getWindow(); //Henter button-logins scene/vindue
            try {
                root = FXMLLoader.load(getClass().getResource("MediaSpecific.fxml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root); //opretter ny scene med MediaSpecific.fxml som indhold
            stage.setScene(scene); //Sætter scenen
            stage.show(); //viser scenen for brugeren
        });
    }



    /* Metode der først fjerner alle film og serier fra deres pågældende Hbox og derefter ittererer igennem et for loop.
    Hvor der tjekkes om det er en movie eller Serie der indeholder det man har indtastet i søgefeltet.*/
    public void searchFunction() {
        hbSeries.getChildren().clear(); //fjern alle gamle serier når man søger
        hbFilm.getChildren().clear();  //fjern alle gamle film når man søger

        for (Media m : mc.searchTitle(txtSearch.getText())) {
            {
                if (m instanceof Movie) { //type tjek på Movie
                    insertMovie(m); //hvis mediet er en Movie så tilføj
                }
                if (m instanceof Series) { //type tjek på Series
                    insertSerie(m); //hvis mediet er en Series så tilføj
                }
            }
        }
    }

    public void initializeCategory(){
        categoryComboBox.getItems().addAll("Action", "Adventure", "Animation", "Biography", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Musical",
                "Mystery", "Romance", "Sci-fi", "Sport", "Thriller", "War", "Western");
    }


    public void searchRating(){
        hbSeries.getChildren().clear(); //fjern alle gamle serier når man søger
        hbFilm.getChildren().clear();  //fjern alle gamle film når man søger
        String ratingString = ratingTextField.getText().replaceAll(",", ".");
        if(ratingString.isEmpty()){
            showAll();
        }
        for (Media m : mc.searchRating(Double.parseDouble(ratingString))){
            {
                if (m instanceof Movie) { //type tjek på Movie
                    insertMovie(m); //hvis mediet er en Movie så tilføj
                }
                if (m instanceof Series) { //type tjek på Series
                    insertSerie(m); //hvis mediet er en Series så tilføj
                }
            }
        }
    }

    public void showAll(){
        hbFilm.getChildren().clear();
        hbSeries.getChildren().clear();
        try {
            mc.readMediaCollection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Løber gennem alle Media objekter
        for (Media media : mc.getContent()) {
            insertMyList(media);
            //Tjekker om media er en film
            if (media instanceof Movie) {
                insertMovie(media);
            }
            if (media instanceof Series) {
                insertSerie(media);
            }
        }

    }

    public void searchCategory() {
        hbSeries.getChildren().clear(); //fjern alle gamle serier når man søger
        hbFilm.getChildren().clear();  //fjern alle gamle film når man søger

            for (Media m : mc.searchCategory(categoryComboBox.getValue())) {
                {
                    if (m instanceof Movie) { //type tjek på Movie
                        insertMovie(m); //hvis mediet er en Movie så tilføj
                    }
                    if (m instanceof Series) { //type tjek på Series
                        insertSerie(m); //hvis mediet er en Series så tilføj
                    }
                }
            }
        }


    /* Metode der styrer button btMovies. Metoden har til formål at skifte side fra MediaMainPage til siden Movies. */
    public void btMovies() throws IOException {
        Stage stage = (Stage) btMovies.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Movies.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /* Metode der styrer button btSeries. Metoden har til formål at skifte side fra MediaMainPage til siden Series. */
    public void btSeries() throws IOException {
        Stage stage = (Stage) btSeries.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Series.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}