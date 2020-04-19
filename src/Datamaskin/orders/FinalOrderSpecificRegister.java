package Datamaskin.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class FinalOrderSpecificRegister {

    public transient static ObservableList<FinalOrderSpecific> SpecificOrderRegister = FXCollections.observableArrayList();

    public void addOrder(TableView tv) {
        tv.setItems(SpecificOrderRegister);
    }

    public void addElement(FinalOrderSpecific anOrder) {
        SpecificOrderRegister.add(anOrder);
    }
}
