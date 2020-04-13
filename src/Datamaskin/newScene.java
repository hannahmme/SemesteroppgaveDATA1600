package Datamaskin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class newScene {

    // metode for å lage en ny scene.
    public static void toMainpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1100, 500));
        primaryStage.show();
    }

    public static void toUserspesificOrder(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Oversikt over dine bestillinger");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public static void toSuperuserpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Hei Superbruker");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

    public static void toEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurer essensielle deler til din datamaskin");
        primaryStage.setScene(new Scene(root, 1350, 800));
        primaryStage.show();
    }

    public static void toExtraOrderEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Legg til ekstra tilbehør");
        primaryStage.setScene(new Scene(root, 950, 700));
        primaryStage.show();

    }

    public static void toEnduserSendOrderPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Fullfør bestilling");
        primaryStage.setScene(new Scene(root, 1020, 725));
        primaryStage.show();
    }
}
