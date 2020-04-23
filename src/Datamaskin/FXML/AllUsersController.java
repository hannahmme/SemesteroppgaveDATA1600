package Datamaskin.FXML;

import Datamaskin.customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AllUsersController {

    @FXML private TableView<Customer> customerTV;

    @FXML private TableColumn<String, Customer> emailColumn;

    @FXML private TableColumn<String, Customer> passwordColumn;

    @FXML private Button btnGoBack;


    // knapp som tar bruker til forrige side
    @FXML void GoBack(ActionEvent event) {

    }

}
