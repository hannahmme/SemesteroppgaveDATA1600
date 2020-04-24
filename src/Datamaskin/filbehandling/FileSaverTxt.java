package Datamaskin.filbehandling;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

//fileSaverTxt implementerer metoden fra interfaces iFileSaver, og fyller method-body her.
public class FileSaverTxt implements iFileSaver{
    @Override
    public void saveToFile(String orderToSave, Path path) throws IOException {
        Files.write(path, orderToSave.getBytes());
    }

}
