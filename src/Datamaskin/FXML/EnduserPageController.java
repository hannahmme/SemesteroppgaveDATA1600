// denne siden gjelder sluttbruker siden der man skal velge komponenter

package Datamaskin.FXML;

import Datamaskin.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EnduserPageController {

    @FXML
    private Button btnGoBack;

    @FXML
    private TextField txtUserid;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Button btnGoToPay;

    @FXML
    private TableView<Component> txtCart;

    @FXML
    private Button btnAddToCart;
    
    @FXML
    private TableView<Component> tableView;

    @FXML
    private TableColumn<Component, String> componentInfo;

    @FXML
    private TableColumn<Component, String> componentName;

    @FXML
    private TableColumn<Component, Integer> componentPrice;

    @FXML
    private TableColumn<Component, CheckBox> componentChecked;

    //liste over valgte produkter
    private ObservableList<Component> cartList = FXCollections.observableArrayList();

    @FXML
    void loadPayment(ActionEvent event) throws IOException {

        // legger inn metoden for å åpne ny scene - Amalie
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        primaryStage.setTitle("Sluttbruker hello: Har du glemt noe tilbehør til din pc?");
        primaryStage.setScene(new Scene(root, 900, 650));
        primaryStage.show();
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {

        //legger inn metoden for å åpne tidligere side (forside) - Hannah
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

/*
    @FXML
    void getSelected(MouseEvent event) {
        if (checkBox1.isSelected()) {

        }
        Component component = tableView.getSelectionModel().getSelectedItem();
        if(component == null){
            lblError.setText("Ingenting huket av.");
        }else{
            String name = component.getComponentName();
            int price = component.getComponentPrice();

            txtCart.setText("Produktnavn: "+ name + "\nPris: "+ price+",-");
        }
    }
*/

    EventHandler checkBoxChanged = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() instanceof CheckBox) {
                CheckBox chk = (CheckBox) event.getSource();
                System.out.println("Action performed on checkbox " + chk.getText());
            }
        }
    };




}
