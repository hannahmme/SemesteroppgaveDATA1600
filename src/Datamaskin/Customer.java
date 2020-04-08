package Datamaskin;

import Datamaskin.Exceptions.InvalidEmailException;

public class Customer {

    public static String validateEmail(String email) throws InvalidEmailException {
        if (email == null || email.equals("") || email.isEmpty()) {
            throw new InvalidEmailException("Please input email!");
        }
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) { // RegEX hentet fra https://emailregex.com/ med små modifikasjoner
            return email;
        }
        throw new InvalidEmailException("Please input a valid email!");
    }




}
