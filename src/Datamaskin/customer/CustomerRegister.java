package Datamaskin.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class CustomerRegister {
    public transient static ObservableList<Customer> Register = FXCollections.observableArrayList();

    public void setCustomerToTV(TableView tv) {
        tv.setItems(Register);
    }

    public void addElement(Customer aCustomer) {
        Register.add(aCustomer);
    }
}
