package datamaskin.orders;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinalOrderOverview {
    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty orderDate;
    private transient SimpleDoubleProperty totalPrice;

    public FinalOrderOverview(String orderID, String email, String orderDate, double totalPrice){
        this.orderID = new SimpleStringProperty(orderID);
        this.email = new SimpleStringProperty(email);
        this.orderDate = new SimpleStringProperty(orderDate);
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

    public static String checkEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !email.isEmpty() && email != null && !email.equals("")){
            return email;
        }
        throw new IllegalArgumentException("Ugyldig ordrenummer!");
    }

    public static String checkOrderID(String orderID){
        if (orderID.matches("[order-]+[0-9]+[0-9]*")) {
            return orderID;
        }
        throw new IllegalArgumentException("Ugyldig ordrenummer!");
    }

    public static String checkDate(String orderID){
        if (orderID.matches("[order-]+[0-9]+[0-9]*")) {
            return orderID;
        }
        throw new IllegalArgumentException("Ugyldig ordrenummer!");
    }

    public static double checkTotalPrice(double totalPrice){
        if (totalPrice>0 && totalPrice<=100000) {
            return totalPrice;
        }
        throw new IllegalArgumentException("Ugyldig ordrenummer!");
    }


}
