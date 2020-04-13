// Tilhører siden med to knapper for superbruker, der admin kan velge mellom å lage nye komponenter eller å se alle ordre
package Datamaskin.FXML;


import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperuserPageController {

    @FXML private Button tilKomponentside;
    @FXML private Button tilOrdreside;
    @FXML private Button tilHovedside;

    //knapp som sender bruker tilbake til hovedsiden
    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        newScene.toMainpage(primaryStage, root);
    }


    //knapp som sender brukeren til siden hvor man kan administrere komponenter
    @FXML void tilKomponentside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilKomponentside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProductAdmPage.fxml"));
        primaryStage.setTitle("Å lage eller ikke lage nye komponenter");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }



    @FXML void tilOrdreside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilOrdreside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AllOrders.fxml"));
        primaryStage.setTitle("Ordre som har blitt laget av brukere");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

}

