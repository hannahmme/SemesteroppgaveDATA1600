package Datamaskin.FXML;

import Datamaskin.customer.Customer;
import Datamaskin.customer.CustomerRegister;
import Datamaskin.customer.CustomerValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewUserController {

    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPassword2;
    @FXML private Label lblErrorEmail;
    @FXML private Label lblErrorPassword;

    CustomerRegister aCustomerRegister = new CustomerRegister();

    // når knappen trykkes kalles metoden for å lage ny bruker og for at vinduet skal lukkes hvis vellykket
    @FXML void makeNewUser(ActionEvent event) throws Exception {
        createCustomerFromGUI();
        if(createCustomerFromGUI()){
            closeWindow(event);
        }
    }


    // metode som lukker vinduet automatisk når det er laget en ny bruker
    public void closeWindow(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    // todo: passordvalidering må fikses på muligens
    // metode som lager en ny bruker hvis den møter kravene
    public boolean createCustomerFromGUI() throws Exception {
        try{
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String password2 = txtPassword2.getText();

            if(CustomerValidator.validateEmail(email) && password.equals(password2) && CustomerValidator.validatePassword(password)) {
                Customer aCustomer = new Customer(email, password);

                aCustomerRegister.addCustomer(aCustomer);
                return true;

            } else if(!password.equals(password2)){
                lblErrorPassword.setText("Passordene du har skrevet inn er ikke like!");
            } else if(!CustomerValidator.validatePassword(password)){
                lblErrorPassword.setText("Passordet må fylle følgende krav: Minst 3 tegn langt");
            } else if(CustomerValidator.validateEmail(email)){
                lblErrorEmail.setText("Skriv inn en gyldig epostadresse");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



}
