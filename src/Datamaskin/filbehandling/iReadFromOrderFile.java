package Datamaskin.filbehandling;

import Datamaskin.product.Product;


import java.io.IOException;
import java.util.List;

public interface iReadFromOrderFile {
    public List<Product> readFromOrderFile(String path) throws IOException;

    public Product parseToProduct(String line) throws IOException;

    //Generisk metode som returnerer en type arvet av Number (Skal kunne returnere double og int
    public double parseToDouble(String str, String errorMessage) throws IOException;

    public int parseToInteger(String str, String errorMessage) throws IOException;

}
