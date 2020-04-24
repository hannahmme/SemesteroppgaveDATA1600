package Datamaskin.Filbehandling;

import Datamaskin.Product.Product;
import Datamaskin.Product.ProductRegister;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {

    void saveToJobj(ProductRegister registry, Path filePath) throws IOException;

}
