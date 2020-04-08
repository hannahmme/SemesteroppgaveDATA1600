package Datamaskin.FXML;

import Datamaskin.Order.Order;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AllOrdersController implements Initializable {

    @FXML private TableView<Order> allOrders;
    @FXML private TableColumn<String, Order> emailColumn;
    @FXML private TableColumn<String, Order> orderIDColumn;
    // @FXML private TableColumn<Date, Order> orderDateColumn;
    @FXML private TableColumn<Double, Order> totalPriceColumn;
    @FXML private Button tilSuperbrukerside;

    //knappen "tilbake" tar brukeren med tilbake til menysiden for superbruker
    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSuperbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
        newScene.tilSuperbrukerside(primaryStage, root);
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
}
