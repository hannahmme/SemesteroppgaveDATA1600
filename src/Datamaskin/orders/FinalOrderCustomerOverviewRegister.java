package Datamaskin.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class FinalOrderCustomerOverviewRegister {

    public static ObservableList<FinalOrderOverview> OrderRegister = FXCollections.observableArrayList();

    public void addOrder(TableView tv) {
        tv.setItems(OrderRegister);
    }

    public void addElement(FinalOrderOverview enOrdre) {
        OrderRegister.add(enOrdre);
    }

    public void deleteElement(FinalOrderOverview anOrder) {
        OrderRegister.remove(anOrder);
    }

    public static FinalOrderOverview addCustomerOverviewInfo(String epost){
        for(FinalOrderOverview anOrder : OrderRegister) {
            if(epost.equals(anOrder.getEmail())){
                return anOrder;
            }
        }
        return null;
    }


}
