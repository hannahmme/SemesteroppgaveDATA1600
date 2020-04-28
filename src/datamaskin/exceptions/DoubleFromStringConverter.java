package datamaskin.exceptions;

import javafx.scene.control.Alert;
import javafx.util.converter.DoubleStringConverter;

public class DoubleFromStringConverter extends DoubleStringConverter {
    public static boolean convertSuccessfull = true;

    @Override
    public Double fromString(String str){
        try{
            Double result = super.fromString(str);
            convertSuccessfull = true;
            return result;
        } catch (NumberFormatException nfe){
            Alert doubleAlert = new Alert(Alert.AlertType.ERROR);
            doubleAlert.setTitle("Ops..");
            doubleAlert.setHeaderText("Feil verdier skrevet inn.");
            doubleAlert.setContentText("Du må skrive inn et gyldig tall.");
            doubleAlert.showAndWait();

            convertSuccessfull = false;
            return 0.0;
        }
    }
}
