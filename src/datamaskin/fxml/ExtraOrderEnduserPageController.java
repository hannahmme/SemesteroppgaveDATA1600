package datamaskin.fxml;

import datamaskin.cart.Cart;
import datamaskin.product.Product;
import datamaskin.Page;
import datamaskin.product.ProductCategories;
import datamaskin.images.ImageClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ExtraOrderEnduserPageController implements Initializable {

    //Handlekurv på høyre side
    @FXML private TableView<Product> tableviewCart;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> descriptionColumn;
    @FXML private TableColumn<Product, Integer> lifetimeColumn;
    @FXML private TableColumn<Product, Double> priceColumn;

    //Tableview på venstre side med ekstra komponenter
    @FXML private TableView<Product> tblExtraProduct;
    @FXML private TableColumn<Product, String> extraProductName;
    @FXML private TableColumn<Product, String> extraProductInfo;
    @FXML private TableColumn<Product, Double> extraProductPrice;
    @FXML private TableColumn<Product, Integer> extraProductLifetime;

    @FXML private Button btnGoToPay;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private Label lblTotalPrice;
    @FXML private ImageView imgImageView;
    @FXML private Text txtWarning;
    @FXML private ImageView mainpageImageView;

    private ImageClass image = new ImageClass();
    private Image homeImage = image.createImage("./src/Datamaskin/images/mainpage.png");
    private Page scene = new Page();
    private Cart shoppingCart = new Cart();

    public ExtraOrderEnduserPageController() throws FileNotFoundException {
    }

    //metode som legger til elementer i handlekurven, dersom de er huket av
    @FXML void addToCart() throws NullPointerException {
        try {
            Product extraProduct = tblExtraProduct.getSelectionModel().getSelectedItem();
            if (extraProduct == null) {
                throw new NullPointerException("Velg et produkt for å legge det til i handlekurven.");
            } else {
                shoppingCart.addElement(extraProduct);
                updateTotalPriceLabel();
                txtWarning.setText("");
            }

        } catch (NullPointerException nullpointer){
            txtWarning.setText(nullpointer.getMessage());
        }
    }

    // Metode som oppdaterer label med totalsum
    private void updateTotalPriceLabel() {
        double totalPrice = shoppingCart.getTotalPrice();
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }

    @FXML void deleteFromCart(ActionEvent event) throws IOException {
        Product chosenProduct = tableviewCart.getSelectionModel().getSelectedItem();
        if(chosenProduct!=null) {
            if (chosenProduct.getCategory().equals("Andre produkter")) {
                shoppingCart.deleteOneProductFromCart(chosenProduct);
            } else {
                boolean goBackIsConfirmed = Page.alertInformation("Du kan ikke slette essensielle komponenter, gå tilbake til forrige side for å endre disse.");
                if (goBackIsConfirmed){
                    Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
                    Page.toEnduserPage(primaryStage, root);
                    primaryStage.show();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tableview på venstre side med ekstra tilbehør
        tblExtraProduct.getItems().addAll(ProductCategories.otherProducts);
        tblExtraProduct.setItems(ProductCategories.otherProducts);

        extraProductName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        extraProductInfo.setCellValueFactory(new PropertyValueFactory<>("Description"));
        extraProductLifetime.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        extraProductPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Handlekurven på høyre side lastes inn når siden lastes inn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        lifetimeColumn.setCellValueFactory(new PropertyValueFactory<>("Lifetime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Kobler handlekurven med tableviewet.
        shoppingCart.attachTableview(tableviewCart);

        //Setter riktig totaltpris ved innlasting av siden
        updateTotalPriceLabel();

        image.setImageView(mainpageImageView, homeImage);
    }

    //Metode som gjør at bilde av produktet som velges vises i produktinfo-imageviewet
    @FXML void selectedItemEvent(MouseEvent event) throws FileNotFoundException {
        Product selectedProduct = tblExtraProduct.getSelectionModel().getSelectedItem();

        // Hvis raden det klikkes på er tom - ikke gjør noe
        if (selectedProduct == null) return;

        // Forsøker å hente ut bilde og vise det
        try {
            String productImage = selectedProduct.getImageUri();
            FileInputStream imageStream = new FileInputStream(productImage);
            Image image = new Image(imageStream);
            imgImageView.setImage(image);
        } catch (FileNotFoundException e) {
            System.err.println("Noe gikk galt ved innlasting av produktbilde. " + e.getMessage());
        }
    }

    // går tilbake til forrige side for å se på valgte komponenter
    @FXML void goBack() throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        Page.toEnduserPage(primaryStage, root);
        primaryStage.show();

    }

    // går tilbake til hovedsiden hvis yes på alert + sletter handlekurven
    @FXML void goToMainpage() throws IOException {
        //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
        boolean goBackIsConfirmed = Page.alertConfirmed("Ønsker du å avslutte din bestilling og gå til hovedsiden?");
        if (goBackIsConfirmed) {
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            Page.toMainpage(primaryStage, root);
            shoppingCart.deleteShoppingcart();
        }
    }

    // går videre til betalingssiden
    @FXML void goToPay() throws IOException {
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        Page.toEnduserSendOrderPage(primaryStage, root);
        primaryStage.show();
    }

    //Metoder som trigges ved "Enter"
    @FXML void btnAddEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            addToCart();
        }
    }
    @FXML void btnGoBackEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goBack();
        }
    }
    @FXML void btnGoPayEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goToPay();
        }
    }
    @FXML void btnToMainpageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goToMainpage();
            }
        }
}

