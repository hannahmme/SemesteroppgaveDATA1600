package Datamaskin.FXML;

import Datamaskin.Cart.Cart;
import Datamaskin.customer.CustomerRegister;
import Datamaskin.customer.CustomerValidator;
import Datamaskin.Exceptions.InvalidEmailException;
import Datamaskin.Filbehandling.FileSaverTxt;
import Datamaskin.Filbehandling.OrderFormatter;
import Datamaskin.images.ImageClass;
import Datamaskin.orders.FinalOrderOverview;
import Datamaskin.orders.FinalOrderOverviewRegister;
import Datamaskin.Product.Product;
import Datamaskin.Page;
import Datamaskin.orders.FinalOrderSpecificRegister;
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

public class EnduserSendOrderPageController implements Initializable {

    @FXML private Button btnSendOrder;
    @FXML private Button btnGoBack;
    @FXML private Button btnGoToMainpage;
    @FXML private TextField txtEpost;
    @FXML private Label lblOrderSent;
    @FXML private Label lblTotalPrice;
    @FXML private ImageView mainpageImageView;
    @FXML private TextField txtDiscount;
    @FXML private PasswordField txtPassword;

    private FileSaverTxt filesaver = new FileSaverTxt();
    private ImageClass image = new ImageClass();
    private Image homeImage = image.createImage("./src/Datamaskin/images/mainpage.png");
    private Cart shoppingcart = new Cart();
    //private FileSaverTxt filesaver = new FileSaverTxt();

    // et register for overordnet info
    static FinalOrderOverviewRegister OrderRegister = new FinalOrderOverviewRegister();

    //denne kastes fordi image.createImage kalles
    public EnduserSendOrderPageController() throws FileNotFoundException {
    }

    // metode som setter den totale prisen basert på komponentene i arrayet
    private void setTotalPriceLabel(){
        double totalPrice = shoppingcart.getTotalPrice();
        lblTotalPrice.setText(String.valueOf(totalPrice));
    }

    //Handlekurven på høyre side
    @FXML private TableView<FinalOrderOverview> finalOrderRegister;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;


    @Override public void initialize(URL location, ResourceBundle resources) {
        //Handlekurven på høyre side lastes inn når siden lastes inn
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        //Kobler handlekurven med tableviewet.
        shoppingcart.attachTableview(finalOrderRegister);

        //Setter riktig totaltpris ved innlasting av siden
        setTotalPriceLabel();

        image.setImageView(mainpageImageView, homeImage);
    }

    @FXML void sendOrder() throws IOException, InvalidEmailException {
        FinalOrderOverview aFinalOrderOverview = createOrderObjectFromGUI();

        if(aFinalOrderOverview != null) {
            OrderRegister.addElement(aFinalOrderOverview);

     // todo: kan dette slettes?
            /*FinalOrderSpecific aFinalOrderSpecific = createSpecificOrderObject(orderID);
            SpecificOrderRegister.addElement(aFinalOrderSpecific);*/

            txtEpost.setText("");
            txtPassword.setText("");

            Path sentOrderPath = Paths.get("./src/Datamaskin/sentOrdersPath/"+orderID+".csv");
            String formattedList = OrderFormatter.formatListOfProductToString(Cart.Register);
            filesaver.saveToFile(formattedList, sentOrderPath);

            //sletter handlekurven *etter* å ha lagret til fil - //todo: kanskje lage exception i tilfelle ikke klarer å lese til filstien (så ikke handlekurven slettes før det faktisk er blitt lagret) - hannah
            shoppingcart.deleteShoppingcart();
        }
    }

    // metode for å generere ordreID
    private static int orderID = 10;
    private String generateOrderID(){
        orderID++;
        return "ordre-"+orderID;
    }

    // metode for å generere en ordre og legget il ordreID og epost i array
    private FinalOrderOverview createOrderObjectFromGUI(){
        String email;
        String password;
        double totalPrice;

        try {
            email = txtEpost.getText();
            password = txtPassword.getText();

            if(!CustomerValidator.validateEmail(email)){
                throw new InvalidEmailException("Skriv inn gyldig e-postadresse");
            } else if(!Objects.equals(CustomerRegister.checkCredentials(email, password), email)){
                throw new InvalidEmailException("Du har skrevet inn ugyldige innloggingsdetaljer!");
            }
            else{
                String orderID = generateOrderID();
                totalPrice = shoppingcart.getTotalPrice();
                String date = String.valueOf(LocalDate.now());

                //lager en ordreID for bestillingen og viser den til bruker
                lblOrderSent.setText("Takk for din ordre.\nOrdrenummer: " + orderID);

                //Oppretter ferdig ordre-objekt
                FinalOrderOverview anFinalOrderOverview = new FinalOrderOverview(orderID, email, date, totalPrice);

                // setter knappene som disabled fordi bestillingen er gjennomført og man må starte på nytt
                btnSendOrder.setDisable(true);
                btnGoBack.setDisable(true);

                // Setter totalprisen til brukers skjerm
                lblTotalPrice.setText(String.valueOf(totalPrice));

                // returnerer ordren siden alt er riktig av input osv
                return anFinalOrderOverview;
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
