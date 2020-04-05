package Datamaskin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    private transient SimpleIntegerProperty orderID;
    private transient SimpleStringProperty epost;
    private transient SimpleStringProperty telefonnr;
    private transient SimpleIntegerProperty totalbeløp;

    public Order (int orderID, String epost, String telefonnr, int totalbeløp){
        this.orderID = new SimpleIntegerProperty(orderID);
        this.epost = new SimpleStringProperty(epost);
        this.telefonnr = new SimpleStringProperty(telefonnr);
        this.totalbeløp = new SimpleIntegerProperty(totalbeløp);

    }





}
