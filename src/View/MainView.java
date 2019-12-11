package View;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        stage.setTitle("Stream Till You Sweam");
        stage.setScene(new Scene(root));
        stage.show();

    }
}


/* Når denne metode kaldes, bliver scenen ændret til MediaPage fra Login */
    /*public static void changeScreen(ActionEvent event) throws IOException
    {
        Parent mediaPageParent = FXMLLoader.load(getClass().getResource("MediaMainPage.fxml"));
        Scene mediaPageScene = new Scene(mediaPageParent);

        // Denne linje giver Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    }
}




/*public class MainView extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.Design.fxml"));
        primaryStage.setTitle("Stream till you sweam");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args){launch(args);}
}*/



/*public class MainView extends Application implements EventHandler<ActionEvent>{ // giver javafx application funktionalitet og implementerer interface Eventhandler.

    Button button = new Button(); // opretter en button med navn button

    public static void main(String[] args) {
        launch(args); //en metode i Application klassen, sætter programmet op som en javafx application
    }

    @Override
    public void start(Stage primaryStage) throws Exception { // main javafx kode starter her. Først kører den launch går den ind i application og så kører den start.
        primaryStage.setTitle("Stream till you sweam");
        button.setText("Click me"); // tilføjer tekst.

        button.setOnAction(this); // buttons kode er i denne klasse

        StackPane layout = new StackPane(); // gør at knappen kommer til at stå i midten.
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,300,275);
        primaryStage.setScene(scene);// brug scenen ovenover til vinduet.
        primaryStage.show();// viser Stage.

    }

    @Override
    public void handle(ActionEvent event) { // kaldes når brugeren klikker på button
        if(event.getSource()==button)
        {
            System.out.println("I love it");
        }
    }
    //LIGEMEGET I ØJEBLIKKET
    // Stage er selve vinduet.
    //Scene er alt der foregår på stage.
    /*private static void launch(String[] args) {
    }

    public void start(Stage primaryStage) {
        Parent root = FXMLLoader.load(getClass().getResource("View.Design.fxml"));
        primaryStage.setScene(new Scene(root, 100, 100));
        primaryStage.show();

    }*/
//}
