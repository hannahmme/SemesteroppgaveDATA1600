package Datamaskin.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HovedsideController {

    // metode som åpner ny scene til superbrukersiden
    @FXML void tilSluttbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primaryStage.setTitle("Velg dine deler");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();



    }


    // metode som åpner ny scene til sluttbrukersiden
    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Superbrukerside.fxml"));
        primaryStage.setTitle("Velg dine deler");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();




    }

}
