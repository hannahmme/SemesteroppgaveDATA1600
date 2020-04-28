package datamaskin.users;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    SimpleStringProperty email;
    SimpleStringProperty password;

    public Customer (String email, String password) {
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public String getEmail(){
        return email.get();
    }
    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getPassword(){
        return password.get();
    }
    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }



}
