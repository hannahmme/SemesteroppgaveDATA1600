package datamaskin.fxml;

import datamaskin.filbehandling.ReadFromCustomerFile;
import datamaskin.users.Customer;
import datamaskin.users.CustomerValidator;
import datamaskin.exceptions.InvalidEmailException;
import datamaskin.Page;
import datamaskin.images.ImageClass;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {
    @FXML private Button btnEnduserPage, btnSuperuserPage, btnUserOrders;
    @FXML private TextField txtUsername, txtPassword, txtUserEmail, txtUserPassword;
    @FXML private Label lblUserError, lblAdminError;
    private ReadFromCustomerFile readFromCustomerFile = new ReadFromCustomerFile();
    private ImageClass image = new ImageClass();

    //Bilder settes i ImageViewet når siden lastes inn, samt effekt på bildet
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImageView(orderImageView, orderImage);
        image.setImageView(buildImageView, hammerImage);
        image.setImageView(adminImageView, adminImage);
        image.setImageView(hardwareImageView, hardwareImage);
        shadowEffect.setRadius(50);
        shadowEffect.setWidth(50);
        shadowEffect.setHeight(25);
        hardwareImageView.setEffect(shadowEffect);
    }

    // kode for bildene som ligger på hovedsiden, final fordi de ikke skal endres
    @FXML private ImageView hardwareImageView, buildImageView, adminImageView, orderImageView;
    private final Image hardwareImage = image.createImage("./src/Datamaskin/images/hardware.jpg");
    private final Image orderImage = image.createImage("./src/Datamaskin/images/order.png");
    private final Image hammerImage = image.createImage("./src/Datamaskin/images/hammer.png");
    private final Image adminImage = image.createImage("./src/Datamaskin/images/admin.png");

    //Effekt for å blurre bildet på mainpage (det blå bildet)
    private final DropShadow shadowEffect = new DropShadow();

    //Kastes fordi createImage-metoden kalles
    public MainpageController() throws FileNotFoundException {}

    //nøkkel for å sortere ordrelisten for sluttbruker, er eposten som skrives inn
    static String sortingKey;

    // metode som sender brukeren til ordresiden for bruker, må valideres med epost og passord
    @FXML void toUserOrders() throws IOException {
        try {
            String email = txtUserEmail.getText();
            String password = txtUserPassword.getText();
            ObservableList<Customer> allCustomersList = readFromCustomerFile.readFromCustomerFile("./src/Datamaskin/users/allCustomers.csv");

            if(CustomerValidator.validateCredentials(email, password, allCustomersList)) {
                sortingKey = email;
            }

            if (!CustomerValidator.validateEmail(email)) {
                throw new InvalidEmailException("Skriv inn gyldig e-postadresse");
            } else if(sortingKey == null){
                lblUserError.setText("Du har skrevet inn feil kombinasjon av epost og passord");
            } else {
                Stage primaryStage = (Stage) btnUserOrders.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("UserspesificOrder.fxml"));
                Page.toUserspesificOrder(primaryStage, root);
                primaryStage.show();
            }
        } catch (InvalidEmailException e) {
            lblUserError.setText(e.getMessage());
            }
    }

    // metode som åpner ny scene til superbrukersiden
    @FXML void toEnduserPage() throws IOException {
        Stage primaryStage = (Stage) btnEnduserPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        Page.toEnduserPage(primaryStage, root);
        primaryStage.show();
    }

    // metode som åpner ny scene til sluttbrukersiden (endret versjon av metoden) - hannah
    @FXML void toSuperUserPage() throws IOException {
        try {
           String username = txtUsername.getText();
           String password = txtPassword.getText();
           boolean isMatching = username.matches("admin") && password.matches("admin");
           if(isMatching){
               lblAdminError.setText("");
               Stage primaryStage = (Stage) btnSuperuserPage.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
               Page.toSuperuserpage(primaryStage, root);
               primaryStage.show();
           }
           else{
               lblAdminError.setText("Feil innloggingsdetaljer");
               throw new NullPointerException("Noe gikk galt ved innlasting av brukernavn/passord");
           }
        }catch(NullPointerException npe){
            System.err.println(npe.getMessage());
        }
    }

    //Enter-funksjon på buttons
    @FXML void btnUserOrdersEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toUserOrders();
        }
    }
    @FXML void btnEnduserPageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toEnduserPage();
        }
    }
    @FXML void btnSuperUserPage(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toSuperUserPage();
        }
    }
}