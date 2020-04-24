package Datamaskin.filbehandling;

import Datamaskin.product.ProductRegister;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveComponentsToFile implements FileSaver {
    /*public void saveToFile(String string, Path path) throws IOException{
        Files.write(path, string.getBytes());
    }*/
    public void saveToJobj(ProductRegister registry, Path filePath) throws IOException {
        /*Path path = Paths.get("productRegJobj.jobj");*/
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(registry);
        }
    }
}
