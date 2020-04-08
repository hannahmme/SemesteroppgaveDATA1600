package Datamaskin.Order;

import Datamaskin.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class OrderRegister {

    public transient static ObservableList<Order> OrdreRegister = FXCollections.observableArrayList();

    public void leggTilOrdre(TableView tv) {
        tv.setItems(OrdreRegister);
    }


    public void addElement(Order enOrdre) {
        OrdreRegister.add(enOrdre);
    }


}
