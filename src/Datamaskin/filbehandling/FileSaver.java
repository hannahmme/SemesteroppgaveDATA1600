package Datamaskin.filbehandling;

import Datamaskin.product.Product;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Path;


public interface FileSaver {

    void saveToJobj(ObservableList<Product> registry, Path filePath) throws IOException;

}
