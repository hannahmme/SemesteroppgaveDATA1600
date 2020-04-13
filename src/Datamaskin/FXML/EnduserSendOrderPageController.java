package Datamaskin.FXML;

import Datamaskin.Customer;
import Datamaskin.Exceptions.InvalidEmailException;
import Datamaskin.Order.FinalOrder;
import Datamaskin.Order.FinalOrderRegister;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class EnduserSendOrderPageController {

    @FXML private Button btnSendOrder;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private TextField txtEpost;
    @FXML private Label lblOrderSent;
    @FXML private Label lblTotalPrice;

    // metode for å gå tilbake til hovedside
    @FXML void goToMainpage(ActionEvent event) throws IOException {

        // legger inn en if-setning, hvis bruker har sendt bestillingen med suksess, får man ikke opp denne alerten - Amalie
        if(btnSendOrder.isDisabled()){
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.toMainpage(primaryStage, root);
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
                newScene.toMainpage(primaryStage, root);
            }
        }
    }

    // metode for å gå tilbake til forrige side
    @FXML void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    @FXML private TableView<FinalOrder> finalOrderRegister;
    @FXML private TableColumn<FinalOrder, String> nameColumn;
    @FXML private TableColumn<FinalOrder, String> priceColumn;
    
    
    public static FinalOrderRegister OrderRegister = new FinalOrderRegister();


    @FXML void sendOrder(ActionEvent event) throws IOException, InvalidEmailException {
        FinalOrder anFinalOrder = createOrderObjectFromGUI();

        if(anFinalOrder != null) {
            OrderRegister.addElement(anFinalOrder);
            txtEpost.setText("");
        }
    }


    // metode for å generere ordreID, tenker det er greit å starte på 100? Så kan eksempler være før 100.
    private static int orderID = 100;
    public String generateOrderID (){
        orderID++;
        return "#"+orderID;
    }

    // metode for å generere en ordre og legget il ordreID og epost i array
    public FinalOrder createOrderObjectFromGUI(){
        String orderID;
        String email;
        int totalbeløp;

        try {
            // sjekker om input er riktig
            email = txtEpost.getText();
            Customer.validateEmail(email);

            // henter totalbeløpet til bestillingen
            totalbeløp = 200;

            // henter datoen
            Date date = Date.valueOf(LocalDate.now());

            //lager en ordreID for bestillingen og viser den til bruker
            orderID = generateOrderID();
            lblOrderSent.setText("Thank you for your order, here is your order ID: " + orderID);

            FinalOrder anFinalOrder = new FinalOrder(orderID, email, date, totalbeløp);

            // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
            btnSendOrder.setDisable(true);
            btnGoBack.setDisable(true);

            // Setter totalprisen til brukers skjerm
            lblTotalPrice.setText(String.valueOf(totalbeløp));

            // returnerer ordren siden alt er riktig av input osv
            return anFinalOrder;

        } catch (InvalidEmailException e){
            lblOrderSent.setText("Order not sent, please put in a valid E-mail address");
        }
        return null;

    }





}

/* Mye av den koden vi trenger her kan finnes fra enduserpagecontroller-siden*/

/* legge til bestillingen gjort på forrige side i et array over fullførte bestillinger.
        Den bør allerede ligge i et "midlertidig" array fra forrige side, i tilfelle bruker vil se handlekurven igjen før bestilling.
        Dermed kan ordren hentes ut fra det midlertidige arrayet og legges til i et endelig array med alle ordre, og slettes
        fra det midlertidige arrayet
        */