package Datamaskin.customer;

import Datamaskin.Exceptions.InvalidEmailException;

public class CustomerValidator {

    public static boolean validateEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !email.isEmpty() && email != null && !email.equals("")){
            return true;
        }
        return false;
    }

    // todo: må lages en test-metode for denne også
    public static boolean validatePassword(String password) {
        if (password.matches("[a-zA-Z0-9._%+-a-zA-Z0-9]{3,}") && !password.isEmpty() && password != null && !password.equals("")){
            return true;
        }
        return false;
    }


}
