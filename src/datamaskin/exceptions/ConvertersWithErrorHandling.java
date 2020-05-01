package datamaskin.exceptions;

import javafx.scene.control.Alert;
import javafx.util.converter.DoubleStringConverter;

public class ConvertersWithErrorHandling{

    //Metode som konverterer en input til en Integer. Gir feilmelding til admin
    //dersom inputen ikke kan konverteres til Integer
    public static class IntegerStringConverter extends javafx.util.converter.IntegerStringConverter {
        private boolean conversionSuccessful = true;

        @Override public Integer fromString(String str){
            try{
                Integer result = super.fromString(str);
                if(result > 0 && result < 36){
                conversionSuccessful = true;
                return result;
                }
            }
            catch(NumberFormatException nfe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ops..");
                alert.setHeaderText("Feil verdier skrevet inn.");
                alert.setContentText("Du må taste inn et gyldig heltall mellom 0 og 36.");
                alert.showAndWait();

                conversionSuccessful = false;
                return 0;
            }
            catch (Exception ex){
                System.out.println("OTHER exception" + ex.getMessage());
                return 0;
            }
            return 0;
        }
        public boolean getSuccessfulIntValue(){
            return conversionSuccessful;
        }
    }

    //Metode som konverterer en input til en Double. Gir feilmelding til admin
    //dersom inputen ikke kan konverteres til Double
    public static class DoubleFromStringConverter extends DoubleStringConverter {
        private boolean convertSuccessfull = true;

        @Override
        public Double fromString(String str) {
            try {
                Double result = super.fromString(str);
                if (result > 0 && result != 0) {
                    convertSuccessfull = true;
                    return result;
                }
            } catch (NumberFormatException nfe) {
                Alert doubleAlert = new Alert(Alert.AlertType.ERROR);
                doubleAlert.setTitle("Ops..");
                doubleAlert.setHeaderText("Feil verdier skrevet inn.");
                doubleAlert.setContentText("Du må skrive inn et gyldig tall over 0.");
                doubleAlert.showAndWait();

                convertSuccessfull = false;
                return 0.0;
            }
            return 0.0;
        }
        public boolean getSuccessfulDoubleValue(){
            return convertSuccessfull;
        }
    }
}