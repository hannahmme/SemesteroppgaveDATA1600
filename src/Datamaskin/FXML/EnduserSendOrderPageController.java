package Datamaskin.FXML;

import Datamaskin.Customer;
import Datamaskin.InvalidEmailException;
import Datamaskin.Order;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EnduserSendOrderPageController {

    @FXML private Button btnSendOrder;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private TextField txtEpost;
    @FXML private Label lblOrderSent;

    @FXML void goToMainpage(ActionEvent event) throws IOException {
        //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Vent litt...");
        alert.setContentText("Ønsker du å avslutte din bestilling og gå til hovedsiden?");
        ButtonType buttonYes = new ButtonType("Ja, det ønsker jeg");
        ButtonType buttonNo = new ButtonType("Nei");
        alert.getButtonTypes().addAll(buttonYes, buttonNo);
        Optional<ButtonType> userAnswer = alert.showAndWait();

        if (userAnswer.get() == buttonYes) {
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.tilHovedside(primaryStage, root);
        }
    }

    @FXML void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    @FXML void sendOrder(ActionEvent event) throws IOException, InvalidEmailException {
        // sjekke om eposten er riktig etter regex
            Order anOrder = createOrderObjectFromGUI();

        /* legge til bestillingen gjort på forrige side i et array over fullførte bestillinger.
        Den bør allerede ligge i et "midlertidig" array fra forrige side, i tilfelle bruker vil se handlekurven igjen før bestilling.
        Dermed kan ordren hentes ut fra det midlertidige arrayet og legges til i et endelig array med alle ordre, og slettes
        fra det midlertidige arrayet
        */

        // setter en tekst som sier at eposten er feil dersom den ikke er etter regex


        // setter en ordreID og en tekst om at bestillingen er gjennomført hvis epost er riktig
        //if (epost == gyldig){}


        // sette knappen for å sende bestillingen som disabled
    }

    private static int orderID = 100;
    public String generateOrderID (){
        orderID++;
        return "#"+orderID;
    }


    public Order createOrderObjectFromGUI(){
        String orderID;
        String email = null;
        int totalbeløp = 0;

        try {
            email = txtEpost.getText();
            Customer.validateEmail(email);

            totalbeløp = 200;

        } catch (InvalidEmailException e){
            e.printStackTrace();
        }

        orderID = generateOrderID();
        lblOrderSent.setText("Thank you for your order, here is your order ID: #" + orderID);


        Order anOrder = new Order(orderID, email, totalbeløp);

        return anOrder;
    }

}