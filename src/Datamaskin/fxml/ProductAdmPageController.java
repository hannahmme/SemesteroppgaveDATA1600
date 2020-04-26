package Datamaskin.fxml;

import Datamaskin.exceptions.InvalidLifetimeException;
import Datamaskin.exceptions.InvalidPriceException;
import Datamaskin.filbehandling.FileSaver;
import Datamaskin.filbehandling.SaveComponentsToFile;
import Datamaskin.Page;
import Datamaskin.product.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ProductAdmPageController implements Initializable{

    @FXML private Button toSuperUserPage;
    @FXML private Text wrongInput;
    private SaveComponentsToFile filesaver = new SaveComponentsToFile();

    // inputfields for å lage et produkt/ komponent
    @FXML private TextField txtComponentname;
    @FXML private TextField txtDescription;
    @FXML private TextField txtLifetime;
    @FXML private TextField txtPrice;
    @FXML private ChoiceBox<String> cboxCategory;

    @FXML private MenuButton menuDropdown;
    @FXML private MenuItem saveToFile;
    @FXML private MenuItem openFromFile;
    @FXML private Label txtSearch;
    @FXML private Button btnSearch;


    // metode for å lage kategoriene i cboksene, så admin må velge en av de
    private void setData(){
        cboxCategory.getItems().addAll("Skjermkort", "Minnekort",
                "Harddisk", "Prosessor", "Strømforsyning", "Lydkort",
                "Optisk disk" , "Farge", "Andre produkter");
    }

    // konfigurerer tabellen
    @FXML private TableView<Product> componentTableview;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> descriptionColumn;
    @FXML private TableColumn<Product, String> lifetimeColumn;
    @FXML private TableColumn<Product, Integer> priceColumn;
    @FXML private TableColumn<Product, String> categoryColumn;

    // oppretter et nytt objekt av typen Produktregister
    public static ProductRegister aRegister = new ProductRegister();

    // knappen for å legge til et nytt produkt i listen
    @FXML void addComponent(ActionEvent event) {
        Product aProduct = createProductObjectFromGUI();
        if(aProduct != null) {
            aRegister.addElement(aProduct);
            emptyTextfield();
        }
    }

    // nullstiller tekstfelt når det blir lagt til et nytt produkt
    private void emptyTextfield() {
        txtComponentname.setText("");
        txtDescription.setText("");
        txtLifetime.setText("");
        txtPrice.setText("");
        wrongInput.setText("");
    }

    //metode som sjekker om tekstfeltene på adminsiden er tomme eller med white-spaces - Hannah
    private boolean isEmptyOrBlank(TextField textfield) {
        return textfield.getText().isEmpty() || textfield.getText().trim().isEmpty();
    }

    // sjekker inputfields for feil og legger til i array
    private Product createProductObjectFromGUI() {
        String name;
        String description;
        String lifetimeString;
        int lifetime;
        String priceString;
        double price;
        String category;

        if (    isEmptyOrBlank(txtComponentname) ||
                isEmptyOrBlank(txtDescription)   ||
                isEmptyOrBlank(txtLifetime)      ||
                isEmptyOrBlank(txtPrice)         ||
                cboxCategory.getSelectionModel().getSelectedItem() == null) {
            wrongInput.setText("Fyll ut alle felter over.");

        } else {
            try {
                name = txtComponentname.getText();
                if(!ProductValidator.validateName(name)){
                    throw new IllegalArgumentException("Skriv inn et gyldig komponentnavn");
                }

                description = txtDescription.getText();
                if(!ProductValidator.validateDescription(description)){
                    throw new IllegalArgumentException("Skriv inn en gyldig beskrivelse");
                }

                lifetimeString = txtLifetime.getText();
                if(!ProductValidator.validateLifetime(lifetimeString)){
                    throw new InvalidLifetimeException("Skriv inn et gyldig antall år");
                }
                else{
                    lifetime = Integer.parseInt(txtLifetime.getText());
                }

                priceString = txtPrice.getText();
                if(!ProductValidator.validatePrice(priceString)){
                    throw new InvalidPriceException("Skriv inn en gyldig pris");
                }
                else{
                    price = Double.parseDouble(txtPrice.getText());
                }

                category = cboxCategory.getSelectionModel().getSelectedItem();
                if(!ProductValidator.validateCategory(category)){
                    throw new IllegalArgumentException("Vennligst velg kategori");
                }

                //oppretter produktet med alle riktige attributter etter at de er sjekket for feil
                Product aProduct = new Product(name, description, lifetime, price, category);

                // metode som også legger til produktet i riktig kategori-array
                ProductCategories.setData(aProduct, category, name);

                // returnerer produktet
                return aProduct;


            } catch (InvalidPriceException | IllegalArgumentException | InvalidLifetimeException e) {
                wrongInput.setText(e.getMessage());
            }
        }
        return null;
    }

    // knapp for å slette komponent fra produktlista
    @FXML void btnDeleteComponentEnter(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            deleteComponent();
        }
    }
    @FXML void deleteComponent() throws IOException {
        Product deleteItem = componentTableview.getSelectionModel().getSelectedItem();
        boolean deleteConfirmed = Page.alertConfirmed("Ønsker du å slette "+deleteItem.getName() + " fra listen?");
        if(deleteConfirmed) {
            ProductRegister.deleteElement(deleteItem);

            // her slettes elementet også fra arrayet
            deleteFromRegister(deleteItem);
        }
    }

    // elementet som slettes i TV slettes fra riktig array/ hashmap så det ikke kommer opp i choiceboksene hos sluttbruker
    private void deleteFromRegister(Product aProduct){
        if(ProductCategories.GraphicCard.containsValue(aProduct)){
            ProductCategories.GraphicCard.remove(aProduct.getName());
        } else if(ProductCategories.Memorycard.containsValue(aProduct)){
            ProductCategories.Memorycard.remove(aProduct.getName());
        } else if(ProductCategories.Harddrive.containsValue(aProduct)){
            ProductCategories.Harddrive.remove(aProduct.getName());
        } else if(ProductCategories.Processor.containsValue(aProduct)){
            ProductCategories.Processor.remove(aProduct.getName());
        } else if(ProductCategories.Power.containsValue(aProduct)){
            ProductCategories.Power.remove(aProduct.getName());
        } else if(ProductCategories.Soundcard.containsValue(aProduct)){
            ProductCategories.Soundcard.remove(aProduct.getName());
        } else if(ProductCategories.OpticalDisk.containsValue(aProduct)){
            ProductCategories.OpticalDisk.remove(aProduct.getName());
        } else if(ProductCategories.Color.containsValue(aProduct)){
            ProductCategories.Color.remove(aProduct.getName());
        } else if(ProductCategories.otherProducts.contains(aProduct)){
            ProductCategories.otherProducts.remove(aProduct);
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

        aRegister.setComponentToTV(componentTableview);

        // for å ha kategoriene i nedtrekkslista fra før av
        setData();
    }

    // metode for å legge til produktet ved å trykke enter
    @FXML void btnAddProdEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Product aProduct = createProductObjectFromGUI();
            if(aProduct != null) {
                aRegister.addElement(aProduct);
                emptyTextfield();
            }
        }
    }

    // kode for å komme tilbake til hovedmenyen for superbruker
    @FXML void toSuperUserPage() throws IOException {
        if(ProductRegister.allCategoriesArePresent()) {
            Stage primaryStage = (Stage) toSuperUserPage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
            Page.toSuperuserpage(primaryStage, root);
        }
        else {
            Page.simpleAlertInformation("Du kan ikke forlate siden enda! Produkter innenfor en av kategoriene mangler," +
                    " og sluttbruker kan da ikke fullføre handelen sin!");
        }
    }

    @FXML void btnGoBackEnter(KeyEvent event) throws IOException {
        if(ProductRegister.allCategoriesArePresent()) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                toSuperUserPage();
            }
        } else {
            Page.simpleAlertInformation("Du kan ikke forlate siden enda! Produkter innenfor en av kategoriene mangler," +
                " og sluttbruker kan da ikke fullføre handelen sin!");
        }
    }

    @FXML void btnMenuEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
                menuDropdown.show();
                menuDropdown.fire();
        }
    }


    // litt enkel filbehandling her, lagre til binære filer? og lage for ordre også
    @FXML void openFromFile(ActionEvent event) {

    }

    //lagre til binær fil - den lagrer til fil, men jeg kan lese den. Skal vel komme kun tall?
    @FXML void saveToFile(ActionEvent event) {
        FileSaver saver = new SaveComponentsToFile();
        Path path = Paths.get("productReg.jobj");
        if(saver != null) {
            try {
                saver.saveToJobj(ProductRegister.Register, path);
                System.out.println("Registeret ble lagret!");
            } catch (IOException e) {
                System.out.println("Lagring til fil feilet. Årsak: " + e.getMessage());
            }
        }
    }
}
