package datamaskin.orders;

import datamaskin.cart.Cart;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;

public class Order {


    // meotde for å hente ut verdier fra pris-kolonnen og legge de sammen, for så å sette verdien til lbl
    public static void getTotalprice(Cart aCart, Label infoLabel) {
        double totalPrice = aCart.getTotalPrice();
        infoLabel.setText(String.valueOf(totalPrice));
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
