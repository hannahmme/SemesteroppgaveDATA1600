package datamaskin.exceptions;

import javafx.scene.control.Alert;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;

public class ConvertersWithErrorHandling {


    //Metode som konverterer en input til en Integer. Gir feilmelding til admin
    //dersom inputen ikke kan konverteres til Integer
    public static class IntegerStringConverter extends javafx.util.converter.IntegerStringConverter {
        private boolean conversionSuccessful = true;

        //metode arvet fra klassen IntegerStringConverter.
        @Override
        public Integer fromString(String str) {
            Integer intResult;
            try {
                intResult = super.fromString(str);
                if (intResult > 0 && intResult < 36) {
                    conversionSuccessful = true;
                    return intResult;
                } else {
                    conversionSuccessful = false;
                    throw new IOException("Noe gikk feil å skrive inn i levetid.");
                }
            } catch (NumberFormatException | IOException ioe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ops..");
                alert.setHeaderText("Feil verdier skrevet inn.");
                alert.setContentText("Du må taste inn et gyldig heltall mellom 0 og 36.");
                alert.showAndWait();

                conversionSuccessful = false;
                return 0;
            }
        }

        //metode som returnerer og konverteringen er true eller false
        public boolean getSuccessfulIntValue() {
            return conversionSuccessful;
        }
    }

    //Metode som konverterer en input til en Double. Gir feilmelding til admin
    //dersom inputen ikke kan konverteres til Double
    public static class DoubleFromStringConverter extends DoubleStringConverter {
        private boolean convertSuccessfull = true;

        @Override
        public Double fromString(String str) {
            Double doubleResult;
            try {
                doubleResult = super.fromString(str);
                if (doubleResult > 0 && doubleResult < 100000) {
                    convertSuccessfull = true;
                    return doubleResult;
                } else {
                    convertSuccessfull = false;
                    throw new IOException("Noe gikk galt med å skrive inn i pris.");
                }
            } catch (NumberFormatException | IOException nfe) {
                Alert doubleAlert = new Alert(Alert.AlertType.ERROR);
                doubleAlert.setTitle("Ops..");
                doubleAlert.setHeaderText("Feil verdier skrevet inn.");
                doubleAlert.setContentText("Du må skrive inn et gyldig tall mellom 0 og 100 000.");
                doubleAlert.showAndWait();

                convertSuccessfull = false;
                return 0.0;
            }
        }

        public boolean getSuccessfulDoubleValue() {
            return convertSuccessfull;
        }
    }


    //metode som sjekker om inputverdier er tomme
    public static boolean isInputNotNull(String str) throws NullPointerException {
        try {
            boolean input = str.trim().isEmpty();
            if (!input) {
                return true;
            } else {
                throw new NullPointerException("Ingenting skrevet inn i feltene. Feil!");
            }
        } catch (NullPointerException npe) {
            Alert inputAlert = new Alert(Alert.AlertType.ERROR);
            inputAlert.setTitle("Heisann!");
            inputAlert.setHeaderText("Gikk det litt fort i svingene...");
            inputAlert.setContentText("Feltene kan ikke stå tomme. Skriv inn gyldig innhold.");
            inputAlert.showAndWait();
        }
        return false;
    }

    //metode som sjekker om input i kategori-feltet på adminsiden matcher kategoriene som allerede er definert
    public static boolean isCategoryMatchingInput(String str) {
        try {
            String input = str.toLowerCase();
            if (    input.matches("skjermkort")     ||
                    input.matches("minnekort")      ||
                    input.matches("strømforsyning") ||
                    input.matches("farge")          ||
                    input.matches("harddisk")       ||
                    input.matches("prosessor")      ||
                    input.matches("lydkort")        ||
                    input.matches("optisk disk")    ||
                    input.matches("andre produkter")) {
                return true;
            } else {
                throw new IOException("Ikke gyldig kategori skrevet inn.");
            }
        } catch (IOException ioe) {
            Alert inputAlert = new Alert(Alert.AlertType.ERROR);
            inputAlert.setTitle("Feil!");
            inputAlert.setHeaderText("Ikke riktig kategori");
            inputAlert.setContentText("Skriv inn en kategori som er definert." +
                    " Du finner definerte kategorier under \"Kategori\"-nedtrekkslisten på venstre side.");
            inputAlert.showAndWait();
        }
        return false;
    }
}
