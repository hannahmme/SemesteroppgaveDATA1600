package datamaskin.orders;

import datamaskin.filbehandling.ReadFromAllOrdersFile;
import datamaskin.filbehandling.ReadFromAnOrderFile;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

            if (!pathNotUsed && doesOrderExistInAllOrders(i)) {
                System.err.println("Ordrenummer finnes i allOrders.csv, men ikke som en spesifikasjon. Se gjennom filnavn i sentOrdersPath.");
            }


            else if (!pathNotUsed && !doesOrderExistInAllOrders(i)) {
                orderNumber = i;
                break;
            }

        }
        return "ordre-"+orderNumber;
    }

    private static final ReadFromAllOrdersFile readFromAllOrdersFile = new ReadFromAllOrdersFile();

    public static boolean doesOrderExistInAllOrders(int i){
        ObservableList<FinalOrderOverview> allOrdersList = FXCollections.observableArrayList();

        try {
            allOrdersList = readFromAllOrdersFile.readFromAllOrdersFile("./src/datamaskin/sentOrdersPath/allOrders.csv");
        } catch (IOException e) {
            System.err.println("Filsti ikke funnet: " + e.getMessage() + ".");
        }
        for(FinalOrderOverview anOrder : allOrdersList){
            if(anOrder.getOrderID().equals("ordre-"+i)){
                return true;
            }
        }


        return false;
    }

}