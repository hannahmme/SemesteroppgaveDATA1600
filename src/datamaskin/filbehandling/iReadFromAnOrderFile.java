package datamaskin.filbehandling;

import datamaskin.product.Product;
import java.io.IOException;
import java.util.List;

public interface iReadFromAnOrderFile {
    List<Product> readFromAnOrderFile(String path) throws IOException;

    Product parseToAnOrder(String line) throws IOException;

    int parseToInt(String str, String errorMessage) throws IOException;

    double parseToDouble(String str, String errorMessage) throws IOException;
}
