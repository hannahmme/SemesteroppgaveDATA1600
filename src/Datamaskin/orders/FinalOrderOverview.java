package Datamaskin.orders;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class FinalOrderOverview {

    private transient SimpleStringProperty orderID;
    private transient SimpleStringProperty email;
    private transient Date orderDate;
    private transient SimpleDoubleProperty totalPrice;

    // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public FinalOrderOverview(String orderID, String email, Date orderDate, double totalPrice){
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

    public int getDate(){
        return orderDate.getDate();
    }
    public void setDate(int year, int month, int date){
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }
    public void setTotalPrice(double Price) {
        this.totalPrice = new SimpleDoubleProperty(Price);
    }


}
