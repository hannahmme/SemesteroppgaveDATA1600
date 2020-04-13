package Datamaskin.Order;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class FinalOrder {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    private transient LocalDate orderDate = LocalDate.now();
    private transient SimpleDoubleProperty totalPrice;

    public FinalOrder(String orderID, String email, LocalDate orderDate, int totalPrice){
        this.orderID = new SimpleStringProperty(orderID);
        this.email = new SimpleStringProperty(email);
        this.orderDate = orderDate;
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

    /*public int getOrderDate() {
        return orderDate.get(LocalDate.now());
    }
    public void setOrderDate(double OrderDate) {
        this.orderDate = new LocalDate(OrderDate);
    }*/

    public double getTotalPrice() {
        return totalPrice.get();
    }
    public void setTotalPrice(double Price) {
        this.totalPrice = new SimpleDoubleProperty(Price);
    }


}
