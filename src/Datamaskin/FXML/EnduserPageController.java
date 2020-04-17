// denne siden gjelder sluttbruker siden der man skal velge komponenter
package Datamaskin.FXML;
import Datamaskin.Cart.Cart;
import Datamaskin.Product.Product;
import Datamaskin.Product.ProductCategories;
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
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class EnduserPageController implements Initializable{

    @FXML private Button btnGoBack;
    @FXML private Button btnGoToPay;

    // label som vi setter lik total pris på det som er valgt av essensielle komponenter
    @FXML private Label lblTotalPrice;
    @FXML private Label lblError;

    @FXML private TableView<Product> tableviewCart;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> descriptionColumn;
    @FXML private TableColumn<Product, Integer> lifetimeColumn;
    @FXML private TableColumn<Product, Double> priceColumn;

    // choicebox som skal populeres med arrays lagret i ProductAdmPage
    @FXML private ChoiceBox<String> cBoxGraphicCard;
    @FXML private ChoiceBox<String> cBoxMemorycard;
    @FXML private ChoiceBox<String> cBoxHarddrive;
    @FXML private ChoiceBox<String> cBoxProcessor;
    @FXML private ChoiceBox<String> cBoxPower;
    @FXML private ChoiceBox<String> cBoxSoundcard;
    @FXML private ChoiceBox<String> cBoxOpticaldisk;
    @FXML private ChoiceBox<String> cBoxColor;

    private static Cart aCart = new Cart();

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
            newScene.toMainpage(primaryStage, root);
            primaryStage.show();
            //sletter handlekurven når bruker ønsker å avrbyte handlingen
            aCart.deleteShoppingcart();

        }
    }

    @FXML void addToCart(ActionEvent event) {
        if(checkIfCboxValuesAreEmpty()){
            lblError.setText("Husk å velge en komponent i alle choiceboksene!");
        } else {
            createCartObjectsFromGUI();
            getTotalprice();
        }
    }

    // meotde for å hente ut verdier fra pris-kolonnen og legge de sammen, for så å sette verdien til lbl
    public void getTotalprice(){
        double totalPrice = 0;

        for(int i = 0; i<tableviewCart.getItems().size(); i++){
            double a = Double.parseDouble(tableviewCart.getColumns().get(3).getCellObservableValue(i).getValue().toString());
            totalPrice += a;
        }
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }

    // metode for å slette gamle objekter og for å lage nye objekter som kommer an på valg i choiceboksene
    private void createCartObjectsFromGUI() {
        updateCart();                   // oppdater alltid handlekurven ut fra det som er i choicebox
        if (Cart.Register.isEmpty()) {  // kun hvis handlekurven er tom skal det lages helt nye produkter som legges til
            createProducts();
        }
    }

    // sjekke om handlekurven allerede har komponenter, da må de slettes for å legge til nye komponenter som bruker vil endre til
    public void updateCart(){
        if (!Cart.Register.isEmpty()) {
            aCart.replaceElements(0, addProduct(cBoxGraphicCard.getValue(), ProductCategories.GraphicCard));
            aCart.replaceElements(1, addProduct(cBoxMemorycard.getValue(), ProductCategories.Memorycard));
            aCart.replaceElements(2, addProduct(cBoxHarddrive.getValue(), ProductCategories.Harddrive));
            aCart.replaceElements(3, addProduct(cBoxProcessor.getValue(), ProductCategories.Processor));
            aCart.replaceElements(4, addProduct(cBoxPower.getValue(), ProductCategories.Power));
            aCart.replaceElements(5, addProduct(cBoxSoundcard.getValue(), ProductCategories.Soundcard));
            aCart.replaceElements(6, addProduct(cBoxOpticaldisk.getValue(), ProductCategories.OpticalDisk));
            aCart.replaceElements(7, addProduct(cBoxColor.getValue(), ProductCategories.Color));
        }
    }

    // metode som oppretter produkter fra hver choicebox (hver stirng representerer navnet på produktet)
    public void createProducts(){
        String graphicCard = cBoxGraphicCard.getValue();
        String memoryCard = cBoxMemorycard.getValue();
        String harddrive = cBoxHarddrive.getValue();
        String processor = cBoxProcessor.getValue();
        String power = cBoxPower.getValue();
        String soundcard = cBoxSoundcard.getValue();
        String opticalDisk = cBoxOpticaldisk.getValue();
        String color = cBoxColor.getValue();

        aCart.addElement(addProduct(graphicCard, ProductCategories.GraphicCard));
        aCart.addElement(addProduct(memoryCard, ProductCategories.Memorycard));
        aCart.addElement(addProduct(harddrive, ProductCategories.Harddrive));
        aCart.addElement(addProduct(processor, ProductCategories.Processor));
        aCart.addElement(addProduct(power, ProductCategories.Power));
        aCart.addElement(addProduct(soundcard, ProductCategories.Soundcard));
        aCart.addElement(addProduct(opticalDisk, ProductCategories.OpticalDisk));
        aCart.addElement(addProduct(color, ProductCategories.Color));
    }

    // metode for å hente frem riktig produkt fra listen(hashmappen) og legge til produktet i handlekurven
    public Product addProduct(String productname, HashMap<String, Product> categoryList){
        Product aProduct = null;
        for(int i = 0; i<categoryList.size(); i++){
            if(productname.equals(categoryList.keySet().toArray()[i].toString())){
                aProduct = categoryList.get(productname);
            }
        }
        return aProduct;
    }

    // metode som kjøres umiddelbart hver gang denne scenen blir laget
    @Override public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        aCart.attachTableview(tableviewCart);

        // setter eksempelinfo til choiceboksene
        setValuesToChoicebox();

        // kaller metode for å velge riktige choicebokser når man går tilbake
        if(!Cart.Register.isEmpty()) {
            setChosenChoicebox();
            getTotalprice();
        }
    }

    // metoder for å sette choicebox til riktig verdi utifra det brukeren allerede har valgt
    public String setAllChosenChoiceboxes(int i){
        String cBoxValue = tableviewCart.getColumns().get(0).getCellObservableValue(i).getValue().toString();
        return cBoxValue;
    }

    // metode som bruker metoden over til å sette verdier til hver cBox
    public void setChosenChoicebox(){ ;
        cBoxGraphicCard.setValue(setAllChosenChoiceboxes(0));
        cBoxMemorycard.setValue(setAllChosenChoiceboxes(1));
        cBoxHarddrive.setValue(setAllChosenChoiceboxes(2));
        cBoxProcessor.setValue(setAllChosenChoiceboxes(3));
        cBoxPower.setValue(setAllChosenChoiceboxes(4));
        cBoxSoundcard.setValue(setAllChosenChoiceboxes(5));
        cBoxOpticaldisk.setValue(setAllChosenChoiceboxes(6));
        cBoxColor.setValue(setAllChosenChoiceboxes(7));
    }

    // metode for å sjekke om alle choiceboksene er valgt
    public boolean checkIfCboxValuesAreEmpty(){
        if(cBoxGraphicCard.getValue().equals("") || cBoxMemorycard.getValue().equals("") || cBoxHarddrive.getValue().equals("") ||
        cBoxProcessor.getValue().equals("") || cBoxPower.getValue().equals("") || cBoxSoundcard.getValue().equals("") ||
        cBoxOpticaldisk.getValue().equals("") || cBoxColor.getValue().equals("")){
            return true;
        }
        return false;
    }
}