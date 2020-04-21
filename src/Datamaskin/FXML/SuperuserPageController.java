// Tilhører siden med to knapper for superbruker, der admin kan velge mellom å lage nye komponenter eller å se alle ordre
package Datamaskin.FXML;


import Datamaskin.Page;
import Datamaskin.images.ImageClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperuserPageController implements Initializable {
    @FXML private Button toSuperUserProductPage;
    @FXML private Button toSuperUserOrderPage;
    @FXML private Button toMainPage;
    private ImageClass image = new ImageClass();
    //Denne er pga bildene som lages
    public SuperuserPageController() throws FileNotFoundException {}

    @FXML
    private ImageView logOutImageView;
    private Image logOutImage = image.createImage("./src/Datamaskin/images/logout.png");
    @FXML
    private ImageView createProdImageView;
    private Image createProdImage = image.createImage("./src/Datamaskin/images/createProd.jpg");
    @FXML
    private ImageView allOrdersImageView;
    private Image allOrdersImage = image.createImage("./src/Datamaskin/images/order.png");

    //Kobler ImageViewet med bildene når siden lastes inn
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImageView(logOutImageView, logOutImage);
        image.setImageView(createProdImageView, createProdImage);
        image.setImageView(allOrdersImageView, allOrdersImage);
    }

    //metode som sender bruker tilbake til hovedsiden
    @FXML void toMainPage() throws IOException {
        Stage primaryStage = (Stage) toMainPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        Page.toMainpage(primaryStage, root);
    }

    //knapp som sender superbrukeren til siden hvor man kan administrere komponenter
    @FXML void toSuperUserProductPage() throws IOException {
        Stage primaryStage = (Stage) toSuperUserProductPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProductAdmPage.fxml"));
        Page.toProductAdminPage(primaryStage, root);
    }

    //sender superbruker til alle ordre-siden
    @FXML void toSuperUserOrderPage() throws IOException {
        Stage primaryStage = (Stage) toSuperUserOrderPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AllOrders.fxml"));
        Page.toAllOrdersPage(primaryStage, root);
    }

    //metoder som sender deg til neste side ved å trykke "Enter" - Hannah
    @FXML
    void btnAllOrdersEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toSuperUserOrderPage();
        }
    }
    @FXML
    void btnChangeEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toSuperUserProductPage();
        }
    }
    @FXML
    void btnLogOutEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toMainPage();
        }
    }
}

