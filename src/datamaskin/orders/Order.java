package datamaskin.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class Order {

    private transient ObservableList<FinalOrderOverview> overviewRegister = FXCollections.observableArrayList();

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
