package Datamaskin.FXML;

import Datamaskin.Cart.Cart;
import Datamaskin.Product.Product;
import Datamaskin.Product.ProductCategories;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


    private Cart shoppingCart = new Cart();

    //metode som legger til elementer i handlekurven, dersom de er huket av. (Funker ikke helt enda)
    @FXML
    void addToCart(ActionEvent event) {
        Product extraProduct = tblExtraProduct.getSelectionModel().getSelectedItem();
        shoppingCart.addElement(extraProduct);
        getTotalprice();
    }

    // meotde for å hente ut verdier fra pris-kolonnen og legge de sammen, for så å sette verdien til lbl
    //Denne er kopiert fra EnduserPageController enn så lenge. 
    public void getTotalprice(){
        double totalPrice = 0;

        for(int i = 0; i<tableviewCart.getItems().size(); i++){
            double a = Double.parseDouble(tableviewCart.getColumns().get(3).getCellObservableValue(i).getValue().toString());
            totalPrice += a;
        }
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }


    @Override public void initialize(URL location, ResourceBundle resources) {
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
        shoppingCart.addComponent(tableviewCart);

        //Setter riktig totaltpris ved innlasting av siden
        updateTotalPriceLabel();
    }


    //Metode som gjør at bilde av produktet som velges vises i produktinfo-imageviewet
    @FXML
    void selectedItemEvent (MouseEvent event) {
        Product selectedProduct  = tblExtraProduct.getSelectionModel().getSelectedItem();

        // Hvis raden det klikkes på er tom - ikke gjør noe
        if(selectedProduct == null) return;

        String productImage = selectedProduct.getImageUri();
        System.out.println(productImage);

        // Forsøker å hente ut bilde og vise det
        try {
            File directory = new File("./");
            System.out.println(directory.getAbsolutePath());



            //FileInputStream imageStream = new FileInputStream("C:\\Git\\Semesteroppgave DATA1600\\src\\Datamaskin\\images\\mus1.jpg");
            FileInputStream imageStream = new FileInputStream("\"./src/Datamaskin/images/mus1.jpg\"");
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
        newScene.toEnduserPage(primaryStage,root);
        primaryStage.show();

    }


    // går tilbake til hovedsiden + alert
    @FXML void goToMainpage(ActionEvent event) throws IOException {
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
            newScene.toMainpage(primaryStage, root);
            primaryStage.show();
        }
    }


    // går videre til betalingssiden
    @FXML void goToPay(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) btnGoToPay.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserSendOrderPage.fxml"));
        newScene.toEnduserSendOrderPage(primaryStage,root);
        primaryStage.show();
    }

    // metode for å legge til essentsielle komponenter fra forrige sider i arrayet
    public void addEssentialComp(){


    }


    // metode som setter den totale prisen basert på komponentene i arrayet
    public void updateTotalPriceLabel(){
        double totalPrice = 0;

        for(int i = 0; i< tableviewCart.getItems().size(); i++){
            String priceColumn = tableviewCart.getColumns().get(3).getCellObservableValue(i).getValue().toString();
            if(priceColumn != null) {
                double price = Double.parseDouble(priceColumn);
                totalPrice += price;
            }
        }
        lblTotalPrice.setText(String.valueOf(totalPrice));

    }





}
