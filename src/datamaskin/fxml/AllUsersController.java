package datamaskin.fxml;

import datamaskin.Page;
import datamaskin.filbehandling.ReadFromCustomerFile;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.users.Customer;
import datamaskin.users.CustomerRegister;
import datamaskin.users.CustomerValidator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllUsersController implements Initializable {

    @FXML private TableView<Customer> customerTV;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> passwordColumn;
    @FXML private Button toSuperuserpage;

    private ReadFromCustomerFile readFromCustomerFile = new ReadFromCustomerFile();

    @FXML void deleteUser(){
        Customer deleteCustomer = customerTV.getSelectionModel().getSelectedItem();
        CustomerRegister.deleteCustomer(deleteCustomer);
        NewUserController.aCustomerRegister.deleteCustomer(deleteCustomer);
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Customer> allOrdersList = readFromCustomerFile.readFromCustomerFile("./src/Datamaskin/users/allCustomers.csv");
            customerTV.getItems().addAll(allOrdersList);
            customerTV.setItems(allOrdersList);
        } catch (IOException e) {
            System.out.println("Filsti ikke funnet: " + e.getMessage());
        }

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));


        //oppdaterer TV for å la oss endre tabellen
        customerTV.setEditable(true);
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    // metode som vil la admin endre komponenter
    public void changeEmailCellEvent(TableColumn.CellEditEvent editedCell){
        Customer selectedCustomer = customerTV.getSelectionModel().getSelectedItem();

        selectedCustomer.setEmail(editedCell.getNewValue().toString());
    }

    // metode som vil la admin endre komponenter
    public void changePasswordCellEvent(TableColumn.CellEditEvent editedCell){
        Customer selectedCustomer = customerTV.getSelectionModel().getSelectedItem();
        if(CustomerValidator.validatePassword(selectedCustomer.getPassword())) {
            selectedCustomer.setPassword(editedCell.getNewValue().toString());
        }
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
