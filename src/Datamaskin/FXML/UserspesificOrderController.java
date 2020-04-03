package Datamaskin.FXML;

import Datamaskin.Order;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class UserspesificOrderController {

    @FXML private TableColumn<Order, Integer> ordernumberColumn;
    @FXML private TableColumn<Order, String> emailColumn;
    @FXML private TableColumn<Order, Date> orderdateColumn;
    @FXML private TableColumn<Order, Double> totalPriceColumn;

    @FXML private Button tilHovedside;

    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        newScene.tilHovedside(primaryStage, FXMLLoader.load(getClass().getResource("Mainpage.fxml")));
    }

    /*
    Kan vi populere tableviewet med samme metoder som vi populerer tableviewet som admin ser, og så
    prøve å bruke filtrering for å filtrere listen med eposten brukeren har skrevet inn før brukeren ser listen?

     */




}
