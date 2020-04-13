package Datamaskin.FXML;

import Datamaskin.Order.FinalOrder;
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
import java.util.ResourceBundle;

public class AllOrdersController implements Initializable {

    @FXML private TableView<FinalOrder> allOrders;
    @FXML private TableColumn<String, FinalOrder> emailColumn;
    @FXML private TableColumn<String, FinalOrder> orderIDColumn;
    // @FXML private TableColumn<Date, Order> orderDateColumn;
    @FXML private TableColumn<Double, FinalOrder> totalPriceColumn;
    @FXML private Button toSuperuserpage;

    //knappen "tilbake" tar brukeren med tilbake til menysiden for superbruker
    @FXML void toSuperuserpage(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) toSuperuserpage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
        newScene.toSuperuserpage(primaryStage, root);
    }

    // metoder for å legge inn ordreregisteret på denne siden
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        // orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

        EnduserSendOrderPageController.OrderRegister.leggTilOrdre(allOrders);
    }
}
