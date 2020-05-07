package datamaskin.filbehandling;

import datamaskin.users.Customer;

public class CustomerFormatter {

    //Metode som lager en kunde til en String med en DELIMITER som skiller hver attributt-verdi.
    public static String formatCustomerToString(Customer customer) {
        String DELIMITER = ";";
        return customer.getEmail() + DELIMITER + customer.getPassword();
    }
}
