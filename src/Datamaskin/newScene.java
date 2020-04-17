package Datamaskin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class newScene {

    //en generell metode som lar deg gå til den siden du ønsker - hannah
    public static void toThisPage(String windowTitle, Button btn, String fxmlpage) throws IOException {
        double scenewidth = 750.0;
        double sceneheight = 1250.0;
        Stage primarystage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(newScene.class.getResource(fxmlpage));
        Scene scene = new Scene(root, scenewidth, sceneheight);
        primarystage.setTitle(windowTitle);
        primarystage.setScene(scene);
        primarystage.show();
    }

    // metode for å lage en ny scene.
    public static void toMainpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toUserspesificOrder(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Oversikt over dine bestillinger");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toSuperuserpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Hei Superbruker");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurer essensielle deler til din datamaskin");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toExtraOrderEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Legg til ekstra tilbehør");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toEnduserSendOrderPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Fullfør bestilling");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toAllOrdersPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Alle ordre");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }

    public static void toProductAdminPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Opprett eller endre komponenter");
        primaryStage.setScene(new Scene(root, 1250, 750));
        primaryStage.show();
    }
}
