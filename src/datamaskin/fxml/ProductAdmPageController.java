package datamaskin.fxml;

import datamaskin.filbehandling.binarysaving.FileHandler;
import datamaskin.exceptions.ConvertersWithErrorHandling;
import datamaskin.exceptions.InvalidLifetimeException;
import datamaskin.exceptions.InvalidPriceException;
//import datamaskin.filbehandling.SaveComponentsToFile;
import datamaskin.Page;
import datamaskin.product.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static datamaskin.product.ProductCategories.*;

public class ProductAdmPageController implements Initializable{
    @FXML private Button toSuperUserPage;
    @FXML private Label wrongInput;

    private final ConvertersWithErrorHandling.IntegerStringConverter intStrConverter
            = new ConvertersWithErrorHandling.IntegerStringConverter();
    private final ConvertersWithErrorHandling.DoubleFromStringConverter doubleStrConverter
            = new ConvertersWithErrorHandling.DoubleFromStringConverter();
    //private SaveComponentsToFile filesaver = new SaveComponentsToFile();

    private Stage stage;
    @FXML private TextField txtComponentname;
    @FXML private TextField txtDescription;
    @FXML private TextField txtLifetime;
    @FXML private TextField txtPrice;
    @FXML private ChoiceBox<String> cboxCategory;

    @FXML private MenuButton menuDropdown;
    @FXML private TextField txtSearch;
    @FXML private ChoiceBox<String> cboxFilter;

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
    @FXML private TableColumn<Product, Integer> lifetimeColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    @FXML private TableColumn<Product, String> categoryColumn;

    // oppretter et nytt objekt av typen Produktregister
    public static ProductRegister aRegister = new ProductRegister();

    // knappen for å legge til et nytt produkt i listen
    @FXML void addComponent() {
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
                description = txtDescription.getText();
                lifetimeString = txtLifetime.getText();
                priceString = txtPrice.getText();
                category = cboxCategory.getSelectionModel().getSelectedItem();

                if (!ProductValidator.validateName(name)){
                    throw new IllegalArgumentException("Skriv inn et gyldig komponentnavn");
                }
                if (!ProductValidator.validateDescription(description)){
                    throw new IllegalArgumentException("Skriv inn en gyldig beskrivelse");
                }
                if (!ProductValidator.validateLifetime(lifetimeString)){
                    throw new InvalidLifetimeException("Skriv inn et gyldig antall år");
                } else{
                    lifetime = Integer.parseInt(txtLifetime.getText());
                }
                if (!ProductValidator.validatePrice(priceString)){
                    throw new InvalidPriceException("Skriv inn en gyldig pris");
                } else{
                    price = Double.parseDouble(txtPrice.getText());
                }

                if(!ProductValidator.validateCategory(category)){
                    throw new IllegalArgumentException("Vennligst velg kategori");
                }

                //oppretter produktet med alle riktige attributter etter at de er sjekket for feil
                Product aProduct = new Product(name, description, lifetime, price, category);

                // metode som også legger til produktet i riktig kategori-array
                ProductCategories.setData(aProduct, category);

                // returnerer produktet
                return aProduct;

            } catch (InvalidPriceException | IllegalArgumentException | InvalidLifetimeException e) {
                wrongInput.setText(e.getMessage());
            }
        }
        return null;
    }

    private void updateProductList() {
        ProductRegister.setComponentToTV(componentTableview);
    }


    private void filter(){
        if(isEmptyOrBlank(txtSearch)) {
            updateProductList();
            return;
        }

        ObservableList<Product> searchResult = null;
        switch (cboxFilter.getValue().toLowerCase()) {
            case "name" : searchResult = aRegister.filterByName(txtSearch.getText()); break;
            case "description" : searchResult = aRegister.filterByDescription(txtSearch.getText()); break;
            case "lifetime" : try {
                searchResult = aRegister.filterByLifetime(Integer.parseInt(txtSearch.getText()));
            } catch (NumberFormatException e) {} break;
            case "price" : try {
                searchResult = aRegister.filterByPrice(Double.parseDouble(txtSearch.getText()));
            } catch (NumberFormatException e) {} break;
            case "category" : searchResult = aRegister.filterByCategory(txtSearch.getText()); break;
        }

        if(searchResult == null) {
            componentTableview.setItems(FXCollections.observableArrayList());
        } else {
            componentTableview.setItems(searchResult);
        }
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
        if(GraphicCard.contains(aProduct)){
            GraphicCard.remove(aProduct);
        } else if(Memorycard.contains(aProduct)){
            Memorycard.remove(aProduct);
        } else if(Harddrive.contains(aProduct)){
            Harddrive.remove(aProduct);
        } else if(Processor.contains(aProduct)){
            Processor.remove(aProduct);
        } else if(Power.contains(aProduct)){
            Power.remove(aProduct);
        } else if(Soundcard.contains(aProduct)){
            Soundcard.remove(aProduct);
        } else if(OpticalDisk.contains(aProduct)){
            OpticalDisk.remove(aProduct);
        } else if(Color.contains(aProduct)){
            Color.remove(aProduct);
        } else if(OtherProducts.contains(aProduct)){
            OtherProducts.remove(aProduct);
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        lifetimeColumn.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

        ProductRegister.setComponentToTV(componentTableview);

        //Choiceboxen for filtrering skal stå på Navn som default
        //cboxFilter.setValue("Navn");

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
        FileHandler.openFile(stage, aRegister);
        txtSearch.setText("");
        ProductRegister.setComponentToTV(componentTableview);
    }

    //lagre til binær fil - den lagrer til fil, men jeg kan lese den. Skal vel komme kun tall?
    @FXML void saveToFile(ActionEvent event) {
        FileHandler.saveFile(stage, aRegister);



        // todo: kode som lå her før
        /*FileSaver saver = new SaveComponentsToFile();
        Path path = Paths.get("productReg.jobj");
        if(saver != null) {
            try {
                saver.saveToJobj(ProductRegister.Register, path);
                System.out.println("Registeret ble lagret!");
            } catch (IOException e) {
                System.out.println("Lagring til fil feilet. Årsak: " + e.getMessage());
            }
        }*/
    }


    //Metoder slik at innholdet i Produkt-tableViewet på adminsiden endres direkte i tblViewet
    @FXML void txtProductNameEdited(TableColumn.CellEditEvent<Product, String> event){
        event.getRowValue().setName(event.getNewValue());
    }
    @FXML void txtProductDescriptionEdited(TableColumn.CellEditEvent<Product, String> event){
        event.getRowValue().setDescription(event.getNewValue());
    }
    @FXML void txtProductLifetimeEdited(TableColumn.CellEditEvent<Product, Integer> event) {
        if (intStrConverter.getSuccessfulIntValue()) {
            event.getRowValue().setLifetime(event.getNewValue());
        }
        componentTableview.refresh();
    }
    @FXML void txtProductPriceEdited(TableColumn.CellEditEvent<Product, Double> event) {
        if (doubleStrConverter.getSuccessfulDoubleValue()) {
            event.getRowValue().setPrice(event.getNewValue());
        }
        componentTableview.refresh();
    }
    @FXML void txtProductCategoryEdited(TableColumn.CellEditEvent<Product, String> event){
        event.getRowValue().setCategory(event.getNewValue());
    }


    @FXML void filterTV(ActionEvent actionEvent) {
        filter();
    }
}
