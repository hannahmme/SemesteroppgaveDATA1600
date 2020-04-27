package datamaskin.fxml;

import datamaskin.customer.Customer;
import datamaskin.customer.CustomerRegister;
import datamaskin.customer.CustomerValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewUserController {

    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPassword2;
    @FXML private Label lblErrorEmail;
    @FXML private Label lblErrorPassword;

    public static CustomerRegister aCustomerRegister = new CustomerRegister();

    // når knappen trykkes kalles metoden for å lage ny bruker og for at vinduet skal lukkes hvis vellykket
    @FXML void makeNewUser(ActionEvent event) throws Exception {
        if(createCustomerFromGUI()){
            createCustomerFromGUI();
            closeWindow(event);
        }
    }

    // metode som lukker vinduet automatisk når det er laget en ny bruker
    public void closeWindow(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    // metode som lager en ny bruker hvis den møter kravene
    public boolean createCustomerFromGUI() throws Exception {
        try{
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String password2 = txtPassword2.getText();

            // validerer epost og passord og sjekker om de er i riktig format/ lengde
            if(!CustomerValidator.validatePassword(password)){ // false returneres om passordet er for kort
                lblErrorPassword.setText("Passordet må fylle følgende krav: Minst 3 tegn langt");}
            else if(!CustomerValidator.validateEmail(email)){ // false returneres om eposten er ugyldig
                lblErrorEmail.setText("Skriv inn en gyldig epostadresse");}

            // sjekker om eposten finnes fra før og om passordene er like
            else if(CustomerValidator.validateAvailability(email)){ // true returneres om eposten finnes fra før
                lblErrorEmail.setText("Epostadressen er allerede tilknyttet en kunde");}
            else if(!password.equals(password2)){ // false returneres om passordene er ulike
                lblErrorPassword.setText("Passordene du har skrevet inn er ikke like!");}

            // hvis epost og passord er i riktig format, og de andre if-ene ikke slår inn lages en ny kunde
            else if(CustomerValidator.validateEmail(email) && CustomerValidator.validatePassword(password) &&
                    !CustomerValidator.validateAvailability(email)) {
                Customer aCustomer = new Customer(email, password);
                aCustomerRegister.addCustomer(aCustomer);
                return true;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
