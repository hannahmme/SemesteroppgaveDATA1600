import Datamaskin.AdminValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testAdmin {

    @Test
    public void testValidLoginAdmin() {
        assertTrue(AdminValidator.validateAdmin("admin", "admin"));
    }

    @Test
    public void testInvalidLoginAdmin() {
        assertFalse(AdminValidator.validateAdmin("", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", ""));
        assertFalse(AdminValidator.validateAdmin(" ", " "));
        assertFalse(AdminValidator.validateAdmin("hei", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", "hei"));
    }
}
