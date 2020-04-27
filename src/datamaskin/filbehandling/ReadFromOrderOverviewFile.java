package datamaskin.filbehandling;

import datamaskin.exceptions.InvalidFileException;
import datamaskin.orders.FinalOrderOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromOrderOverviewFile implements iReadFromOrderOverviewFile{

    @Override
    public ObservableList<FinalOrderOverview> readFromOrderOverviewFile(String path) throws IOException {
        ObservableList<FinalOrderOverview> listOfOrders = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line = reader.readLine();

            while(line != null){
                listOfOrders.add(parseOrder(line));
                line = reader.readLine();
            }
        }
        return listOfOrders;
    }

    @Override
    public FinalOrderOverview parseOrder(String line) throws IOException {

        // splitter stringen i syv deler med semikolon
        String[] splitOrder = line.split(";");
        if(splitOrder.length != 4) {
            throw new InvalidFileException("Bruk semikolon for å separere alle datafeltene");
        }

        // sier hvilke deler av stringen som gjelder hvilke attributter med spesifikke feilmeldinger
        String email        = FinalOrderOverview.checkEmail(splitOrder[0]);
        String orderID      = FinalOrderOverview.checkOrderID((splitOrder[1]));
        String orderDate    = FinalOrderOverview.checkDate((splitOrder[2]));
        double totalPrice   = FinalOrderOverview.checkTotalPrice(parseToDouble(splitOrder[3], "Totalpris (felt 4) er ikke et tall"));

        return new FinalOrderOverview(email, orderID, orderDate, totalPrice);
    }

    @Override
    public double parseToDouble(String str, String errorMessage) throws IOException {
        double stringToDouble;
        try{
            stringToDouble = Double.parseDouble(str);
        } catch (NumberFormatException e){
            throw new IOException("Ikke gyldig tall");
        }
        return stringToDouble;
    }



}
