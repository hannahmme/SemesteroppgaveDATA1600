package Datamaskin.filbehandling;

import Datamaskin.orders.FinalOrderOverview;
import java.io.IOException;
import java.util.List;

public interface iReadFromOrderOverviewFile {
    List<FinalOrderOverview> readFromOrderOverviewFile(String path) throws IOException;

    FinalOrderOverview parseOrder(String line) throws IOException;

    //Generisk metode som returnerer en type arvet av Number (Skal kunne returnere double)
    double parseToDouble(String str, String errorMessage) throws IOException;

}
