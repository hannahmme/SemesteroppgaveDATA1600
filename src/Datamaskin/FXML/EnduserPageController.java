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


public class EnduserPageController implements Initializable {

    private CheckBox checkBox1 = new CheckBox();
    private CheckBox checkBox2 = new CheckBox();
    private CheckBox checkBox3 = new CheckBox();
    private CheckBox checkBox4 = new CheckBox();
    private CheckBox checkBox5 = new CheckBox();
    private CheckBox checkBox6 = new CheckBox();

    private Component object1 = new Component("Skjermkort", "Nyeste på markedet", 1000, checkBox1);
    private Component object2 = new Component("Tastatur", "Best i test", 500, checkBox2);
    private Component object3 = new Component("Mus", "Passer til alle pcer", 499, checkBox3);
    private Component object4 = new Component("Harddisk", "God plass!", 4000, checkBox4);
    private Component object5 = new Component("Monitor", "4k-skjerm", 2999, checkBox5);
    private Component object6 = new Component("Prosessor", "Beste CPU-en", 1599, checkBox6);


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //liste som er i tableviewet
        ObservableList<Component> komponentList = FXCollections.observableArrayList();
        komponentList.addAll(object1, object2, object3, object4, object5, object6);

        tableView.getItems().addAll(komponentList);
        componentInfo.setCellValueFactory(new PropertyValueFactory<Component, String>("componentInfo"));
        componentName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
        componentPrice.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentPrice"));
        componentChecked.setCellValueFactory(new PropertyValueFactory<Component, CheckBox>("checkbox"));

    }

    //liste over valgte produkter
    private ObservableList<Component> cartList = FXCollections.observableArrayList();

    @FXML
    void loadPayment(ActionEvent event) throws IOException {

        // legger inn metoden for å åpne ny scene - Amalie
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("OrderComponents.fxml"));
        primaryStage.setTitle("Sluttbruker hello: her gjør du ferdig din bestilling");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    @FXML
    void AddToCart(ActionEvent event) {
        isChecked();
        System.out.println(cartList);
        txtCart.setItems(cartList);
        }

        //hører til side hvor sluttbruker legger til tilbehør (checkbokser).
    public ObservableList isChecked(){
        if (checkBox1.isSelected()) {
            cartList.add(object1);
            checkBox1.setSelected(false);
        }
        if (checkBox2.isSelected()) {
            cartList.add(object2);
            checkBox2.setSelected(false);
        }
        if (checkBox3.isSelected()) {
            cartList.add(object3);
            checkBox3.setSelected(false);
        }
        if (checkBox4.isSelected()) {
            cartList.add(object4);
            checkBox4.setSelected(false);
        }
        if (checkBox5.isSelected()) {
            cartList.add(object5);
            checkBox5.setSelected(false);
        }
        if (checkBox6.isSelected()) {
            cartList.add(object6);
            checkBox6.setSelected(false);
        }
        return cartList;
    }

    @FXML
    void getSelected(MouseEvent event) {
        if (checkBox1.isSelected()) {

        }
   /*     Component component = tableView.getSelectionModel().getSelectedItem();
        if(component == null){
            lblError.setText("Ingenting huket av.");
        }else{
            String name = component.getComponentName();
            int price = component.getComponentPrice();

            txtCart.setText("Produktnavn: "+ name + "\nPris: "+ price+",-");
        }*/
    }

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
