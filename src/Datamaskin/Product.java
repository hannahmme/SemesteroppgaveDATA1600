package Datamaskin;

import Datamaskin.Exceptions.InvalidLifetimeException;
import Datamaskin.Exceptions.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty lifetime;
    private SimpleDoubleProperty price;
    private SimpleStringProperty category;

    public Product(String name, String description, int lifetime, double price, String category) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.lifetime = new SimpleIntegerProperty(lifetime);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
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
    public void setPrice(int componentPrice) {
        this.price = new SimpleDoubleProperty(componentPrice);
    }

    public String getCategory() {
        return category.get();
    }
    public void setCategory(String componentCategory) {
        this.category = new SimpleStringProperty(componentCategory);
    }


    @Override public String toString(){
        return "Name: " + name.getValue() + "; Description: " + description.getValue() + "; Lifetime: " + lifetime.getValue() +
                "; Price: " + price.getValue() + "; Category: " + category.getValue();
    }


    // enhtesvalideringsmetoder
    public static String validateName(String name) throws IllegalArgumentException {
        if (name.matches("[a-zA-Z \\-]*")&& name.length()<20 && !name.equals("")) {
            return name;
        }
        throw new IllegalArgumentException("Skriv inn et gyldignavn!");
    }

    public static String validateDescription(String description) throws IllegalArgumentException {
        if (description.matches("[a-zA-Z \\-]*") && description.length()<170 && !description.equals("")) {
            return description;
        }
        throw new IllegalArgumentException("Skriv inn en gyldig beskrivelse!");
    }

    public static Integer validateLifetime (int lifetime) throws InvalidLifetimeException {
        if (lifetime > 0 && lifetime < 20){
            return lifetime;
        }
        throw new InvalidLifetimeException("Put in a valid number of years");
    }

    public static Double validatePrice (double price) throws InvalidPriceException {
        if (price > 0 && price < 10000){
            return price;
        }
        throw new InvalidPriceException("Put in a valid price");
    }


    public static String validateCategory (String category){
        if(category.isEmpty()){
            throw new IllegalArgumentException("Choose a category" +
                    "!");
        }
        return category;
    }



    // testmetoder

    public static boolean testPrice (double price){
        if (price > 0 && price < 10000){
            return true;
        }
        return false;
    }



}
