// denne siden gjelder sluttbruker siden der man skal velge komponenter
package Datamaskin.FXML;
import Datamaskin.Cart.Cart;
import Datamaskin.Cart.EssentialProductsCart;
import Datamaskin.Component;
import Datamaskin.Exceptions.InvalidLifetimeException;
import Datamaskin.Exceptions.InvalidPriceException;
import Datamaskin.Product.Product;
import Datamaskin.Product.ProductCategories;
import Datamaskin.Product.ProductRegister;
import Datamaskin.newScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class EnduserPageController implements Initializable{

    @FXML private Button btnGoBack;
    @FXML private Button btnGoToPay;
    @FXML private Button btnAddToCart;

    @FXML private Label lblError;

    // label som vi setter lik total pris på det som er valgt av essensielle komponenter
    @FXML private Label lblTotalPrice;

    @FXML private TableView<Product> tableviewCart;
    @FXML private TableColumn<String, Product> nameColumn;
    @FXML private TableColumn<String, Product> descriptionColumn;
    @FXML private TableColumn<Integer, Product> lifetimeColumn;
    @FXML private TableColumn<Double, Product> priceColumn;

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

    // under her er kode for å populere handlekurven
    private static Cart aCart = new Cart();


    @FXML void addToCart(ActionEvent event) {

        Product aProduct = createCartObjectFromGUI();
        if(aProduct != null) {
            aCart.addElement(aProduct);
            // metode for å disable legg til knappen så man ikke får lagt til mer,
            // men man må få endret på tableviewet på en eller annen måte
        }

    }

    private Product createCartObjectFromGUI() {
        Product graphicCard;

        String graphiccard = cBoxGraphicCard.getValue();
        // sjekke om choicebox er tomt med en if-setning. hvordan?

        // sjekke hvilken verdi hver choicebox har, og bruke denne til å hente pris osv til tableviewet

        
        // løkke og if-setning som finner riktig objekt i array og legger det til i handlekurven
        for(int i = 0; i<ProductCategories.GraphicCard.size(); i++){
            if(graphiccard.equals(ProductCategories.GraphicCard.keySet().toArray()[i].toString())){
                graphicCard = ProductCategories.GraphicCard.get(graphiccard);
                System.out.println(graphicCard);
            }
        }



        return null;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        aCart.addComponent(tableviewCart);

        setValuesToChoicebox();
    }
}
