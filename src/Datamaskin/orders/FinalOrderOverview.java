package Datamaskin.orders;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalOrderOverview {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty orderDate;
    private transient SimpleDoubleProperty totalPrice;

    public FinalOrderOverview(String orderID, String email, String date, double totalPrice){
        this.orderID = new SimpleStringProperty(orderID);
        this.email = new SimpleStringProperty(email);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
        this.orderDate = new SimpleStringProperty(date);
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

    public String getOrderDate() {
        return orderDate.get();
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = new SimpleStringProperty(orderDate);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }
    public void setTotalPrice(double Price) {
        this.totalPrice = new SimpleDoubleProperty(Price);
    }
}
