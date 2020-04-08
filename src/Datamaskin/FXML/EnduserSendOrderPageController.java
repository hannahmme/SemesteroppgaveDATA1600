package Datamaskin.FXML;

import Datamaskin.Customer;
import Datamaskin.Exceptions.InvalidEmailException;
import Datamaskin.Order.Order;
import Datamaskin.Order.OrderRegister;
import Datamaskin.Product.Product;
import Datamaskin.Product.ProductRegister;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        // legger inn en if-setning, hvis bruker har sendt bestillingen med suksess, får man ikke opp denne alerten - Amalie
        if(btnSendOrder.isDisabled()){
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.tilHovedside(primaryStage, root);
        } else {
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
    }

    @FXML void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    public static OrderRegister orderRegister = new OrderRegister();

    @FXML void sendOrder(ActionEvent event) throws IOException, InvalidEmailException {
        Order anOrder = createOrderObjectFromGUI();

        if(anOrder != null) {
            orderRegister.addElement(anOrder);
            txtEpost.setText("");
        }
    }

    // metode for å generere ordreID
    private static int orderID = 100;
    public String generateOrderID (){
        orderID++;
        return "#"+orderID;
    }


    public Order createOrderObjectFromGUI(){
        String orderID;
        String email;
        int totalbeløp;

        try {
            // sjekker om input er riktig
            email = txtEpost.getText();
            Customer.validateEmail(email);

            // henter totalbeløpet til bestillingen
            totalbeløp = 200;

            //lager en ordreID for bestillingen og viser den til bruker
            orderID = generateOrderID();
            lblOrderSent.setText("Thank you for your order, here is your order ID: #" + orderID);

            Order anOrder = new Order(orderID, email, totalbeløp);

            // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
            btnSendOrder.setDisable(true);
            btnGoBack.setDisable(true);

            // returnerer ordren siden alt er riktig av input osv
            return anOrder;

        } catch (InvalidEmailException e){
            lblOrderSent.setText("Order not sent, please put in a valid E-mail address");
        }
        return null;

    }

}

/* legge til bestillingen gjort på forrige side i et array over fullførte bestillinger.
        Den bør allerede ligge i et "midlertidig" array fra forrige side, i tilfelle bruker vil se handlekurven igjen før bestilling.
        Dermed kan ordren hentes ut fra det midlertidige arrayet og legges til i et endelig array med alle ordre, og slettes
        fra det midlertidige arrayet
        */