package datamaskin.filbehandling.binarysaving;

import datamaskin.product.ProductRegister;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileHandler {

    private enum DialogMode {Open, Save}

    //metode som skriver til binær fil
    public static void saveFile(Stage stage, ProductRegister register) {
        File selectedFile = getFileFromFileChooser(DialogMode.Save, stage);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileSaverJobj saver = null;

            switch (fileExt) {
                case ".jobj" : saver = new FileSaverJobj(); break;
                default : Messages.showErrorDialog("Du kan bare lagre til jobj filer.");
            }

            if(saver != null) {
                try {
                    saver.saveToJobj(register, selectedFile.toPath());
                    Messages.showSuccessDialog("Registeret ble lagret!");
                } catch (IOException e) {
                    Messages.showErrorDialog("Lagring til fil feilet. Grunn: " + e.getMessage());
                }
            }
        }
    }

    //metode som åpner dialog-vindu for å velge fil
    public static Path getFilePathToJobj(Stage stage){
        File selectedFile = getFileFromFileChooser(DialogMode.Open, stage);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);

            switch (fileExt) {
                case ".jobj" : return selectedFile.toPath();
                default : return null;
            }
        }
        return null;
    }

    // metode der man kan velge kun serialiserte filer for import
    private static File getFileFromFileChooser(DialogMode mode, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/datamaskin/binaryFilesPath"));
        fileChooser.setTitle("Velg fil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serialiserte filer", "*.jobj"));
        if(mode == DialogMode.Open) {
            return fileChooser.showOpenDialog(stage);
        } else {
            return fileChooser.showSaveDialog(stage);
        }
    }

    // metode som finner hvilken type fil det er (txt eller jobj osv)
    private static String getFileExt(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}

