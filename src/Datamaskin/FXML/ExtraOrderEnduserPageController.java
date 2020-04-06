package Datamaskin.FXML;

import Datamaskin.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExtraOrderEnduserPageController implements Initializable {

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
    private TextArea txtExtraComponentInfo;

    @FXML
    private TableView<Component> tblExtraComponent;

    @FXML
    private TableColumn<Component, String> componentName;

    @FXML
    private TableColumn<Component, String> componentInfo;

    @FXML
    private TableColumn<Component, Double> componentPrice;

    @FXML
    private TableColumn<Component, CheckBox> componentChosen;

    @FXML
    private Button btnSaveToCart;

    @FXML
    private Button btnGoToPay;

    @FXML
    private TextField txtTotalPay;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnGoToMainpage;

    private ObservableList<Component> extraComponentList = FXCollections.observableArrayList();

    @FXML
    void addToCart(ActionEvent event) {
        isChecked();
        System.out.println(extraComponentList);
        tblExtraComponent.setItems(extraComponentList);
    }


    public ObservableList isChecked(){
        if (checkBox1.isSelected()) {
            extraComponentList.add(object1);
            checkBox1.setSelected(false);
        }
        if (checkBox2.isSelected()) {
            extraComponentList.add(object2);
            checkBox2.setSelected(false);
        }
        if (checkBox3.isSelected()) {
            extraComponentList.add(object3);
            checkBox3.setSelected(false);
        }
        if (checkBox4.isSelected()) {
            extraComponentList.add(object4);
            checkBox4.setSelected(false);
        }
        if (checkBox5.isSelected()) {
            extraComponentList.add(object5);
            checkBox5.setSelected(false);
        }
        if (checkBox6.isSelected()) {
            extraComponentList.add(object6);
            checkBox6.setSelected(false);
        }
        return extraComponentList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //liste som er i tableviewet
        ObservableList<Component> komponentList = FXCollections.observableArrayList();
        komponentList.addAll(object1, object2, object3, object4, object5, object6);

        tblExtraComponent.getItems().addAll(komponentList);
        componentInfo.setCellValueFactory(new PropertyValueFactory<Component, String>("componentInfo"));
        componentName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
        componentPrice.setCellValueFactory(new PropertyValueFactory<Component, Double>("componentPrice"));
        componentChosen.setCellValueFactory(new PropertyValueFactory<Component, CheckBox>("checkbox"));
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        primaryStage.setTitle("Velg essensielle deler for å bygge din egen datamaskin!");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    @FXML
    void goToMainpage(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        primaryStage.setTitle("Konfigurasjonssystem for datamaskiner");
        primaryStage.setScene(new Scene(root, 1200,800));
        primaryStage.show();
    }

    @FXML
    void goToPay(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        primaryStage.setTitle("Send bestilling");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();

    }

}
