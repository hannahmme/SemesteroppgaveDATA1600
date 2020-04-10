package Datamaskin.FXML;

import Datamaskin.Component;
import Datamaskin.newScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
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

    @FXML private TextArea txtExtraComponentInfo;
    @FXML private TableView<Component> tblCart;
    @FXML private TableColumn<Component, String> cartName;
    @FXML private TableColumn<Component, String> cartInfo;
    @FXML private TableColumn<Component, Integer> cartPrice;
    @FXML private TableView<Component> tblExtraComponent;
    @FXML private TableColumn<Component, String> componentName;
    @FXML private TableColumn<Component, String> componentInfo;
    @FXML private TableColumn<Component, Double> componentPrice;
    @FXML private TableColumn<Component, CheckBox> componentChosen;
    @FXML private TableColumn<Component, CheckBox> cartCheck;

    @FXML private Button btnSaveToCart;
    @FXML private Button btnGoToPay;
    @FXML private TextField txtTotalPay;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;

    private ObservableList<Component> selectedComponents = FXCollections.observableArrayList();

    //metode som sjekker om checkboksene er huket av og legger til komponentene dersom de er huket av.
    private void updateCart(){
        if (checkBox1.isSelected()) {
            Component valgtKomponent = object1;
            selectedComponents.add(valgtKomponent);
            checkBox1.setSelected(false);
        }
        if (checkBox2.isSelected()) {
            selectedComponents.add(object2);
            checkBox2.setSelected(false);
        }
        if (checkBox3.isSelected()) {
            selectedComponents.add(object3);
            checkBox3.setSelected(false);
        }
        if (checkBox4.isSelected()) {
            selectedComponents.add(object4);
            checkBox4.setSelected(false);
        }
        if (checkBox5.isSelected()) {
            selectedComponents.add(object5);
            checkBox5.setSelected(false);
        }
        if (checkBox6.isSelected()) {
            selectedComponents.add(object6);
            checkBox6.setSelected(false);
        }
    }

    //metode som legger til elementer i handlekurven, dersom de er huket av. (Funker ikke helt enda)
    @FXML
    void addToCart(ActionEvent event) {
        updateCart();
        System.out.println(selectedComponents);

        cartPrice.setCellValueFactory(new PropertyValueFactory<>("cartPrice"));
        cartInfo.setCellValueFactory(new PropertyValueFactory<>("cartInfo"));
        cartName.setCellValueFactory(new PropertyValueFactory<>("cartName"));
        cartCheck.setCellValueFactory(new PropertyValueFactory<>("cartCheck"));
        tblCart.setItems(selectedComponents);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //liste over ekstra tilbehør med checkbokser.
        ObservableList<Component> komponentList = FXCollections.observableArrayList();
        komponentList.addAll(object1, object2, object3, object4, object5, object6);

        tblExtraComponent.getItems().addAll(komponentList);
        componentInfo.setCellValueFactory(new PropertyValueFactory<>("componentInfo"));
        componentName.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        componentPrice.setCellValueFactory(new PropertyValueFactory<>("componentPrice"));
        componentChosen.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        newScene.toEnduserPage(primaryStage,root);
        primaryStage.show();

        // kaller metode for å velge riktige choiceboxer når man går tilbake

        // kaller metode for å sette totalbeløpet på nytt


    }

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
    void goToPay(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        newScene.toEnduserSendOrderPage(primaryStage,root);
        primaryStage.show();

    }

}
