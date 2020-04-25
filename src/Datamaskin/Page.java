package Datamaskin;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Page {


    //Generell metode som gir alert hvis man skal gå til hovedsiden - Hannah
    public static boolean alertConfirmed(String content) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Vent litt...");
        alert.setContentText(content);
        ButtonType buttonYes = new ButtonType("Ja, det ønsker jeg");
        ButtonType buttonNo = new ButtonType("Nei");
        alert.getButtonTypes().addAll(buttonYes, buttonNo);
        Optional<ButtonType> userAnswer = alert.showAndWait();

        return userAnswer.get() == buttonYes;
    }

    public static boolean alertInformation (String content){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Vent litt...");
        alert.setContentText(content);
        ButtonType buttonYes = new ButtonType("Ta meg med til forrige side");
        ButtonType buttonNo = new ButtonType("Bli på denne siden");
        alert.getButtonTypes().addAll(buttonYes, buttonNo);
        Optional<ButtonType> userAnswer = alert.showAndWait();

        return userAnswer.get() == buttonYes;
    }
    
    // metode for å lage en ny scene.
    public static void toMainpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toUserspesificOrder(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Oversikt over dine bestillinger");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toSuperuserpage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Hei Superbruker");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Konfigurer essensielle deler til din datamaskin");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toExtraOrderEnduserPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Legg til ekstra tilbehør");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }


    public static void toEnduserSendOrderPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Fullfør bestilling");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toAllOrdersPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Alle ordre");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void toProductAdminPage(Stage primaryStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        primaryStage.setTitle("Opprett eller endre komponenter");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();
    }

    public static void tonewUserPage(Stage newStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        newStage.setTitle("Opprett en ny bruker");
        newStage.setScene(new Scene(root, 625, 335));
        newStage.show();
    }

    public static void toAllUsersPage(Stage newStage, Parent FXMLString) throws IOException {
        Parent root = FXMLString;
        newStage.setTitle("Alle brukere");
        newStage.setScene(new Scene(root, 1250, 670));
        newStage.show();
    }

}
