package Datamaskin.Cart;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// her trenger vi bare navn på vaen og pris, siden det er alt som betyr noe når bestilleren er klar for å betale.
// lurer brukeren på noe ved produktet må hun gå tilbake til forrige side for å lese mer om det

public class AllProductsCart {
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public AllProductsCart(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String componentName) {
        this.name = new SimpleStringProperty(componentName);
    }

    public double getPrice() {
        return price.get();
    }
    public void setPrice(double componentPrice) {
        this.price = new SimpleDoubleProperty(componentPrice);
    }
}
