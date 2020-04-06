package Datamaskin.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EnduserSendOrderPageController {

    @FXML
    private Button btnSendOrder;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnGoToMainpage;

    @FXML
    void goToMainpage(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    @FXML
    void sendOrder(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnSendOrder.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        primaryStage.setTitle("Send din bestilling");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

}