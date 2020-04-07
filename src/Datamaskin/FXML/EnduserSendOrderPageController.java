package Datamaskin.FXML;

import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EnduserSendOrderPageController {

    @FXML
    private Button btnSendOrder;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnGoToMainpage;

    @FXML
    void goToMainpage(ActionEvent event) throws IOException {
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
            primaryStage.show();
        }
    }

    @FXML
    void sendOrder(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnSendOrder.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));

        primaryStage.show();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

}