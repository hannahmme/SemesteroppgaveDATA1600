package Datamaskin.FXML;

import Datamaskin.Customer;
import Datamaskin.Exceptions.InvalidEmailException;
import Datamaskin.newScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {
    public String eposten;

    @FXML private Button tilSluttbrukerside;
    @FXML private Button tilSuperbrukerside;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private Button btnUserOrders;
    @FXML private TextField txtEmail;

    public MainpageController() throws FileNotFoundException {
    }

    @FXML void btnUserOrders(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnUserOrders.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserspesificOrder.fxml"));
        newScene.toUserspesificOrder(primaryStage, root);
        primaryStage.show();
    }

    // bruke denne med try/catch for å legge til en verdi som skal brukes for å filtrere userSpecific order
    public void checkEmail() throws InvalidEmailException {
        String email = txtEmail.getText();
        Customer.validateEmail(email);

        eposten = txtEmail.getText();
    }


    // metode som åpner ny scene til superbrukersiden
    @FXML void tilSluttbrukerside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilSluttbrukerside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnduserPage.fxml"));
        newScene.toEnduserPage(primaryStage, root);
    }

    // metode som åpner ny scene til sluttbrukersiden
    @FXML void tilSuperbrukerside(ActionEvent event) throws IOException {
        String username = null;
        String password = null;
        try {
            username = txtUsername.getText();
            password = txtPassword.getText();
        } catch(Exception e){
            e.printStackTrace();
        }

        if (username.matches("admin") && password.matches("admin")) {
            try {
                Stage primaryStage = (Stage) tilSuperbrukerside.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
                newScene.toSuperuserpage(primaryStage, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private ImageView orderImageView;
    private String orderPath = "./src/Datamaskin/images/order.png";
    private FileInputStream orderStream = new FileInputStream(orderPath);
    private Image orderImage = new Image(orderStream);



    @FXML
    private ImageView buildImageView;
    private String imagepath = "./src/Datamaskin/images/hammer.png";
    private FileInputStream hammerStream = new FileInputStream(imagepath);
    private Image hammerImage = new Image(hammerStream);


    @FXML
    private ImageView adminImageView;
    private String adminPath = "./src/Datamaskin/images/admin.png";
    private FileInputStream adminStream = new FileInputStream(adminPath);
    private Image adminImage = new Image(adminStream);

    private void setImageView(ImageView iv, Image image){
        iv.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImageView(orderImageView, orderImage);
        setImageView(buildImageView, hammerImage);
        setImageView(adminImageView, adminImage);
    }
}
