package Datamaskin.FXML;

import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderComponentsController {

    @FXML private TextField txtEpost;
    @FXML private Label gjennomførtBestilling;

    @FXML private Button tilHovedside;

    @FXML private Button tilSluttbrukerside;

    // metode for å legge til bestillingen i et array
    @FXML void sendBestilling(ActionEvent event) {

        // sjekke om eposten er riktig etter regex

        // setter en tekst for å vise at handelen er gjennomført dersom epost er riktig etter regex
        gjennomførtBestilling.setText("Takk for din bestilling");

        /* legge til bestillingen gjort på forrige side i et array over fullførte bestillinger.
        Den bør allerede ligge i et "midlertidig" array fra forrige side, i tilfelle bruker vil se handlekurven igjen før bestilling.
        Dermed kan ordren hentes ut fra det midlertidige arrayet og legges til i et endelig array med alle ordre, og slettes
        fra det midlertidige arrayet
        */

        // setter en tekst som sier at eposten er feil deroms den ikke er etter regex

        // må sette knappen for å gå tilbake til handlekurven som disabled
    }


    //knapp for å sende bruker tilbake til hovedsiden
    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        newScene.tilHovedside(primaryStage, root);

        /* metode for å tømme det midlertidige arrayet som inneholder ordren fra handlekurven.
        Dette fordi at en ny bruker ikke skal ha tilgang på den dersom bruker ikke sender bestilling, men bare går tilbake til hovedsiden

        Dersom arrayet ikke er tomt, kan det kanskje komme opp en varselmelding om at all fremgang vil bli slettet?
        Så vet brukeren at hvis hun trykker på hjem-knappen, vil orderen bli slettet.

         */

    }

    //knapp for å sende bruker tilbake til handlekurven dersom hun vil endre på bestillingen
    @FXML void tilSluttbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSluttbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        newScene.toEnduserPage(primaryStage, root);


    }

}
