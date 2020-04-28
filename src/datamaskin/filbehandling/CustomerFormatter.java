package datamaskin.filbehandling;

import datamaskin.users.Customer;

public class CustomerFormatter {
    private static String DELIMITER = ";";

    //Metode som lager en kunde til en String med en DELIMITER som skiller hver attributt-verdi.
    public static String formatCustomerToString(Customer customer) {
        return customer.getEmail() + DELIMITER + customer.getPassword();
    }
}
