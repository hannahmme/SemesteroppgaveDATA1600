package Datamaskin.Order;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    //private transient SimpleStringProperty telefonnr;
    private transient SimpleDoubleProperty totalPrice;

    public Order (String orderID, String epost, int totalPrice){
        this.orderID = new SimpleStringProperty(orderID);
        this.epost = new SimpleStringProperty(epost);
        //this.telefonnr = new SimpleStringProperty(telefonnr);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);

    }

    public String getName() {
        return name.get();
    }
    public void setName(String componentName) {
        this.name = new SimpleStringProperty(componentName);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String componentName) {
        this.name = new SimpleStringProperty(componentName);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }
    public void setTotalPrice(double componentPrice) {
        this.totalPrice = new SimpleDoubleProperty(componentPrice);
    }



}
