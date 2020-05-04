package datamaskin.filbehandling;

import datamaskin.orders.FinalOrderOverview;
import java.io.IOException;
import java.util.List;

public interface iReadFromAllOrdersFile {
    List<FinalOrderOverview> readFromAllOrdersFile(String path) throws IOException;

    FinalOrderOverview parseToFinalOrderOverview(String line) throws IOException;

    double parseToDouble(String str, String errorMessage) throws IOException;
}
