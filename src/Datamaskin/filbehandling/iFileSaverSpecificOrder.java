package Datamaskin.filbehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface iFileSaverSpecificOrder {

    static void makeString(Path path, String str) throws IOException {
        Files.write(path, str.getBytes());
    }

    String DELIMITER = ";";




}
