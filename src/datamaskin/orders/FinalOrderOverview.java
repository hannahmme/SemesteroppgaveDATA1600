package datamaskin.orders;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.io.IOException;

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

    // metode for å generere ordreID. Setter en begrensning på 100 ordre
    public static String generateOrderID() throws IOException {
        int orderNumber = 0;
        boolean pathNotUsed;

        for(int i = 1; i<100; i++) {
            File orderPath = new File(
                    "./src/Datamaskin/sentOrdersPath/ordre-" + i + ".csv");
            pathNotUsed = orderPath.exists();
            if (!pathNotUsed) {
                orderNumber = i;
                break;
            }
        }
        return "ordre-"+orderNumber;
    }
}