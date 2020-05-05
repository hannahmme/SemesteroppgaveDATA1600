package datamaskin.filbehandling.binarysaving;

import javafx.scene.control.Alert;

public class Messages {

    // metode som viser en suksess-dialog hvis det gikk bra
    public static void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setHeaderText("Ugyldig data!");
        alert.setContentText(message);

        alert.showAndWait();
    }

    // metode som viser feil-dialog om prosessen feilet
    public static void showSuccessDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produktregister");
        alert.setHeaderText("Operasjon vellykket");
        alert.setContentText(message);

        alert.showAndWait();
    }
}