package Datamaskin.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class FinalOrderOverviewRegister {
    public static ObservableList<FinalOrderOverview> OrderRegister = FXCollections.observableArrayList();

    public void addOrder(TableView tv) {
        tv.setItems(OrderRegister);
    }

    public void addElement(FinalOrderOverview enOrdre) {
        OrderRegister.add(enOrdre);
    }

    public FinalOrderOverview addCustomerOverviewInfo(String epost){
        for(FinalOrderOverview anOrder : OrderRegister) {
            if(epost.equals(anOrder.getEmail())){
                return anOrder;
            }
        }
        return null;
    }

    public void deleteCustomerOverviewInfo(ArrayList<FinalOrderOverview> aList){
        for(FinalOrderOverview anOrder : aList) {
            aList.remove(anOrder);
        }
    }

}
