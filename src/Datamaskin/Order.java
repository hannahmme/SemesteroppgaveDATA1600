package Datamaskin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty epost;
    //private transient SimpleStringProperty telefonnr;
    private transient SimpleIntegerProperty totalbeløp;

    public Order (String orderID, String epost,  int totalbeløp){
        this.orderID = new SimpleStringProperty(orderID);
        this.epost = new SimpleStringProperty(epost);
        //this.telefonnr = new SimpleStringProperty(telefonnr);
        this.totalbeløp = new SimpleIntegerProperty(totalbeløp);

    }





}
