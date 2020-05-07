package datamaskin.designpatternDecorator;

import datamaskin.orders.FinalOrderOverview;
import javafx.beans.property.SimpleDoubleProperty;

public class StudentDiscount extends FinalOrderOverview {
    private FinalOrderOverview order;
    private SimpleDoubleProperty discount;

    public StudentDiscount(FinalOrderOverview order, double discount){
        super("", "", "", 0.0);
        this.order = order;
        this.discount = new SimpleDoubleProperty(discount);
    }

    @Override public double getTotalPrice(){
        return order.getTotalPrice() - discount.getValue();
    }

    @Override public String toString(){
        return String.format("\"%s\", ordreNr: %s, pris: %s (det er %s kr ekstra)",
               order.getEmail(), order.getOrderID(), order.getTotalPrice(), discount);
    }
}
