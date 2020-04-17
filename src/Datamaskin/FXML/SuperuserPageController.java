// Tilhører siden med to knapper for superbruker, der admin kan velge mellom å lage nye komponenter eller å se alle ordre
package Datamaskin.FXML;


import Datamaskin.newScene;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperuserPageController implements Initializable {

    @FXML private Button tilKomponentside;
    @FXML private Button tilOrdreside;
    @FXML private Button tilHovedside;

    public SuperuserPageController() throws FileNotFoundException {
    }

    @FXML
    void btnAllOrdersEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Stage primaryStage = (Stage) tilOrdreside.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("AllOrders.fxml"));
            newScene.toSuperuserpage(primaryStage, root);
        }
    }

    @FXML
    void btnChangeEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Stage primaryStage = (Stage) tilKomponentside.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ProductAdmPage.fxml"));
            newScene.toSuperuserpage(primaryStage, root);
        }
    }

    @FXML
    void btnLogOutEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
            newScene.toSuperuserpage(primaryStage, root);
        }
    }

    //metode som kobler bildet og imageViewet sammen.
    private void setImageView(ImageView iv, Image image){
        iv.setImage(image);
    }

    //metode som oppretter et bilde via path og returnerer et bilde
    private Image createImage(String path) throws FileNotFoundException {
        FileInputStream imageStream = new FileInputStream(path);
        Image image = new Image(imageStream);
        return image;
    }

    @FXML
    private ImageView logOutImageView;
    private Image logOutImage = createImage("./src/Datamaskin/images/logout.png");

    @FXML
    private ImageView createProdImageView;
    private Image createProdImage = createImage("./src/Datamaskin/images/createProd.jpg");

    @FXML
    private ImageView allOrdersImageView;
    private Image allOrdersImage = createImage("./src/Datamaskin/images/order.png");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImageView(logOutImageView, logOutImage);
        setImageView(createProdImageView, createProdImage);
        setImageView(allOrdersImageView, allOrdersImage);
    }


    //knapp som sender bruker tilbake til hovedsiden
    @FXML void tilHovedside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilHovedside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
        newScene.toMainpage(primaryStage, root);
    }


    //knapp som sender brukeren til siden hvor man kan administrere komponenter
    @FXML void tilKomponentside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilKomponentside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProductAdmPage.fxml"));
        primaryStage.setTitle("Å lage eller ikke lage nye komponenter");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }



    @FXML void tilOrdreside(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) tilOrdreside.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AllOrders.fxml"));
        primaryStage.setTitle("Ordre som har blitt laget av brukere");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

}

