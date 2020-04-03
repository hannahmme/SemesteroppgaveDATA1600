package Datamaskin.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BestillingssideController {

    @FXML private TextField txtEpost;

    @FXML private Button tilHovedside;

    // metode for å legge til bestillingen i et array
    @FXML void sendBestilling(ActionEvent event) {

    }


    //knapp for å sende bruker tilbake til hovedsiden
    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Hovedside.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

    }

}
