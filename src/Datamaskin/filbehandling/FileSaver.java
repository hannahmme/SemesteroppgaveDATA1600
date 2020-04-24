package Datamaskin.filbehandling;

import Datamaskin.product.ProductRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {

    void saveToJobj(ProductRegister registry, Path filePath) throws IOException;

}
