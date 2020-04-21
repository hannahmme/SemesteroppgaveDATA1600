import Datamaskin.customer.CustomerValidator;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class testEmail {

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

}
