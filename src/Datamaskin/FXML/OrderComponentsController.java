package Datamaskin.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderComponentsController {

    @FXML private TextField txtEpost;
    @FXML private Label gjennomførtBestilling;

    @FXML private Button tilHovedside;

    // metode for å legge til bestillingen i et array
    @FXML void sendBestilling(ActionEvent event) {

        // sjekke om eposten er riktig etter regex

        // setter en tekst for å vise at handelen er gjennomført dersom epost er riktig etter regex
        gjennomførtBestilling.setText("Takk for din bestilling");

        /* legge til bestillingen gjort på forrige side i et array over fullførte bestillinger.
        Den bør allerede ligge i et "midlertidig" array fra forrige side, i tilfelle bruker vil se handlekurven igjen før bestilling.
        Dermed kan ordren hentes ut fra det midlertidige arrayet og legges til i et endelig array med alle ordre.
        */

        // setter en tekst som sier at eposten er feil deroms den ikke er etter regex
    }


    //knapp for å sende bruker tilbake til hovedsiden
    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

    }

}
