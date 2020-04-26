package Datamaskin.filbehandling;

import Datamaskin.product.Product;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class SaveComponentsToFile implements FileSaver {

    public void saveToJobj(ObservableList<Product> registry, Path filePath) throws IOException {
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(registry);
        }

    }
}
