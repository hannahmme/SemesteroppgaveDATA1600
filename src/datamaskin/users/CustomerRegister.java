package datamaskin.users;

import datamaskin.exceptions.InvalidEmailException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerRegister {
    public transient static ObservableList<Customer> CustomerRegister = FXCollections.observableArrayList();

    public void addCustomer(Customer aCustomer) {
        CustomerRegister.add(aCustomer);
    }

    // kaller på denne metoden som sjekker email+passord match i array og returnerer email hvis den får en match
    public static String checkCredentials(String email, String password) throws InvalidEmailException {
        for(Customer aCustomer : CustomerRegister){
            if(email.equals(aCustomer.getEmail()) && password.equals(aCustomer.getPassword())){
                return aCustomer.getEmail();
            }
        }
        return null;
    }

    //metode som gjør det mulig å slette en kunde
    public static void deleteCustomer(Customer customerToDelete){
        if(!(customerToDelete == null)){
            CustomerRegister.remove(customerToDelete);
        }
    }
}
