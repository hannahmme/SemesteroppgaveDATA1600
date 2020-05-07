package datamaskin.fxml;

import datamaskin.cart.Cart;
import datamaskin.users.CustomerValidator;
import datamaskin.exceptions.InvalidEmailException;
import datamaskin.filbehandling.FileSaverTxt;
import datamaskin.filbehandling.OrderFormatter;
import datamaskin.images.ImageClass;
import datamaskin.orders.FinalOrderOverview;
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
import java.util.ResourceBundle;

import static datamaskin.cart.Cart.findExpectedLifetime;
import static datamaskin.users.CustomerValidator.getCustomerList;

public class EnduserSendOrderPageController implements Initializable {
    @FXML private Button btnSendOrder, btnGoBack, btnGoToMainpage, btnNewUser;;
    @FXML private Label lblOrderSent, lblTotalPrice, lblExpectedLifetime, lblErrorLogin;
    @FXML private ImageView mainpageImageView;

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;

    //metode for å sette passord fra bruker når oppretter bruker
    public void setTxtPassword(String inputPassword){
        this.txtPassword.setText(inputPassword);
    }

    //metode for å sette epost i feltet når bruker oppretter ny bruker
    public void setTxtEmail(String inputEmail){
        this.txtEmail.setText(inputEmail);
    }

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
        lblExpectedLifetime.setText(findExpectedLifetime() + " år");
    }

    public void ifShoppingcartIsNull() {
        if (shoppingcart.getTotalPrice(lblTotalPrice) == 0 || lblTotalPrice.getText().equals("")) {
            btnSendOrder.setDisable(true);
            btnGoBack.setDisable(true);
            btnNewUser.setDisable(true);
        }
    }

    @FXML void sendOrder() throws IOException {
        String orderID = FinalOrderOverview.generateOrderID();
        FinalOrderOverview aFinalOrderOverview = createOrderObjectFromGUI(orderID);

        if(aFinalOrderOverview != null) {
            txtEmail.setText("");
            txtPassword.setText("");

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
            shoppingcart.deleteShoppingcart();
            lblTotalPrice.setText("");
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
            email = txtEmail.getText();
            password = txtPassword.getText();

            if(!CustomerValidator.validateEmail(email)){
                throw new InvalidEmailException("Skriv inn gyldig e-postadresse");
            } else if(getCustomerList()!=null && !CustomerValidator.validateCredentials(email, password, getCustomerList())){
                throw new InvalidEmailException("Du har skrevet inn ugyldige innloggingsdetaljer!");
            } else{
                totalPrice = shoppingcart.getTotalPrice(lblTotalPrice);
                String date = String.valueOf(LocalDate.now());

                //lager en ordreID for bestillingen og viser den til bruker
                lblErrorLogin.setText("");
                lblOrderSent.setText("Takk for din ordre.\nOrdrenummer: " + orderID);

                // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
                btnSendOrder.setDisable(true);
                btnGoBack.setDisable(true);

                //Oppretter ferdig ordre-objekt som returneres
                return new FinalOrderOverview(orderID, email, date, totalPrice);
            }
        } catch (InvalidEmailException e){
            lblErrorLogin.setText(e.getMessage());
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
        Parent root = FXMLLoader.load(getClass().getResource("EnduserExtraOrderPage.fxml"));
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