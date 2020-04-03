package Datamaskin.FXML;

import Datamaskin.nyScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KomponentsideController {

    @FXML private Button tilSuperbrukerside;

    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSuperbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Superbrukerside.fxml"));
        nyScene.tilSuperbrukerside(primaryStage, root);

    }

}
