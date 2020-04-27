package datamaskin.filbehandling;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import datamaskin.orders.FinalOrderOverview;
import datamaskin.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFromAllOrdersFile implements iReadFromAllOrdersFile{
    @Override
    public ObservableList<FinalOrderOverview> readFromAllOrdersFile(String path) throws IOException {
        ObservableList<FinalOrderOverview> listOfFinalOrders = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line = reader.readLine();

            while(line != null){
                listOfFinalOrders.add(parseToFinalOrderOverview(line));
                line = reader.readLine();
            }
        }
        return listOfFinalOrders;
    }

    @Override
    public FinalOrderOverview parseToFinalOrderOverview(String line) throws IOException {
        String[] split = line.split(";");
        if(split.length != 4){
            throw new IOException("Ikke riktig bruk av delimiter");
        }

        String orderID  = split[0];
        String email    = split[1];
        String date     = split[2];
        double price    = parseToDouble(split[3], "Pris er ikke et gyldig tall.");

        return new FinalOrderOverview(orderID, email, date, price);
    }

    @Override
    public double parseToDouble(String str, String errorMessage) throws IOException {
        double stringToDouble;
        try{
            stringToDouble = Double.parseDouble(str);
        } catch(NumberFormatException nfe){
            throw new IOException("Ikke gyldig tall.");
        }
        return stringToDouble;
    }
}
