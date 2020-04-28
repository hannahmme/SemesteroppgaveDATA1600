package datamaskin.users;

import datamaskin.exceptions.InvalidEmailException;
import datamaskin.filbehandling.ReadFromCustomerFile;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CustomerValidator {

    public static boolean validateEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !email.isEmpty() && email != null && !email.equals("")){
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        if (password.matches("[a-zA-Z0-9._%+!-<>@£$€#¤%&*|§]{3,}") && !password.isEmpty() && password != null && !password.equals("")){
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
            return allCustomersList;
        } catch (IOException e){
            System.out.println("Filsti ikke funnet: " + e.getMessage());
        }
        return null;
    }

}