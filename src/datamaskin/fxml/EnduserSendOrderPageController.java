package datamaskin.fxml;

import datamaskin.cart.Cart;
import datamaskin.users.CustomerValidator;
import datamaskin.exceptions.InvalidEmailException;
import datamaskin.filbehandling.FileSaverTxt;
import datamaskin.filbehandling.OrderFormatter;
import datamaskin.images.ImageClass;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.orders.Order;
import datamaskin.product.Product;
import datamaskin.Page;
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
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import static datamaskin.users.CustomerValidator.getCustomerList;

public class EnduserSendOrderPageController implements Initializable {
    @FXML private Button btnSendOrder;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private TextField txtEpost;
    @FXML private TextField txtDiscount;
    @FXML private Label lblOrderSent;
    @FXML private Label lblTotalPrice;
    @FXML private ImageView mainpageImageView;
    @FXML private PasswordField txtPassword;
    @FXML private TableView<FinalOrderOverview> finalOrderRegister;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;

    private FileSaverTxt filesaver = new FileSaverTxt();
    private ImageClass image = new ImageClass();
    private Image homeImage = image.createImage("./src/Datamaskin/images/mainpage.png");
    private Cart shoppingcart = new Cart();

    //denne kastes fordi image.createImage kalles
    public EnduserSendOrderPageController() throws FileNotFoundException {
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        shoppingcart.attachTableview(finalOrderRegister);
        image.setImageView(mainpageImageView, homeImage);

        shoppingcart.getTotalPrice(lblTotalPrice);
    }

    @FXML void sendOrder() throws IOException {
        String orderID = Order.generateOrderID();
        FinalOrderOverview aFinalOrderOverview = createOrderObjectFromGUI(orderID);

        if(aFinalOrderOverview != null) {
            txtEpost.setText("");
            txtPassword.setText("");
            txtDiscount.setText("");

            try{
            //kode som lagrer orderen til forhåndsdefinert filsti med generert ordreID.
            Path sentOrderPath = Paths.get("./src/Datamaskin/sentOrdersPath/"+orderID+".csv");
            String formattedList = OrderFormatter.formatListOfProductToString(Cart.Cart);
            filesaver.saveToFile(formattedList, sentOrderPath);

            //kode som lagrer orderen til forhåndsdefinert filsti (alle ordre samlet i csv.fil)
            Path allOrderPath = Paths.get("./src/Datamaskin/sentOrdersPath/allOrders.csv");
            String formattedFinalOrder = OrderFormatter.formatFinalOrderOverViewToString(aFinalOrderOverview);
            filesaver.appendToFile("\n", allOrderPath);
            filesaver.appendToFile(formattedFinalOrder, allOrderPath);

            //sletter handlekurven *etter* å ha lagret til fil
    // todo: kanskje lage exception i tilfelle ikke klarer å lese til filstien (så ikke handlekurven slettes før det faktisk er blitt lagret) - hannah
            shoppingcart.deleteShoppingcart();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    // metode for å generere en ordre
    private FinalOrderOverview createOrderObjectFromGUI(String orderID) throws IOException {
        String email;
        String password;
        double totalPrice;

        try {
            email = txtEpost.getText();
            password = txtPassword.getText();

            if(!CustomerValidator.validateEmail(email)){
                throw new InvalidEmailException("Skriv inn gyldig e-postadresse");
            } else if(!CustomerValidator.validateCredentials(email, password, Objects.requireNonNull(getCustomerList()))){
                throw new InvalidEmailException("Du har skrevet inn ugyldige innloggingsdetaljer!");
            } else{
                totalPrice = shoppingcart.getTotalPrice(lblTotalPrice);
                String date = String.valueOf(LocalDate.now());

                //lager en ordreID for bestillingen og viser den til bruker
                lblOrderSent.setText("Takk for din ordre.\nOrdrenummer: " + orderID);

                // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
                btnSendOrder.setDisable(true);
                btnGoBack.setDisable(true);

                //Oppretter ferdig ordre-objekt som returneres
                return new FinalOrderOverview(orderID, email, date, totalPrice);
            }
        } catch (InvalidEmailException e){
            lblOrderSent.setText(e.getMessage());
        }
        return null;
    }

    // metode for å gå tilbake til hovedside
    @FXML void goToMainpage() throws IOException {
        if(btnSendOrder.isDisabled()){
            Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            Page.toMainpage(primaryStage, root);
        } else {
            //Man får en advarsel om at hvis man går til hovedsiden, vil bestillingen avsluttes - Hannah
            boolean goBackIsConfirmed = Page.alertConfirmed("Ønsker du å avslutte din bestilling og gå til hovedsiden?");
            if(goBackIsConfirmed){
                Stage primaryStage = (Stage) btnGoToMainpage.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
                Page.toMainpage(primaryStage, root);
                shoppingcart.deleteShoppingcart();
            }
        }
    }

    // metode for å gå tilbake til forrige side
    @FXML void goBack() throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ExtraOrderEnduserPage.fxml"));
        Page.toExtraOrderEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    // metode som åpner siden der man kan lage en ny bruker
    @FXML void newUser() throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
        Page.tonewUserPage(newStage, root);
        newStage.show();
    }

    //Enter-funksjon på buttons
    @FXML void btnNewUserEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            newUser();
        }
    }
    @FXML void btnSendOrderEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            sendOrder();
        }
    }
    @FXML void btnMainPageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goToMainpage();
        }
    }
    @FXML void btnGoBackEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            goBack();
        }
    }
}