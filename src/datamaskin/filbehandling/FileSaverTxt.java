package datamaskin.filbehandling;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

//fileSaverTxt implementerer metoden fra interfaces iFileSaver, og fyller method-body her.
public class FileSaverTxt implements iFileSaver{
    @Override
    public void saveToFile(String orderToSave, Path path) throws IOException {
        Files.write(path, orderToSave.getBytes());
    }

//StandardOpenOption.APPEND gjør at det som lagres, legges automatisk til på siste linje
    @Override
    public void appendToFile(String str, Path path) throws IOException {
        Files.write(path, str.getBytes(), StandardOpenOption.APPEND);
    }

}



