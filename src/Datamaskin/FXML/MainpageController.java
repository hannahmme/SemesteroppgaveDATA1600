package Datamaskin.FXML;

import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class MainpageController {

    @FXML private Button tilSluttbrukerside;
    @FXML private Button tilSuperbrukerside;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;


    @FXML private Button tilBrukerOrdreSide;

    @FXML void tilBrukerOrdreSide(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSluttbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserspesificOrder.fxml"));
        newScene.toUserspesificOrder(primaryStage, root);
        primaryStage.show();
    }

    // metode som åpner ny scene til superbrukersiden
    @FXML void tilSluttbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSluttbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        newScene.toEnduserPage(primaryStage, root);
    }


    // metode som åpner ny scene til sluttbrukersiden
    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        String username = null;
        String password = null;
        try {
            username = txtUsername.getText();
            password = txtPassword.getText();
        } catch(Exception e){
            e.printStackTrace();
        }

        if (username.matches("admin") && password.matches("admin")) {
            try {
                Stage primaryStage = (Stage) tilSuperbrukerside.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
                newScene.tilSuperbrukerside(primaryStage, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
