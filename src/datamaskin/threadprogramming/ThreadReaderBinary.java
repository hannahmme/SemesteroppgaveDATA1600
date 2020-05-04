package datamaskin.threadprogramming;

import datamaskin.product.ProductRegister;
import javafx.concurrent.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;

public class ThreadReaderBinary extends Task<ProductRegister> {
    private final Path path;

    public ThreadReaderBinary(Path filepath){
        this.path = filepath;
    }

    @Override
    protected ProductRegister call() throws Exception {
        try{
            Thread.sleep(2500);
            InputStream fin = new FileInputStream(path.toFile());
            ObjectInputStream oin = new ObjectInputStream(fin);
            ProductRegister register = (ProductRegister) oin.readObject();

        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new IOException("Noe gikk galt. Se konsoll");
        }
        return null;
    }
}
