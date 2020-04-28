import datamaskin.users.CustomerValidator;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class testCustomer {

    @Test
    public void testValidEmail() {
        assertTrue(CustomerValidator.validateEmail("henrik.lieng@oslomet.no"));
        assertTrue(CustomerValidator.validateEmail("example@example.com"));
        assertTrue(CustomerValidator.validateEmail("uk@domain.co.uk"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(CustomerValidator.validateEmail(""));
        assertFalse(CustomerValidator.validateEmail("henrik.lieng"));
        assertFalse(CustomerValidator.validateEmail("@oslomet.no"));
        assertFalse(CustomerValidator.validateEmail("henrik.lieng@invalid"));
        assertFalse(CustomerValidator.validateEmail("test@"));
        assertFalse(CustomerValidator.validateEmail(";bot@evil.com"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(CustomerValidator.validatePassword("tre"));
        assertTrue(CustomerValidator.validatePassword("ok3"));
        assertTrue(CustomerValidator.validatePassword("heihei"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(CustomerValidator.validatePassword(""));
        assertFalse(CustomerValidator.validatePassword("/"));
        assertFalse(CustomerValidator.validatePassword(" 1"));
        assertFalse(CustomerValidator.validatePassword("                invalid"));
        assertFalse(CustomerValidator.validatePassword("ii"));
        assertFalse(CustomerValidator.validatePassword(";;"));
    }

}
