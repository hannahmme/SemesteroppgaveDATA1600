package datamaskin.users;

import datamaskin.exceptions.InvalidEmailException;
import datamaskin.filbehandling.ReadFromCustomerFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CustomerValidator {

    public static boolean validateEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !email.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        if (password.matches("[a-zA-Z0-9._%+!-<>@£$€#¤&*|§]{3,}") && !password.isEmpty()){
            return true;
        }
        return false;
    }

    // metode som sjekker om emailen allerede er i bruk, returnerer false hvis den er ledig
    public static boolean validateAvailability (String email, ObservableList<Customer> aCustomerRegister){
        for(Customer aCustomer : aCustomerRegister){
            if(email.equals(aCustomer.getEmail())){
                return true;
            }
        }
        return false;
    }

    // metode som sjekker om passord og epost matcher, gir true hvis de matcher
    public static boolean validateCredentials(String email, String password, ObservableList<Customer> aCustomerRegister) throws InvalidEmailException {
        for(Customer aCustomer : aCustomerRegister){
            if(email.equals(aCustomer.getEmail()) && password.equals(aCustomer.getPassword())){
                return true;
            }
        }
        return false;
    }

    private static ReadFromCustomerFile readFromCustomerFile = new ReadFromCustomerFile();

    // metode som henter og returnerer en liste med kundene
    public static ObservableList<Customer> getCustomerList() throws IOException {
        try {
            ObservableList<Customer> allCustomersList = readFromCustomerFile.readFromCustomerFile("./src/Datamaskin/users/allCustomers.csv");
            ObservableList<Customer> validCustomersList = FXCollections.observableArrayList();

            for(Customer aCustomer: allCustomersList){
                if (!validateEmail(aCustomer.getEmail()) || !validatePassword(aCustomer.getPassword())){
                    System.err.println("Epost eller passord i filen er i feil format.");
                } else if(checkDuplicate(validCustomersList, aCustomer)) {
                    System.err.println("Denne meldingen skrives ut i CustomerValidator i getCustomerList-metoden");
                    System.err.println("Duplikat: Det finnes to eposter som er identiske i csv-filen");
                } else if(validateEmail(aCustomer.getEmail()) && validatePassword(aCustomer.getPassword())
                        && !checkDuplicate(validCustomersList,aCustomer)) {
                    validCustomersList.add(aCustomer);
                }
            }
            return validCustomersList;
        } catch (IOException e){
            System.err.println("Filsti ikke funnet: " + e.getMessage());
        }
        return null;
    }

    public static boolean checkDuplicate(ObservableList<Customer> validCustomersList, Customer aCustomer){

        for(Customer anotherC : validCustomersList){
            if(aCustomer.getEmail().equals(anotherC.getEmail())) {
                return true;
            }
        }
        return false;
    }

}