package datamaskin.fxml;

import datamaskin.Page;
import datamaskin.users.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static datamaskin.users.CustomerValidator.getCustomerList;

public class AllUsersController implements Initializable {

    @FXML private TableView<Customer> customerTV;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> passwordColumn;
    @FXML private Button toSuperuserpage;

    @Override public void initialize(URL url, ResourceBundle rb) {
        try {
            customerTV.getItems().addAll(Objects.requireNonNull(getCustomerList()));
            customerTV.setItems(getCustomerList());
        } catch (IOException e) {
            System.err.println("Filsti ikke funnet: " + e.getMessage());
        }
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        customerTV.setEditable(false);
    }

    //knappen "tilbake" tar brukeren med tilbake til menysiden for superbruker
    @FXML void toSuperuserpage() throws IOException {
        Stage primaryStage = (Stage) toSuperuserpage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SuperuserPage.fxml"));
        Page.toSuperuserpage(primaryStage, root);
    }
    @FXML void btnSuperUserPageEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            toSuperuserpage();
        }
    }
}