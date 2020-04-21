package Datamaskin.Filbehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveComponentsToFile implements iFileSaver {
    public void saveToFile(String string, Path path) throws IOException{
        Files.write(path, string.getBytes());
    }
}
