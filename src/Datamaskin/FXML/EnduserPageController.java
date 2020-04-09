// denne siden gjelder sluttbruker siden der man skal velge komponenter
package Datamaskin.FXML;
import Datamaskin.Component;
import Datamaskin.Product.ProductCategories;
import Datamaskin.newScene;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class EnduserPageController implements Initializable{

    @FXML private Button btnGoBack;
    @FXML private Button btnGoToPay;

    @FXML private TextField txtUserid;
    @FXML private TextField txtTotalPrice;

    @FXML private Label lblError;

    @FXML private TableView<Component> txtCart;
    @FXML private Button btnAddToCart;
    
    @FXML private TableView<Component> tableView;
    @FXML private TableColumn<Component, String> componentInfo;
    @FXML private TableColumn<Component, String> componentName;
    @FXML private TableColumn<Component, Integer> componentPrice;
    @FXML private TableColumn<Component, CheckBox> componentChecked;

    // choicebox som skal populeres med arrays lagret i ProductAdmPage
    @FXML private ChoiceBox<String> cBoxGraphicCard;
    @FXML private ChoiceBox<String> cBoxMemorycard;
    @FXML private ChoiceBox<String> cBoxHarddrive;
    @FXML private ChoiceBox<String> cBoxProcessor;
    @FXML private ChoiceBox<String> cBoxPower;
    @FXML private ChoiceBox<String> cBoxSoundcard;
    @FXML private ChoiceBox<String> cBoxOpticaldisk;
    @FXML private ChoiceBox<String> cBoxColor;

    // metode som setter verdier til choicebox
    public void setValuesToChoicebox(){
        for(int index = 0; index<ProductCategories.GraphicCard.size(); index++) {
            cBoxGraphicCard.getItems().add(ProductCategories.CategorynameToString(ProductCategories.GraphicCard, index));
        }
        for(int index = 0; index<ProductCategories.Memorycard.size(); index++) {
            cBoxMemorycard.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Memorycard, index));
        }
        for(int index = 0; index<ProductCategories.Harddrive.size(); index++) {
            cBoxHarddrive.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Harddrive, index));
        }
        for(int index = 0; index<ProductCategories.Processor.size(); index++) {
            cBoxProcessor.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Processor, index));
        }
        for(int index = 0; index<ProductCategories.Power.size(); index++) {
            cBoxPower.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Power, index));
        }
        for(int index = 0; index<ProductCategories.Soundcard.size(); index++) {
            cBoxSoundcard.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Soundcard, index));
        }
        for(int index = 0; index<ProductCategories.OpticalDisk.size(); index++) {
            cBoxOpticaldisk.getItems().add(ProductCategories.CategorynameToString(ProductCategories.OpticalDisk, index));
        }
        for(int index = 0; index<ProductCategories.Color.size(); index++) {
            cBoxColor.getItems().add(ProductCategories.CategorynameToString(ProductCategories.Color, index));
        }

    }




    // label som vi setter lik total pris på det som er valgt av essensielle komponenter
    @FXML private Label lblTotalPrice;

    //liste over valgte produkter
    private ObservableList<Component> cartList = FXCollections.observableArrayList();

    // knapp som sender bruker til neste side
    @FXML void loadPayment(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        newScene.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    // knapp som sender bruker til forrige side + advarsel om å avslutte
    @FXML void goBack(ActionEvent event) throws IOException {
        //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Vent litt...");
        alert.setContentText("Ønsker du å avslutte din bestilling og gå til hovedsiden?");
        ButtonType buttonYes = new ButtonType("Ja, det ønsker jeg");
        ButtonType buttonNo = new ButtonType("Nei");
        alert.getButtonTypes().addAll(buttonYes, buttonNo);
        Optional<ButtonType> userAnswer = alert.showAndWait();

        if (userAnswer.get() == buttonYes) {
            //legger inn metoden for å åpne tidligere side (forside) - Hannah
            Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.tilHovedside(primaryStage, root);
            primaryStage.show();
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValuesToChoicebox();
    }
}
