package Datamaskin.Filbehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//kode hentet fra http://www.javapractices.com/topic/TopicAction.do?Id=245
//vet ikke hvordan jeg skal bruke den haha

public final class SaveToBinaryFile {
    public static void main(String[] args) throws IOException{
        SaveToBinaryFile binary = new SaveToBinaryFile();
        byte[] bytes = binary.readBinaryFile(FILE_NAME);
        binary.writeBinaryFile(bytes, OUTPUT_FILE_NAME);
    }

    final static String FILE_NAME = "personReg1.txt";
    final static String OUTPUT_FILE_NAME = "PersonRegBinary1.txt";

    byte[] readBinaryFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }

    void writeBinaryFile(byte[] bytes, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, bytes);
    }

}
