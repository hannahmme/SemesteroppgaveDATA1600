package Datamaskin.FXML;

import Datamaskin.Order.Order;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class UserspesificOrderController implements Initializable {

        @FXML private Button toMainpage;

        @FXML private TableView<Order> allOrders;
        @FXML private TableColumn<Order, String> emailColumn;
        @FXML private TableColumn<Order, String> orderIDColumn;
        // @FXML private TableColumn<Date, Order> orderDateColumn;
        @FXML private TableColumn<Double, Order> totalPriceColumn;
        @FXML private Button tilSuperbrukerside;

        //knappen "tilbake" tar brukeren med tilbake til menysiden for superbruker
        @FXML void toMainpage(ActionEvent event) throws IOException {
            Stage primaryStage = (Stage) toMainpage.getScene().getWindow();
            newScene.tilHovedside(primaryStage, FXMLLoader.load(getClass().getResource("Mainpage.fxml")));
        }


        // metoder for å legge inn ordreregisteret på denne siden
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        // orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

        EnduserSendOrderPageController.orderRegister.leggTilOrdre(allOrders);

    }


    // for å søke etter emailen til bruker skjult, så bare brukerens ordre kommer opp
        public void checkEmail(){

                // setter en sorteringsgreie så kun emailen som skrives inn kommer opp

        }

}

