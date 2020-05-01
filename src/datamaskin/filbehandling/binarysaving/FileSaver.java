package datamaskin.filbehandling.binarysaving;

import datamaskin.product.ProductRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {
    void saveToJobj(ProductRegister registry, Path filePath) throws IOException;
}
