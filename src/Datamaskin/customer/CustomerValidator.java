package Datamaskin.customer;

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
    public static boolean validateAvailability (String email){
        for(Customer aCustomer : CustomerRegister.CustomerRegister){
            if(email.equals(aCustomer.getEmail())){
                return true; // returnerer true hvis emailen finnes fra før
            }
        }
        return false;
    }

}