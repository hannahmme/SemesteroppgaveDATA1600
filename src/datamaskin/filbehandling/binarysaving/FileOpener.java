package datamaskin.filbehandling.binarysaving;

import datamaskin.product.ProductRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {

    void open(ProductRegister personRegister, Path filePath) throws IOException;

}
