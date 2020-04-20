package Datamaskin.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ProductRegister {
    public transient static ObservableList<Product> Register = FXCollections.observableArrayList();

    public void setComponentToTV(TableView tv) {
        tv.setItems(Register);
    }

    public void addElement(Product aProduct) {
        Register.add(aProduct);
    }
}
