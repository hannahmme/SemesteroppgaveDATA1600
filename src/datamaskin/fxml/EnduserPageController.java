// denne siden gjelder sluttbruker siden der man skal velge komponenter
package datamaskin.fxml;
import datamaskin.cart.Cart;
import datamaskin.product.Product;
import datamaskin.product.ProductCategories;
import datamaskin.Page;
import datamaskin.images.ImageClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;
import static datamaskin.cart.Cart.addProduct;
import static datamaskin.cart.Cart.findIndex;

public class EnduserPageController implements Initializable {
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToPay;
    @FXML private Label lblTotalPrice;
    @FXML private Label lblError;
    @FXML private ImageView mainpageImageView;

    @FXML private Label lblInfoGraphicCard;
    @FXML private Label lblInfoMemorycard;
    @FXML private Label lblInfoHarddrive;
    @FXML private Label lblInfoProcessor;
    @FXML private Label lblInfoPower;
    @FXML private Label lblInfoSoundcard;
    @FXML private Label lblInfoOpticaldisk;
    @FXML private Label lblInfoColor;

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


    // metode som kaller på andre metoder for å legge til produkter i handlekurven
    @FXML void addToCart() {
        if (checkBoxesAreEmpty()) {
            lblError.setText("Husk å velge en komponent i alle choiceboksene!");
        } else {
            lblError.setText("");
            createCartObjectsFromGUI();
            getTotalprice();
        }
    }

    private ImageClass image = new ImageClass();
    private Image homeImage = image.createImage("./src/Datamaskin/images/mainpage.png");
    private static Cart aCart = new Cart();

    //Kastes fordi createImage-metoden kalles
    public EnduserPageController() throws FileNotFoundException {
    }

    //en generell metode hvor du sender inn HashMap som Choiseboksene toStringer og setter - Hannah
    private void setValues (HashMap<String, Product> productCategoryMap, ChoiceBox<String> cBox){
        for(int position = 0; position < productCategoryMap.size(); position++){
            cBox.getItems().add(ProductCategories.CategorynameToString(productCategoryMap, position));
        }
    }

    // meotde for å hente ut verdier fra pris-kolonnen og legge de sammen, for så å sette verdien til lbl
    private void getTotalprice() {
        double totalPrice = aCart.getTotalPrice();
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }

    // metode for å slette gamle objekter og for å lage nye objekter som kommer an på valg i choiceboksene
    private void createCartObjectsFromGUI() {
        if (Cart.Register.isEmpty()) {  // kun hvis handlekurven er tom skal det lages helt nye produkter som legges til
            createProducts();
        } else {
            updateCart();               // oppdater alltid handlekurven ut fra det som er i choicebox
        }
    }

    // sjekke om handlekurven allerede har komponenter, da må de slettes for å legge til nye komponenter som bruker vil endre til
    private void updateCart() {
        if (!Cart.Register.isEmpty()) {
            aCart.replaceElements(findIndex("Skjermkort"), addProduct(cBoxGraphicCard.getValue(), ProductCategories.GraphicCard));
            aCart.replaceElements(findIndex("Minnekort"), addProduct(cBoxMemorycard.getValue(), ProductCategories.Memorycard));
            aCart.replaceElements(findIndex("Harddisk"), addProduct(cBoxHarddrive.getValue(), ProductCategories.Harddrive));
            aCart.replaceElements(findIndex("Prosessor"), addProduct(cBoxProcessor.getValue(), ProductCategories.Processor));
            aCart.replaceElements(findIndex("Strømforsyning"), addProduct(cBoxPower.getValue(), ProductCategories.Power));
            aCart.replaceElements(findIndex("Lydkort"), addProduct(cBoxSoundcard.getValue(), ProductCategories.Soundcard));
            aCart.replaceElements(findIndex("Optisk disk"), addProduct(cBoxOpticaldisk.getValue(), ProductCategories.OpticalDisk));
            aCart.replaceElements(findIndex("Farge"), addProduct(cBoxColor.getValue(), ProductCategories.Color));
        }
    }

    // metode som oppretter produkter fra hver choicebox (hver stirng representerer navnet på produktet)
    private void createProducts() {
        aCart.addElement(addProduct(cBoxGraphicCard.getValue(), ProductCategories.GraphicCard));
        aCart.addElement(addProduct(cBoxMemorycard.getValue(), ProductCategories.Memorycard));
        aCart.addElement(addProduct(cBoxHarddrive.getValue(), ProductCategories.Harddrive));
        aCart.addElement(addProduct(cBoxProcessor.getValue(), ProductCategories.Processor));
        aCart.addElement(addProduct(cBoxPower.getValue(), ProductCategories.Power));
        aCart.addElement(addProduct(cBoxSoundcard.getValue(), ProductCategories.Soundcard));
        aCart.addElement(addProduct(cBoxOpticaldisk.getValue(), ProductCategories.OpticalDisk));
        aCart.addElement(addProduct(cBoxColor.getValue(), ProductCategories.Color));
    }

    // metoder for å sette choicebox til riktig verdi utifra det brukeren allerede har valgt
    private String setAllChosenChoiceboxes(int i) {
        String cBoxValue = tableviewCart.getColumns().get(0).getCellObservableValue(i).getValue().toString();
        return cBoxValue;
    }

    // metode som bruker metoden over til å sette verdier til hver cBox
    private void setChosenChoicebox() {
        cBoxGraphicCard.setValue(setAllChosenChoiceboxes(findIndex("Skjermkort")));
        cBoxMemorycard.setValue(setAllChosenChoiceboxes(findIndex("Minnekort")));
        cBoxHarddrive.setValue(setAllChosenChoiceboxes(findIndex("Harddisk")));
        cBoxProcessor.setValue(setAllChosenChoiceboxes(findIndex("Prosessor")));
        cBoxPower.setValue(setAllChosenChoiceboxes(findIndex("Strømforsyning")));
        cBoxSoundcard.setValue(setAllChosenChoiceboxes(findIndex("Lydkort")));
        cBoxOpticaldisk.setValue(setAllChosenChoiceboxes(findIndex("Optisk disk")));
        cBoxColor.setValue(setAllChosenChoiceboxes(findIndex("Farge")));
    }


    // metode som kjøres umiddelbart hver gang denne scenen blir laget
    @Override public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        aCart.attachTableview(tableviewCart);

        image.setImageView(mainpageImageView, homeImage);

        // setter eksempelinfo til choiceboksene todo: dette må flyttes til main? eller hentes fra riktig sted evt. hvor hentes det fra? bør hentes fra TV
        setValues(ProductCategories.GraphicCard, cBoxGraphicCard);
        setValues(ProductCategories.Memorycard, cBoxMemorycard);
        setValues(ProductCategories.Harddrive, cBoxHarddrive);
        setValues(ProductCategories.Processor, cBoxProcessor);
        setValues(ProductCategories.Power, cBoxPower);
        setValues(ProductCategories.Soundcard, cBoxSoundcard);
        setValues(ProductCategories.OpticalDisk, cBoxOpticaldisk);
        setValues(ProductCategories.Color, cBoxColor);

        // kaller metode for å velge riktige choicebokser når man går tilbake
        if (!Cart.Register.isEmpty()) {
            setChosenChoicebox();
            getTotalprice();
        }
    }

    // metode for å sjekke om alle choiceboksene er valgt
    private boolean checkBoxesAreEmpty() {
        return cBoxGraphicCard.getValue()   == null ||
                cBoxMemorycard.getValue()   == null ||
                cBoxHarddrive.getValue()    == null ||
                cBoxProcessor.getValue()    == null ||
                cBoxPower.getValue()        == null ||
                cBoxSoundcard.getValue()    == null ||
                cBoxOpticaldisk.getValue()  == null ||
                cBoxColor.getValue()        == null;
    }

    // knapp som sender bruker til neste side
    @FXML void loadPayment() throws IOException {
        ObservableList<Product> items = tableviewCart.getItems();
        if (checkBoxesAreEmpty()) {
            lblError.setText("Du har ikke valgt alle nødvendige komponenter til din datamaskin.");
        }
        if (items.isEmpty()) {
            lblError.setText("Legg til dine valgte komponenter i handlekurven for å gå videre.");
            return;
        }
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        Page.toExtraOrderEnduserPage(primaryStage, root);
    }

    // knapp som sender bruker til forrige side + advarsel om å avslutte
    @FXML void goBack() throws IOException {
        //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
        boolean goBackIsConfirmed = Page.alertConfirmed("Ønsker du å avslutte din bestilling og gå til hovedsiden?");
        if (goBackIsConfirmed) {
            Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            Page.toMainpage(primaryStage, root);
            aCart.deleteShoppingcart();
        }
    }

    //Metoder som sender bruker videre eller tilbake ved å trykke på "Enter"-knappen
    @FXML void btnAddEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            addToCart();
        }
    }

    // metode for å gå tilbake til hovedsiden
    @FXML void btnGoBackEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goBack();
            }
        }

    // metode for å gå til neste side
    @FXML void btnNextPageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            loadPayment();
        }
    }


    //todo: metode for å sette infotekst ved siden av hver CB. vet ikke hvordan den skal vite når noe velges?
    public void changedGraphicCard() {
        Product newValue = Cart.addProduct(cBoxGraphicCard.getValue(),ProductCategories.GraphicCard);
        lblInfoGraphicCard.setText(newValue.getDescription());

        // osv med de andre kategoriene
    }



}