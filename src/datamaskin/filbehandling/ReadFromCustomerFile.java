package datamaskin.filbehandling;

import datamaskin.users.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromCustomerFile implements iReadFromCustomerFile{

    @Override
    public ObservableList<Customer> readFromCustomerFile(String path) throws IOException {
        ObservableList<Customer> listOfFinalOrders = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line = reader.readLine();

            while(line != null){
                listOfFinalOrders.add(parseToCustomer(line));
                line = reader.readLine();
            }
        }
        return listOfFinalOrders;
    }


    @Override
    public Customer parseToCustomer(String line) throws IOException {
        String[] split = line.split(";");
        if(split.length != 2){
            throw new IOException("Ikke riktig bruk av delimiter");
        }

        String email    = split[0];
        String password = split[1];

        return new Customer(email, password);
    }
}
