package Datamaskin.Cart;

import Datamaskin.Product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Cart {
    public transient static ObservableList<Product> Register = FXCollections.observableArrayList();

    public void addComponent(TableView tv) {
        tv.setItems(Register);
    }

    public void addElement(Product etProduct) {
        Register.addAll(etProduct);
    }


}
