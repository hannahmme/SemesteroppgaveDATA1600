package datamaskin.exceptions;

import javafx.scene.control.Alert;
import javafx.util.converter.IntegerStringConverter;

public class IntegerFromStringConverter extends IntegerStringConverter {
    public static boolean convertSuccessfull = true;

    @Override
    public Integer fromString(String str){
        try{
            Integer result = super.fromString(str);
            convertSuccessfull = true;
            return result;
        } catch(NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops..");
            alert.setHeaderText("Feil verdier skrevet inn.");
            alert.setContentText("Du må taste inn et gyldig heltall.");
            alert.showAndWait();

            convertSuccessfull = false;
            return 0;
        }
    }
}
