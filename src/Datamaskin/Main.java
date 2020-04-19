package Datamaskin;

import Datamaskin.orders.OrderExample;
import Datamaskin.Product.ProductCategories;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();

        //Setter all eksempeldata ved start av applikasjon så den ikke dupliseres
        ProductCategories.setExampleData();
        OrderExample.setExampleData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}