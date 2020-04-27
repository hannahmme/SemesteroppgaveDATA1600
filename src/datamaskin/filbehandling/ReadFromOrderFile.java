package datamaskin.filbehandling;

import datamaskin.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromOrderFile implements iReadFromOrderFile{
    @Override
    public ObservableList<Product> readFromOrderFile(String path) throws IOException {
        ObservableList<Product> listOfProducts = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line = reader.readLine();

            while(line != null){
                listOfProducts.add(parseToProduct(line));
                line = reader.readLine();
            }
        }
        return listOfProducts;
    }

    @Override
    public Product parseToProduct(String line) throws IOException {
        String[] split = line.split(";");
        if(split.length != 5){
            throw new IOException("Ikke riktig bruk av delimiter");
        }

        String productName      = split[0];
        String produtInfo       = split[1];
        int producLifetime      = parseToInteger(split[2], "Levetid er ikke et tall.");
        double productPrice     = parseToDouble(split[3], "Prisen er ikke et tall.");
        String productCategory  = split[4];
        return new Product(productName, produtInfo, producLifetime, productPrice, productCategory);
    }

    //Todo: har prøvd å lage disse to metodene under til generiske metoder. Får prøve igjen senere
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

    @Override
    public int parseToInteger(String str, String errorMessage) throws IOException {
        int stringToInt;
        try{
            stringToInt = Integer.parseInt(str);
        } catch (NumberFormatException e){
            throw new IOException("Ikke gyldig tall");
        }
        return stringToInt;
    }


}
