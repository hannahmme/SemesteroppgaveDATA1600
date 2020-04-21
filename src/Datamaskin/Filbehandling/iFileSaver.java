package Datamaskin.Filbehandling;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

//hensikten med interfacet er at denne metoden kan implementeres der den trengs
public interface iFileSaver {
    public void saveToFile(String string, Path path) throws IOException;
}
