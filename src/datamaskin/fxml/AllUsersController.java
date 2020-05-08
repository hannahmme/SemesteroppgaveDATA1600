package datamaskin.fxml;

import datamaskin.Page;
import datamaskin.users.Customer;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static datamaskin.users.CustomerValidator.getCustomerList;

public class AllUsersController implements Initializable {

    @FXML private TableView<Customer> customerTV;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> passwordColumn;
    @FXML private Button toSuperuserpage;
    @FXML private Text txtErrorMessage;

    @Override public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Customer> validCustomersList;
        try {
            validCustomersList = getCustomerList();

            if(validCustomersList.isEmpty()){
                txtErrorMessage.setText("Kunne ikke laste inn kunderegister.");
            }
            customerTV.getItems().addAll(validCustomersList);
            customerTV.setItems(validCustomersList);
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