package datamaskin.filbehandling.binarysaving;

import datamaskin.product.ProductRegister;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverJobj implements FileSaver{

    @Override
    public void saveToJobj(ProductRegister registry, Path filePath) throws IOException {
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(registry);
        }
    }

}
