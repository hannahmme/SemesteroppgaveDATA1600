package Datamaskin.FXML;

import Datamaskin.Cart.Cart;
import Datamaskin.Customer;
import Datamaskin.Exceptions.InvalidEmailException;
import Datamaskin.Order.FinalOrder;
import Datamaskin.Order.FinalOrderRegister;
import Datamaskin.Product.Product;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class EnduserSendOrderPageController implements Initializable {

    @FXML private Button btnSendOrder;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private TextField txtEpost;
    @FXML private Label lblOrderSent;
    @FXML private Label lblTotalPrice;

    private Cart shoppingcart = new Cart();


    // metode som setter den totale prisen basert på komponentene i arrayet
    public void setTotalPriceLabel(){
        double totalPrice = shoppingcart.getTotalPrice();
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }


    // metode for å gå tilbake til hovedside
    @FXML void goToMainpage(ActionEvent event) throws IOException {

        // legger inn en if-setning, hvis bruker har sendt bestillingen med suksess, får man ikke opp denne alerten - Amalie
        if(btnSendOrder.isDisabled()){
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.toMainpage(primaryStage, root);
        } else {
            //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
            shoppingcart.alertWhenMainpage(btnGoToMainpage);

                //metode som nullstiller handlekurven om bruker avslutter handlingen
                shoppingcart.deleteShoppingcart();
            }
    }

    // metode for å gå tilbake til forrige side
    @FXML void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }


    //Handlekurv på høyre side
    //@FXML private TableView<Product> tableviewCart;
    @FXML private TableView<FinalOrder> finalOrderRegister;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    
    public static FinalOrderRegister OrderRegister = new FinalOrderRegister();

    @Override public void initialize(URL location, ResourceBundle resources) {
        //Handlekurven på høyre side lastes inn når siden lastes inn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));


        //Kobler handlekurven med tableviewet.
        shoppingcart.attachTableview(finalOrderRegister);

        //Setter riktig totaltpris ved innlasting av siden
        setTotalPriceLabel();
    }


    @FXML void sendOrder(ActionEvent event) throws IOException, InvalidEmailException {
        FinalOrder anFinalOrder = createOrderObjectFromGUI();

        if(anFinalOrder != null) {
            OrderRegister.addElement(anFinalOrder);
            txtEpost.setText("");
            shoppingcart.deleteShoppingcart();
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
        double totalPrice;

        try {
            // sjekker om input er riktig
            email = txtEpost.getText();

            if(!Customer.validateEmail(email)){
                throw new InvalidEmailException("Skriv inn gyldig e-postadresse");
            }
            else{
                // henter totalbeløpet til bestillingen
                totalPrice = shoppingcart.getTotalPrice();

                // henter datoen, ikke helt ferdig
                Date date = Date.valueOf(LocalDate.now());

                //lager en ordreID for bestillingen og viser den til bruker
                orderID = generateOrderID();
                lblOrderSent.setText("Takk for din ordre.\nOrdrenummer: " + orderID);

                FinalOrder anFinalOrder = new FinalOrder(orderID, email, date, totalPrice);

                // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
                btnSendOrder.setDisable(true);
                btnGoBack.setDisable(true);

                // Setter totalprisen til brukers skjerm
                lblTotalPrice.setText(String.valueOf(totalPrice));

                // returnerer ordren siden alt er riktig av input osv
                return anFinalOrder;
            }
        } catch (InvalidEmailException e){
            lblOrderSent.setText("Ordre ikke registrert, vennligst skriv inn gyldig e-postadresse");
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