package Datamaskin.filbehandling;

import Datamaskin.product.Product;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class SaveComponentsToFile implements FileSaver {

    public void saveToJobj(List<Product> registry, Path filePath) throws IOException {
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(registry);
        }

    }
}
