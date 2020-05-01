package datamaskin.filbehandling.binarysaving;

import datamaskin.product.Product;
import datamaskin.product.ProductRegister;
import java.io.*;
import java.nio.file.Path;

import static datamaskin.cart.Cart.Register;

public class FileOpenerJobj implements FileOpener {

    @Override
    public void open(ProductRegister productRegister, Path filePath) throws IOException {

    try (InputStream fin = new FileInputStream(filePath.toFile());
             ObjectInputStream oin = new ObjectInputStream(fin))
        {
            ProductRegister register = (ProductRegister) oin.readObject();
            for(Product p: Register){
                productRegister.addElement(p);
            }
            //register.getRegister().forEach(productRegister::addElement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Noe gikk galt. Se konsoll");
        }
    }
}