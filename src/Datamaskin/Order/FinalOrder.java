package Datamaskin.Order;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalOrder {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    // private transient Date orderDate;
    private transient SimpleDoubleProperty totalPrice;

    public FinalOrder(String orderID, String email, int totalPrice){
        this.orderID = new SimpleStringProperty(orderID);
        this.email = new SimpleStringProperty(email);
        // this.orderDate = orderDate;
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
    }

    public String getOrderID() {
        return orderID.get();
    }
    public void setOrderID(String OrderID) {
        this.orderID = new SimpleStringProperty(OrderID);
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String Email) {
        this.email = new SimpleStringProperty(Email);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }
    public void setTotalPrice(double Price) {
        this.totalPrice = new SimpleDoubleProperty(Price);
    }


}
