package Datamaskin.Order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class FinalOrderRegister {

    public transient static ObservableList<FinalOrder> OrdreRegister = FXCollections.observableArrayList();

    public void leggTilOrdre(TableView tv) {
        tv.setItems(OrdreRegister);
    }


    public void addElement(FinalOrder enOrdre) {
        OrdreRegister.add(enOrdre);
    }


}
