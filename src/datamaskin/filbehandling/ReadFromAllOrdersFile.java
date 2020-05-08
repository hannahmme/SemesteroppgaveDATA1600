package datamaskin.filbehandling;

import datamaskin.orders.FinalOrderOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromAllOrdersFile implements iReadFromAllOrdersFile{

    //metode som leser fra allOrders.csv og parser hver linje til en ordre
    @Override
    public ObservableList<FinalOrderOverview> readFromAllOrdersFile(String path) throws IOException {
        ObservableList<FinalOrderOverview> listOfFinalOrders = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line = reader.readLine();

            while(line != null && parseToFinalOrderOverview(line)!=null){
                listOfFinalOrders.add(parseToFinalOrderOverview(line));
                line = reader.readLine();
            }
        }
        return listOfFinalOrders;
    }

    @Override
    public FinalOrderOverview parseToFinalOrderOverview(String line) throws IOException {
        String[] split = line.split(";");

        if (split.length == 4) {
            String orderID = split[0];
            String email = split[1];
            String date = split[2];
            double price = parseToDouble(split[3], "Pris er ikke et gyldig tall.");

            return new FinalOrderOverview(orderID, email, date, price);

        } else {
            throw new IOException("Ikke riktig bruk av delimiter");
        }
    }

    @Override
    public double parseToDouble(String str, String errorMessage) throws IOException {
        double stringToDouble;
        try{
            stringToDouble = Double.parseDouble(str);
        } catch(NumberFormatException nfe){
            throw new IOException("Ikke riktig tall i ordrefilen tabellen prøver å lese inn." +
                                  " Feil kastet fra ReadFromAllOrdersFile.java");
        }
        return stringToDouble;
    }
}
