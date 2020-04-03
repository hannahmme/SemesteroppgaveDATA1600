package Datamaskin.FXML;

import Datamaskin.nyScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OrdresideSluttbrukerController {

    @FXML private Button tilHovedside;

    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Hovedside.fxml"));
        nyScene.tilHovedside(primaryStage, root);
    }

}
