package Datamaskin.FXML;

import Datamaskin.Cart.Cart;
import Datamaskin.Product.Product;
import Datamaskin.Product.ProductCategories;
import Datamaskin.Page;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.Optional;
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

    private Page scene = new Page();
    private Cart shoppingCart = new Cart();

    //metode som legger til elementer i handlekurven, dersom de er huket av. (Funker ikke helt enda)
    @FXML void addToCart(ActionEvent event) {
        Product extraProduct = tblExtraProduct.getSelectionModel().getSelectedItem();
        shoppingCart.addElement(extraProduct);
        updateTotalPriceLabel();
    }

    // Metode som oppdaterer label med totalsum
    private void updateTotalPriceLabel() {
        double totalPrice = shoppingCart.getTotalPrice();
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tableview på venstre side med ekstra tilbehør
        tblExtraProduct.getItems().addAll(ProductCategories.otherProducts);
        tblExtraProduct.setItems(ProductCategories.otherProducts);
        extraProductName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        extraProductInfo.setCellValueFactory(new PropertyValueFactory<>("Description"));
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
    }


    //Metode som gjør at bilde av produktet som velges vises i produktinfo-imageviewet
    @FXML
    void selectedItemEvent(MouseEvent event) throws FileNotFoundException {
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
    @FXML void goBack(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        Page.toEnduserPage(primaryStage, root);
        primaryStage.show();

    }

    // går tilbake til hovedsiden hvis yes på alert + sletter handlekurven
    @FXML void goToMainpage(ActionEvent event) throws IOException {
        //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
        boolean goBackIsConfirmed = scene.comfirmNavigationToMainpage();
        if (goBackIsConfirmed) {
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            Page.toMainpage(primaryStage, root);
            shoppingCart.deleteShoppingcart();
        }
    }

    // går videre til betalingssiden
    @FXML void goToPay(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        Page.toEnduserSendOrderPage(primaryStage, root);
        primaryStage.show();
    }

    @FXML
    void btnAddEnter(KeyEvent event) {

    }

    @FXML void btnGoBackEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
            Page.toExtraOrderEnduserPage(primaryStage, root);
            primaryStage.show();
        }
    }

    @FXML void btnGoPayEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
            Page.toEnduserSendOrderPage(primaryStage, root);
            primaryStage.show();
        }
    }

    @FXML
    void btnToMainpageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            boolean goBackIsConfirmed = scene.comfirmNavigationToMainpage();
            if (goBackIsConfirmed) {
                Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
                Page.toMainpage(primaryStage, root);
                shoppingCart.deleteShoppingcart();
                primaryStage.show();
            }
        }
    }
}
