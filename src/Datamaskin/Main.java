package Datamaskin;

import Datamaskin.customer.CustomerRegister;
import Datamaskin.product.ProductCategories;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1250, 670));
        primaryStage.show();

        ProductCategories.setExampleData();
        CustomerRegister.setExampleCustomers();
    }

    public static void main(String[] args) {
        launch(args);
    }
}