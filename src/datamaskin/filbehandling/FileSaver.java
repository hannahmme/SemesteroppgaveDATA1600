package datamaskin.filbehandling;

import datamaskin.product.Product;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface FileSaver {

    void saveToJobj(List<Product> registry, Path filePath) throws IOException;

}
