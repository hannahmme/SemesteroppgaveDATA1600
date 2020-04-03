package Datamaskin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty lifetime;
    private SimpleIntegerProperty price;

    public Product(String name, String description, String lifetime, int price) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.lifetime = new SimpleStringProperty(lifetime);
        this.price = new SimpleIntegerProperty(price);
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

    public String getLifetime() {
        return lifetime.get();
    }
    public void setLifetime(String componentLifetime) {
        this.lifetime = new SimpleStringProperty(componentLifetime);
    }

    public int getPrice() {
        return price.get();
    }
    public void setPrice(int componentPrice) {
        this.price = new SimpleIntegerProperty(componentPrice);
    }

}
