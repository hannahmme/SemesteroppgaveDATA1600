package datamaskin.filbehandling;

import java.io.IOException;
import java.nio.file.Path;

//hensikten med interfacet er at denne metoden kan implementeres der den trengs
public interface iFileSaver {
    void saveToFile(String string, Path path) throws IOException;

    void appendToFile(String str, Path path) throws IOException;
}

