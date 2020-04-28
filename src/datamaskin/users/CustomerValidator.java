package datamaskin.users;

import datamaskin.exceptions.InvalidEmailException;
import javafx.collections.ObservableList;

public class CustomerValidator {

    public static boolean validateEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !email.isEmpty() && email != null && !email.equals("")){
            return true;
        }
        return false;
    }

    // todo: må lages en test-metode for denne også
    public static boolean validatePassword(String password) {
        if (password.matches("[a-zA-Z0-9._%+-]{3,}") && !password.isEmpty() && password != null && !password.equals("")){
            return true;
        }
        return false;
    }

    // her sjekkes det om emailen allerede er i bruk
    public static boolean validateAvailability (String email, ObservableList<Customer> aCustomerRegister){
        for(Customer aCustomer : aCustomerRegister){
            if(email.equals(aCustomer.getEmail())){
                return true; // returnerer true hvis emailen finnes fra før
            }
        }
        return false;
    }

    // sjekker om passord og epost matcher, gir true hvis de matcher
    public static boolean validateCredentials(String email, String password, ObservableList<Customer> aCustomerRegister) throws InvalidEmailException {
        for(Customer aCustomer : aCustomerRegister){
            if(email.equals(aCustomer.getEmail()) && password.equals(aCustomer.getPassword())){
                return true;
            }
        }
        return false;
    }

}