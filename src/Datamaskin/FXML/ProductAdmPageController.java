package Datamaskin.FXML;

import Datamaskin.ProductRegister;
import Datamaskin.Product;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductAdmPageController implements Initializable{

    @FXML private Button tilSuperbrukerside;

    // inputfields for å lage et produkt/ komponent
    @FXML private TextField txtComponentname;
    @FXML private TextField txtDescription;
    @FXML private TextField txtLifetime;
    @FXML private TextField txtPrice;

    // konfigurerer tabellen
    @FXML private TableView<Product> componentTableview;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> descriptionColumn;
    @FXML private TableColumn<Product, String> lifetimeColumn;
    @FXML private TableColumn<Product, Integer> priceColumn;

    // oppretter et nytt objekt av typen Produktregister
    ProductRegister aRegister = new ProductRegister();


    // knappen for å legge til et nytt produkt i listen
    @FXML void addComponent(ActionEvent event) {
        Product aProduct = createProductObjectFromGUI();
        if(aProduct != null) {
            aRegister.addElement(aProduct);
            tømTxtFelt();
        }
    }

    private void tømTxtFelt() {
        txtComponentname.setText("");
        txtDescription.setText("");
        txtLifetime.setText("");
        txtPrice.setText("");
    }

    private Product createProductObjectFromGUI(){
        //try catch metoder må inn her også
        String name = txtComponentname.getText();
        String description = txtDescription.getText();
        String lifetime = txtLifetime.getText();
        int price = Integer.parseInt(txtPrice.getText());

        Product aProduct = new Product(name, description, lifetime, price);
        return aProduct;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        aRegister.leggTilKomponent(componentTableview);

    }

        // kode for å komme tilbake til hovedmenyen for superbruker
    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSuperbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
        newScene.tilSuperbrukerside(primaryStage, root);
    }
}
