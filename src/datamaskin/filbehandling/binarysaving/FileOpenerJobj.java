package datamaskin.filbehandling.binarysaving;

import datamaskin.product.ProductRegister;
import java.io.*;
import java.nio.file.Path;


public class FileOpenerJobj implements FileOpener {
    @Override
    public void open(ProductRegister productRegister, Path filePath) throws IOException {
    try {
        InputStream fin = new FileInputStream(filePath.toFile());
        ObjectInputStream oin = new ObjectInputStream(fin);
        ProductRegister register = (ProductRegister) oin.readObject();

        register.removeAll();
        register.getRegister().forEach(productRegister::addElement);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Noe gikk galt. Se konsoll");
        }
    }
}