package datamaskin.orders;

// er denne noe vits i å ha?


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FinalOrderSpecific {
    private SimpleStringProperty orderID;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty lifetime;
    private SimpleDoubleProperty price;

    public FinalOrderSpecific (String orderID, String name, String description, int lifetime, double price) {
        this.orderID = new SimpleStringProperty(orderID);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.lifetime = new SimpleIntegerProperty(lifetime);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getOrderID() {
        return orderID.get();
    }
    public void setOrderID(String componentOrderID) {
        this.orderID = new SimpleStringProperty(componentOrderID);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String componentName) {
        this.name = new SimpleStringProperty(componentName);
    }

    public String getDescription() {
        return description.get();
    }
    public void setDescription(String componentDescription) {
        this.description = new SimpleStringProperty(componentDescription);
    }

    public int getLifetime() {
        return lifetime.get();
    }
    public void setLifetime(int componentLifetime) {
        this.lifetime = new SimpleIntegerProperty(componentLifetime);
    }

    public double getPrice() {
        return price.get();
    }
    public void setPrice(double componentPrice) {
        this.price = new SimpleDoubleProperty(componentPrice);
    }





}
