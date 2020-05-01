package datamaskin.filbehandling.binarysaving;

import datamaskin.filbehandling.binarysaving.*;
import datamaskin.fxml.ProductAdmPageController;
import datamaskin.product.Product;
import datamaskin.product.ProductRegister;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class FileHandler {

    private enum DialogMode {Open, Save}

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

    public static void openFile(Stage stage, ProductRegister register) {
        File selectedFile = getFileFromFileChooser(DialogMode.Open, stage);

        if (selectedFile != null) {
            String fileExt = getFileExt(selectedFile);
            FileOpenerJobj opener = null;

            switch (fileExt) {
                case ".jobj" : opener = new FileOpenerJobj(); break;
                default : Messages.showErrorDialog("Du kan bare åpne jobj filer.");
            }

            if(opener != null) {
                try {
                    opener.open(register, selectedFile.toPath());
                } catch (IOException e) {
                    Messages.showErrorDialog("Åpning av filen feilet. Grunn: " + e.getMessage());
                }
            }
        }
    }

    // metode der man kan velge kun serialiserte filer for import
    private static File getFileFromFileChooser(DialogMode mode, Stage stage) {
        FileChooser fileChooser = new FileChooser();
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

