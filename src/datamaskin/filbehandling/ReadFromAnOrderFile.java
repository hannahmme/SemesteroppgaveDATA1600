package datamaskin.filbehandling;

import datamaskin.product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromAnOrderFile implements iReadFromAnOrderFile{
        //metode som leser fra en spesifikk ordrefil og parser hver linje til et produkt.
        //Produktet legges i en ObservableList<Product> og returneres.
        @Override
        public ObservableList<Product> readFromAnOrderFile(String path) throws IOException {
            ObservableList<Product> listOfSpecificOrders = FXCollections.observableArrayList();

            if (path == null) {
                throw new IOException("Det finnes ingen fil");
            }

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
                String line = reader.readLine();

                while (line != null && parseToAnOrder(line) != null) {
                    listOfSpecificOrders.add(parseToAnOrder(line));
                    line = reader.readLine();
                }

                return listOfSpecificOrders;
            }
        }

        @Override
        public Product parseToAnOrder(String line) throws IOException {
            String[] split = line.split(";");

            if (split.length == 6) {
                String name         = split[0];
                String description  = split[1];
                int lifetime        = parseToInt(split[2], "Pris er ikke et gyldig tall.");
                double price        = parseToDouble(split[3], "Pris er ikke et gyldig tall.");
                String category     = split[4];
                String imageUri     = split[5];

                return new Product(name, description, lifetime, price, category, imageUri);

            } else {
                throw new IOException("Ikke riktig bruk av delimiter. Se følgende linje: " + line );
            }
        }

    @Override
    public int parseToInt(String str, String errorMessage) throws IOException {
        int stringToInt;
        try{
            stringToInt = Integer.parseInt(str);
        } catch(NumberFormatException nfe){
            throw new IOException("Levetid er ikke gyldig tall.");
        }
        return stringToInt;
    }

    @Override
        public double parseToDouble(String str, String errorMessage) throws IOException {
            double stringToDouble;
            try{
                stringToDouble = Double.parseDouble(str);
            } catch(NumberFormatException nfe){
                throw new IOException("Pris er ikke et gyldig tall.");
            }
            return stringToDouble;
        }
}
